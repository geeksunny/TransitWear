package com.radicalninja.transitwear.data.db;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = TransitDB.NAME, version = TransitDB.VERSION)
public class TransitDB {

    protected static final String NAME = "TransitWearDB";
    protected static final int VERSION = 1;

    // TODO add some general CRUD methods with generic DbFlow Transaction objects and callbacks
    // TODO create ModelView query objects

}
