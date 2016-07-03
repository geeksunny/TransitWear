package com.radicalninja.transitwear.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.radicalninja.transitwear.App;

public enum Preferences {

    INSTANCE(App.getInstance().getApplicationContext());

    private static final String PREFS_FILE = "TransitWear";

    private static final String KEY_FIRST_START = "FirstStart";

    private SharedPreferences prefs;

    Preferences(Context context) {
        prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
    }

    public boolean isFirstAppStart() {
        return prefs.getBoolean(KEY_FIRST_START, false);
    }

    public void setFirstAppStart(final boolean started) {
        prefs.edit().putBoolean(KEY_FIRST_START, started).apply();
    }

}
