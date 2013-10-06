package com.example.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.GestureStore;
import android.gesture.Prediction;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.widget.Toast;

public class TouchTestActivity extends NoTitleActivity {
	
	GestureLibrary library;

	//@Override
	protected void onCreatem(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touch_test);
		
		try {
//			GestureLi
			File file = new File(Environment.getExternalStorageDirectory().getPath() + "/code/ss");
			library = GestureLibraries.fromFile(file);
			if (!library.load()) {
				if (!file.exists()) file.createNewFile();
				new GestureStore().save(new FileOutputStream(file));
			}
			if (library.load()) {
				Toast.makeText(this, "加载成功", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "加载失败", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		GestureOverlayView view = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
		view.addOnGesturePerformedListener(new OnGesturePerformedListener() {
			@Override
			public void onGesturePerformed(GestureOverlayView v, Gesture g) {
				// TODO Auto-generated method stub

				if (library.getGestureEntries().size() > 0) {
//					Toast.makeText(TouchTestActivity.this, "搜索手势", Toast.LENGTH_SHORT).show();
					ArrayList<Prediction> list = library.recognize(g);
					boolean has = false;
					for (Prediction p : list) {
						System.out.println(p.name + " : " + p.score);
						if (p.score > 2) {
							has = true;
							Toast.makeText(TouchTestActivity.this, "识别成功，手势：  " + p.name, Toast.LENGTH_SHORT).show();
						}
					}
					if (!has) {
						Toast.makeText(TouchTestActivity.this, "识别成功失败 ", Toast.LENGTH_SHORT).show();
					}
				} else {
					library.addGesture("toRight", g);
					if (library.save()) {
						Toast.makeText(TouchTestActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(TouchTestActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
					}
					
				}
				
				
			}
		});
		
		
		
	}
	
	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(e);
		
//		System.out.println(e.getPointerCount() + " - " + e.getAction() + " - " + e.getHistorySize());
		
//		int count = e.getPointerCount();
//		switch(count) {
//		case 1:
//			System.out.println("一指");
//			break;
//		case 2:
////			int x = (int) (e.getX() - e.getX(1));
////			int y = (int) (e.getY() - e.getY(1));
////			System.out.println("双指 x = " + x + " , y = " + y);
//			
////			System.out.println("双指: " + e.getY() + " - " + e.getY(0) + " - " + e.getY(1));
//			
////			System.out.println("双指: " + e.getY() + " - " + e.getY(1));
//			
////			System.out.println(e.getHistoricalY(0) + " - " + e.getHistoricalY(1));
//			
////			System.out.println("双指: " + (e.getY(1) - e.getY() > 0 ? "下" : "上"));
//			
////			System.out.println(e.getOrientation() + " - " + e.getOrientation(1));
////			System.out.println(e.getAction());
//			if (e.getHistorySize() > 0) {
////				System.out.println("双指: " + (e.getX() - e.getHistoricalX(0) > 0 ? "右" : "左"));
////				System.out.println("双指: " + (e.getY() - e.getHistoricalY(0) > 0 ? "下" : "上"));
//				
////				System.out.println(e.getAxisValue(MotionEvent.AXIS_X) + " - " + e.getAxisValue(MotionEvent.AXIS_Y));
////				System.out.println(e.get);
//				
//			}
//			
//			break;
//		case 3:
//			System.out.println("三指");
//			break;
//		}
//		
//		return true;
	}
}
