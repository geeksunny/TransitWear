package com.radicalninja.transitwear.ui.view;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SplashViewLayout extends CoordinatorLayout {

    private SplashView splashView;
    private boolean isSplashVisible = false;

    public SplashViewLayout(Context context) {
        super(context);
    }

    public SplashViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SplashViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public boolean isSplashVisible() {
//        return isSplashVisible;
//    }
//
//    public void setSplashVisible(final boolean splashVisible) {
//        if (splashVisible != isSplashVisible) {
//            isSplashVisible = splashVisible;
//            invalidate();
//        }
//    }

    public void stopLoading() {
//        final int childCount = getChildCount();
//        final List<View> siblingViews = new ArrayList<>(childCount - 1);
//        for (int i = 0; i < childCount; i++) {
//            final View view = getChildAt(i);
//            if (view instanceof SplashView) {
//                continue;
//            }
//            siblingViews.add(view);
//        }
//        splashView.fadeIntoViews(siblingViews, 2000, null);

//        splashView.fade(false, 0.25f, 2000, null);
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
//        // TODO: enforce splashScreen is drawn over everything else, including in overlay mode.
//        // TODO: Fading each child animation might not be necessary if this can be achieved. ^
//        if (isSplashVisible) {
//            splashView.draw(c);
//        }
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        if (child instanceof SplashView && null == splashView) {
            splashView = (SplashView) child;
//            removeView(child);
        }
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (null == splashView) {
            defaultSplashView();
        }
        initSplashView();
        isSplashVisible = true;
    }

    protected void initSplashView() {
//        splashView.setOverlayMode(true);
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View view = getChildAt(i);
//            final int visibility = (view instanceof SplashView) ? VISIBLE : GONE;
//            view.setVisibility(visibility);
            if (view instanceof AppBarLayout) {
                splashView.setElevation(view.getElevation()+50f);
            }
        }
    }

    protected void defaultSplashView() {
        splashView = new SplashView(getContext());
        final LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        splashView.setLayoutParams(params);
        addView(splashView);
        splashView.bringToFront();
    }
}
