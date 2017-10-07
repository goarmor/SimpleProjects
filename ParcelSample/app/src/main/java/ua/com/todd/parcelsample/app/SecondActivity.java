package ua.com.todd.parcelsample.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import ua.com.todd.parcelsample.app.pojo.SampleParcelable;
import ua.com.todd.parcelsample.app.pojo.SampleSerializable;

public class SecondActivity extends Activity {

    private SampleParcelable p1;
    private SampleParcelable p2;
    private SampleSerializable s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent i = getIntent();
        p1 = i.getParcelableExtra(MainActivity.P1_KEY);
        p2 = i.getParcelableExtra(MainActivity.P2_KEY);
        s = (SampleSerializable) i.getSerializableExtra(MainActivity.S_KEY);

        Log.i("SecondActivity", "Is ref equals: " + (p1.sampleParcelable1 == p2.sampleParcelable1));
        Log.i("SecondActivity", "p1: " + p1.toString());
        Log.i("SecondActivity", "p2: " + p2.toString());
        Log.i("SecondActivity", "S: " +  s.toString());
    }
}
