package ua.com.todd.parcelsample.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import ua.com.todd.parcelsample.app.pojo.SampleParcelable;
import ua.com.todd.parcelsample.app.pojo.SampleParcelable1;
import ua.com.todd.parcelsample.app.pojo.SampleSerializable;

public class MainActivity extends Activity {

    public static String P1_KEY = "p1";
    public static String P2_KEY = "p2";
    public static String S_KEY = "s";

    private SampleParcelable1 p3 = new SampleParcelable1(100, "SampleParcelable1");
    private SampleParcelable p1 = new SampleParcelable(p3, 1001, "SampleParcelable_1");
    private SampleParcelable p2 = new SampleParcelable(p3, 1002, "SampleParcelable_2");
    private SampleSerializable s = new SampleSerializable(500, "Ser");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        Log.i("MainActivity", "Is ref equals: " + (p1.sampleParcelable1 == p2.sampleParcelable1));
        Log.i("MainActivity", "p1: " + p1.toString());
        Log.i("MainActivity", "p2: " + p2.toString());
        Log.i("MainActivity", "S: " +  s.toString());

        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra(P1_KEY, p1)
                .putExtra(P2_KEY, p2)
                .putExtra(S_KEY, s);
        startActivity(i);
    }
}
