package com.example.michael.teach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] values = new String[] {
                "SharedPreferences",
                "FileRead",
                "SQLite",
                "伺服器資料庫"
        };
        final Class[] activity=new Class[]{
                SharedPreferencesActivity.class,
                FileActivity.class,
                SQLiteActivity.class,
                JsonActivity.class
        };
        listView=(ListView)findViewById(R.id.mylistview) ;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                Intent gonext=new Intent();
                gonext.setClass(MainActivity.this, activity[position]);
                startActivity(gonext);
            }

        });
    }
}
