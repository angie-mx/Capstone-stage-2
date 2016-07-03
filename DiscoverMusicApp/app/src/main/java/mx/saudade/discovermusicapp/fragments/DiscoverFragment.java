package mx.saudade.discovermusicapp.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import org.apache.commons.lang3.StringUtils;

import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.activities.PlaylistActivity;
import mx.saudade.discovermusicapp.controllers.MoodController;
import mx.saudade.discovermusicapp.services.MoodPlaylistService;
import mx.saudade.discovermusicapp.utils.ActivityUtils;

/**
 * Created by angie on 7/1/16.
 */
public class DiscoverFragment extends Fragment {

    private MoodController moodController;

    private Button searchButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.discover_fragment, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        moodController = new MoodController(getView());
        searchButton = getButton(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMoods();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(
                mMessageReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                mMessageReceiver, new IntentFilter(MoodPlaylistService.WEB_SERVICE_EVENT));
    }

    private void searchMoods() {
        Intent intent = createSearchIntent();
        getActivity().startService(intent);
    }

    private Intent createSearchIntent() {
        boolean years[] = moodController.getYears();

        Intent i = new Intent(getActivity(), MoodPlaylistService.class);
        i.putExtra(MoodPlaylistService.TRACK_VALENCE_EXTRA
                , String.valueOf(getSeekBar(R.id.valence_seekbar).getProgress()));
        i.putExtra(MoodPlaylistService.TRACK_AROUSAL_EXTRA
                , String.valueOf(getSeekBar(R.id.arousal_seekbar).getProgress()));
        i.putExtra(MoodPlaylistService.YEAR_MAX_EXTRA, StringUtils.EMPTY);
        i.putExtra(MoodPlaylistService.YEAR_MIN_EXTRA, StringUtils.EMPTY);
        i.putExtra(MoodPlaylistService.BEFORE_1950_EXTRA, years[0]);
        i.putExtra(MoodPlaylistService.DATE_50_EXTRA, years[1]);
        i.putExtra(MoodPlaylistService.DATE_60_EXTRA, years[2]);
        i.putExtra(MoodPlaylistService.DATE_70_EXTRA, years[3]);
        i.putExtra(MoodPlaylistService.DATE_80_EXTRA, years[4]);
        i.putExtra(MoodPlaylistService.DATE_90_EXTRA, years[5]);
        i.putExtra(MoodPlaylistService.DATE_00_EXTRA, years[6]);
        i.putExtra(MoodPlaylistService.DATE_10_EXTRA, years[7]);
        i.putExtra(MoodPlaylistService.GENRE_NO_EXTRA, moodController.getMoods());

        return i;
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Parcelable parcelable = intent.getParcelableExtra(MoodPlaylistService.WEB_SERVICE_EXTRA);
            ActivityUtils.loadActivity((AppCompatActivity) getActivity()
                    , PlaylistActivity.class, PlaylistActivity.TRACKS_EXTRA_KEY, parcelable);
        }
    };

    private Button getButton(int id) {
        return (Button) getView().findViewById(id);
    }

    private SeekBar getSeekBar(int id) {
        return (SeekBar) getView().findViewById(id);
    }

}
