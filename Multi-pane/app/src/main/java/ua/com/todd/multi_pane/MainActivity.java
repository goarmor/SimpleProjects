package ua.com.todd.multi_pane;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity implements OnVersionNameSelectionChangeListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null){

            if (savedInstanceState != null){
                return;
            }

            VersionsFragment versionsFragment = new VersionsFragment();
            versionsFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, versionsFragment)
                    .commit();
        }
    }

    @Override
    public void OnSelectionChanged(int versionNameIndex) {
        DescriptionFragment descriptionFragment = (DescriptionFragment) getFragmentManager()
                .findFragmentById(R.id.description_fragment);

        if (descriptionFragment != null){
            descriptionFragment.setDescription(versionNameIndex);
        } else {
            DescriptionFragment newDesriptionFragment = new DescriptionFragment();
            Bundle args = new Bundle();

            args.putInt(DescriptionFragment.KEY_POSITION,versionNameIndex);
            newDesriptionFragment.setArguments(args);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fragment_container,newDesriptionFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}

