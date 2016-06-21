package mx.saudade.discovermusicapp.handlers;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;

/**
 * Created by angie on 6/20/16.
 */
public class LocationHandler implements GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = LocationHandler.class.getSimpleName();

    public static final String COUNTRY_CODE_NOT_AVAILABLE = "N/A";

    private Context context;

    private GoogleApiClient googleApiClient;

    private Location location;

    PermissionHandler permissionHandler;

    public LocationHandler(Activity context) {
        this.context = context;
        permissionHandler = new PermissionHandler(context
                , PermissionHandler.Permission.LOCATION);
    }

    public void onCreate() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    public void onStart() {
        googleApiClient.connect();
    }

    public void onStop() {
        googleApiClient.disconnect();
    }

    public void onRequestPermissionsResult(int requestCode, int[] grantResults) {
        if (permissionHandler.checkRequestPermissionResult(requestCode, grantResults)) {
            Log.v(TAG, "onRequestPermissionsResult permission granted");
            googleApiClient.reconnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (!permissionHandler.checkPermission()) {
            return;
        }
        location = LocationServices.FusedLocationApi.getLastLocation(
                googleApiClient);
        Log.i(TAG, "location " + location.toString());
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(TAG, "onConnectionSuspended " + i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed " + connectionResult.getErrorMessage());
    }

    public Location getLocation() {
        return location;
    }

    public String getCountryCode() {
        if (location == null) {
            return COUNTRY_CODE_NOT_AVAILABLE;
        }

        try {
            Geocoder getCoder = new Geocoder(context);
            List<Address> address = getCoder.getFromLocation(location.getLatitude()
                    , location.getLongitude(), 1);

            return address.get(0).getCountryCode();
        }catch (IOException exception) {
            Log.e(TAG, "Error getting country code " + exception.getMessage());
            return COUNTRY_CODE_NOT_AVAILABLE;
        }
    }

}
