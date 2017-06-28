package com.radicalninja.transitwear.data.db;

import android.graphics.Color;

import com.radicalninja.transitwear.data.model.Route;
import com.radicalninja.transitwear.data.model.Stop;
import com.radicalninja.transitwear.data.model.Type;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.onebusaway.gtfs.impl.GtfsDaoImpl;
import org.onebusaway.gtfs.serialization.GtfsReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataImporter {

    private static final String DATA_DIR = "google_transit";

    // TODO: Determine if this should be kept and used in processRoutes()
//    private static final Map<String, Integer> TRAIN_COLORS = new HashMap<>(9);
//    static {
//        TRAIN_COLORS.put("red", 0xffee254c);
//        TRAIN_COLORS.put("blue", 0xff487a9b);
//        TRAIN_COLORS.put("brn", 0xffa1857d);
//        TRAIN_COLORS.put("g", 0xff2f9463);
//        TRAIN_COLORS.put("org", 0xfff16f20);
//        TRAIN_COLORS.put("p", 0xff6b668d);
//        TRAIN_COLORS.put("pexp", 0xff6b668d);
//        TRAIN_COLORS.put("pink", 0xfff8bbc7);
//        TRAIN_COLORS.put("y", 0xfff9f304);
//    }

    private final File inputPath;
    private final List<org.onebusaway.gtfs.model.Route> gtfsRoutes = new ArrayList<>();
    private final List<org.onebusaway.gtfs.model.Stop> gtfsStops = new ArrayList<>();
    private final List<org.onebusaway.gtfs.model.StopTime> gtfsStopTimes = new ArrayList<>();
    private final List<org.onebusaway.gtfs.model.Trip> gtfsTrips = new ArrayList<>();

    private final List<Route> newRoutes = new ArrayList<>();
    private final List<Stop> newStops = new ArrayList<>();

    public DataImporter() throws FileNotFoundException {
        inputPath = new File(DATA_DIR);
        if (!inputPath.exists() || !inputPath.isDirectory()) {
            throw new FileNotFoundException("GTFS data directory path is invalid.");
        }
    }

    private void read() throws IOException {
        final GtfsReader reader = new GtfsReader();
        final GtfsDaoImpl entityStore = new GtfsDaoImpl();
        reader.setInputLocation(inputPath);
        //reader.addEntityHandler(new EntityHandler)
        reader.setEntityStore(entityStore);

        reader.run();
        gtfsRoutes.addAll(entityStore.getAllRoutes());
        gtfsStops.addAll(entityStore.getAllStops());
        gtfsStopTimes.addAll(entityStore.getAllStopTimes());
        gtfsTrips.addAll(entityStore.getAllTrips());

        entityStore.clear();
        entityStore.close();
        reader.close();
    }

    public void performImport() throws IOException {
        read();
        processRoutes();
        processStops();
    }

    private <T extends BaseModel> void saveAll(final List<T> objects) {
        for (final BaseModel object : objects) {
            object.save();
        }
    }

    private void processRoutes() {
        for (final org.onebusaway.gtfs.model.Route route : gtfsRoutes) {
            final Type routeType;
            final int colorHex;
            switch (route.getType()) {
                case 1: // TRAIN
                    routeType = Type.TRAIN;
                    colorHex = Color.parseColor(route.getColor());
                    break;
                case 3: // BUS
                    routeType = Type.BUS;
                    colorHex = 0;
                    break;
                default:
                    continue;
            }
            final Route newRoute = new Route(route.getId().toString(), route.getShortName(), route.getLongName(), routeType, colorHex);
            newRoutes.add(newRoute);
        }
        saveAll(newRoutes);
    }

    private void processTrips() {

    }

    private void processStops() {
        final Map<String, Stop> index = new HashMap<>(gtfsStops.size());

    }

}
