package mx.saudade.discovermusicapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.controllers.MoodController;

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

    private void searchMoods() {

    }

    private Button getButton(int id) {
        return (Button) getView().findViewById(id);
    }

}
