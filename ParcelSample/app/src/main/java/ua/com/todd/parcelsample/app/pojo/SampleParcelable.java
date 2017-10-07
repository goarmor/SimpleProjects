package ua.com.todd.parcelsample.app.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class SampleParcelable implements Parcelable {

    public SampleParcelable1 sampleParcelable1;
    public int num;
    public String str;

    public SampleParcelable(SampleParcelable1 sampleParcelable1, int num, String str) {
        this.sampleParcelable1 = sampleParcelable1;
        this.num = num;
        this.str = str;
    }

    public SampleParcelable(Parcel in) {
        sampleParcelable1 = in.readParcelable(SampleParcelable1.class.getClassLoader());
        num = in.readInt();
        str = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(sampleParcelable1, flags);
        dest.writeInt(num);
        dest.writeString(str);
    }

    public static final Parcelable.Creator<SampleParcelable> CREATOR = new Parcelable.Creator<SampleParcelable>() {
        public SampleParcelable createFromParcel(Parcel in) {
            return new SampleParcelable(in);
        }

        public SampleParcelable[] newArray(int size) {
            return new SampleParcelable[size];
        }
    };

    @Override
    public String toString() {
        return "SampleParcelable{" +
                "sampleParcelable1=" + sampleParcelable1 +
                ", num=" + num +
                ", str='" + str + '\'' +
                '}';
    }
}
