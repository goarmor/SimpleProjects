package com.example.todd.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    static String TAG = MainActivity.class.getName();

    String[] array = {"Some1","Some2","Some3","Some4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment f = MyFragment.getInstance("FRAGMENT 1");
        Fragment f1 = MyFragment.getInstance("FRAGMENT 2");
        Fragment f2 = MyFragment.getInstance("FRAGMENT 3");
        Fragment f3 = MyFragment.getInstance("FRAGMENT 4");
        Fragment f4 = MyFragment.getInstance("FRAGMENT 5");

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.some_conteiner, f)
                .commit();
        fm.beginTransaction()
                .replace(R.id.some_conteiner, f1)
                .addToBackStack(null)
                .commit();
        fm.beginTransaction()
                .replace(R.id.some_conteiner, f2)
                .addToBackStack(null)
                .commit();
        fm.beginTransaction()
                .replace(R.id.some_conteiner, f3)
                .addToBackStack(null)
                .commit();
        fm.beginTransaction()
                .replace(R.id.some_conteiner, f4)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
