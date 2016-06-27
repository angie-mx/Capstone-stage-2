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
public class Request implements Parcelable {

    private String fct;

    @Element(name = "resultsnumber", required = false)
    private String resultsNumber;

    @Element(name = "trackvalence", required = false)
    private String trackValence;

    @Element(name = "trackarousal", required = false)
    private String trackArousal;

    @Element(name = "yearmax", required = false)
    private int yearMax;

    @Element(name = "yearmin", required = false)
    private int yearMin;

    @Element(name = "before1950", required = false)
    private boolean before1950;

    @Element(name = "date50", required = false)
    private boolean date50;

    @Element(name = "date60", required = false)
    private boolean date60;

    @Element(name = "date70", required = false)
    private boolean date70;

    @Element(name = "date80", required = false)
    private boolean date80;

    @Element(name = "date90", required = false)
    private boolean date90;

    @Element(name = "date00", required = false)
    private boolean date00;

    @Element(name = "date10", required = false)
    private boolean date10;

    @Element(name = "popularitymax", required = false)
    private int popularityMax;

    @Element(name = "popularitymin", required = false)
    private int popularityMin;

    @Element(name = "genreNo", required = false)
    private String genreNo;

    @Element(name = "listenercountry", required = false)
    private String listenerCountry;

    @Element(name = "format", required = false)
    private String format;

    @Element(name = "apikey", required = false)
    private String apiKey;

    @ElementList(name = "starttrackid", required = false)
    private List<Integer> startTrackId;

    public Request() { }

    protected Request(Parcel in) {
        fct = in.readString();
        resultsNumber = in.readString();
        trackValence = in.readString();
        trackArousal = in.readString();
        yearMax = in.readInt();
        yearMin = in.readInt();
        before1950 = in.readByte() != 0;
        date50 = in.readByte() != 0;
        date60 = in.readByte() != 0;
        date70 = in.readByte() != 0;
        date80 = in.readByte() != 0;
        date90 = in.readByte() != 0;
        date00 = in.readByte() != 0;
        date10 = in.readByte() != 0;
        popularityMax = in.readInt();
        popularityMin = in.readInt();
        genreNo = in.readString();
        listenerCountry = in.readString();
        format = in.readString();
        apiKey = in.readString();
    }

    public static final Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };

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

    public int getYearMax() {
        return yearMax;
    }

    public void setYearMax(int yearMax) {
        this.yearMax = yearMax;
    }

    public int getYearMin() {
        return yearMin;
    }

    public void setYearMin(int yearMin) {
        this.yearMin = yearMin;
    }

    public boolean isBefore1950() {
        return before1950;
    }

    public void setBefore1950(boolean before1950) {
        this.before1950 = before1950;
    }

    public boolean isDate50() {
        return date50;
    }

    public void setDate50(boolean date50) {
        this.date50 = date50;
    }

    public boolean isDate60() {
        return date60;
    }

    public void setDate60(boolean date60) {
        this.date60 = date60;
    }

    public boolean isDate80() {
        return date80;
    }

    public void setDate80(boolean date80) {
        this.date80 = date80;
    }

    public boolean isDate90() {
        return date90;
    }

    public void setDate90(boolean date90) {
        this.date90 = date90;
    }

    public boolean isDate00() {
        return date00;
    }

    public void setDate00(boolean date00) {
        this.date00 = date00;
    }

    public boolean isDate10() {
        return date10;
    }

    public void setDate10(boolean date10) {
        this.date10 = date10;
    }

    public String getGenreNo() {
        return genreNo;
    }

    public void setGenreNo(String genreNo) {
        this.genreNo = genreNo;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fct", fct)
                .append("resultsNumber", resultsNumber)
                .append("trackValence", trackValence)
                .append("trackArousal", trackArousal)
                .append("yearMax", yearMax)
                .append("yearMin", yearMin)
                .append("before1950", before1950)
                .append("date50", date50)
                .append("date60", date60)
                .append("date70", date70)
                .append("date80", date80)
                .append("date90", date90)
                .append("date00", date00)
                .append("date10", date10)
                .append("popularityMax", popularityMax)
                .append("popularityMin", popularityMin)
                .append("genreNo", genreNo)
                .append("listenerCountry", listenerCountry)
                .append("format", format)
                .append("startTrackId", startTrackId)
                .append("apiKey", apiKey)
                .toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fct);
        dest.writeString(resultsNumber);
        dest.writeString(trackValence);
        dest.writeString(trackArousal);
        dest.writeInt(yearMax);
        dest.writeInt(yearMin);
        dest.writeByte((byte) (before1950 ? 1 : 0));
        dest.writeByte((byte) (date50 ? 1 : 0));
        dest.writeByte((byte) (date60 ? 1 : 0));
        dest.writeByte((byte) (date70 ? 1 : 0));
        dest.writeByte((byte) (date80 ? 1 : 0));
        dest.writeByte((byte) (date90 ? 1 : 0));
        dest.writeByte((byte) (date00 ? 1 : 0));
        dest.writeByte((byte) (date10 ? 1 : 0));
        dest.writeInt(popularityMax);
        dest.writeInt(popularityMin);
        dest.writeString(genreNo);
        dest.writeString(listenerCountry);
        dest.writeString(format);
        dest.writeString(apiKey);
    }
}
