package com.example.my;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AndroidtoJs extends Object {

	Context context;


    public AndroidtoJs(Context context) {
		super();
		this.context = context;

	}
	// 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public String loadJson(String url) {
		String jsonFile=getJSONFile(context,url);
		return jsonFile;
    }


	private String getJSONFile(Context context,String file) {
		//1.	创建字符串存储对象StringBuilder
		StringBuilder stringBuilder=new StringBuilder();
		//2.	获得AssetManager对象context.getAssets();方式来获取
		AssetManager assetManager=context.getAssets();
		try {
			//3.	通过AssetManager对象的open方法获得输入流对象，绑定字符集urf-8
			InputStreamReader inputStreamReader=new InputStreamReader(assetManager.open(file),"utf-8");
			//4.	创建BufferedReader对象绑定输入流对象
			BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
			String line="";
			//5.	循环遍历读取出文件内的数据存储到StringBuilder内
			while ((line=bufferedReader.readLine())!=null){
				stringBuilder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//6.	返回StringBuilder.toString();则能获取到文件内所有的JSON数据了；
		return stringBuilder.toString();
	}

    }