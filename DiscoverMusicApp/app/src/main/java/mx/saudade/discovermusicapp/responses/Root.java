package mx.saudade.discovermusicapp.responses;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by angie on 6/19/16.
 */
@org.simpleframework.xml.Root(name = "root")
public class Root implements Parcelable {

    @Element(name = "request")
    private Request request;

    @Element(name = "response")
    private Response response;

    @ElementList(name = "tracks")
    private List<Track> tracks;

    @Element(name = "played")
    private String played;

    @ElementList(name = "favonot", required = false)
    private List<String> favonot;

    public Root() { }

    protected Root(Parcel in) {
        request = in.readParcelable(Request.class.getClassLoader());
        response = in.readParcelable(Response.class.getClassLoader());
        tracks = in.createTypedArrayList(Track.CREATOR);
        played = in.readString();
        favonot = in.createStringArrayList();
    }

    public static final Creator<Root> CREATOR = new Creator<Root>() {
        @Override
        public Root createFromParcel(Parcel in) {
            return new Root(in);
        }

        @Override
        public Root[] newArray(int size) {
            return new Root[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(request, flags);
        dest.writeParcelable(response, flags);
        dest.writeTypedList(tracks);
        dest.writeString(played);
        dest.writeStringList(favonot);
    }
}
