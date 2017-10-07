package ua.com.todd.parcelsample.app.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class SampleParcelable1 implements Parcelable {

    public int num1;
    public String str1;

    public SampleParcelable1(int num1, String str1) {
        this.num1 = num1;
        this.str1 = str1;
    }

    public SampleParcelable1(Parcel in) {
        num1 = in.readInt();
        str1 = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(num1);
        dest.writeString(str1);
    }

    public static final Parcelable.Creator<SampleParcelable1> CREATOR = new Parcelable.Creator<SampleParcelable1>() {
        public SampleParcelable1 createFromParcel(Parcel in) {
            return new SampleParcelable1(in);
        }

        public SampleParcelable1[] newArray(int size) {
            return new SampleParcelable1[size];
        }
    };

    @Override
    public String toString() {
        return "SampleParcelable1{" +
                "num1=" + num1 +
                ", str1='" + str1 + '\'' +
                '}';
    }
}
