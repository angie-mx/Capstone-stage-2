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
public class Track  implements Parcelable {

    @Element(name = "id")
    private int id;

    @Element(name = "title")
    private String title;

    @Element(name = "artist")
    private Artist artist;

    @Element(name = "releasedate")
    private String releaseDate;

    @Element(name = "genre")
    private String genre;

    @Element(name = "arousal")
    private int arousal;

    @Element(name = "valence")
    private int valence;

    @Element(name = "popularity")
    private int popularity;

    @ElementList(name = "originalid", required = false)
    private List<String> originalId;

    @ElementList(name = "ASIN", required = false)
    private List<String> asin;

    @Element(name = "favorite")
    private int favorite;

    @Element(required = false)
    private String lyrics;

    public Track() { }


    protected Track(Parcel in) {
        id = in.readInt();
        title = in.readString();
        artist = in.readParcelable(Artist.class.getClassLoader());
        releaseDate = in.readString();
        genre = in.readString();
        arousal = in.readInt();
        valence = in.readInt();
        popularity = in.readInt();
        originalId = in.createStringArrayList();
        asin = in.createStringArrayList();
        favorite = in.readInt();
        lyrics = in.readString();
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getArousal() {
        return arousal;
    }

    public void setArousal(int arousal) {
        this.arousal = arousal;
    }

    public int getValence() {
        return valence;
    }

    public void setValence(int valence) {
        this.valence = valence;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public List<String> getOriginalId() {
        return originalId;
    }

    public void setOriginalId(List<String> originalId) {
        this.originalId = originalId;
    }

    public List<String> getAsin() {
        return asin;
    }

    public void setAsin(List<String> asin) {
        this.asin = asin;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getShareMessage() {
        return "Take a look to this amazing track: " + this.title + " by " + artist.getName()
                + ". Discover Music";
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("artist", artist)
                .append("releaseDate", releaseDate)
                .append("genre", genre)
                .append("arousal", arousal)
                .append("valence", valence)
                .append("popularity", popularity)
                .append("originalId", originalId)
                .append("asin", asin)
                .append("favorite", favorite)
                .append("lyrics", lyrics)
                .toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeParcelable(artist, flags);
        dest.writeString(releaseDate);
        dest.writeString(genre);
        dest.writeInt(arousal);
        dest.writeInt(valence);
        dest.writeInt(popularity);
        dest.writeStringList(originalId);
        dest.writeStringList(asin);
        dest.writeInt(favorite);
        dest.writeString(lyrics);
    }
}
