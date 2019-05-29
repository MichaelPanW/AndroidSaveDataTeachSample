package com.example.michael.teach;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FileActivity extends AppCompatActivity {
    EditText edittext;
    String project="kcbs";
    String def="";
    String key="word";
    File outFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        edittext=(EditText)findViewById(R.id.editText);
        Button button=(Button)findViewById(R.id.button);
        Button read=(Button)findViewById(R.id.button2);
        Context context=this;
        File dir = context.getFilesDir();
        //在該目錄底下開啟或建立檔名為 "test.txt" 的檔案
        outFile = new File(dir, "test.txt");
        String data = readFromFile(outFile);
        edittext.setText( data);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToFile(outFile, edittext.getText().toString());
                Toast.makeText(FileActivity.this,"儲存成功",Toast.LENGTH_SHORT).show();
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = readFromFile(outFile);
                edittext.setText( data);
                Toast.makeText(FileActivity.this,"讀取成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //writeToFile 方法如下
    private void writeToFile(File fout, String data) {
        FileOutputStream osw = null;
        try {
            osw = new FileOutputStream(fout);
            osw.write(data.getBytes());
            osw.flush();
        } catch (Exception e) {
            ;
        } finally {
            try {
                osw.close();
            } catch (Exception e) {
                ;
            }
        }
    }
    private String readFromFile(File fin) {
        StringBuilder data = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fin), "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
        } catch (Exception e) {
            ;
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                ;
            }
        }
        return data.toString();
    }

}
