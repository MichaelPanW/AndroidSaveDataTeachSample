package com.example.michael.teach;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonActivity extends AppCompatActivity {

    EditText edittext;
    ListView show;
    //RequestQueue mQueue;

    private SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_read_add);
        edittext=(EditText)findViewById(R.id.editText);
        Button button=(Button)findViewById(R.id.button);

        show=(ListView)findViewById(R.id.showText);
        //mQueue = Volley.newRequestQueue(JsonActivity.this);
        //linkdb();
        new Thread(runnable).start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(runnable).start();
                Toast.makeText(JsonActivity.this,"儲存成功",Toast.LENGTH_SHORT).show();
            }
        });

    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            String[] values=null;
            Log.e("getRequest",val);
            try {
                JSONArray jsonData= new JSONArray(val);
                values=new String[jsonData.length()];
                for(int i=0;i<jsonData.length();i++){
                    JSONObject itemData= new JSONObject((jsonData.get(i).toString()));
                    values[i]=itemData.getString("text");
                }
                //帶入對應資料
            } catch (JSONException e) {
                e.printStackTrace();
                values=new String[0];
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(JsonActivity.this,
                    android.R.layout.simple_list_item_1, values);
            show.setAdapter(adapter);
            edittext.setText("");

        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO: http request.
            String getRequest=linkdb(
                    "http://ielder.im.nuu.edu.tw/touchtravel/testteachsample.php?name="+
                            edittext.getText());
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", getRequest);
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };
    public String linkdb(String codeURL){
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(codeURL);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            return readStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
        }
        finally {
            urlConnection.disconnect();
        }
        return  "";
    }
    String readStream(InputStream inputStream) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        for (String line; (line = r.readLine()) != null; ) {
            total.append(line).append('\n');
        }
        return total.toString();
    }

}
