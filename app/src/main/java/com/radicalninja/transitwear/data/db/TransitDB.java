package com.radicalninja.transitwear.data.db;

import android.support.annotation.NonNull;

import com.radicalninja.transitwear.data.model.TrainStop_Route;
import com.radicalninja.transitwear.data.model.TrainStop_Route_Table;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.SQLite;

@Database(name = TransitDB.NAME, version = TransitDB.VERSION)
public class TransitDB {

    protected static final String NAME = "TransitWearDB";
    protected static final int VERSION = 1;

    // TODO add some general CRUD methods with generic DbFlow Transaction objects and callbacks
    // TODO create ModelView query objects

    /**
     * Perform an asynchronous query for all stops
     * @param routeId
     * @param callback
     */
    public static void getStopsForRoute(final int routeId, @NonNull final QueryCallback<TrainStop_Route> callback) {
        final Condition[] conditions =
                callback.conditions(TrainStop_Route_Table.route__id.eq(routeId));
        SQLite.select()
                .from(TrainStop_Route.class)
                .where(conditions)
                .async()
                .queryListResultCallback(callback)
                .error(callback)
                .success(callback)
                .execute();
    }

}
