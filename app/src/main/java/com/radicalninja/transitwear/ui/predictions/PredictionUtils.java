package com.radicalninja.transitwear.ui.predictions;

import android.util.Log;

import com.radicalninja.transitwear.data.api.train.ArrivalPrediction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PredictionUtils {

    private static final String TAG = PredictionUtils.class.getSimpleName();

    //20160626 21:05:35
    private static final String TIMESTAMP_FORMAT_IN = "yyyyMMdd HH:mm:ss";
    private static final String ARRIVAL_TIME_FORMAT = "%dm";

    public static String getArrivalTimeString(final ArrivalPrediction prediction) {
        try {
            final SimpleDateFormat formatIn = new SimpleDateFormat(TIMESTAMP_FORMAT_IN, Locale.US);
            final Date arrivalTime = formatIn.parse(prediction.getArrivalTime());
            final Date now = new Date();
            final long timeDifference = arrivalTime.getTime() - now.getTime();
            final long minutes = timeDifference / 1000 / 60;
            return String.format(Locale.US, ARRIVAL_TIME_FORMAT, minutes);
        } catch (ParseException e) {
            Log.e(TAG, "Error parsing arrival time.", e);
        }
        return "";
    }

}
