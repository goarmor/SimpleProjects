package ua.com.todd.prefsample.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.com.todd.prefsample.R;
import ua.com.todd.prefsample.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Bind(R.id.edit_text)
    EditText editText;

    @Bind(R.id.button_store_data)
    Button buttonStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        int visibility = getSettingsManager().getButtonVisibility() ? View.GONE : View.VISIBLE;
        buttonStore.setVisibility(visibility);

        editText.setText(getSettingsManager().getData());
    }

    @OnClick(R.id.button_hide)
    protected void onClickHide() {
        boolean isVisible = buttonStore.getVisibility() == View.VISIBLE;
        int visibility = isVisible ? View.GONE : View.VISIBLE;
        buttonStore.setVisibility(visibility);
        getSettingsManager().storeButtonVisibility(isVisible);
    }

    @OnClick(R.id.button_store_data)
    protected void onStoreData() {
        getSettingsManager().storeData(editText.getText().toString());
    }
}
