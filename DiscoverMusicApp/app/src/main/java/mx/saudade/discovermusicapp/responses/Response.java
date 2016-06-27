package mx.saudade.discovermusicapp.responses;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.simpleframework.xml.Element;

/**
 * Created by angie on 6/19/16.
 */
public class Response implements Parcelable {

    @Element
    private int code;

    @Element(name = "anwser")
    private String answer;

    @Element
    private double time;

    public Response() { }

    protected Response(Parcel in) {
        code = in.readInt();
        answer = in.readString();
        time = in.readDouble();
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("answer", answer)
                .append("time", time)
                .toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(answer);
        dest.writeDouble(time);
    }
}
