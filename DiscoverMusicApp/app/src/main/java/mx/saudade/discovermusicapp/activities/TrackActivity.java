package mx.saudade.discovermusicapp.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.fragments.TrackFragment;
import mx.saudade.discovermusicapp.utils.NavigationUtils;

/**
 * Created by angie on 7/3/16.
 */
public class TrackActivity extends AppCompatActivity {

    private static final String TAG = TrackActivity.class.getSimpleName();

    public static final String TRACK_EXTRA_KEY = TAG + "_track_extra_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        Fragment fragment = createTrackFragment(getExtra());
        NavigationUtils.loadFragment(this, fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.track_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_favorites) {

            return true;
        } else if (id == R.id.action_share) {

        }

        return super.onOptionsItemSelected(item);
    }



    private Parcelable getExtra() {
        if (this.getIntent() == null) {
            return null;
        }
        return this.getIntent().getParcelableExtra(TRACK_EXTRA_KEY);
    }

    private Fragment createTrackFragment(Parcelable parcelable) {
        Fragment trackFragment = new TrackFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(TrackFragment.TRACK_KEY, parcelable);
        trackFragment.setArguments(bundle);
        return trackFragment;
    }

}
