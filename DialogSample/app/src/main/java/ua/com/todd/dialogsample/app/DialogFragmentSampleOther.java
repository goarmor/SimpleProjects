package ua.com.todd.dialogsample.app;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class DialogFragmentSampleOther extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new CustomDialog(getActivity());
    }
}