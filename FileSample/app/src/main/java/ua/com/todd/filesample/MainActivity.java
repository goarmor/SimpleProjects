package ua.com.todd.filesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", getCacheDir().toString());
        Log.i("MainActivity", getFilesDir().toString());
        Log.i("MainActivity", getObbDir().toString());
        Log.i("MainActivity", getObbDirs().toString());
        Log.i("MainActivity", getCodeCacheDir().toString());
        Log.i("MainActivity", getPackageResourcePath().toString());
        Log.i("MainActivity", getExternalCacheDir().toString());
        Log.i("MainActivity", getExternalCacheDirs().toString());
        Log.i("MainActivity", getExternalMediaDirs().toString());
        Log.i("MainActivity", getNoBackupFilesDir().toString());
    }
}
