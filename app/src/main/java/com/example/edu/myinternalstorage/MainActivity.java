package com.example.edu.myinternalstorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonFileReadFromInner = findViewById(R.id.buttonFileReadFromInner);
        buttonFileReadFromInner.setOnClickListener(this);
        Button buttonFileWriteFromInner = findViewById(R.id.buttonFileWriteFromInner);
        buttonFileWriteFromInner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText editTextInput = findViewById(R.id.editTextInput);
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream =null;
        switch (v.getId()){
            case R.id.buttonFileReadFromInner:
                try {
                    fileInputStream = openFileInput("test.txt");
                    byte[] buffer = new byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    editTextInput.setText(new String(buffer));
                    fileInputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }break;


            case R.id.buttonFileWriteFromInner:
                try {
                    fileOutputStream = openFileOutput("test.txt", Context.MODE_APPEND);
                    fileOutputStream.write(editTextInput.getText().toString().getBytes());
                    editTextInput.setText("");
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }break;
        }
    }
}
