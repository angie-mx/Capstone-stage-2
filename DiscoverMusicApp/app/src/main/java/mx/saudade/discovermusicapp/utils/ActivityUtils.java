package mx.saudade.discovermusicapp.utils;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import mx.saudade.discovermusicapp.R;

/**
 * Created by angie on 7/3/16.
 */
public final class ActivityUtils {

    public static void loadActivity(AppCompatActivity activity, Class clz) {
        Intent i = new Intent();
        i.setClass(activity, clz);
        activity.startActivity(i);
    }

    public static void loadActivity(AppCompatActivity activity, Class clz, String extra_key
            , Parcelable extra) {
        Intent intent = new Intent();
        intent.setClass(activity, clz);
        intent.putExtra(extra_key, extra);
        activity.startActivity(intent);
    }

    public static void loadFragment(AppCompatActivity activity, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_fragment_container, fragment)
                .commit();
    }

}
