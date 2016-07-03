package mx.saudade.discovermusicapp.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.responses.Track;
import mx.saudade.discovermusicapp.utils.AdapterUtils;

/**
 * Created by angie on 6/28/16.
 */
public class TrackAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Activity activity;

    private List<Track> tracks;

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
                Toast.makeText(activity, "click " + tracks.get(holder.getAdapterPosition())
                        , Toast.LENGTH_SHORT).show();
            }
        });
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "play " + tracks.get(holder.getAdapterPosition()).getTitle()
                        , Toast.LENGTH_SHORT).show();
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
    public ImageButton play;

    public ViewHolder(View itemView) {
        super(itemView);
        layout = (RelativeLayout) itemView.findViewById(R.id.item_layout);
        title = (TextView) itemView.findViewById(R.id.item_title);
        artist = (TextView) itemView.findViewById(R.id.item_artist);
        play = (ImageButton) itemView.findViewById(R.id.item_play);
    }

}