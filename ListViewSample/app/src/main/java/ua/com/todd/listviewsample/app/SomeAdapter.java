package ua.com.todd.listviewsample.app;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SomeAdapter extends BaseAdapter {

    private Context context;
    private List<SomeItem> list = new ArrayList<SomeItem>();

    public SomeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SomeItem getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SomeItem item = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(item.data);
        switch (item.someType) {

            case BLUE:
                convertView.setBackgroundColor(Color.BLUE);
                break;
            case GREEN:
                convertView.setBackgroundColor(Color.GREEN);
                break;
            case RED:
                convertView.setBackgroundColor(Color.RED);
                break;
        }
        return convertView;
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public void add(SomeItem item) {
        list.add(item);
        notifyDataSetChanged();
    }

    public void add(List<SomeItem> items) {
        list.addAll(items);
        notifyDataSetChanged();
    }

    private class ViewHolder {

        TextView textView;

        ViewHolder(View view) {
            textView = (TextView) view.findViewById(R.id.text);
        }
    }

    public static class SomeItem {
        public SomeItem(String data, SomeType someType) {
            this.data = data;
            this.someType = someType;
        }

        public String data;
        public SomeType someType;
    }

    public enum SomeType {
        BLUE, GREEN, RED
    }
}
