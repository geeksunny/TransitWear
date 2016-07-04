package com.radicalninja.transitwear.ui.view;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.radicalninja.transitwear.R;
import com.radicalninja.transitwear.util.Utility;

public class SplashView extends FrameLayout {

    private TextView title;

    public SplashView(Context context) {
        super(context);
        init();
    }

    public SplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SplashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SplashView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        final LayoutParams params =
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
        final int margin = (int) Utility.convertDpToPixel(125f, getContext());

        // Title
        title = new TextView(getContext());
        final LayoutParams titleParams =
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        titleParams.setMargins(0, margin, 0, 0);
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        title.setLayoutParams(titleParams);
        title.setText(R.string.app_name);
        title.setTextSize(28f);
        addView(title);

        // Loading Indicator
        final ProgressBar progressBar =
                new ProgressBar(getContext(), null, android.R.attr.progressBarStyle);
        final int progressSize = (int) Utility.convertDpToPixel(48f, getContext());
        final LayoutParams progressParams =
                new LayoutParams(progressSize, progressSize);
        progressParams.setMargins(0, 0, 0, margin);
        progressParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        progressBar.setLayoutParams(progressParams);
        progressBar.getIndeterminateDrawable().
                setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        addView(progressBar);
    }

    public void hideTitle(final boolean hide) {
        title.setVisibility(hide ? GONE : VISIBLE);
    }

    public void setOverlayMode(final boolean inOverlayMode) {
        final int colorRes = inOverlayMode ? R.color.overlayBackground : R.color.colorPrimaryDark;
        final int color = getContext().getResources().getColor(colorRes);
        setBackgroundColor(color);
        hideTitle(inOverlayMode);
    }

    public void fadeIntoView(final View view, final long duration, @Nullable final Animation.AnimationListener animationListener) {
        // TODO: add in visibility setting after animation completion?
        final AlphaAnimation animationFrom = new AlphaAnimation(1f, 0f);
        animationFrom.setDuration(duration);
        animationFrom.setFillAfter(true);
        if (null != animationListener) {
            animationFrom.setAnimationListener(animationListener);
        }
        final AlphaAnimation animationTo = new AlphaAnimation(0f, 1f);
        animationTo.setDuration(duration);
        animationTo.setFillAfter(true);
        this.setAnimation(animationFrom);
        view.setAnimation(animationTo);
        this.animate();
        view.animate();
    }

    public void fade(final boolean fadeIn, final long duration, @Nullable final Animation.AnimationListener animationListener) {
        // TODO: add in visibility setting after animation completion?
        final float alphaStart = fadeIn ? 0f : 1f;
        final float alphaEnd = fadeIn ? 1f : 0f;
        final AlphaAnimation animation = new AlphaAnimation(alphaStart, alphaEnd);
        animation.setDuration(duration);
        if (null != animationListener) {
            animation.setAnimationListener(animationListener);
        }
        animation.setFillAfter(true);
        this.setAnimation(animation);
        this.animate();
    }

}
