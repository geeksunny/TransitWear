package com.radicalninja.transitwear;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.radicalninja.transitwear.data.db.TrainStopImporter;
import com.radicalninja.transitwear.util.SimpleCallback;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.io.IOException;

public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    public App() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    public void doInitialDataImport(final SimpleCallback callback) {
        new DataImportTask(callback).execute();
    }

    class DataImportTask extends AsyncTask<Void, Void, Void> {

        final SimpleCallback callback;

        public DataImportTask(final SimpleCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                new TrainStopImporter().runImport();
            } catch (IOException e) {
                Log.e("App", "Error importing train stops.", e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            callback.onComplete();
        }
    }
}
