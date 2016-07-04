package mx.saudade.discovermusicapp.responses;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.simpleframework.xml.Element;

/**
 * Created by angie on 6/19/16.
 */
@org.simpleframework.xml.Root(name = "GetLyricResult")
public class LyricsResult implements Parcelable {

    @Element(name = "TrackChecksum", required = false)
    private String trackChecksum;

    @Element(name = "TrackId")
    private int trackId;

    @Element(name = "LyricChecksum", required = false)
    private String checksum;

    @Element(name = "LyricId")
    private int id;

    @Element(name = "LyricSong")
    private String song;

    @Element(name = "LyricArtist")
    private String artist;

    @Element(name = "LyricUrl")
    private String url;

    @Element(name = "LyricCovertArtUrl", required = false)
    private String covertArtUrl;

    @Element(name = "LyricRank")
    private int rank;

    @Element(name = "LyricCorrectUrl")
    private String correctUrl;

    @Element(name = "Lyric", required = false)
    private String lyric;

    public LyricsResult() { }

    protected LyricsResult(Parcel in) {
        trackChecksum = in.readString();
        trackId = in.readInt();
        checksum = in.readString();
        id = in.readInt();
        song = in.readString();
        artist = in.readString();
        url = in.readString();
        covertArtUrl = in.readString();
        rank = in.readInt();
        correctUrl = in.readString();
        lyric = in.readString();
    }

    public static final Creator<LyricsResult> CREATOR = new Creator<LyricsResult>() {
        @Override
        public LyricsResult createFromParcel(Parcel in) {
            return new LyricsResult(in);
        }

        @Override
        public LyricsResult[] newArray(int size) {
            return new LyricsResult[size];
        }
    };

    public String getLyric() {
        return lyric;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("trackChecksum", trackChecksum)
                .append("trackId", trackId)
                .append("checksum", checksum)
                .append("id", id)
                .append("song", song)
                .append("artist", artist)
                .append("url", url)
                .append("covertArtUrl", covertArtUrl)
                .append("rank", rank)
                .append("correctUrl", correctUrl)
                .append("lyric", lyric)
                .toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(trackChecksum);
        dest.writeInt(trackId);
        dest.writeString(checksum);
        dest.writeInt(id);
        dest.writeString(song);
        dest.writeString(artist);
        dest.writeString(url);
        dest.writeString(covertArtUrl);
        dest.writeInt(rank);
        dest.writeString(correctUrl);
        dest.writeString(lyric);
    }
}
