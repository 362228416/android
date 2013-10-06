package com.example.test;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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
		
//		HttpClient client = AndroidHttpClient.newInstance("");
//		try {
			//HttpResponse response = client.execute(new HttpGet("http://d.hiphotos.baidu.com/album/w%3D230/sign=1847e71f63d9f2d3201123ec99ed8a53/d8f9d72a6059252db799c5fa359b033b5ab5b946.jpg"));
			//HttpResponse response = client.execute(new HttpGet("http://www.baidu.com/"));
			
//			URL url = new URL("http://www.baidu.com");
//			URLConnection conn = url.openConnection();
//			Object content = conn.getContent();
//			System.out.println(content);
//			
			
			//System.out.println(response.getEntity().isStreaming());
			
//			InputStream in = response.getEntity().getContent();
//			File file = new File("tt.jpg");
//			FileOutputStream fos = new FileOutputStream(file);
//			int b = 0;
//			while ((b = in.read()) != -1) {
//				fos.write(b);
//			}
//			fos.close();
//			in.close();
//			
//			img.setImageURI(Uri.fromFile(file));
			
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		try {
//			
//			RestTemplate rest = new RestTemplate();
//			String result = rest.getForObject("http://www.baidu.com", String.class);
//			Toast.makeText(this, result, Toast.LENGTH_LONG).show();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		

		
		Intent da = new Intent(this, DrawActivity.class);	// huadian 
		startActivity(da);
		
		
		
		/*
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
		*/
		
		
		
		
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
