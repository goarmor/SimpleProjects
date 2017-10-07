package ua.com.todd.prefsample;

import android.app.Application;

public class CustomApplication extends Application {

    private SettingsManager settingsManager;

    @Override
    public void onCreate() {
        super.onCreate();
        settingsManager = new SettingsManager(this);
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }
}
