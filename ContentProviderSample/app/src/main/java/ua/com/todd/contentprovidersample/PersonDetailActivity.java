package ua.com.todd.contentprovidersample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;


public class PersonDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);


        getActionBar().setDisplayHomeAsUpEnabled(true);



        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putLong(PersonDetailFragment.ARG_ITEM_ID,
                    getIntent().getLongExtra(PersonDetailFragment.ARG_ITEM_ID, -1));
            PersonDetailFragment fragment = new PersonDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.person_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, PersonListActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
