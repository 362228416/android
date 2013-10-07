package com.example.test;

import java.net.Socket;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConnectActivity extends NoTitleActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connect);
		final EditText txtIP = (EditText) findViewById(R.id.txtIP);
		final EditText txtPort = (EditText) findViewById(R.id.txtPort);
		
		Button btn = (Button) findViewById(R.id.btnConnect);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				// 监测是否能链接
				AsyncTask<String, View, Boolean> task = new AsyncTask<String, View, Boolean>(){
					@Override
					protected Boolean doInBackground(String... params) {
						String ip = params[0];
						int port = Integer.valueOf(params[1]);
						try {
							Socket s = new Socket(ip, port);
							s.close();
							return true;
						} catch (Exception e) {
							e.printStackTrace();
							return false;
						}
					}
				};
				task.execute(txtIP.getText().toString(), txtPort.getText().toString());
				try {
					Boolean isConnect = task.get();
					// 链接成功调整到触摸板
					if (isConnect) {
						Toast.makeText(ConnectActivity.this, "链接成功", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(ConnectActivity.this, TouchActivity.class);
						intent.putExtra("ip", txtIP.getText().toString());
						intent.putExtra("port", Integer.valueOf(txtPort.getText().toString()));
						startActivity(intent);
					} else {
						Toast.makeText(ConnectActivity.this, "链接" + txtIP.getText().toString() + "失败", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					Toast.makeText(ConnectActivity.this, "链接" + txtIP.getText().toString() + "失败", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}

}
