package mx.saudade.discovermusicapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import mx.saudade.discovermusicapp.fragments.DiscoverFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String PLAYLIST_FRAGMENT_ID = "PLAYLIST_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new DiscoverFragment());
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_fragment_container, fragment)
                .commit();
    }

    public void loadFragment(Fragment fragment, String id) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_fragment_container, fragment)
                .addToBackStack(id)
                .commit();
    }

}
