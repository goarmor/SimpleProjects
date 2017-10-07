package ua.com.todd.loader;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.ContentObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String TAG = MainActivity.class.getName();
    final static int SOME_LOADER = 11111;
    final static int SOME_ASYNC_LOADER = 11112;
    private TextView textTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTime = (TextView) findViewById(R.id.text_time);

        Bundle bndl = new Bundle();
        getLoaderManager().initLoader(SOME_LOADER, bndl, loaderCallBack);
        getLoaderManager().initLoader(SOME_ASYNC_LOADER, bndl, loaderCallBack);
    }

    public void onStart(View view) {
        textTime.setText("Wait...");
        Loader loader = getLoaderManager().getLoader(SOME_LOADER);
        loader.forceLoad();
    }

    public void onObserver(View v) {
        Log.d(TAG, "onObserver");
        Loader<String> loader = getLoaderManager().getLoader(SOME_LOADER);
        final ContentObserver observer = loader.new ForceLoadContentObserver();
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                observer.dispatchChange(false);
            }
        }, 5000);
    }

    LoaderManager.LoaderCallbacks<String> loaderCallBack = new LoaderManager.LoaderCallbacks<String>(){
        @Override
        public Loader<String> onCreateLoader(int id, Bundle args) {
            Loader<String> loader = null;
            if (id == SOME_LOADER) {
                loader = new TimeLoader(MainActivity.this, args);
                Log.i(TAG, "onCreateLoader");
            }
            if (id == SOME_ASYNC_LOADER) {
                loader = new TimeAsyncLoader(MainActivity.this, args);
                Log.i(TAG, "onCreateAsyncLoader");
            }
            return loader;
        }

        @Override
        public void onLoadFinished(Loader<String> loader, String data) {
            Log.i(TAG, "onLoadFinished: result = " + data);
            textTime.setText(data);
        }

        @Override
        public void onLoaderReset(Loader<String> loader) {
            Log.i(TAG, "onLoaderReset: " + loader.getId());
        }
    };

    public void onAsyncStart(View view) {
        textTime.setText("Wait async loader...");
        Loader loader = getLoaderManager().getLoader(SOME_ASYNC_LOADER);
        loader.forceLoad();
    }
}
