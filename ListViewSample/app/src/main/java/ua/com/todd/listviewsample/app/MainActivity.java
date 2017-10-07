package ua.com.todd.listviewsample.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    private ListView listView;
    private SomeAdapter someAdapter;


    List<SomeAdapter.SomeItem> items = Arrays.asList(
            new SomeAdapter.SomeItem("Bla", SomeAdapter.SomeType.BLUE),
            new SomeAdapter.SomeItem("Bla1", SomeAdapter.SomeType.RED),
            new SomeAdapter.SomeItem("Bla2", SomeAdapter.SomeType.RED),
            new SomeAdapter.SomeItem("Bla3", SomeAdapter.SomeType.BLUE),
            new SomeAdapter.SomeItem("Bla4", SomeAdapter.SomeType.BLUE));
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            onLoad(items);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        someAdapter = new SomeAdapter(this);
        listView = (ListView) findViewById(android.R.id.list);
        listView.setEmptyView(findViewById(android.R.id.empty));
        listView.setAdapter(someAdapter);

        onUpdateData(null);
    }

    private void onLoad(List<SomeAdapter.SomeItem> someData) {
        someAdapter.add(someData);
    }

    public void onUpdateData(View view) {
        handler.sendMessageDelayed(new Message(), 3000);
    }

    public void onDeleteData(View view) {
        someAdapter.clear();
        onUpdateData(null);
    }

    public void onAddData(View view) {
        SomeAdapter.SomeItem item = new SomeAdapter.SomeItem("Bla" + (items.size() - 1), SomeAdapter.SomeType.GREEN);
        someAdapter.add(item);
    }
}
