package mx.saudade.discovermusicapp.adapters;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.activities.TrackActivity;
import mx.saudade.discovermusicapp.responses.Track;
import mx.saudade.discovermusicapp.utils.AdapterUtils;
import mx.saudade.discovermusicapp.utils.NavigationUtils;
import mx.saudade.discovermusicapp.utils.ConnectivityUtils;

/**
 * Created by angie on 6/28/16.
 */
public class TrackAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Activity activity;

    private List<Track> tracks;

    public static int INVALID_INDEX = -1;

    protected int selectedIndex = INVALID_INDEX;

    public TrackAdapter(Activity activity, List<Track> tracks) {
        this.activity = activity;
        this.tracks = tracks;
    }

    @Override
    public mx.saudade.discovermusicapp.adapters.ViewHolder onCreateViewHolder(ViewGroup parent
            , int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.track_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ConnectivityUtils.checkConnectivity(activity)) {
                   return;
                }
                Track track = tracks.get(holder.getAdapterPosition());
                selectedIndex = holder.getAdapterPosition();
                notifyDataSetChanged();
                NavigationUtils.loadActivity((AppCompatActivity) activity, TrackActivity.class
                        , TrackActivity.TRACK_EXTRA_KEY, track);
            }
        });

        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(mx.saudade.discovermusicapp.adapters.ViewHolder holder
            , int position) {
        Track track = tracks.get(position);

        holder.layout.setBackgroundColor(AdapterUtils.getColorFromGenre(activity, track.getGenre()));

        holder.title.setText(track.getTitle());

        if(track.getArtist() != null) {
            holder.artist.setText(track.getArtist().getName());
        } else {
            holder.artist.setText(StringUtils.EMPTY);
        }
        updateSelectedItem(holder, position);

    }

    private void updateSelectedItem(ViewHolder holder, int position) {
        if (position == selectedIndex) {
            holder.title.setTextColor(ContextCompat.getColor(activity, R.color.white));
            holder.artist.setTextColor(ContextCompat.getColor(activity, R.color.white));
        } else {
            holder.title.setTextColor(ContextCompat.getColor(activity, R.color.black));
            holder.artist.setTextColor(ContextCompat.getColor(activity, R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

}

class ViewHolder extends RecyclerView.ViewHolder {
    public RelativeLayout layout;
    public TextView title;
    public TextView artist;

    public ViewHolder(View itemView) {
        super(itemView);
        layout = (RelativeLayout) itemView.findViewById(R.id.item_layout);
        title = (TextView) itemView.findViewById(R.id.item_title);
        artist = (TextView) itemView.findViewById(R.id.item_artist);
    }

}