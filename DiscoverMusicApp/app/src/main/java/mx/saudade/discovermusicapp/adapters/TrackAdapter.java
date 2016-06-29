package mx.saudade.discovermusicapp.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.responses.Track;

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
        return holder;
    }

    @Override
    public void onBindViewHolder(mx.saudade.discovermusicapp.adapters.ViewHolder holder
            , int position) {
        Track track = tracks.get(position);
        holder.title.setText(track.getTitle());

        if(track.getArtist() != null) {
            holder.artist.setText(track.getArtist().getName());
            Picasso.with(activity).load(track.getArtist().getImgUrl())
                    .placeholder(R.drawable.no_cover).into(holder.cover);
        } else {
            holder.artist.setText(StringUtils.EMPTY);
            holder.cover.setImageResource(R.drawable.no_cover);
        }
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

}

class ViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView artist;
    public ImageView cover;
    public ImageButton play;

    public ViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.item_title);
        artist = (TextView) itemView.findViewById(R.id.item_artist);
        cover = (ImageView) itemView.findViewById(R.id.item_cover);
        play = (ImageButton) itemView.findViewById(R.id.item_play);
    }

}