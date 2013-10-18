package com.ydpp.touch;

import android.view.MotionEvent;

public interface OnMultipointGestureListener {

	/**
	 * 长按
	 * @param e
	 */
	void onLongPress(MotionEvent e);
	
	/**
	 * 单击
	 * @param e
	 */
	void onClick(MotionEvent e);
	
	/**
	 * 滑动
	 * @param e
	 */
	void onScroll(MotionEvent e);
	
	/**
	 * 双指滑动
	 * @param e
	 */
	boolean onTwoPointScroll(MotionEvent e, float x, float y, float x2, float y2);
	
	/**
	 * 双指离开
	 * @param e
	 * @return
	 */
	boolean onTwoPointUp(MotionEvent e, float x, float y, float x2, float y2);
	
	/**
	 * 三指滑动
	 * @param e
	 */
	void onThreePointScroll(MotionEvent e);
	
	/**
	 * 双指单击
	 * @param e
	 */
	void onTwoPointClick(MotionEvent e);
	
	/**
	 * 三指单击
	 * @param e
	 */
	void onThreePointClick(MotionEvent e);
	
	/**
	 * 三指事件
	 * @param e
	 */
	boolean onThreeTouch(MotionEvent e);
}
