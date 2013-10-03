package com.example.test;

import com.example.test.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView username;
	TextView password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		username = (TextView) findViewById(R.id.username);
		password = (TextView) findViewById(R.id.password);
		
		Button btnCannel = (Button) findViewById(R.id.btnCannel);
		Button btnLogin = (Button) findViewById(R.id.btnLogin);
		btnCannel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				login();
			}
		});
		
	}
	
	@SuppressLint("NewApi")
	void login() {
		
		
		String uid = username.getText().toString();
		String pwd = password.getText().toString();
		if (uid.equals(pwd)) {
			 Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show();
			 Intent intent = new Intent(this, AppActivity.class);
			 intent.putExtra("uid", uid);
			 intent.putExtra("pwd", pwd);
			 intent.putExtra("msg", "login success!");
			 startActivity(intent);
		} else {
			Toast.makeText(this, "Login Fails", Toast.LENGTH_LONG).show();
		}
		
		
		
		/*
		if ("admin".equals(uid) && "admin".equals(pwd)) {
			 Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "Login Fails", Toast.LENGTH_LONG).show();
		}
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
