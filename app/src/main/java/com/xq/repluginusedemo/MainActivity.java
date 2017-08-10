package com.xq.repluginusedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qihoo360.replugin.RePlugin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onJumpToImagePlugin(View view) {
        // 打开一个插件的Activity
        RePlugin.startActivity(MainActivity.this,
                RePlugin.createIntent("image", "com.xq.imageplugindemo.MainActivity"));

    }
}
