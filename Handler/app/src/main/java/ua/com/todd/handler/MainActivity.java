package ua.com.todd.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            toast("handler: " + isMainThread());
            Log.i("HANDLER", "handler: " + isMainThread());
        }
    };

    private HandlerExample handlerExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread.start();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("HANDLER", "runOnUiThread: " + isMainThread());
            }
        });

        View view = findViewById(R.id.text);
        view.post(new Runnable() {
            @Override
            public void run() {
                Log.i("HANDLER", "view.post: " + isMainThread());
            }
        });

        Timer timer = new Timer("SomeTimer",true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("HANDLER", "timer: " + isMainThread() + " thread: " + Thread.currentThread());
            }
        }, 4000, 4000);
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("HANDLER", "timer1: " + isMainThread() + " thread: " + Thread.currentThread());
            }
        }, 4000, 4000);

        handlerExample = new HandlerExample();
    }

    public boolean isMainThread(){
        return  Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    public void toast(String str){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    Thread thread = new Thread() {
        Handler handler1 = new Handler();

        @Override
        public void run() {
            Log.i("HANDLER", "Thread: " + isMainThread());
            Message msg = new Message();
            msg.what = 4;
            handler.sendMessageDelayed(msg, 3000);
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast("handler1: " + isMainThread());
                    Log.i("HANDLER", "handler1: " + isMainThread());
                }
            }, 6000);
        }
    };

    public void onStart(View view) {
        handlerExample.startup();
    }

    public void onStop(View view) {
        handlerExample.shutdown();
    }
}
