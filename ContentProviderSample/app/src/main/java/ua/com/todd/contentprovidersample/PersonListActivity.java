package ua.com.todd.contentprovidersample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ua.com.todd.contentprovidersample.database.DatabaseHandler;
import ua.com.todd.contentprovidersample.database.Person;


public class PersonListActivity extends FragmentActivity implements
		PersonListFragment.Callbacks {


	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_list);

		if (findViewById(R.id.person_detail_container) != null) {

			mTwoPane = true;


			((PersonListFragment) getSupportFragmentManager().findFragmentById(
					R.id.person_list)).setActivateOnItemClick(true);
		}


	}


	@Override
	public void onItemSelected(long id) {
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putLong(PersonDetailFragment.ARG_ITEM_ID, id);
			PersonDetailFragment fragment = new PersonDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.person_detail_container, fragment).commit();

		} else {

			Intent detailIntent = new Intent(this, PersonDetailActivity.class);
			detailIntent.putExtra(PersonDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.list_activity, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean result = false;
		if (R.id.newPerson == item.getItemId()) {
			result = true;
			Person p = new Person();
			DatabaseHandler.getInstance(this).putPerson(p);
			onItemSelected(p.id);
		}
		
		return result;
	}
}
