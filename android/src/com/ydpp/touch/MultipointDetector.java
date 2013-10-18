package com.ydpp.touch;

import android.view.MotionEvent;

public class MultipointDetector {

	private OnMultipointGestureListener listener;

	public MultipointDetector(OnMultipointGestureListener listener) {
		this.listener = listener;
	}
	
	float px1, py1, px2, py2, px3, py3;
	
	boolean threePressed = false;
	boolean twoPressed = false;
	
	public boolean onTouchEvent (MotionEvent e) {
		
		int count = e.getPointerCount();
		switch(count) {
		case 1:
			if (threePressed || twoPressed) {
				threePressed = false;
				twoPressed = false;
//				return false;
				break;
			}
			// TODO 滑动
			if (e.getAction() == MotionEvent.ACTION_MOVE) {
				listener.onScroll(e);
			}
			// TODO 单击
//			System.out.println(e.getEventTime() - e.getDownTime());
			if (e.getAction() == MotionEvent.ACTION_UP && e.getEventTime() - e.getDownTime() < 500) {
				listener.onClick(e);
			}
			break;
		case 2:
			twoPressed = true;
			if (threePressed) {
				threePressed = true;
				break;
			}
			float x = e.getX();
			float y = e.getY();
			if (e.getHistorySize() > 0  && e.getAction() == MotionEvent.ACTION_MOVE) {
				listener.onTwoPointScroll(e, x, y, px2, py2);
			}
//			System.out.println("双指: " + e.getAction() + " - " + MotionEvent.ACTION_POINTER_UP);
//			if (e.getAction() == MotionEvent.ACTION_POINTER_DOWN) {
			if (e.getHistorySize() == 0) {
				px2 = px2 == 0 ? x : px2;
				py2 = py2 == 0 ? y : py2;
			}
			
			
			if (e.getAction() == MotionEvent.ACTION_POINTER_UP) {
//				System.out.println("双指单击");
//				System.out.println(e.getEventTime()  + " - " +  e.getDownTime());
				if (e.getEventTime() - e.getDownTime() < 130) {
					listener.onTwoPointClick(e);
				} else {
					listener.onTwoPointUp(e, x, y, px2, py2);
				}
			}
			
			
//			float pos = x - px2;
//			System.out.println(pos);
//			System.out.println(px2 + " , " + x);
			if (e.getAction() == MotionEvent.ACTION_POINTER_UP) {
//				System.out.println("双指: " + (e.getX() - e.getHistoricalX(0) > 0 ? "右" : "左"));
//				System.out.println("双指: " + (e.getY() - e.getHistoricalY(0) > 0 ? "下" : "上"));
				
//				System.out.println("双指手势: " + (e.getX() - px2 > 0 ? "右" : "左") + "  " + e.getX() + " , " + px2 + " position : " + Math.abs(e.getX() - px2));
//				System.out.println("双指手势: " + (e.getY() - py2 > 0 ? "下" : "上"));
				
//				System.out.println("双指手势: " + (pos > 0 ? "右" : "左") + " , " + pos);
//				System.out.println((pos > 0 ? "前进" : "后退"));
//				String action = pos > 0 ? "go" : "back";
				//send(new WindowsMessage(action));
//				System.out.println(action);
//				new TouchTask2().execute(new WindowsMessage(action));
//				System.out.println("完成" + e.getHistorySize());
				
				
				px2 = 0;
				py2 = 0;
//				System.out.println(e.getAxisValue(MotionEvent.AXIS_X) + " - " + e.getAxisValue(MotionEvent.AXIS_Y));
//				System.out.println(e.get);
				
			}
			
//			return false;
			break;
		case 3:
			threePressed = true;
//			System.out.println("三指");
			if (!listener.onThreeTouch(e)) {
				break;
			}
			if (e.getAction() == MotionEvent.ACTION_MOVE) {
				listener.onThreePointScroll(e);
//				return false;
			}
			if (e.getAction() == MotionEvent.ACTION_POINTER_UP && e.getEventTime() - e.getDownTime() < 130) {
				listener.onThreePointClick(e);
//				return false;
			}
			break;
		}
		
		return true;
		
	}
	
}
