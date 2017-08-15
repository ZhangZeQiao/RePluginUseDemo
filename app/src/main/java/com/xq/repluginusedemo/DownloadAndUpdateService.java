package com.xq.repluginusedemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.qihoo360.replugin.RePlugin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 小侨
 * @time 2017/8/11  10:24
 * @desc 更新插件
 */

public class DownloadAndUpdateService extends IntentService {

    public DownloadAndUpdateService() {
        // 实现父类的构造方法，用于命名工作线程，只用于调试。
        super("DownloadAndUpdateService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Intent是从Activity发过来的，携带识别参数，根据参数不同执行不同的任务
        // 插件下载地址
        String urlPath = intent.getStringExtra("urlPath");
        // 插件下载后的存放路径
        String downloadDir = intent.getStringExtra("downloadDir");

        File file = null;
        try {
            // 统一资源
            URL url = new URL(urlPath);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();

            // 文件大小
            int fileLength = httpURLConnection.getContentLength();
            // 文件名
            String filePathUrl = httpURLConnection.getURL().getFile();
            String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);

            URLConnection con = url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());

            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
                // 下载百分比
                Log.v("xq", "下载了-------> " + len * 100 / fileLength);
            }
            bin.close();
            out.close();
            // 升级安装插件新版本
            RePlugin.install(path);
            Log.v("xq", "下载完成 : " + path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
