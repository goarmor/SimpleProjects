package ua.com.todd.customdrawableview;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class CustomMoveLayout extends FrameLayout {

    private float prevX;
    private float prevY;

    private void init() {

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        View view = findViewById(R.id.some_view);
        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        prevX = event.getX();
                        prevY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float currX = event.getX();
                        float currY = event.getY();
                        float deltaX = currX - prevX;
                        float deltaY = currY - prevY;
                        FrameLayout.LayoutParams frameLayout = (LayoutParams) v.getLayoutParams();
                        frameLayout.topMargin += deltaY;
                        frameLayout.leftMargin += deltaX;
                        requestLayout();
                        break;
                }
                return true;
            }
        });
    }

    public CustomMoveLayout(Context context) {
        this(context, null, 0);
    }

    public CustomMoveLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomMoveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
}
