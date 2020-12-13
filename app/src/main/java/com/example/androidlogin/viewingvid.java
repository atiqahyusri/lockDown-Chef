package com.example.androidlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class viewingvid extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewingvid);

        MediaController mediaController = new MediaController(this);

        videoView = findViewById(R.id.vid);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/uploadvideo-4a45e.appspot.com/o/videos%2F1595313676933.qt?alt=media&token=13d613e9-b5b9-4136-9b4f-205dc898f752");//video url
        videoView.setVideoURI(uri);
        videoView.start();
    }
}