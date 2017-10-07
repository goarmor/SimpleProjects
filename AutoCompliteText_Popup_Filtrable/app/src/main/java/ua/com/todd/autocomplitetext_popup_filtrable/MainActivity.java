package ua.com.todd.autocomplitetext_popup_filtrable;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity {

    List<String> languages= new ArrayList<>(Arrays.asList("Android ","java","IOS","SQL","JDBC","Web services","Erlang",
            "Asm","Lisp","Haskell","F-Sharp","OCalm","bash","javascript","Carry"));

    private AutoCompleteTextView textView;
    private EditText editText;
    private FilteredListAdapter listAdapter;
    private Filter filter;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (AutoCompleteTextView) findViewById(R.id.auto);
        editText = (EditText) findViewById(R.id.edit);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_auto,languages);
        textView.setAdapter(adapter);

        editText.addTextChangedListener(textWatcher);
        listAdapter = new FilteredListAdapter(this, R.layout.item_auto,languages);
        filter = listAdapter.getFilter();
        setListAdapter(listAdapter);

        View contentView = findViewById(R.id.root);
        contentView.setOnTouchListener(touchListener);

        View view = View.inflate(MainActivity.this, R.layout.layout_popup, null);
        popupWindow = new PopupWindow(
                view,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            filter.filter(s);
        }
    };

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(MotionEvent.ACTION_UP == event.getAction()){

//                popupWindow.showAsDropDown(getListView(), 50,50);
                popupWindow.showAtLocation(getListView(), Gravity.CENTER, 0, 0);
            }
            return true;
        }
    };
}
