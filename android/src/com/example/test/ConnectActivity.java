package com.example.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConnectActivity extends NoTitleActivity {

	private EditText txtIP;
	private EditText txtPort;
	private Button btnConnect;
	private WebView web;
	private Button btnCancel;
	
	private AsyncTask<String, View, Boolean> task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connect);
		
		// find view
		txtIP = (EditText) findViewById(R.id.txtIP);
		txtPort = (EditText) findViewById(R.id.txtPort);
		web = (WebView) findViewById(R.id.web);
		btnConnect = (Button) findViewById(R.id.btnConnect);
		btnCancel = (Button) findViewById(R.id.btncCancel);
		
		// loading image
		web.setVisibility(View.INVISIBLE);
		web.loadUrl("file:///android_asset/loading.gif");
		
		// bind event
		btnConnect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				disable();
				task = new CheckConnectTask();
				task.execute();
			}
		});
		
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cancel();
			}
		});
		
		
	}
	
	// 取消
	private void cancel() {
		if (btnConnect.isEnabled()) {
			finish();
		} else {
			task.cancel(true);
			enable();
		}
	}

	// 禁用按钮
	private void disable() {
		web.setVisibility(View.VISIBLE);
		btnConnect.setEnabled(false);
		txtIP.setEnabled(false);
		txtPort.setEnabled(false);
	}
	
	// 启用按钮
	private void enable() {
		web.setVisibility(View.INVISIBLE);
		btnConnect.setEnabled(true);
		txtIP.setEnabled(true);
		txtPort.setEnabled(true);
	}


	// 检查连接任务
	class CheckConnectTask extends AsyncTask<String, View, Boolean> {
		Socket s = new Socket();
		
		@Override
		protected Boolean doInBackground(String... params) {
			// 验证
			String ip = txtIP.getText().toString();
			int port = Integer.valueOf(txtPort.getText().toString());
			try {
				s.connect(new InetSocketAddress(ip, port), 3000);
				s.close();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			enable();
			if (result) {
				Toast.makeText(ConnectActivity.this, "链接成功", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(ConnectActivity.this, TouchActivity.class);
				intent.putExtra("ip", txtIP.getText().toString());
				intent.putExtra("port", Integer.valueOf(txtPort.getText().toString()));
				startActivity(intent);
			} else {
				Toast.makeText(ConnectActivity.this, "链接" + txtIP.getText().toString() + "失败", Toast.LENGTH_SHORT).show();
			}
		}
		
		@Override
		protected void onCancelled() {
			super.onCancelled();
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	
}



