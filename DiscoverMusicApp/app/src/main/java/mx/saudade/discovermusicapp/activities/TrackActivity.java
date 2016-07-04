package mx.saudade.discovermusicapp.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.data.AppCursorHelper;
import mx.saudade.discovermusicapp.fragments.TrackFragment;
import mx.saudade.discovermusicapp.responses.Track;
import mx.saudade.discovermusicapp.utils.NavigationUtils;

/**
 * Created by angie on 7/3/16.
 */
public class TrackActivity extends AppCompatActivity {

    private static final String TAG = TrackActivity.class.getSimpleName();

    public static final String TRACK_EXTRA_KEY = TAG + "_track_extra_key";

    private AppCursorHelper cursorHelper;

    private Track track;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        cursorHelper = new AppCursorHelper(getApplicationContext());
        track = (Track) getExtra();
        Fragment fragment = createTrackFragment(getExtra());
        NavigationUtils.loadFragment(this, fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.track_menu, menu);
        setShareAction(menu);
        setFavoriteAction(menu);
        return true;
    }

    private void setShareAction(Menu menu) {
        final ShareActionProvider provider = (ShareActionProvider) MenuItemCompat
                .getActionProvider(menu.findItem(R.id.action_share));
        NavigationUtils.share(provider, track.getShareMessage());
        provider.setOnShareTargetSelectedListener(new ShareActionProvider.OnShareTargetSelectedListener() {
            @Override
            public boolean onShareTargetSelected(ShareActionProvider shareActionProvider, Intent intent) {
                NavigationUtils.share(shareActionProvider, track.getShareMessage());
                return false;
            }
        });
    }

    private void setFavoriteAction(Menu menu) {
        MenuItem favoriteItem = menu.findItem(R.id.action_add_favorites);
        if(cursorHelper.getTrack(track.getId()) == null) {
            favoriteItem.setIcon(R.drawable.ic_favorite_off);
        } else {
            favoriteItem.setIcon(R.drawable.ic_favorite_on);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_favorites) {
            changeStateAsFavorite(item);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeStateAsFavorite(MenuItem item) {
        int message;
        if (cursorHelper.getTrack(track.getId()) == null) {
            cursorHelper.insertTrack(track);
            item.setIcon(R.drawable.ic_favorite_on);
            message = R.string.track_saved;
        } else {
            cursorHelper.deleteTrack(track.getId());
            item.setIcon(R.drawable.ic_favorite_off);
            message = R.string.track_removed;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
