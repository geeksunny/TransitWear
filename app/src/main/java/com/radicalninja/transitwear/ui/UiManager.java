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
import com.radicalninja.transitwear.ui.predictions.PredictionListFragment;
import com.radicalninja.transitwear.ui.view.SplashView;
import com.radicalninja.transitwear.util.SimpleCallback;

import java.util.ArrayList;
import java.util.List;

public enum UiManager {

    // TODO: Add nav drawer, fab, etc. management here.

    INSTANCE;

    private static Handler uiHandler;

    private FragmentManager fragmentManager;
    private int contentFrameId = R.id.container;
    private View container, appbar;
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

        manager.appbar = mainActivity.findViewById(R.id.appbar);
        manager.toolbar = (Toolbar) mainActivity.findViewById(R.id.toolbar);
        mainActivity.setSupportActionBar(manager.toolbar);
        manager.container = mainActivity.findViewById(manager.contentFrameId);
        manager.fragmentManager = mainActivity.getSupportFragmentManager();
        manager.splashView = (SplashView) mainActivity.findViewById(R.id.splash);
        //manager.splashView.setOverlayMode(false);
        manager.fab = (FloatingActionButton)  mainActivity.findViewById(R.id.fab);
        manager.fab.setOnClickListener(manager.fabClickListener);

        manager.container.setVisibility(View.GONE);
        manager.appbar.setVisibility(View.GONE);
        manager.fab.setVisibility(View.GONE);
        manager.splashView.setVisibility(View.VISIBLE);

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
        tx.replace(R.id.container, new PredictionListFragment());
        tx.commit();
    }

    private List<View> getUiViewList() {
        // todo : clean this up? (splashView should probably be reapproached... this feels too dirty)
        final List<View> views = new ArrayList<>(3);
        views.add(appbar);
        views.add(fab);
        views.add(container);
        return views;
    }

    public void stopLoading() {
//        if (isSplashMode) {
            // fade to appView
            splashView.fadeIntoViews(getUiViewList(), 1500, null);
            // disable splash mode
            //splashView.setOverlayMode(true);
//            isSplashMode = false;
//        } else {
//            // fade out
//            splashView.fade(false, 850, null);
//        }
    }

    public void startLoading() {
        splashView.fade(true, 1000, null);
    }

}
