package com.radicalninja.transitwear.ui.view.util;

import android.view.View;
import android.view.animation.AlphaAnimation;

public class SplashHelper {

    public static void animateTransition(final View from, final View to, final long duration) {
        final AlphaAnimation animationFrom = new AlphaAnimation(1f, 0f);
        animationFrom.setDuration(duration);
        animationFrom.setFillAfter(true);
        final AlphaAnimation animationTo = new AlphaAnimation(0f, 1f);
        animationTo.setDuration(duration);
        animationTo.setFillAfter(true);
        from.setAnimation(animationFrom);
        to.setAnimation(animationTo);
        from.animate();
        to.animate();
    }

}
