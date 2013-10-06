package com.example.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImgActivity extends Activity {

	boolean flag = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		LinearLayout layout = new LinearLayout(this);
		setContentView(layout);
		
		// 显示或隐藏图片
		
		final ImageView view = new ImageView(this);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		view.setBackgroundColor(Color.YELLOW);
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag) {
					view.setImageDrawable(null);
					flag = false;
				} else {
					view.setImageResource(R.drawable.ic_launcher);
					flag = true;
				}
				
			}
		});
		layout.addView(view);
		
	}
}
