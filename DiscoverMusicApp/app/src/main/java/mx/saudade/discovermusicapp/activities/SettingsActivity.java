package mx.saudade.discovermusicapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.fragments.SettingsFragment;
import mx.saudade.discovermusicapp.utils.ActivityUtils;

/**
 * Created by angie on 7/3/16.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityUtils.loadFragment(this, new SettingsFragment());
    }
}
