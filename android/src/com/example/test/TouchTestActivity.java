package com.example.test;

import android.app.Activity;
import android.view.MotionEvent;

public class TouchTestActivity extends Activity {

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
//		super.onTouchEvent(event);
		
		System.out.println(event.getPointerCount());
		
		
		return true;
	}
}
