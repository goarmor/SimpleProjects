package ua.com.todd.autocomplitetext_popup_filtrable;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FilteredListAdapter extends ArrayAdapter<String> implements Filterable {

    private ListFilter filter = new ListFilter();
    private List<String> objects;
    private List<String> originalObjects;
    private ArrayList<String> filteredList;

    public FilteredListAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.objects = objects;
        originalObjects = new ArrayList<>(objects);
        filteredList = new ArrayList<String>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        String str = textView.getText().toString();
        textView.setText(Html.fromHtml(str));
        return textView;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            objects.clear();
            filteredList.clear();
            FilterResults filterResults = new FilterResults();

            if (constraint != null || constraint.length() != 0) {
                for (String str : originalObjects) {
                    if (str.contains(constraint.toString())) {
                        str = str.replaceAll(constraint.toString(), "<big><b>" + constraint + "</b></big>");
                        filteredList.add(str);
                    }
                }
            }

            filterResults.values = filteredList;
            filterResults.count = filteredList.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            objects.addAll(((List<String>) results.values));
            notifyDataSetChanged();
        }
    }
}
