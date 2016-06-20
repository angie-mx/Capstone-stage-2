package mx.saudade.discovermusicapp.responses;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by angie on 6/19/16.
 */
public class Root {

    private Request request;

    private Response response;

    private Tracks tracks;

    private String played;

    private List<String> favonot;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public String getPlayed() {
        return played;
    }

    public void setPlayed(String played) {
        this.played = played;
    }

    public List<String> getFavonot() {
        return favonot;
    }

    public void setFavonot(List<String> favonot) {
        this.favonot = favonot;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("request", request)
                .append("response", response)
                .append("tracks", tracks)
                .append("request", request)
                .append("played", played)
                .append("favonot", favonot)
                .toString();
    }
}
