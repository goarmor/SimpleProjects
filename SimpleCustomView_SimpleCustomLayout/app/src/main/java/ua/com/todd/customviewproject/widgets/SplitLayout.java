package ua.com.todd.customviewproject.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class SplitLayout extends ViewGroup {
    public SplitLayout(Context context) {
        super(context);
    }

    public SplitLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SplitLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int itemWidth = (r-l)/getChildCount();
        for(int i=0; i< this.getChildCount(); i++){
            View v = getChildAt(i);
            v.layout(itemWidth*i, 0, (i+1)*itemWidth, b-t);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wspec = MeasureSpec.makeMeasureSpec(
                getMeasuredWidth()/getChildCount(), MeasureSpec.EXACTLY);
        int hspec = MeasureSpec.makeMeasureSpec(
                getMeasuredHeight(), MeasureSpec.EXACTLY);
        for(int i=0; i<getChildCount(); i++){
            View v = getChildAt(i);
            v.measure(wspec, hspec);
        }
    }
}
