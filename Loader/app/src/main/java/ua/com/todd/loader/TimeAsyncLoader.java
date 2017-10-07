package ua.com.todd.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeAsyncLoader extends AsyncTaskLoader<String> {

    final String TAG = TimeAsyncLoader.class.getName();

    String format = "yyyy.MM.dd G 'at' HH:mm:ss";

    public TimeAsyncLoader(Context context, Bundle args) {
        super(context);
        Log.d(TAG, "TimeAsyncLoader");
    }

    @Override
    public String loadInBackground() {
        Log.d(TAG, "loadInBackground start");
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(new Date());
    }

}