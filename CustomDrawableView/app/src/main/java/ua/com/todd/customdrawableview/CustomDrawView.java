package ua.com.todd.customdrawableview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CustomDrawView extends View implements View.OnTouchListener {

    private List<Point> points = new ArrayList<Point>();
    private Paint paint;
    private int radius = 30;

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        setOnTouchListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isCrossed(event))
                    return false;
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    private boolean isCrossed(MotionEvent event) {
        boolean isInCircle = false;

        for (Point point : points) {
            isInCircle = Math.pow((point.x - event.getX()), 2) +
                    Math.pow((point.y - event.getY()), 2) <
                    Math.pow(radius * 2, 2);
            if (isInCircle)
                break;
        }

        return isInCircle;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                points.add(new Point((int) event.getX(), (int) event.getY()));
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (Point point : points) {
            canvas.drawCircle(point.x, point.y, radius, paint);
        }
        super.onDraw(canvas);
    }

    public CustomDrawView(Context context) {
        this(context, null, 0);
    }

    public CustomDrawView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
}
