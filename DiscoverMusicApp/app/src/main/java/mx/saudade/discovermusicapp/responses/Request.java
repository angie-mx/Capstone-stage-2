package mx.saudade.discovermusicapp.responses;

/**
 * Created by angie on 6/19/16.
 */
public class Request {

    private String fct;
    private int popularityMax;
    private int popularityMin;
    private int startTrackId;
    private boolean date70;
    private int trackValence;
    private int trackArousal;
    private int resultsNumber;
    private String listenerCountry;

    public String getFct() {
        return fct;
    }

    public void setFct(String fct) {
        this.fct = fct;
    }

    public int getPopularityMax() {
        return popularityMax;
    }

    public void setPopularityMax(int popularityMax) {
        this.popularityMax = popularityMax;
    }

    public int getPopularityMin() {
        return popularityMin;
    }

    public void setPopularityMin(int popularityMin) {
        this.popularityMin = popularityMin;
    }

    public int getStartTrackId() {
        return startTrackId;
    }

    public void setStartTrackId(int startTrackId) {
        this.startTrackId = startTrackId;
    }

    public boolean isDate70() {
        return date70;
    }

    public void setDate70(boolean date70) {
        this.date70 = date70;
    }

    public int getTrackValence() {
        return trackValence;
    }

    public void setTrackValence(int trackValence) {
        this.trackValence = trackValence;
    }

    public int getTrackArousal() {
        return trackArousal;
    }

    public void setTrackArousal(int trackArousal) {
        this.trackArousal = trackArousal;
    }

    public int getResultsNumber() {
        return resultsNumber;
    }

    public void setResultsNumber(int resultsNumber) {
        this.resultsNumber = resultsNumber;
    }

    public String getListenerCountry() {
        return listenerCountry;
    }

    public void setListenerCountry(String listenerCountry) {
        this.listenerCountry = listenerCountry;
    }
}
