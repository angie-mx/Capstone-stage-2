package mx.saudade.discovermusicapp.responses;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by angie on 6/19/16.
 */
public class Track {

    private int id;

    private String title;

    private Artist artist;

    @SerializedName("releasedate")
    private int releaseDate;

    private String genre;

    private int arousal;

    private int valence;

    private int popularity;

    private List<String> originalId;

    private List<String> asin;

    private int favorite;

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

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
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
                .toString();
    }
}
