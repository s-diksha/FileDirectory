package com.example.filedirectory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Locale;

public class TextEditor extends AppCompatActivity {

    ImageView newImg, edit, search, undo, mic1, vol, save;
    EditText file_name, content1;
    TextToSpeech textToSpeech;
    String textname,textcontent;

    private final int REQ_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_editor);

        ActivityCompat.requestPermissions(TextEditor.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR)
                {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        newImg = (ImageView)findViewById(R.id.add_image);
        edit = (ImageView)findViewById(R.id.edit_image);
        search = (ImageView)findViewById(R.id.search_image);
        save = (ImageView)findViewById(R.id.save_image);
        undo  =(ImageView)findViewById(R.id.undo_image);
        mic1 = (ImageView)findViewById(R.id.voice_image);
        vol = (ImageView)findViewById(R.id.volumne_image);

        vol.setClickable(true);
        file_name = (EditText) findViewById(R.id.filename_text);
        content1 = (EditText)findViewById(R.id.content_text);

        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak=content1.getText().toString();
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onPause()
    {
        if(textToSpeech != null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }
    /*
        public void voice1(View view)
        {
            String toSpeak=content1.getText().toString();
            textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
        }
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    content1.append(result.get(0).toString());
                }
                break;
            }
        }
    }

    public void create_pdf(View view)
    {
        File externalFile;
        textname=file_name.getText().toString();
        textcontent=content1.getText().toString();

        try {
            File myFile = new File(Environment.getExternalStorageDirectory().getPath()+"/Documents/"+textname+".txt");
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(textcontent);
            myOutWriter.close();
            fOut.close();
            Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void newpdf(View view)
    {
        file_name.setText("");
        content1.setText("");
    }
    public void speech1(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");
        try {
            startActivityForResult(intent, REQ_CODE);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Sorry your device not supported",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void readpdf(View view) {
        textname=file_name.getText().toString();
        try
        {
            File file=new File(Environment.getExternalStorageDirectory().getPath()+"/Documents/" +textname+".txt");

            String readstr;
            FileInputStream fis=new FileInputStream(file);
            readstr= String.valueOf(fis.read());

            content1.setText(readstr);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

