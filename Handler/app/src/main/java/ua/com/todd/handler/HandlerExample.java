package ua.com.todd.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

public class HandlerExample {
    private Handler mWorkHandler;
    private HandlerThread mHandlerThread;

    public enum State {
        stopped,
        starting,
        started,
        stopping
    }

    private State mState = State.stopped;
    private final Runnable mActionNetworkCheck = new Runnable() {
        @Override
        public void run() {
            Log.i("HANDLER", "HandlerExample mActionNetworkCheck: " + Thread.currentThread() + " sec: " + 10000);
            if (isItWork()) {
                mWorkHandler.postDelayed(mActionNetworkCheck, 10000);
            }
        }
    };
    private final Runnable mActionWifiScan = new Runnable() {
        @Override
        public void run() {
            Log.i("HANDLER", "HandlerExample mActionWifiScan: " + Thread.currentThread() + " sec: " + 30000);
            if (isItWork()) {
                mWorkHandler.postDelayed(mActionWifiScan, 30000);
            }
        }
    };
    private final Runnable mActionUsbIo = new Runnable() {
        @Override
        public void run() {
            Log.i("HANDLER", "HandlerExample mActionUsbIo: " + Thread.currentThread() + " sec: " + 4000);
            if (isItWork()) {
                mWorkHandler.postDelayed(mActionUsbIo, 4000);
            }
        }
    };

    private synchronized boolean isItWork() {
        return mState == State.started;
    }

    public void startup() {
        synchronized (this) {
            if (mState == State.stopped) {
                mState = State.starting;
            } else {
                throw new IllegalStateException("Example must be stopped for start");
            }
        }

        mHandlerThread = new HandlerThread("WorkHandlerThread") {
            @Override
            protected void onLooperPrepared() {
                mWorkHandler = new Handler(getLooper());
                synchronized (HandlerExample.this) {
                    mState = HandlerExample.State.started;
                }
                mWorkHandler.post(mActionNetworkCheck);
                mWorkHandler.post(mActionWifiScan);
                mWorkHandler.post(mActionUsbIo);
            }
        };
        mHandlerThread.start();
    }

    public void shutdown() {
        synchronized (this) {
            if (mState == State.started) {
                mState = State.stopping;
            } else {
                throw new IllegalStateException("Example must be started for shutdown");
            }
        }

        mHandlerThread.quit();
        try {
            mHandlerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mWorkHandler = null;
        mHandlerThread = null;
        synchronized (this) {
            mState = State.stopped;
        }
    }
}
