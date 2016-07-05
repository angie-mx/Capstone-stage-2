package mx.saudade.discovermusicapp.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.apache.commons.lang3.StringUtils;

import mx.saudade.discovermusicapp.AnalyticsApplication;
import mx.saudade.discovermusicapp.BuildConfig;
import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.activities.PlaylistActivity;
import mx.saudade.discovermusicapp.controllers.TrackServiceCallController;
import mx.saudade.discovermusicapp.responses.LyricsResult;
import mx.saudade.discovermusicapp.responses.Track;
import mx.saudade.discovermusicapp.services.ArtistPlaylistService;
import mx.saudade.discovermusicapp.services.LyricsService;
import mx.saudade.discovermusicapp.services.TrackPlaylistService;
import mx.saudade.discovermusicapp.services.YoutubeService;
import mx.saudade.discovermusicapp.utils.NavigationUtils;
import mx.saudade.discovermusicapp.utils.ConnectivityUtils;

/**
 * Created by angie on 7/3/16.
 */
public class TrackFragment extends Fragment implements YouTubePlayer.OnInitializedListener {

    private static final String TAG = TrackFragment.class.getSimpleName();

    public static final String TRACK_KEY = TAG + "_track_key";

    private Track track;

    private YouTubePlayerSupportFragment mYoutubePlayerFragment;

    private String youtubeId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        track = getTrack();
        return inflater.inflate(R.layout.track_fragment, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TrackServiceCallController.searchLyric(getActivity(), track);
        TrackServiceCallController.startYoutubeService(getActivity(), track);
        initViews();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(
                lyricsMessageReceiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(
                trackPlaylistMessageReceiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(
                artistPlaylistMessageReceiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(
                                youtubeVideoMessageReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                lyricsMessageReceiver, new IntentFilter(LyricsService.WEB_SERVICE_EVENT));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                trackPlaylistMessageReceiver, new IntentFilter(TrackPlaylistService.WEB_SERVICE_EVENT));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                artistPlaylistMessageReceiver, new IntentFilter(ArtistPlaylistService.WEB_SERVICE_EVENT));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                                youtubeVideoMessageReceiver, new IntentFilter(YoutubeService.WEB_SERVICE_EVENT));
    }

    private void initViews() {
        getTextView(R.id.track_title).setText(track.getTitle());
        getTextView(R.id.track_artist).setText(track.getArtist().getName());
        getTextView(R.id.track_release).setText(track.getReleaseDate());

        getButton(R.id.track_by_track).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ConnectivityUtils.checkConnectivity(getActivity())) {
                    return;
                }
                TrackServiceCallController.searchByTrack(getActivity(), track);
                ((AnalyticsApplication) getActivity().getApplication()).trackEvent(getString(R.string.track),
                        getString(R.string.playlist_by_track), track.getId() + " " + track.getTitle());
            }
        });

        getButton(R.id.track_by_artist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ConnectivityUtils.checkConnectivity(getActivity())) {
                    return;
                }
                TrackServiceCallController.searchByArtist(getActivity(), track);
                ((AnalyticsApplication) getActivity().getApplication()).trackEvent(
                        getString(R.string.track), getString(R.string.playlist_by_track)
                        , track.getArtist().getMbid() + " " + track.getArtist().getName());
            }
        });
    }

    private Track getTrack() {
        if (this.getArguments() == null) {
            return new Track();
        }
        return this.getArguments().getParcelable(TRACK_KEY);
    }

    private BroadcastReceiver lyricsMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LyricsResult parcelable = intent.getParcelableExtra(LyricsService.WEB_SERVICE_EXTRA);
            if (parcelable == null || StringUtils.isEmpty(parcelable.getLyric())) {
                getTextView(R.id.track_lyrics).setText(R.string.no_lyrics);
            } else {
                getTextView(R.id.track_lyrics).setText(parcelable.getLyric());
            }
        }
    };

    private BroadcastReceiver trackPlaylistMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Parcelable parcelable = intent.getParcelableExtra(TrackPlaylistService.WEB_SERVICE_EXTRA);
            NavigationUtils.loadActivity((AppCompatActivity) getActivity()
                    , PlaylistActivity.class, PlaylistActivity.TRACKS_EXTRA_KEY, parcelable);
        }
    };

    private BroadcastReceiver artistPlaylistMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Parcelable parcelable = intent.getParcelableExtra(ArtistPlaylistService.WEB_SERVICE_EXTRA);
            NavigationUtils.loadActivity((AppCompatActivity) getActivity()
                    , PlaylistActivity.class, PlaylistActivity.TRACKS_EXTRA_KEY, parcelable);
        }
    };

    private BroadcastReceiver youtubeVideoMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                youtubeId = intent.getStringExtra(YoutubeService.WEB_SERVICE_EXTRA);
                initYoutubeFragment();
            }
    };

    private void initYoutubeFragment() {
        mYoutubePlayerFragment = new YouTubePlayerSupportFragment();
        mYoutubePlayerFragment.initialize(BuildConfig.GOOGLE_API_KEY, this);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_youtube_player, mYoutubePlayerFragment);
        fragmentTransaction.commit();
    }

    private TextView getTextView(int id) {
        return (TextView) getView().findViewById(id);
    }

    private Button getButton(int id) {
        return (Button) getView().findViewById(id);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer
            , boolean b) {
        if(!b){
            youTubePlayer.cueVideo(youtubeId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider
            , YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this.getActivity(), 1).show();
        } else {
            Toast.makeText(this.getActivity(), "YouTubePlayer.onInitializationFailure(): "
                    + youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
