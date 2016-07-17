package com.radicalninja.transitwear.ui.view;

import android.animation.Animator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
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

    public void fade(final boolean fadeIn, final float xyScale, final long duration,
                        @Nullable final Animator.AnimatorListener animationListener) {
        final float alphaStart = fadeIn ? 0f : 1f;
        final float alphaEnd = fadeIn ? 1f : 0f;

        final float scale = fadeIn ? xyScale : -xyScale;
        final float scaleStart = fadeIn ? 1f - xyScale : 1f;
        Log.e("abc", "scaleStart "+scaleStart+" xyScale "+xyScale);

        setScaleX(scaleStart);
        setScaleY(scaleStart);
        setAlpha(alphaStart);
        final ViewPropertyAnimator animator = animate().
                scaleXBy(scale).scaleYBy(scale).alpha(alphaEnd).
                setDuration(duration).
                setInterpolator(new AccelerateInterpolator()).
                setListener(animationListener);
        animator.start();
    }

}
