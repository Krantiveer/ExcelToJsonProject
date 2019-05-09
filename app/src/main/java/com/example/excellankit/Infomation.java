package com.example.excellankit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class Infomation extends AppCompatActivity {
    private static final int REQUEST_CODE=43;
    Button select;
    String filepath;

    public final static String MESSAGE_KEY ="616";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        select=findViewById(R.id.select);
        //path=findViewById(R.id.path);
        //uriText=findViewById(R.id.uri);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processFile();
            }
        });
        int a= 10;
        //path.setText(a);
    }
    private void processFile(){
        FileChooser fileChooser = new FileChooser(Infomation.this);

        fileChooser.setFileListener(new FileChooser.FileSelectedListener() {
            @Override
            public void fileSelected(final File file) {
                // ....do something with the file
                String filename = file.getAbsolutePath();
                filepath=filename;

                Log.i("FileName", filename);


                // then actually do something in another module

            }
        });
// Set up and filter my extension I am looking for
        //fileChooser.setExtension("pdf");
        fileChooser.showDialog();

    }


    public void sendmessage(View view) {


//        String message = message_text.getText().toString();
        String message=filepath;
        Intent intent= new Intent(Infomation.this ,MainActivity.class);

        intent.putExtra(MESSAGE_KEY,message);

        startActivity(intent);
    }
}
