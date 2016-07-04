package com.radicalninja.transitwear.ui;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.radicalninja.transitwear.App;
import com.radicalninja.transitwear.R;
import com.radicalninja.transitwear.data.Preferences;
import com.radicalninja.transitwear.ui.predictions.PredictionListFragment;
import com.radicalninja.transitwear.ui.view.SplashView;
import com.radicalninja.transitwear.util.SimpleCallback;

import java.lang.ref.WeakReference;

public enum UiManager {

    // TODO: Add nav drawer management here.

    INSTANCE;

    private static Handler uiHandler;

    private FragmentManager fragmentManager;
    private int contentFrameId;
    private SplashView splashView;
    private Toolbar toolbar;

    private boolean isSplashMode;
    private WeakReference<View> appView;

    public static void postToUiThread(final Runnable r) {
        uiHandler.post(r);
    }

    public static void postDelayedToUiThread(final Runnable r, final long delayMillis) {
        uiHandler.postDelayed(r, delayMillis);
    }

    @UiThread
    public static void init(@NonNull final MainActivity mainActivity, final int contentFrameId) {
        final UiManager manager = INSTANCE;
        uiHandler = new Handler();
        manager.fragmentManager = mainActivity.getSupportFragmentManager();
        manager.splashView = (SplashView) mainActivity.findViewById(R.id.splash);
        manager.splashView.setOverlayMode(false);
        manager.toolbar = (Toolbar) mainActivity.findViewById(R.id.toolbar);
        manager.contentFrameId = contentFrameId;
        manager.isSplashMode = true;
    }

    public void startApp(@NonNull final View appView) {
        this.appView = new WeakReference<>(appView);
        if (Preferences.INSTANCE.isFirstAppStart()) {
            final SimpleCallback callback = new SimpleCallback() {
                @Override
                public void onComplete() {
                    Preferences.INSTANCE.setFirstAppStart(false);
                    loadInitialFragment();
                }
            };
            App.getInstance().doInitialDataImport(callback);
        } else {
             loadInitialFragment();
        }
    }

    private void loadInitialFragment() {
        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.replace(R.id.container, new PredictionListFragment());
        tx.commit();
    }

    public void stopLoading() {
        if (isSplashMode && null != appView.get()) {
            // fade to appView
            splashView.fadeIntoView(appView.get(), 850, null);
            // disable splash mode
            splashView.setOverlayMode(true);
            isSplashMode = false;
            // clear unneeded references
            appView.clear();
        } else {
            // fade out
            splashView.fade(false, 850, null);
        }
    }

    public void startLoading() {
        splashView.fade(true, 850, null);
    }

}
