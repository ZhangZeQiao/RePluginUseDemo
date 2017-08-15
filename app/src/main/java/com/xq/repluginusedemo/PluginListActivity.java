package com.xq.repluginusedemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qihoo360.replugin.RePlugin;

/**
 * @author 小侨
 * @time 2017/8/15  20:59
 * @desc ${TODD}
 */

public class PluginListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_list);
    }

    public void onDownloadTextPlugin(View view) {
        // 插件下载地址
        String urlPath = "https://raw.githubusercontent.com/ZhangZeQiao/ImagePluginDemo/1c317ce94b0a0bb0378f284446bfae7c80c39f52/app/src/main/res/raw/text.apk";
        // 插件下载后的存放路径
        String downloadDir = Environment.getExternalStorageDirectory().getAbsolutePath();

        Intent intent = new Intent(this, DownloadAndUpdateService.class);
        intent.putExtra("urlPath", urlPath);
        intent.putExtra("downloadDir", downloadDir);
        startService(intent);
    }

    public void onOpenTextPlugin(View view) {
        // 打开一个插件的Activity
        RePlugin.startActivity(PluginListActivity.this,
                RePlugin.createIntent("text", "com.xq.textplugindemo.MainActivity"));
    }

    public void onUpdateTextPlugin(View view) {
        // 插件下载地址
        String urlPath = "https://raw.githubusercontent.com/ZhangZeQiao/ImagePluginDemo/631acc2415b86f62b6e50e6faa3c8b78bac04980/app/src/main/assets/text.apk";
        // 插件下载后的存放路径
        String downloadDir = Environment.getExternalStorageDirectory().getAbsolutePath();

        Intent intent = new Intent(this, DownloadAndUpdateService.class);
        intent.putExtra("urlPath", urlPath);
        intent.putExtra("downloadDir", downloadDir);
        startService(intent);
    }

    public void unUpdateTextPlugin(View view) {
        RePlugin.uninstall("text");
    }
}
