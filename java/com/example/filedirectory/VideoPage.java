package com.example.filedirectory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoPage extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    ListView listView;
    VideoView videoView;
    ImageView image_back;

    String[] listValue = new String[]
            {
                    "nature.mp4",
                    "waterfall.mp4"
            };

    List<String> LISTSTRING;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_page);

        listView = (ListView)findViewById(R.id.listView1);

        image_back = (ImageView)findViewById(R.id.back_image);
        image_back.setClickable(true);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        LISTSTRING = new ArrayList<String>(Arrays.asList(listValue));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, android.R.id.text1, LISTSTRING){

            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                View view = super.getView(position, convertView, parent);

                TextView ListItemShow = (TextView) view.findViewById(android.R.id.text1);

                ListItemShow.setTextColor(Color.parseColor("#FFFFFF"));

                return view;
            }

        };

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String  itemValue    = (String) listView.getItemAtPosition(i);
                Toast.makeText(VideoPage.this, " " +itemValue , Toast.LENGTH_LONG).show();

                /*Intent intent = new Intent(VideoPage.this,VideoPlay.class );
                intent.putExtra(Intent.EXTRA_TEXT, itemValue);
                startActivity(intent);*/
                videoView= findViewById(R.id.videoView1);
                switch (i) {
                    case 0:
                        videoView.setVideoURI(Uri.parse("android.resource://" +  getPackageName() + "/" + R.raw.nature));
                        break;
                    case 1:
                        videoView.setVideoURI(Uri.parse("android.resource://" +  getPackageName() + "/" + R.raw.waterfall));
                        break;
                    default:
                        break;
                }
                videoView.setMediaController(new MediaController(VideoPage.this));
                videoView.requestFocus();
                videoView.start();

            }
        });
    }


}