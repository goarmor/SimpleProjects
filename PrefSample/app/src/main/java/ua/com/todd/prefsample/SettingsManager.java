package ua.com.todd.prefsample;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingsManager {

    private static String KEY_IS_VISIBLE = "is_visible";
    private static String KEY_DATA = "data";

    private SharedPreferences sp;

    public SettingsManager(Application app) {
        sp = PreferenceManager.getDefaultSharedPreferences(app);
    }

    private void storeString(String key, String string) {
        SharedPreferences.Editor e = sp.edit();
        e.putString(key, string);
        e.apply();
    }

    private String getString(String key) {
        return sp.getString(key, "");
    }

    private void storeBoolean(String key, boolean isVisible) {
        SharedPreferences.Editor e = sp.edit();
        e.putBoolean(key, isVisible);
        e.apply();
    }

    private boolean getBoolean(String key) {
        return sp.getBoolean(key, true);
    }

    public void storeButtonVisibility(boolean isVisible) {
        storeBoolean(KEY_IS_VISIBLE, isVisible);
    }

    public boolean getButtonVisibility() {
        return getBoolean(KEY_IS_VISIBLE);
    }

    public void storeData(String data) {
        storeString(KEY_DATA, data);
    }

    public String getData() {
        return getString(KEY_DATA);
    }
}
