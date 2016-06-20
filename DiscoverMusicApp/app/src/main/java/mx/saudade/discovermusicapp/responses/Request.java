package mx.saudade.discovermusicapp.responses;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by angie on 6/19/16.
 */
public class Request {

    private String format;

    private String fct;

    @SerializedName("popularitymax")
    private int popularityMax;

    @SerializedName("popularitymin")
    private int popularityMin;

    @SerializedName("starttrackid")
    private List<Integer> startTrackId;

    private boolean date70;

    @SerializedName("trackvalence")
    private String trackValence;

    @SerializedName("trackarousal")
    private String trackArousal;

    @SerializedName("resultsnumber")
    private String resultsNumber;

    @SerializedName("listenercountry")
    private String listenerCountry;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

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

    public List<Integer> getStartTrackId() {
        return startTrackId;
    }

    public void setStartTrackId(List<Integer> startTrackId) {
        this.startTrackId = startTrackId;
    }

    public boolean isDate70() {
        return date70;
    }

    public void setDate70(boolean date70) {
        this.date70 = date70;
    }

    public String getTrackValence() {
        return trackValence;
    }

    public void setTrackValence(String trackValence) {
        this.trackValence = trackValence;
    }

    public String getTrackArousal() {
        return trackArousal;
    }

    public void setTrackArousal(String trackArousal) {
        this.trackArousal = trackArousal;
    }

    public String getResultsNumber() {
        return resultsNumber;
    }

    public void setResultsNumber(String resultsNumber) {
        this.resultsNumber = resultsNumber;
    }

    public String getListenerCountry() {
        return listenerCountry;
    }

    public void setListenerCountry(String listenerCountry) {
        this.listenerCountry = listenerCountry;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("format", format)
                .append("fct", fct)
                .append("popularityMax", popularityMax)
                .append("popularityMin", popularityMin)
                .append("startTrackId", startTrackId)
                .append("date70", date70)
                .append("trackValence", trackValence)
                .append("trackArousal", trackArousal)
                .append("resultsNumber", resultsNumber)
                .append("listenerCountry", listenerCountry)
                .toString();
    }
}
