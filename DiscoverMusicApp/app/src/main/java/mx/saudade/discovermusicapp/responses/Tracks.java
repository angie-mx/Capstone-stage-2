package mx.saudade.discovermusicapp.responses;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by angie on 6/19/16.
 */

public class Tracks {

    private List<Track> track;

    public List<Track> getTrack() {
        return track;
    }

    public void setTrack(List<Track> track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("track", track)
                .toString();
    }
}
