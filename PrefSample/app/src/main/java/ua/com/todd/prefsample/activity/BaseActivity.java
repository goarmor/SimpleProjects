package ua.com.todd.prefsample.activity;

import android.app.Activity;
import android.os.Bundle;

import ua.com.todd.prefsample.CustomApplication;
import ua.com.todd.prefsample.SettingsManager;

public abstract class BaseActivity extends Activity {
    private SettingsManager settingsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomApplication app = (CustomApplication) getApplication();
        settingsManager = app.getSettingsManager();
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }
}
