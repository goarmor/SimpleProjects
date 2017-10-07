package ua.com.todd.animationsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onClick(View view) {
        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }
}
