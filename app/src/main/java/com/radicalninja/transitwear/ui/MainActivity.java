package com.radicalninja.transitwear.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.radicalninja.transitwear.App;
import com.radicalninja.transitwear.R;
import com.radicalninja.transitwear.data.Preferences;
import com.radicalninja.transitwear.ui.predictions.PredictionListFragment;
import com.radicalninja.transitwear.ui.view.SplashView;
import com.radicalninja.transitwear.ui.view.util.SplashHelper;
import com.radicalninja.transitwear.util.SimpleCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (!Preferences.INSTANCE.isFirstAppStart()) {
            final SimpleCallback callback = new SimpleCallback() {
                @Override
                public void onComplete() {
                    loadInitialFragment();
//                    startInterface();
                }
            };
            App.getInstance().doInitialDataImport(callback);
        } else {
            loadInitialFragment();
//            startInterface();
        }
    }

    public void startInterface() {
//        loadInitialFragment();
        final SplashView splashView = (SplashView) findViewById(R.id.splash);
        final View view = findViewById(R.id.content);
        SplashHelper.animateTransition(splashView, view, 850);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadInitialFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        tx.replace(R.id.container, new PredictionListFragment());
        tx.commit();
    }

}
