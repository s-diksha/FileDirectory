package com.example.filedirectory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import static android.content.Intent.ACTION_SEND;

public class MusicPage extends AppCompatActivity {
    ImageView image_back;
    ListView listView;
    String  itemValue;
    ArrayList<File> ListFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_page);

        ActivityCompat.requestPermissions(MusicPage.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        File f = new File(Environment.getExternalStorageDirectory().getPath() + "/");
        ListFile = listmusic(f);

        listView = (ListView) findViewById(R.id.listView1);

        image_back = (ImageView) findViewById(R.id.back_image);
        image_back.setClickable(true);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        List<HashMap<String, String>> aList = new ArrayList<>();


        for(int i=0;i<ListFile.size();i++)
        {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("txt", ListFile.get(i).getName().toString());
            hm.put("img", Integer.toString(R.drawable.icons2));
            aList.add(hm);
        }

        String[] from = {"img", "txt"};

        int[] to = {R.id.imageView1, R.id.title1};
        SimpleAdapter adapter1 = new SimpleAdapter(getBaseContext(), aList, R.layout.activity_list_view, from, to);
        listView.setAdapter(adapter1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemValue =ListFile.get(i).getAbsolutePath();

               // startActivity(new Intent(getApplicationContext(),MusicPlay.class).putExtra("song",ListFile.get(i).getPath()));
                // videoView.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory().getPath()+ListFile.get(i).getPath()));
                //Toast.makeText(VideoPage.this, " " +itemValue , Toast.LENGTH_LONG).show();

            }
        });

        registerForContextMenu(listView);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.onclick_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.option1:
                //Toast.makeText(this, "Share selected", Toast.LENGTH_SHORT).show();
                //return true;
                File file = new File(itemValue);
                Intent i  = new Intent(ACTION_SEND);
                Uri myUri = FileProvider.getUriForFile(this,getApplicationContext().getPackageName() + ".provider",file);
                i.setDataAndType(Uri.parse(itemValue), "audio/*");
                i.putExtra(Intent.EXTRA_STREAM, myUri);
                startActivity(i);
                return true;

            case R.id.option2:
                File f1 = new File(itemValue);
                f1.delete();
                return true;

             default:
                 return super.onContextItemSelected(item);
        }
    }

    public ArrayList listmusic(File root) {
        ArrayList<File> ListFile = new ArrayList<>();
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                ListFile.addAll(listmusic(file));
            } else {
                if (file.getName().contains(".mp3") || file.getName().contains(".MP3")) {
                    ListFile.add(file);
                }
            }

        }
        return ListFile;
    }
}