package ua.com.todd.animationsample;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class MainActivity extends Activity {

    private static final int RED = 0xffFF8080;
    private static final int BLUE = 0xff8080FF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSecond(View view) {
        Intent i = new Intent(this, SecondActivity.class);

        startActivity(i);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }

    public void onClickAlpha(View view) {
        AlphaAnimation animation1 = new AlphaAnimation(1f, 0f);
        animation1.setDuration(3000);
        view.startAnimation(animation1);
    }

    public void onClickAlphaRotation(View view) {
        Animation a =  AnimationUtils.loadAnimation(this, R.anim.rotate);
        view.startAnimation(a);
    }

    public void onClickAlphaPropertyRotation(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 1f,0.9f, 0f);
        anim.setDuration(7000);
        anim.setInterpolator(new CycleInterpolator(2f));
        anim.start();
    }

    public void onClickObjectProperty(View view) {
        ValueAnimator colorAnim = ObjectAnimator.ofInt(view, "backgroundColor", RED, BLUE);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }
}
