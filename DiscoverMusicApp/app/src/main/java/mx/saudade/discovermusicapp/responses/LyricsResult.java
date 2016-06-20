package mx.saudade.discovermusicapp.responses;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.simpleframework.xml.*;

/**
 * Created by angie on 6/19/16.
 */

@org.simpleframework.xml.Root(name = "GetLyricResult")
public class LyricsResult {

    @Element(name = "TrackId")
    private int trackId;

    @Element(name = "LyricChecksum")
    private String checksum;

    @Element(name = "LyricId")
    private int id;

    @Element(name = "LyricSong")
    private String song;

    @Element(name = "LyricArtist")
    private String artist;

    @Element(name = "LyricUrl")
    private String url;

    @Element(name = "LyricCovertArtUrl")
    private String covertArtUrl;

    @Element(name = "LyricRank")
    private int rank;

    @Element(name = "LyricCorrectUrl")
    private String correctUrl;

    @Element(name = "Lyric")
    private String lyric;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
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

}
