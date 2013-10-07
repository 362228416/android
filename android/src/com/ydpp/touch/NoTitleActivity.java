package com.ydpp.touch;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class NoTitleActivity extends Activity {
	
	LinearLayout layout;
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,    
        WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		layout = new LinearLayout(this);
		setContentView(layout);
	};
	
}
