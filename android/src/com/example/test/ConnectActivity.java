package com.example.test;

import java.net.Socket;
import java.util.concurrent.ExecutionException;

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
		final EditText text = (EditText) findViewById(R.id.txtIP);
		Button btn = (Button) findViewById(R.id.btnConnect);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
					AsyncTask<String, View, Boolean> task = new AsyncTask<String, View, Boolean>(){
						@Override
						protected Boolean doInBackground(String... params) {
							String ip = params[0];
							try {
								Socket s = new Socket(ip, 5230);
								s.close();
								return true;
							} catch (Exception e) {
								e.printStackTrace();
								return false;
							}
						}
					};
					task.execute(text.getText().toString());
					try {
						Boolean isConnect = task.get();
						if (isConnect) {
							Toast.makeText(ConnectActivity.this, "链接成功", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(ConnectActivity.this, TouchActivity.class);
							intent.putExtra("ip", text.getText().toString());
							startActivity(intent);
						} else {
							Toast.makeText(ConnectActivity.this, "链接" + text.getText().toString() + "失败", Toast.LENGTH_SHORT).show();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		});
	}

}
