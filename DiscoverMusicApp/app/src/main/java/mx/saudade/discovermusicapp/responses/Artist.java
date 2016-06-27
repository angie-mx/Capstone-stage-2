package mx.saudade.discovermusicapp.responses;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.simpleframework.xml.Element;

/**
 * Created by angie on 6/19/16.
 */
public class Artist implements Parcelable {

    private int id;

    @Element(name = "name")
    private String name;

    @Element(name = "mbid")
    private String mbid;

    @Element(name = "imgurl")
    private String imgUrl;

    public Artist() { }

    protected Artist(Parcel in) {
        id = in.readInt();
        name = in.readString();
        mbid = in.readString();
        imgUrl = in.readString();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("mbid", mbid)
                .append("imgUrl", imgUrl)
                .toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(mbid);
        dest.writeString(imgUrl);
    }
}
