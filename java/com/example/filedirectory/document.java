package com.example.filedirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class document extends AppCompatActivity {
    ImageView wordView, pptView, pdfView, excelView, textView, image_back, create;
    ListView listView;
    String[] wordValue = new String[]
            {
                    "Syllabus.docx",
                    "Topics.docx"
            };
    String[] pptValue = new String[]
            {
                    "Android.ppt",
                    "Movies.ppt"
            };
    String[] pdfValue = new String[]
            {
                    "Holidays.pdf",
                    "Shops.pdf"
            };
    String[] textValue = new String[]
            {
                    "Songs.txt",
                    "Names.txt"
            };
    String[] excelValue = new String[]
            {
                    "Documents.xls",
                    "Books.xls"
            };
    List<String> LISTSTRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);

        wordView = (ImageView) findViewById(R.id.word_image);
        pptView = (ImageView) findViewById(R.id.ppt_image);
        pdfView = (ImageView) findViewById(R.id.pdf_image);
        excelView = (ImageView) findViewById(R.id.excel_image);
        textView = (ImageView) findViewById(R.id.text_image);
        create = (ImageView)findViewById(R.id.create_doc_image);

        wordView.setClickable(true);
        pptView.setClickable(true);
        create.setClickable(true);
        pdfView.setClickable(true);
        excelView.setClickable(true);
        textView.setClickable(true);

        listView = (ListView)findViewById(R.id.listView1);

        image_back = (ImageView)findViewById(R.id.back_image);
        image_back.setClickable(true);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        wordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LISTSTRING = new ArrayList<String>(Arrays.asList(wordValue));
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(document.this,android.R.layout.simple_list_item_2, android.R.id.text1, LISTSTRING){

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

                        // Show Alert
                        Toast.makeText(document.this, " " +itemValue , Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        pptView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LISTSTRING = new ArrayList<String>(Arrays.asList(pptValue));
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(document.this,android.R.layout.simple_list_item_2, android.R.id.text1, LISTSTRING){

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

                        // Show Alert
                        Toast.makeText(document.this, " " +itemValue , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        pdfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LISTSTRING = new ArrayList<String>(Arrays.asList(pdfValue));
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(document.this,android.R.layout.simple_list_item_2, android.R.id.text1, LISTSTRING){

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

                        // Show Alert
                        Toast.makeText(document.this, " " +itemValue , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        excelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LISTSTRING = new ArrayList<String>(Arrays.asList(excelValue));
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(document.this,android.R.layout.simple_list_item_2, android.R.id.text1, LISTSTRING){

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

                        // Show Alert
                        Toast.makeText(document.this, " " +itemValue , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LISTSTRING = new ArrayList<String>(Arrays.asList(textValue));
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(document.this,android.R.layout.simple_list_item_2, android.R.id.text1, LISTSTRING){

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

                        // Show Alert
                        Toast.makeText(document.this, " " +itemValue , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(document.this, TextEditor.class);
                startActivity(intent);
            }
        });
    }

}