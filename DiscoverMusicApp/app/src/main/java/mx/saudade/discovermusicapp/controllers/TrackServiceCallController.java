package mx.saudade.discovermusicapp.controllers;

import android.app.Activity;
import android.content.Intent;

import mx.saudade.discovermusicapp.responses.Track;
import mx.saudade.discovermusicapp.services.ArtistPlaylistService;
import mx.saudade.discovermusicapp.services.LyricsService;
import mx.saudade.discovermusicapp.services.TrackPlaylistService;

/**
 * Created by angie on 7/3/16.
 */
public class TrackServiceCallController {

    public static void searchLyric(Activity activity, Track track) {
        Intent intent = createSearchLyricsIntent(activity, track);
        activity.startService(intent);
    }

    private static Intent createSearchLyricsIntent(Activity activity, Track track) {
        Intent intent = new Intent(activity, LyricsService.class);
        intent.putExtra(LyricsService.ARTIST_EXTRA, track.getArtist().getName());
        intent.putExtra(LyricsService.SONG_EXTRA, track.getTitle());
        return intent;
    }

    public static void searchByTrack(Activity activity, Track track) {
        Intent intent = createSearchPlaylistByTrack(activity, track);
        activity.startService(intent);
    }

    private static Intent createSearchPlaylistByTrack(Activity activity, Track track) {
        Intent intent = new Intent(activity, TrackPlaylistService.class);
        intent.putExtra(TrackPlaylistService.ID_EXTRA, track.getId());
        return intent;
    }

    public static void searchByArtist(Activity activity, Track track) {
        Intent intent = createSearchPlaylistByArtist(activity, track);
        activity.startService(intent);
    }

    private static Intent createSearchPlaylistByArtist(Activity activity, Track track) {
        Intent intent = new Intent(activity, ArtistPlaylistService.class);
        intent.putExtra(ArtistPlaylistService.ARTIST_ID_EXTRA, track.getArtist().getMbid());
        return intent;
    }

}
