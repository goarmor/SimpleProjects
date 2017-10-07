package ua.com.todd.parcelsample.app.pojo;

import java.io.Serializable;

public class SampleSerializable implements Serializable {
    public int num;
    public String str;

    public SampleSerializable(int num, String str) {
        this.num = num;
        this.str = str;
    }

    @Override
    public String toString() {
        return "SampleSerializable{" +
                "num=" + num +
                ", str='" + str + '\'' +
                '}';
    }
}
