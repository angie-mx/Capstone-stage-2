package mx.saudade.discovermusicapp.responses;

import java.util.List;

/**
 * Created by angie on 6/19/16.
 */

public class Root {

    private Request request;
    private Response response;
    private List<Track> tracks;
    private String played;
    private String favonot;

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

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getPlayed() {
        return played;
    }

    public void setPlayed(String played) {
        this.played = played;
    }

    public String getFavonot() {
        return favonot;
    }

    public void setFavonot(String favonot) {
        this.favonot = favonot;
    }
}
