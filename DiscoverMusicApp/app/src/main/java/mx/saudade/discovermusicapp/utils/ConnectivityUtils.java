package mx.saudade.discovermusicapp.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import mx.saudade.discovermusicapp.R;

/**
 * Created by angie on 7/4/16.
 */
public final class ConnectivityUtils {

    private ConnectivityUtils() { }

    private static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static boolean checkConnectivity(Context context) {
        if (isOnline(context)) {
            return true;
        }
        showDialog(context);
        return false;
    }

    private static void showDialog(Context context) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.connectivity_error)
                .setMessage(R.string.check_connectivity)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        dialog.show();
    }

}
