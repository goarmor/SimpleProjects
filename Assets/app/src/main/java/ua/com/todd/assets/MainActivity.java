package ua.com.todd.assets;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView editText = (TextView) findViewById(R.id.textEdit);
        TextView text = (TextView) findViewById(R.id.text);

        editText.setTypeface(Typeface.createFromAsset(
                getAssets(), "catwalk.ttf"));
        text.setTypeface(Typeface.createFromAsset(
                getAssets(), "catwalk.ttf"));

        AssetManager am = getAssets();
        InputStream is = null;
        try {
            is = am.open("text/some.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = convertStreamToString(is);
        editText.setText(s);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String convertStreamToString(InputStream is) {

        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line = "";
        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total.toString();
    }
}
