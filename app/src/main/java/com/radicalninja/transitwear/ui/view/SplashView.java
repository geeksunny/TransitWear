package com.radicalninja.transitwear.ui.view;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.radicalninja.transitwear.R;

public class SplashView extends FrameLayout {

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

        // Title
        final TextView title = new TextView(getContext());
        final LayoutParams titleParams =
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        titleParams.setMargins(0, 350, 0, 0);
        title.setGravity(Gravity.CENTER_HORIZONTAL);
        title.setLayoutParams(titleParams);
        title.setText(R.string.app_name);
        title.setTextSize(32f);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        addView(title);

        // Loading Indicator
        final ProgressBar progressBar =
                new ProgressBar(getContext(), null, android.R.attr.progressBarStyleSmall);
        final LayoutParams progressParams =
                new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        progressParams.setMargins(0, 0, 0, 350);
        progressParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        progressBar.setLayoutParams(progressParams);
        progressBar.getIndeterminateDrawable().
                setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        addView(progressBar);
    }

}
