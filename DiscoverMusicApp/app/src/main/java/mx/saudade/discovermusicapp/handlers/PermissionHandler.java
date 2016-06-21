package mx.saudade.discovermusicapp.handlers;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import mx.saudade.discovermusicapp.R;

/**
 * @author Angelica Mendez, on 6/21/16.
 */
public class PermissionHandler<T extends Activity> {

    private final Permission permission;
    private T activity;
    private boolean finishActivity;

    public PermissionHandler(T activity, Permission permission) {
        this.activity = activity;
        this.permission = permission;
    }

    public boolean checkPermission() {
        int permissionState = ContextCompat.checkSelfPermission(activity
                , permission.getPermissionName());

        if (permissionState == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        //If we we don't have the permission.

        // Display an explanation dialog, in case the user need more details
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity
                , permission.getPermissionName())) {
            showRequestPermissionRationaleDialog();
            // No explanation needed, we can request the permissionName.
        } else {
            showRequestPermissionDialog();
        }
        return false;
    }

    public boolean checkRequestPermissionResult(int requestCode, int[] grantResults) {
        if (requestCode == permission.getRequestPermissionCode()) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted
                return true;
            } else {
                // permission denied
                checkPermission();
            }
        }
        return false;
    }

    private void showRequestPermissionDialog() {
        ActivityCompat.requestPermissions(activity,
                new String[]{permission.getPermissionName()}
                , permission.getRequestPermissionCode());
    }

    private void showRequestPermissionRationaleDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity)
                .setTitle(permission.getDialogTitleId())
                .setMessage(permission.getDialogMessageId())
                .setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showRequestPermissionDialog();
                    }
                })
                .setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (finishActivity) {
                            activity.finish();
                        }
                    }
                });
        alertDialog.show();
    }

    public void setFinishActivity(boolean finishActivity) {
        this.finishActivity = finishActivity;
    }

    public enum Permission {

        LOCATION(Manifest.permission.ACCESS_FINE_LOCATION
                , 101
                , R.string.location_permission_title
                , R.string.location_permission_message);

        private final String permissionName;
        private final int requestPermissionCode;
        private final int dialogTitleId;
        private final int dialogMessageId;

        Permission(String permissionName, int requestPermissionCode, int dialogTitleId
                , int dialogMessageId) {
            this.permissionName = permissionName;
            this.requestPermissionCode = requestPermissionCode;
            this.dialogTitleId = dialogTitleId;
            this.dialogMessageId = dialogMessageId;
        }

        public String getPermissionName() {
            return this.permissionName;
        }

        public int getRequestPermissionCode() {
            return this.requestPermissionCode;
        }

        public int getDialogTitleId() {
            return this.dialogTitleId;
        }

        public int getDialogMessageId() {
            return this.dialogMessageId;
        }
    }

}
