package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.LinearLayout;

public class DrawActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 除去标题栏，一定要加在setContentView(layout); 前面
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		LinearLayout layout = new LinearLayout(this);
		setContentView(layout);
		final DrawView dv = new DrawView(this);
//		dv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//		dv.setBackgroundColor(Color.YELLOW);
		dv.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View view, MotionEvent e) {
				dv.x = e.getX();
				dv.y = e.getY();
				dv.invalidate();
				return true;
			}
		});
		
		layout.addView(dv);
		
	}

}


class DrawView extends View {
	
	float x, y;
	
	public DrawView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		System.out.println("touch x = " + x + ", y = " + y);
		Paint p = new Paint();
		p.setColor(Color.RED);
		canvas.drawCircle(x, y, 15, p);
	}
	
}