package ua.com.todd.loader;

import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeLoader extends Loader<String> {
    final String TAG = TimeLoader.class.getName();

    private GetTimeTask getTimeTask;
    private String format  = "h:mm:ss a";

    public TimeLoader(Context context, Bundle args) {
        super(context);
        Log.i(TAG, "create TimeLoader");
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.i(TAG, "onStartLoading");
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.i(TAG, "onStopLoading");
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        Log.i(TAG, "onForceLoad");
        if (getTimeTask != null)
            getTimeTask.cancel(true);
        getTimeTask = new GetTimeTask(this);
        getTimeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, format);
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
        Log.i(TAG, "onAbandon");
    }

    @Override
    protected void onReset() {
        super.onReset();
        Log.i(TAG, "onReset");
    }
}
