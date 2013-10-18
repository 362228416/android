package com.ydpp.touch;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.accounts.Account;
import android.app.IntentService;
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

/**
 * 
 * 实现了设置最低亮度
 * 点击，双击，长按，单指滑动功能
 * 勉强能用，呵呵，有待加强
 */
public class TouchActivity extends NoTitleActivity implements OnMultipointGestureListener {

	MultipointDetector detector;		// 手势解析
	
	int SCREEN_BRIGHTNESS;			// 原来的亮度
	
	static String url;
	
	static String clickLeft;
	static String clickRight;
	static String commandGo;
	static String commandBack;
	static String upScroll;
	static String downScroll;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String ip = getIntent().getStringExtra("ip");
		int port = getIntent().getIntExtra("port", 5230);
		url = "http://" + ip + ":" + port + "/?";
		clickLeft = "http://" + ip + ":" + port + "/?action=clickLeft";
		clickRight = "http://" + ip + ":" + port + "/?action=clickRight";
		commandGo = "http://" + ip + ":" + port + "/?action=go";
		commandBack = "http://" + ip + ":" + port + "/?action=back";
		upScroll = "http://" + ip + ":" + port + "/?action=scroll&y=1";
		downScroll = "http://" + ip + ":" + port + "/?action=scroll&y=-1";
		
		
		detector = new MultipointDetector(this);
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
				return detector.onTouchEvent(e);
			}
		});
	}
	


	@Override
	public void onLongPress(MotionEvent e) {
		new TouchTask().execute(new WindowsMessage("clickRight"));
	}

	public void onScroll(MotionEvent e) {
//		int pc = e.getPointerCount();
//		System.out.println(pc);
//		System.out.println("onScroll : " + pointer);
		
		// 这里的pointer总是1，坑爹
		/*
		switch (pointer) {
		case 1:
			new TouchTask().execute(new WindowsMessage("move", x, y, e.getX(), e.getY(), e2.getX(), e2.getY()));
			break;
		case 2:
			new TouchTask().execute(new WindowsMessage("scroll", x, y, e.getX(), e.getY(), e2.getX(), e2.getY()));
			break;
		}
		*/
		
//		new TouchTask().execute(new WindowsMessage("move", e.getX(), e.getY()));
		if (e.getHistorySize() > 0) {
			
//			System.out.println(e.getX() + " - " + e.getY() + " - " + e.getHistoricalX(0) + " - " + e.getHistoricalY(0));
//			System.out.println("单指滑动");
			
			float x = e.getX() - e.getHistoricalX(0);
			float y = e.getY() - e.getHistoricalY(0);
//			System.out.println(x + " , " + y);
			new TouchTask().execute(new WindowsMessage("move", x, y));
//			new TouchTask().execute(new WindowsMessage("move", (e.getX() - e.getHistoricalX(0)), (e.getY() - e.getHistoricalY(0))));
		}
		
		
	}

	@Override
	public void onClick(MotionEvent e) {
//		new TouchTask().execute(new WindowsMessage("clickLeft"));
//		System.out.println("clickLeft");
		new SimpleTouchTask().execute(clickLeft);
	}

	@Override
	public boolean onTwoPointScroll(MotionEvent e, float x, float y, float x2,
			float y2) {
		float posx = Math.abs(e.getX() - e.getHistoricalX(0));
		float posy = Math.abs(e.getY() - e.getHistoricalY(0));
		if (posx <= 0.1 && posy > 0.1) {
//			System.out.println((y - y2 > 0 ? "上滑动" : "下滑动"));
			new SimpleTouchTask().execute(e.getHistoricalY(0) - y > 0 ? upScroll : downScroll);
		}
		return false;
	}
	
	@Override
	public boolean onTwoPointUp(MotionEvent e, float x, float y, float x2, float y2) {
		// TODO Auto-generated method stub
		float pos = x - x2;
		if (Math.abs(pos) > 60) {
//			String action = pos > 0 ? "go" : "back";
//			System.out.println(action);
			new SimpleTouchTask().execute(pos > 0 ? commandGo : commandBack);
		}
		return false;
	}

	@Override
	public void onTwoPointClick(MotionEvent e) {
//		System.out.println("双指单击");
		new SimpleTouchTask().execute(clickRight);
	}

	@Override
	public void onThreePointClick(MotionEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("三指单击");
	}
	
	@Override
	public void onThreePointScroll(MotionEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("三指滑动");
	}
	
	boolean threePressed;
	@Override
	public boolean onThreeTouch(MotionEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e.getAction() + ", " + MotionEvent.ACTION_MOVE + ", " + MotionEvent.ACTION_DOWN + ", " + MotionEvent.ACTION_UP + ", " + MotionEvent.ACTION_POINTER_UP);
//		if (e.getAction() == MotionEvent.ACTION_MOVE) {
////			System.out.println("三指拖动");
//		}
//		if (e.getAction() == MotionEvent.ACTION_POINTER_UP && e.getEventTime() - e.getDownTime() < 130) {
//		
//		}
//		if (!threePressed && e.getAction() == MotionEvent.ACTION_POINTER_3_DOWN) {
//			threePressed = true;
//			System.out.println("三指按下");
//		}
//		if (e.getAction() == MotionEvent.ACTION_POINTER_UP) {
//			threePressed = false;
//			System.out.println("三指放开");
//		}
		
		
		return false;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 还原亮度
		android.provider.Settings.System.putInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, SCREEN_BRIGHTNESS);
	}
	
}

class TouchTask extends AsyncTask<WindowsMessage, View, String> {
	
	static String ip;
	static int port;
	public static final String url;
	static {
		url = TouchActivity.url;
	}
	
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
//			String url = "http://" + ip + ":" + port + "/?" + makeUrl(message);
			String str = url + "action=" + message.action + "&x=" + message.x + "&y=" + message.y;
//			System.out.println(url);
//			client.execute(new HttpGet(str));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static String makeUrl(WindowsMessage message) {
		return "action=" + message.action + "&x=" + message.x + "&y=" + message.y;
	}
	
	
}

class SimpleTouchTask extends AsyncTask<String, View, String> {
	
	static String ip;
	static int port;
	public static final String url;
	static {
		url = TouchActivity.url;
	}
	
	@Override
	protected String doInBackground(String... params) {
		for (String windowsMessage : params) {
			try {
				System.out.println(windowsMessage);
				HttpClient client = new DefaultHttpClient();
				client.execute(new HttpGet(windowsMessage));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return null;
	}
}


class WindowsMessage {
	String action;
	float x, y, x1, y1, x2, y2;
	public WindowsMessage(String action, float x, float y) {
		this.action = action;
		this.x = x;
		this.y = y;
	}
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

class LocalService extends IntentService  {

	public LocalService(String name) {
		super(name);
		System.out.println("TouchService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		System.out.println("onHandleIntent");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	
}
