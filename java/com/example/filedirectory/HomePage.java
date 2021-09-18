package com.example.filedirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity {

    ImageView docView, photoView, musicView, apkView, downloadView, videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        docView = (ImageView)findViewById(R.id.doc_image);
        photoView  =(ImageView)findViewById(R.id.galary_image);
        musicView = (ImageView)findViewById(R.id.music_image);
        apkView = (ImageView)findViewById(R.id.apk_image);
        downloadView = (ImageView)findViewById(R.id.download_image);
        videoView = (ImageView)findViewById(R.id.video_image);

        docView.setClickable(true);
        photoView.setClickable(true);
        musicView.setClickable(true);
        apkView.setClickable(true);
        downloadView.setClickable(true);
        videoView.setClickable(true);

        docView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,document.class);
                startActivity(i);
            }
        });

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,PhotoPage.class);
                startActivity(i);
            }
        });

        musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,MusicPage.class);
                startActivity(i);
            }
        });

        apkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,APKPage.class);
                startActivity(i);
            }
        });

        downloadView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,DownloadsPage.class);
                startActivity(i);
            }
        });

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,VideoPage.class);
                startActivity(i);
            }
        });

    }
}
