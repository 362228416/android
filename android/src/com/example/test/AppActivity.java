package com.example.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AppActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView txtMsg = (TextView) findViewById(R.id.msg);
		Intent intent = getIntent();
		txtMsg.setText("uid:" + intent.getStringExtra("uid") + "\npwd:"
				+ intent.getStringExtra("pwd") + "\nmsg:"
				+ intent.getStringExtra("msg"));
		
		
//		ImageView img = new ImageView(this);
		
		
	}
	
	
	
	
}
