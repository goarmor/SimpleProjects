package ua.com.todd.dialogsample.app;

import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;

public class DialogMediator {

    private static DialogMediator dialogMediator = new DialogMediator();

    public static DialogMediator getInstance() {
        return dialogMediator;
    }

    private DialogMediator() {

    }

    public void openSimpleDialog(Context context, String str, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setTitle("Some title for dialog");
        adb.setMessage(str);
        adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setPositiveButton("Yes", clickListener);
        adb.setNegativeButton("No", clickListener);
        adb.setNeutralButton("Cancle", clickListener);
        adb.create().show();
    }

    public void openTimeDialog(Context context, TimePickerDialog.OnTimeSetListener timeSetListener) {
        final TimePickerDialog tpd = new TimePickerDialog(context,
                timeSetListener, 12, 12, false);
        tpd.setTitle("Time Picker title");
        tpd.setIcon(android.R.drawable.ic_dialog_info);
        tpd.show();
    }

    public void openDateDialog(Context context, DatePickerDialog.OnDateSetListener onDateSetListener) {
        final DatePickerDialog dpd = new DatePickerDialog(context,
                onDateSetListener, 1900, 12, 12);
        dpd.setTitle("Time Picker title");
        dpd.setIcon(android.R.drawable.ic_dialog_info);
        dpd.setCancelable(false);
        dpd.show();
    }

    public void openListDialog(Context context, final OnClickList onClickList) {
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                context,
                android.R.layout.select_dialog_singlechoice, new String[]{"Ann", "Todd", "Bob", "Mary"});

        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setTitle("Some title for dialog");
        adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setPositiveButton("Ok", null);
        adb.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                if (onClickList != null)
                    onClickList.onClick(strName);
            }
        });
        adb.show();
    }

    public Handler openProgressDialog(Context context, final OnStopProgress onStopProgress) {
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setTitle("Title");
        pd.setMessage("Message");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(10);
        pd.show();
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (pd.getProgress() < pd.getMax()) {
                    pd.setProgress(msg.arg1);
                } else {
                    pd.dismiss();
                    onStopProgress.OnStopProgress();
                }
                return false;
            }
        });
        return handler;
    }

    public void openCustomDialog(Context context) {
        Dialog dialog = new CustomDialog(context);
        dialog.show();
    }

    public void openActivityDialog(Context context) {
        Intent i = new Intent(context, ActivityLikeDialog.class);
        context.startActivity(i);
    }

    public void openFullScreenSimpleDialog(Context context) {
        Dialog dialog = new CustomDialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.show();
    }

    public void openDialogFragment(Activity activity) {
        new DialogFragmentSample().show(activity.getFragmentManager(), "");
    }

    public void openDialogFragmentOther(Activity activity) {
        new DialogFragmentSampleOther().show(activity.getFragmentManager(), "");
    }

    public interface OnStopProgress {
        void OnStopProgress();
    }

    public interface OnClickList {
        void onClick(String str);
    }
}
