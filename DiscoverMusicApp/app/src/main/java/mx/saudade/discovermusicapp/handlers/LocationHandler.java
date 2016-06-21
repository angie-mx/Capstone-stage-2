package mx.saudade.discovermusicapp.handlers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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

    public LocationHandler(Context context) {
        this.context = context;
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //FIXME change this implementation
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context
                , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
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
