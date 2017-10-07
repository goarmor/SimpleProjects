package ua.com.todd.mergeadapter.app;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.commonsware.cwac.merge.MergeAdapter;

public class MainActivity extends ListActivity {

    SomeAdapter someAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MergeAdapter mergeAdapter = new MergeAdapter();
        someAdapter = new SomeAdapter();
        SomeAdapter1 someAdapter1 = new SomeAdapter1();
        SomeAdapter2 someAdapter2 = new SomeAdapter2();
        SomeAdapter3 someAdapter3 = new SomeAdapter3();
        mergeAdapter.addView(View.inflate(this, R.layout.some_layout, null));
        mergeAdapter.addAdapter(someAdapter);
        mergeAdapter.addView(View.inflate(this, R.layout.some_layout, null));
        mergeAdapter.addAdapter(someAdapter1);
        mergeAdapter.addView(View.inflate(this, R.layout.some_layout, null));
        mergeAdapter.addAdapter(someAdapter2);
        mergeAdapter.addView(View.inflate(this, R.layout.some_layout, null));
        mergeAdapter.addAdapter(someAdapter3);
        mergeAdapter.addView(View.inflate(this, R.layout.some_layout, null));
        setListAdapter(mergeAdapter);
    }

    public void OnClick(View view) {
        String[] str = {"SOME","SOME","SOME","SOME","SOME"};
        someAdapter.str = str;
        someAdapter.notifyDataSetChanged();
    }

    class SomeAdapter extends BaseAdapter{

        public String[] str = {"SOME"};
        @Override
        public int getCount() {
            return str.length;
        }

        @Override
        public String getItem(int position) {
            return str[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this, R.layout.some_layout, null);
            TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText(getItem(position));
            view.setBackgroundColor(Color.RED);
            return view;
        }
    }

    class SomeAdapter1 extends BaseAdapter{
        String[] str = {"SOME","SOME","SOME"};
        @Override
        public int getCount() {
            return str.length;
        }

        @Override
        public String getItem(int position) {
            return str[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this, R.layout.some_layout, null);
            TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText(getItem(position));
            view.setBackgroundColor(Color.YELLOW);
            return view;
        }
    }

    class SomeAdapter2 extends BaseAdapter{
        String[] str = {"SOME","SOME","SOME"};
        @Override
        public int getCount() {
            return str.length;
        }

        @Override
        public String getItem(int position) {
            return str[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this, R.layout.some_layout, null);
            TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText(getItem(position));
            view.setBackgroundColor(Color.GREEN);
            return view;
        }
    }

    class SomeAdapter3 extends BaseAdapter{
        String[] str = {"SOME","SOME","SOME"};
        @Override
        public int getCount() {
            return str.length;
        }

        @Override
        public String getItem(int position) {
            return str[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this, R.layout.some_layout, null);
            TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText(getItem(position));
            view.setBackgroundColor(Color.BLUE);
            return view;
        }
    }
}
