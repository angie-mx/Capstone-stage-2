package mx.saudade.discovermusicapp.responses;

/**
 * Created by angie on 6/19/16.
 */
public class Track {

    private int id;
    private String title;
    private Artist artist;
    private int releaseDate;
    private String genre;
    private int arousal;
    private int valence;
    private int popularity;
    private int originalId;
    private String asin;
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

    public int getOriginalId() {
        return originalId;
    }

    public void setOriginalId(int originalId) {
        this.originalId = originalId;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
