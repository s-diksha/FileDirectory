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
import android.speech.tts.TextToSpeech.Engine;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Locale;


public class Trial extends AppCompatActivity {

    ImageView newImg, edit, search, undo, mic1, vol, save;
    EditText file_name, content;
    TextToSpeech t1;
    String pdfname,pdfcontent;

    private final int REQ_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);
        ActivityCompat.requestPermissions(Trial.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);


        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
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


        file_name = (EditText) findViewById(R.id.filename_text);
        content = (EditText)findViewById(R.id.content_text);
    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }

    public void voice1(View view)
    {
        String toSpeak1 = content.getText().toString();

        t1.speak(toSpeak1, TextToSpeech.QUEUE_FLUSH,null);

        Toast.makeText(getApplicationContext(),toSpeak1,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    content.append(result.get(0).toString());
                }
                break;
            }
        }
    }
    public void create_pdf(View view)
    {
        pdfname=file_name.getText().toString();
        pdfcontent=content.getText().toString();


        PdfDocument mypdfdocument=new PdfDocument();
        PdfDocument.PageInfo mypageInfo=new PdfDocument.PageInfo.Builder(300,600,1).create();
        PdfDocument.Page mypage=mypdfdocument.startPage(mypageInfo);

        Paint mypaint=new Paint();

        int x=10,y=25;

        for(String line:pdfcontent.split("\n"))
        {
            mypage.getCanvas().drawText(line,x,y,mypaint);
            y+=mypaint.descent()-mypaint.ascent();

        }
        mypdfdocument.finishPage(mypage);

        String mypdfpath= Environment.getExternalStorageDirectory().getPath()+"/Documents/" +pdfname+".pdf";
        File myfile=new File(mypdfpath);

        try
        {
            mypdfdocument.writeTo(new FileOutputStream(myfile));
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();

        }
        mypdfdocument.close();
    }

    public void newpdf(View view)
    {
        file_name.setText("");
        content.setText("");
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
        pdfname=file_name.getText().toString();

        File file=new File(Environment.getExternalStorageDirectory().getPath()+"/Documents/" +pdfname+".pdf");

        String readstr;
        try
        {
            PdfReader pdfReader=new PdfReader(file.getPath());
            readstr= PdfTextExtractor.getTextFromPage(pdfReader,1).trim();
            pdfReader.close();
            content.setText(readstr);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
