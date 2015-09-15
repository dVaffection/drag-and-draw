package com.dvlab.draganddraw.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.dvlab.draganddraw.entities.Box;

import java.util.ArrayList;
import java.util.List;

public class BoxDrawingView extends View {

    final public static String TAG = BoxDrawingView.class.getSimpleName();

    private Box currentBox;
    private List<Box> boxes = new ArrayList<>();

    private Paint boxPaint;
    private Paint backgroundPaint;

    public BoxDrawingView(Context context) {
        this(context, null);
    }

    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Paint the boxes a nice semitransparent red (ARGB)
        boxPaint = new Paint();
        boxPaint.setColor(0x22ff0000);

        // Paint the background off-white
        backgroundPaint = new Paint();
        backgroundPaint.setColor(0xfff8efe0);
    }

    public boolean onTouchEvent(MotionEvent event) {
        PointF pointF = new PointF(event.getX(), event.getY());
        Log.i(TAG, "Received event at x=" + pointF.x + ", y=" + pointF.y + ":");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, " ACTION_DOWN");

                currentBox = new Box(pointF);
                boxes.add(currentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, " ACTION_MOVE");

                if (currentBox != null) {
                    currentBox.setCurrent(pointF);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, " ACTION_UP");

                currentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG, " ACTION_CANCEL");

                currentBox = null;
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Fill the background
        canvas.drawPaint(backgroundPaint);

        for (Box box : boxes) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            canvas.drawRect(left, top, right, bottom, boxPaint);
        }
    }

}
