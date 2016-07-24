package com.radicalninja.transitwear.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.radicalninja.transitwear.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UiManager.init(this);
        UiManager.INSTANCE.startApp();
    }

    @Override
    public void onBackPressed() {
        if (!UiManager.INSTANCE.back()) {
            super.onBackPressed();
        }
    }
}
