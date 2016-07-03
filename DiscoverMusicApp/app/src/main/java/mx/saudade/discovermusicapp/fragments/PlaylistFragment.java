package mx.saudade.discovermusicapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.adapters.TrackAdapter;
import mx.saudade.discovermusicapp.responses.Root;
import mx.saudade.discovermusicapp.responses.Track;

/**
 * Created by angie on 6/28/16.
 */
public class PlaylistFragment extends Fragment {

    private static final String TAG = PlaylistFragment.class.getSimpleName();

    public static final String TRACKS_KEY = TAG + "_tracks_key";

    private static final int ADAPTER_COLUMNS = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.playlist_fragment, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createRecyclerView();
    }

    private void createRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.playlist_recyclerview);
        TrackAdapter adapter = new TrackAdapter(getActivity(), getTracks());
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
        StaggeredGridLayoutManager sglm =
                new StaggeredGridLayoutManager(ADAPTER_COLUMNS, StaggeredGridLayoutManager.VERTICAL);
        sglm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(sglm);
    }

    private List<Track> getTracks() {
        if (this.getArguments() == null) {
            return new ArrayList<Track>();
        }
        Root root = this.getArguments().getParcelable(TRACKS_KEY);
        return root.getTracks();
    }

}
