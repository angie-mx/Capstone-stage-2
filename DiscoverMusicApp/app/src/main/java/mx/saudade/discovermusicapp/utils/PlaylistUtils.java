package mx.saudade.discovermusicapp.utils;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.List;

import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.adapters.TrackAdapter;
import mx.saudade.discovermusicapp.responses.Track;

/**
 * Created by angie on 7/4/16.
 */
public final class PlaylistUtils {

    private static final int ADAPTER_COLUMNS = 2;

    private PlaylistUtils() { }

    public static void createRecyclerView(Activity activity, View view, List<Track> tracks) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.playlist_recyclerview);
        TrackAdapter adapter = new TrackAdapter(activity, tracks);
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
        StaggeredGridLayoutManager sglm =
                new StaggeredGridLayoutManager(ADAPTER_COLUMNS, StaggeredGridLayoutManager.VERTICAL);
        sglm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(sglm);
    }
}
