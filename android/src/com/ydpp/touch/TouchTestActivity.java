package com.ydpp.touch;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.gesture.GestureLibrary;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class TouchTestActivity extends NoTitleActivity {
	
	GestureLibrary library;

	//@Override
	protected void onCreatem(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touch_test);
		/*
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
		*/
		
		
		
		
		
		
		
		
	}
	
	
	float px1, py1, px2, py2, px3, py3;
	
	boolean longPressed = false;
	
	long downTime = 0;
	
	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		// TODO Auto-generated method stub
//		return super.onTouchEvent(e);
		
//		System.out.println(e.getPointerCount() + " - " + e.getAction() + " - " + e.getHistorySize());
		
//		System.out.println(e.getAction() + " - " + e.getEventTime());
		
		int count = e.getPointerCount();
		float x = e.getX();
		float y = e.getY();
		switch(count) {
		case 1:
//			System.out.println(e.getDownTime());
//			System.out.println();
			
			// TODO　单指滑动
			if (e.getAction() == MotionEvent.ACTION_MOVE) {
				System.out.println("滑动");
			}
			if (e.getAction() == MotionEvent.ACTION_UP) {
				longPressed = false;
				if (e.getEventTime() - e.getDownTime() < 500) {
					System.out.println("单击");
				}
			}
//			if (e.getAction() == MotionEvent.ACTION_UP && e.getEventTime() - e.getDownTime() < 500) {
//				System.out.println("单击");
//				
//			}
			
//			// TODO 长按
//			if (e.getAction() == MotionEvent.ACTION_DOWN) {
//				downTime = e.getDownTime();
////				System.out.println(e.getEventTime()  + "-" + e.getDownTime());
//			}
//			if (!longPressed && e.getEventTime() - downTime > 500) {
//				longPressed = true;
//				System.out.println("长按");
//			}
//			System.out.println(e.getEventTime() - downTime);
//			if (e.getAction() == MotionEvent.ACTION_DOWN && pressed) {
//				pressed = true;
////				System.out.println("长按");
////				firstLongDown = false;
//				System.out.println(e.getEventTime() - e.getDownTime());
//			}
//			if (e.get)
//			if (e.getAction() == MotionEvent.ACTION_DOWN && firstLongDown && e.getEventTime() - e.getDownTime() > 700) {
//				System.out.println("长按");
//				firstLongDown = false;
//			}
			//System.out.println("一指");
			break;
		case 2:
			//System.out.println(e.getAction() + " , " + MotionEvent.ACTION_POINTER_UP + " , " + (e.getAction() == MotionEvent.ACTION_POINTER_UP));
			System.out.println(e.getAction());
			if (e.getHistorySize() > 0) {
			
//				System.out.println("双指: " + (e.getY() - e.getHistoricalY(0) > 0 ? "下" : "上"));
//				System.out.println(e.getHistoricalY(0)- e.getY());
				
				float posx = Math.abs(e.getX() - e.getHistoricalX(0));
				float posy = Math.abs(e.getY() - e.getHistoricalY(0));
//				System.out.println(posy);
				if (posx <= 0.1 && posy > 0.1) {
//					System.out.println(pos);
//					System.out.println((e.getY() - e.getHistoricalY(0) > 0 ? "上滑动" : "下滑动"));
//					new TouchTask2().execute(new WindowsMessage("scroll", 0, (e.getHistoricalY(0)- e.getY()), 0, 0, 0, 0));
					new TouchTask2().execute(new WindowsMessage("scroll", 0, (e.getHistoricalY(0)- e.getY() > 0 ? 1 : -1), 0, 0, 0, 0));
					
				}
				
			}
			
//			if (e.getAction() == MotionEvent.ACTION_POINTER_DOWN) {
			if (e.getHistorySize() == 0) {
				px2 = px2 == 0 ? x : px2;
				py2 = py2 == 0 ? y : py2;
				
			}
			
			float pos = x - px2;
//			System.out.println(pos);
//			System.out.println(px2 + " , " + x);
			if (Math.abs(pos) > 30 && e.getAction() == MotionEvent.ACTION_POINTER_UP) {
//				System.out.println("双指: " + (e.getX() - e.getHistoricalX(0) > 0 ? "右" : "左"));
//				System.out.println("双指: " + (e.getY() - e.getHistoricalY(0) > 0 ? "下" : "上"));
				
//				System.out.println("双指手势: " + (e.getX() - px2 > 0 ? "右" : "左") + "  " + e.getX() + " , " + px2 + " position : " + Math.abs(e.getX() - px2));
//				System.out.println("双指手势: " + (e.getY() - py2 > 0 ? "下" : "上"));
				
//				System.out.println("双指手势: " + (pos > 0 ? "右" : "左") + " , " + pos);
//				System.out.println((pos > 0 ? "前进" : "后退"));
				String action = pos > 0 ? "go" : "back";
				//send(new WindowsMessage(action));
				new TouchTask2().execute(new WindowsMessage(action));
//				System.out.println("完成" + e.getHistorySize());
				
				
				px2 = 0;
				py2 = 0;
//				System.out.println(e.getAxisValue(MotionEvent.AXIS_X) + " - " + e.getAxisValue(MotionEvent.AXIS_Y));
//				System.out.println(e.get);
				
			}
			
			break;
		case 3:
			System.out.println("三指");
			break;
		}
		
		return true;
	}
	
	
	
	
	
	
	
	
	
}

class TouchTask2 extends AsyncTask<WindowsMessage, View, String> {
	
	static String url = "http://192.168.137.1:5230/?";
	
	@Override
	protected String doInBackground(WindowsMessage... params) {
		for (WindowsMessage windowsMessage : params) {
			send(windowsMessage);
		} 
		return null;
	}
	
	static void send(WindowsMessage message) {
		try {
			HttpClient client = new DefaultHttpClient();
			String str = url + "action=" + message.action + "&x=" + message.x + "&y=" + message.y;
//			System.out.println(url);
			client.execute(new HttpGet(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
