package com.radicalninja.transitwear.ui.predictions;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.radicalninja.transitwear.R;
import com.radicalninja.transitwear.data.model.Stop;

import at.grabner.circleprogress.CircleProgressView;

public class StopDetailsView extends FrameLayout {

    private TextView stopName, routeName, stopDirection, lastRefreshed;
    private CircleProgressView progress1, progress2, progress3;
    private Stop stop;

    public StopDetailsView(Context context) {
        super(context);
        init();
    }

    public StopDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StopDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public StopDetailsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        final View view = LayoutInflater.from(getContext())
                .inflate(R.layout.stop_details_view, this, false);
        stopName = (TextView) view.findViewById(R.id.stopName);
        routeName = (TextView) view.findViewById(R.id.routeName);
        stopDirection = (TextView) view.findViewById(R.id.stopDirection);
        lastRefreshed = (TextView) view.findViewById(R.id.lastRefreshed);
        progress1 = (CircleProgressView) view.findViewById(R.id.progress1);
        progress2 = (CircleProgressView) view.findViewById(R.id.progress2);
        progress3 = (CircleProgressView) view.findViewById(R.id.progress3);
        addView(view);
    }

    public void setStop(final Stop stop) {
        this.stop = stop;
        stopName.setText(stop.getStopName());
        routeName.setText(stop.getStationName());
        stopDirection.setText(stop.getStationDescription());
    }

    public void setPredictions() {
        // TODO: Hook up prediction time monitoring with runnables? update progress bars (+ predictions in list?)
        // todo: set the lastRefreshed timestamp here.
    }

}
