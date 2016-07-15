package com.radicalninja.transitwear.ui;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.radicalninja.transitwear.App;
import com.radicalninja.transitwear.R;
import com.radicalninja.transitwear.data.Preferences;
import com.radicalninja.transitwear.ui.home.HomeFragment;
import com.radicalninja.transitwear.ui.predictions.PredictionListFragment;
import com.radicalninja.transitwear.ui.view.SplashView;
import com.radicalninja.transitwear.util.SimpleCallback;

public enum UiManager {

    // TODO: Add nav drawer, fab, etc. management here.

    INSTANCE;

    private static Handler uiHandler;

    private FragmentManager fragmentManager;
    private int contentFrameId = R.id.container;
    private SplashView splashView;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    private boolean isSplashMode;

    public static void postToUiThread(final Runnable r) {
        uiHandler.post(r);
    }

    public static void postDelayedToUiThread(final Runnable r, final long delayMillis) {
        uiHandler.postDelayed(r, delayMillis);
    }

    @UiThread
    public static void init(@NonNull final MainActivity mainActivity) {
        final UiManager manager = INSTANCE;
        uiHandler = new Handler();

        manager.toolbar = (Toolbar) mainActivity.findViewById(R.id.toolbar);
        mainActivity.setSupportActionBar(manager.toolbar);
        manager.fragmentManager = mainActivity.getSupportFragmentManager();
        manager.splashView = (SplashView) mainActivity.findViewById(R.id.splash);
        manager.fab = (FloatingActionButton)  mainActivity.findViewById(R.id.fab);
        manager.fab.setOnClickListener(manager.fabClickListener);

        manager.isSplashMode = true;
    }

    final View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }
    };

    public void startApp() {
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
        tx.replace(contentFrameId, HomeFragment.newInstance());
        tx.commit();
    }

    public void stopLoading() {
        splashView.fade(false, 0.15f, 500, null);
    }

    public void startLoading() {
        splashView.fade(true, 0.15f, 500, null);
    }

}
