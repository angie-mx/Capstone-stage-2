package mx.saudade.discovermusicapp.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import mx.saudade.discovermusicapp.AnalyticsApplication;
import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.fragments.DiscoverFragment;
import mx.saudade.discovermusicapp.utils.NavigationUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        NavigationUtils.loadFragment(this, new DiscoverFragment());
        ((AnalyticsApplication) getApplication()).trackScreen(getString(R.string.discover));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.discover_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            NavigationUtils.loadActivity(this, SettingsActivity.class);
            return true;
        } else if (id == R.id.action_about) {
            NavigationUtils.loadActivity(this, AboutActivity.class);
        } else if (id == R.id.action_favorites) {
            NavigationUtils.loadActivity(this, FavoritesActivity.class);
        }

        return super.onOptionsItemSelected(item);
    }
}
