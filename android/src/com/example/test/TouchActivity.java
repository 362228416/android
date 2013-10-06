package com.example.test;

import java.io.OutputStream;
import java.net.Socket;

import android.accounts.Account;
import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchActivity extends NoTitleActivity implements OnGestureListener {

	GestureDetector detector;		// 手势解析
	
	int pointer;					// 手指按下数量
	
	int SCREEN_BRIGHTNESS;			// 原来的亮度
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		detector = new GestureDetector(this, this);
		try {
			// 原来的亮度
			SCREEN_BRIGHTNESS = android.provider.Settings.System.getInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);
		} catch (Exception e) {
		}
		// 将亮度调为最低
		android.provider.Settings.System.putInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, 0);
		layout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent e) {
				pointer = e.getPointerCount();
				return detector.onTouchEvent(e);
			}
		});
	}
	
	@Override
	public boolean onDown(MotionEvent e) {
//		System.out.println("onDown " + e.getSize() + " - " + e.getPointerCount());
//		new TouchTask().execute(new WindowsMessage("onDown"));
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e, MotionEvent e2, float x,
			float y) {
		System.out.println("onFling : " + pointer);
//		new TouchTask().execute(new WindowsMessage("onFling", x, y, e.getX(), e.getY(), e2.getX(), e2.getY()));
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		System.out.println("onLongPress");
		new TouchTask().execute(new WindowsMessage("onLongPress"));
	}

	@Override
	public boolean onScroll(MotionEvent e, MotionEvent e2, float x,
			float y) {
//		int pc = e.getPointerCount();
//		System.out.println(pc);
		System.out.println("onScroll : " + pointer);
		switch (pointer) {
		case 1:
			new TouchTask().execute(new WindowsMessage("move", x, y, e.getX(), e.getY(), e2.getX(), e2.getY()));
			break;
		case 2:
			new TouchTask().execute(new WindowsMessage("scroll", x, y, e.getX(), e.getY(), e2.getX(), e2.getY()));
			break;
		}
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		System.out.println("onShowPress");
//		new TouchTask().execute(new WindowsMessage("onSingleTapUp"));
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		System.out.println("onSingleTapUp");
		new TouchTask().execute(new WindowsMessage("clickLeft"));
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 还原亮度
		android.provider.Settings.System.putInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, SCREEN_BRIGHTNESS);
		try {
			TouchTask.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class TouchTask extends AsyncTask<WindowsMessage, View, String> {
	
	public static Socket socket;
	

	@Override
	protected String doInBackground(WindowsMessage... params) {
		try {
			for (WindowsMessage windowsMessage : params) {
				send(windowsMessage);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	static synchronized void send(Object obj) {
		try {
//			if (socket == null || socket.isClosed()) {
//				socket = new Socket("192.168.137.1", 5230);
//			}
			Socket socket = new Socket("192.168.137.1", 5230);

			OutputStream out = socket.getOutputStream();
			out.write(obj.toString().getBytes());
			out.flush();
			
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}


class WindowsMessage {
	String action;
	float x, y, x1, y1, x2, y2;
	public WindowsMessage(String action) {
		this.action = action;
	}
	public WindowsMessage(String action, float x, float y, float x1, float y1, float x2, float y2) {
		super();
		this.action = action;
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	public String toJson() {
		return "[\"action\":\"" + action + "\", \"x\":" + x + ", \"y\":" + y + "]";
	}
	@Override
	public String toString() {
		return action + "," + x + "," + y + "," + x1 + "," + y1 + "," + x2 + "," + y2;
	}
}


/// 服务，暂时没用到
class SyncAdapter extends AbstractThreadedSyncAdapter {

	public SyncAdapter(Context context, boolean autoInitialize) {
		super(context, autoInitialize);
	}

	@Override
	public void onPerformSync(Account account, Bundle extras, String authority,
			ContentProviderClient provider, SyncResult syncResult) {
		
	}
	
}

class LocalService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}
	
}
