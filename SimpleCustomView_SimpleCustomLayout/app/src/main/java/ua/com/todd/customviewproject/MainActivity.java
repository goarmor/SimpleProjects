package ua.com.todd.customviewproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import ua.com.todd.customviewproject.widgets.UploadImageView;

public class MainActivity extends AppCompatActivity {

    private String urls[] = {
            "http://cs629303.vk.me/v629303862/11ab6/M3f1GLO5vRo.jpg",
            "http://cs629303.vk.me/v629303862/11aa1/4EHXpKsqvTE.jpg",
            "http://cs629303.vk.me/v629303862/11a93/P35Y7fG8kHw.jpg",
            "http://cs629303.vk.me/v629303862/11a5a/nvKmJS-Vgpc.jpg",
            "http://cs629303.vk.me/v629303862/11a53/ChYUPpEHrjY.jpg",
            "http://cs629303.vk.me/v629303862/11a4c/XB37O3tvB8U.jpg",
            "http://cs629303.vk.me/v629303862/11a45/MP3KZpjtyWw.jpg",
            "http://cs629303.vk.me/v629303862/11a30/r3cvGh5CWfQ.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(new ImageAdapter(urls));
    }


    class ImageAdapter extends BaseAdapter {

        private String[] urls;

        public ImageAdapter(String[] urls) {
            this.urls = urls;
        }

        @Override
        public int getCount() {
            return urls.length;
        }

        @Override
        public String getItem(int position) {
            return urls[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this, R.layout.layout_image, null);
            UploadImageView imageView = (UploadImageView) view.findViewById(android.R.id.content);
            imageView.upload(getItem(position));
            return view;
        }
    }
}
