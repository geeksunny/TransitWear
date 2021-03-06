package com.radicalninja.transitwear.ui;

import android.animation.Animator;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.radicalninja.transitwear.App;
import com.radicalninja.transitwear.R;
import com.radicalninja.transitwear.data.Preferences;
import com.radicalninja.transitwear.data.model.Route;
import com.radicalninja.transitwear.data.model.Stop;
import com.radicalninja.transitwear.ui.home.HomeFragment;
import com.radicalninja.transitwear.ui.predictions.PredictionListFragment;
import com.radicalninja.transitwear.ui.stations.StationsListFragment;
import com.radicalninja.transitwear.ui.view.SplashView;
import com.radicalninja.transitwear.util.SimpleCallback;

public enum UiManager {

    // TODO: Add nav drawer, fab, etc. management here.
    INSTANCE;

    private static final float SPLASH_FADE_SCALE = 0.15f;

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
                Snackbar.make(view, "Find stops near me", Snackbar.LENGTH_LONG)
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

    public boolean back() {
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
            return true;
        }
        return false;
    }

    public void setTitle(final CharSequence title) {
        toolbar.setTitle(title);
    }

    public void setTitle(@StringRes final int titleResId) {
        toolbar.setTitle(titleResId);
    }

    private void loadFragment(final Fragment fragment) {
        loadFragment(fragment, true);
    }

    private void loadFragment(final Fragment fragment, final boolean addToBackStack) {
        FragmentTransaction tx = fragmentManager.beginTransaction();
        if (addToBackStack) {
            tx.addToBackStack(null);
        }
        tx.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        tx.replace(contentFrameId, fragment);
        tx.commit();
    }

    private void loadInitialFragment() {
        loadFragment(HomeFragment.newInstance(), false);
    }

    public void toHomeFragment() {
        loadFragment(HomeFragment.newInstance());
    }

    public void toStopsFragment(final Route route) {
        loadFragment(StationsListFragment.newInstance(route));
    }

    public void toPredictionsFragment(final Stop stop) {
        loadFragment(PredictionListFragment.newInstance(stop));
    }

    // TODO: Better handling here, don't cancel any existing animation, etc
    public void stopLoading() {
        if (splashView.getAlpha() != 0) {
            final Animator.AnimatorListener listener;
            if (isSplashMode) {
                listener = new Animator.AnimatorListener() {
                    @Override public void onAnimationCancel(Animator animator) { }
                    @Override public void onAnimationStart(Animator animator) { }
                    @Override public void onAnimationRepeat(Animator animator) { }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        isSplashMode = false;
                        splashView.setOverlayMode(true);
                    }
                };
            } else {
                listener = null;
            }
            final float scale = isSplashMode ? SPLASH_FADE_SCALE : -SPLASH_FADE_SCALE;
            splashView.fade(false, scale, 500, listener);
        }
    }

    public void startLoading() {
        if (splashView.getAlpha() != 1) {
            final float scale = isSplashMode ? SPLASH_FADE_SCALE : -SPLASH_FADE_SCALE;
            splashView.fade(true, scale, 500, null);
        }
    }

}
