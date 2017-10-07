package ua.com.todd.dialogsample.app;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","onStop");
    }

    public void onClick(View view) {
        DialogMediator dm = DialogMediator.getInstance();
        switch (view.getId()) {
            case R.id.simple_dialog:
                dm.openSimpleDialog(this, "Some text for dialog", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case Dialog.BUTTON_POSITIVE:
                                toast("Yes");
                                break;
                            case Dialog.BUTTON_NEGATIVE:
                                toast("No");
                                break;
                            case Dialog.BUTTON_NEUTRAL:
                                dialog.dismiss();
                                break;
                        }
                    }
                });
                break;
            case R.id.time_dialog:
                dm.openTimeDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        toast(hourOfDay + " : " + minute);
                    }
                });
                break;

            case R.id.date_dialog:
                dm.openDateDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        toast(year + " : " + monthOfYear + " : " + dayOfMonth);
                    }
                });
                break;

            case R.id.list_dialog:
                dm.openListDialog(this, new DialogMediator.OnClickList() {
                    @Override
                    public void onClick(String str) {
                        toast(str);
                    }
                });
                break;

            case R.id.progress_dialog:
                Handler handler = dm.openProgressDialog(this, new DialogMediator.OnStopProgress() {
                    @Override
                    public void OnStopProgress() {
                        toast("Stop progress");
                    }
                });

                for (int i = 1; i < 12; i++) {
                    Message m = new Message();
                    m.arg1 = i;
                    handler.sendMessageDelayed(m, i * 1000);
                }
                break;

            case R.id.custom_dialog:
                dm.openCustomDialog(this);
                break;

            case R.id.activity_dialog:
                dm.openActivityDialog(this);
                break;

            case R.id.full_custom_dialog:
                dm.openFullScreenSimpleDialog(this);
                break;
            case R.id.fragment_dialog:
                dm.openDialogFragment(this);
                break;
            case R.id.fragment_other_dialog:
                dm.openDialogFragmentOther(this);
                break;
        }
    }

    private void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
