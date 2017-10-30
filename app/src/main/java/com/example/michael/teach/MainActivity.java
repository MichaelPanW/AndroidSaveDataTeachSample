package com.example.michael.teach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(MainActivity.this);
        ImageLoader.getInstance().init(configuration);

        String url="https://www.kpopn.com/wp-content/uploads/2016/08/20160825-momo.jpg";
        ImageView img=(ImageView)findViewById(R.id.imageView);
        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        imageLoader.displayImage(url, img);

    }
}
