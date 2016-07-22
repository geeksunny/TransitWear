package com.radicalninja.transitwear.data.db;

import android.support.annotation.NonNull;

import com.radicalninja.transitwear.data.model.Route;
import com.radicalninja.transitwear.data.model.Route_Table;
import com.radicalninja.transitwear.data.model.TrainStop;
import com.radicalninja.transitwear.data.model.TrainStop_Route;
import com.radicalninja.transitwear.data.model.TrainStop_Route_Table;
import com.radicalninja.transitwear.data.model.TrainStop_Table;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Join;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;

@Database(name = TransitDB.NAME, version = TransitDB.VERSION)
public class TransitDB {

    protected static final String NAME = "TransitWearDB";
    protected static final int VERSION = 1;

    protected static final String ALIAS_ROUTES = "R";
    protected static final String ALIAS_TRAIN_STOPS = "TS";
    protected static final String ALIAS_TRAIN_STOPS_ROUTES = "TSR";

    // TODO add some general CRUD methods with generic DbFlow Transaction objects and callbacks
    // TODO create ModelView query objects

    /**
     * Perform an asynchronous query for all stations for a given route.
     * @param routeId
     * @param callback
     */
    public static void getStationsForRoute(final int routeId, @NonNull final QueryCallback<TrainStop_Route> callback) {
        final Condition[] conditions =
                callback.conditions(TrainStop_Route_Table.route__id.eq(routeId));
        // TODO: Clean up this query?
        SQLite.select()
                .from(TrainStop_Route.class).as(ALIAS_TRAIN_STOPS_ROUTES)
                .join(TrainStop.class, Join.JoinType.LEFT_OUTER).as(ALIAS_TRAIN_STOPS)
                .on(TrainStop_Route_Table.trainStop_stopId.withTable(NameAlias.builder(ALIAS_TRAIN_STOPS_ROUTES).build()).eq(TrainStop_Table.stopId.withTable(NameAlias.builder(ALIAS_TRAIN_STOPS).build())))
                .join(Route.class, Join.JoinType.LEFT_OUTER).as(ALIAS_ROUTES)
                .on(TrainStop_Route_Table.route__id.withTable(NameAlias.builder(ALIAS_TRAIN_STOPS_ROUTES).build()).eq(Route_Table._id.withTable(NameAlias.builder(ALIAS_ROUTES).build())))
                .where(conditions)
                .orderBy(TrainStop_Table.directionId.withTable(NameAlias.builder(ALIAS_TRAIN_STOPS).build()), true)
                .groupBy(TrainStop_Table.mapId.withTable(NameAlias.builder(ALIAS_TRAIN_STOPS).build()))
                .async()
                .queryListResultCallback(callback)
                .error(callback)
                .success(callback)
                .execute();
    }

}
