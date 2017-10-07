package ua.com.todd.loader;

import android.content.Loader;
import android.os.AsyncTask;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

class GetTimeTask extends AsyncTask<String, Void, String> {
    final String TAG = TimeLoader.class.getName();
    private Loader<String> loader;

    public GetTimeTask(Loader<String> loader) {
        this.loader = loader;
    }

    @Override
    protected String doInBackground(String... params) {
        Log.i(TAG, "doInBackground");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(params[0],
                Locale.getDefault());
        return sdf.format(new Date());
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.i(TAG, "onPostExecute " + result);
        loader.deliverResult(result);
    }

}
