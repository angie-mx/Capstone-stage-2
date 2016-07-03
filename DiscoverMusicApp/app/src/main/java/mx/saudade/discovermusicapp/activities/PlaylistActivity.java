package mx.saudade.discovermusicapp.activities;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.fragments.PlaylistFragment;
import mx.saudade.discovermusicapp.utils.ActivityUtils;

/**
 * Created by angie on 7/3/16.
 */
public class PlaylistActivity extends AppCompatActivity {

    private static final String TAG = PlaylistActivity.class.getSimpleName();

    public static final String TRACKS_EXTRA_KEY = TAG + "_tracks_extra_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = createPlaylistFragment(getExtra());
        ActivityUtils.loadFragment(this, fragment);
    }

    private Parcelable getExtra() {
        if (this.getIntent() == null) {
            return null;
        }
        return this.getIntent().getParcelableExtra(TRACKS_EXTRA_KEY);
    }

    private Fragment createPlaylistFragment(Parcelable parcelable) {
        Fragment playlistFragment = new PlaylistFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PlaylistFragment.TRACKS_KEY, parcelable);
        playlistFragment.setArguments(bundle);
        return playlistFragment;
    }
}
