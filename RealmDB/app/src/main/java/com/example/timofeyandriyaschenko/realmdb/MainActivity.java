package com.example.timofeyandriyaschenko.realmdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm myRealm = Realm.getInstance(this);

        myRealm.beginTransaction();

        Country country1 = myRealm.createObject(Country.class);

        country1.setName("Norway");
        country1.setPopulation(5165800);

        myRealm.commitTransaction();

        RealmResults<Country> results1 =
                myRealm.where(Country.class).findAll();

        for(Country c:results1) {
            Log.d("results1", c.getName());
        }

    }
}
