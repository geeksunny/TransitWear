package com.radicalninja.transitwear.db;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = TrainDB.NAME, version = TrainDB.VERSION)
public class TrainDB {

    protected static final String NAME = "TrainDatabase";
    protected static final int VERSION = 1;

    // TODO add some general CRUD methods with generic DbFlow Transaction objects and callbacks

}
