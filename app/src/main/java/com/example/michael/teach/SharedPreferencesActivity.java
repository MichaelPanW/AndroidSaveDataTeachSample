package com.example.michael.teach;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreferencesActivity extends AppCompatActivity {
    EditText edittext;
    String project="kcbs";
    String def="";
    String key="word";
    android.content.SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        edittext=(EditText)findViewById(R.id.editText);
        Button button=(Button)findViewById(R.id.button);
        Button read=(Button)findViewById(R.id.button2);

        settings = SharedPreferencesActivity.this.getSharedPreferences(
                project, Context.MODE_PRIVATE);
        edittext.setText( settings.getString(key, def));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings = SharedPreferencesActivity.this.getSharedPreferences(
                        project, Context.MODE_PRIVATE);
                android.content.SharedPreferences.Editor editor = settings.edit();
                editor.putString("word", edittext.getText().toString()).commit();
                Toast.makeText(SharedPreferencesActivity.this,"儲存成功",Toast.LENGTH_SHORT).show();
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittext.setText( settings.getString(key, def));
                Toast.makeText(SharedPreferencesActivity.this,"讀取成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
