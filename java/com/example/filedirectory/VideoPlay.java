package com.example.filedirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class VideoPlay extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        Intent intent = getIntent();
        String text1 = intent.getStringExtra(VideoPage.EXTRA_TEXT);

        videoView= findViewById(R.id.videoView1);
        videoView.setVideoURI(Uri.parse("android.resource://" +  getPackageName() + "/" + R.raw.nature));

    }
}
