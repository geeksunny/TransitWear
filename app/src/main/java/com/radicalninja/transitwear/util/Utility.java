package com.radicalninja.transitwear.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Utility {

    public static float convertDpToPixel(float dp, Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    public static float convertPixelsToDp(float px, Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }

}
