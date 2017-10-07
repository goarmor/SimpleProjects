package com.example.timofeyandriyaschenko.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User sampleUser = new User();
        sampleUser.userName = "Steph";
        sampleUser.profilePictureUrl = "https://i.imgur.com/tGbaZCY.jpg";

        Post samplePost = new Post();
        samplePost.user = sampleUser;
        samplePost.text = "Won won!";

        PostsDatabaseHelper databaseHelper = PostsDatabaseHelper.getInstance(this);

        databaseHelper.addPost(samplePost);

        List<Post> posts = databaseHelper.getAllPosts();
        for (Post post : posts) {
            // do something
        }
    }
}
