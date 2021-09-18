package com.example.filedirectory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import static android.content.Intent.ACTION_SEND;


public class PhotoPage extends AppCompatActivity {
    ImageView image_back;
    Button b;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_page);

        b= (Button)findViewById(R.id.button11);

        ActivityCompat.requestPermissions(PhotoPage.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        image_back = (ImageView)findViewById(R.id.back_image);
        image_back.setClickable(true);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent i = getIntent();
        uri = i.getParcelableExtra("uri");
        image_back.setImageURI(uri);
    }

    public void shareImage(View view)
    {
        try {
            File f1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Documents/hi.txt");
            Uri myUri = Uri.parse(f1.getAbsolutePath());
            Intent i1 = new Intent(Intent.ACTION_SEND);
            i1.setType("text/plain");
            i1.putExtra(Intent.EXTRA_SUBJECT, "Sharing Product");
            i1.putExtra(Intent.EXTRA_STREAM, myUri);
            startActivity(Intent.createChooser(i1, "Share URL"));
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }
}
