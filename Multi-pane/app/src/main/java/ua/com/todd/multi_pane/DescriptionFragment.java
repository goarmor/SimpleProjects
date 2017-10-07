package ua.com.todd.multi_pane;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DescriptionFragment extends Fragment {

    final static String KEY_POSITION = "position";
    int mCurrentPosition = -1;

    String[] mVersionDescriptions;
    TextView mVersionDescriptionTextView;

    public DescriptionFragment(){

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mVersionDescriptions = getResources().getStringArray(R.array.version_descriptions);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null){
            mCurrentPosition = savedInstanceState.getInt(KEY_POSITION);
        }


        View view = inflater.inflate(R.layout.fragment_description, container, false);

        mVersionDescriptionTextView = (TextView) view.findViewById(R.id.version_description);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();


        Bundle args = getArguments();
        if (args != null){
            setDescription(args.getInt(KEY_POSITION));
        } else if(mCurrentPosition != -1){
            setDescription(mCurrentPosition);
        }
    }

    public void setDescription(int descriptionIndex){
        mVersionDescriptionTextView.setText(mVersionDescriptions[descriptionIndex]);
        mCurrentPosition = descriptionIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION,mCurrentPosition);
    }
}
