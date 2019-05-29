package com.example.michael.teach;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class SQLiteActivity extends AppCompatActivity {
    EditText edittext;
    ListView show;
    SQLiteDatabaseHelper sqlDH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_read_add);
        edittext=(EditText)findViewById(R.id.editText);
        sqlDH=new SQLiteDatabaseHelper(this);
        Button button=(Button)findViewById(R.id.button);
        show=(ListView)findViewById(R.id.showText);
        showAllList(sqlDH.select());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDH.insert(edittext.getText().toString());
                edittext.setText("");
                showAllList(sqlDH.select());
                Toast.makeText(SQLiteActivity.this,"儲存成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
    void showAllList(Cursor myCursor){
        SimpleCursorAdapter adpater=new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, myCursor,
                new String[]{MemberDao.Schema.Columns.Name},
                new int[] { android.R.id.text1 });
        show.setAdapter(adpater);
    }
}
