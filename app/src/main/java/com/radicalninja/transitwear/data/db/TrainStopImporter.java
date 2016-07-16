package com.radicalninja.transitwear.data.db;

import android.util.Log;

import com.opencsv.CSVReader;
import com.radicalninja.transitwear.App;
import com.radicalninja.transitwear.data.model.Route;
import com.radicalninja.transitwear.data.model.TrainStop;
import com.radicalninja.transitwear.data.model.Type;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TrainStopImporter {

    //TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO
    //TODO
    //TODO   Move this into a build flavor for purely local testing only!
    //TODO      Standard flavor will use an API endpoint for populating DBs.
    //TODO   Remove OpenCsv and the CSV file from the new standard flavor.
    //TODO
    //TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO

    private static final String TAG = TrainStopImporter.class.getSimpleName();

    // TODO – Look in to a cleaner way to handle this mapping that doesn't include Reflection
    private final String HEADER_STOPID = "STOP_ID";
    private final String HEADER_DIRECTION = "DIRECTION_ID";
    private final String HEADER_STOPNAME = "STOP_NAME";
    private final String HEADER_STATIONNAME = "STATION_NAME";
    private final String HEADER_STATIONDESC = "STATION_DESCRIPTIVE_NAME";
    private final String HEADER_MAPID = "MAP_ID";
    private final String HEADER_ADA = "ADA";
    private final String HEADER_RED = "RED";
    private final String HEADER_BLUE = "BLUE";
    private final String HEADER_GREEN = "G";
    private final String HEADER_BROWN = "BRN";
    private final String HEADER_PURPLE = "P";
    private final String HEADER_PURPLEEXP = "Pexp";
    private final String HEADER_YELLOW = "Y";
    private final String HEADER_PINK = "Pnk";
    private final String HEADER_ORANGE = "O";
    private final String HEADER_LOCATION = "Location";

    private Map<String, Integer> headerRowMap = new HashMap<>();

    public TrainStopImporter() {
        //
    }

    public void runImport() throws IOException {
        // todo: check if data is already imported, throw exception if yes
        // todo: clean this up
        importRoutes();
        importStops();
    }

    private void importRoutes() {
        Log.d(TAG, "Saving train routes with dbflow");
        new Route("red", "Red", "Red Line", Type.TRAIN).save();
        new Route("blue", "Blue", "Blue Line", Type.TRAIN).save();
        new Route("brn", "Brown", "Brown Line", Type.TRAIN).save();
        new Route("g", "Green", "Green Line", Type.TRAIN).save();
        new Route("org", "Orange", "Orange Line", Type.TRAIN).save();
        new Route("p", "Purple", "Purple Line", Type.TRAIN).save();
        new Route("pexp", "Purple Express", "Purple Line Express", Type.TRAIN).save();
        new Route("pink", "Pink", "Pink Line", Type.TRAIN).save();
        new Route("y", "Yellow", "Yellow Line", Type.TRAIN).save();
    }

    private void importStops() throws IOException {
        final InputStream csvInputStream = App.getInstance().getAssets().open("train_stops.csv");
        final CSVReader csvReader = new CSVReader(new InputStreamReader(csvInputStream));
        Log.d(TAG, "Beginning TrainStop Import Job from file (assets/train_stops.csv)");
        for (final String[] line : csvReader) {
            if (headerRowMap.isEmpty()) {
                // TODO: Map headers
                for (int i = 0; i < line.length; i++) {
                    final String cell = line[i];
                    headerRowMap.put(cell, i);
                }
                continue;
            }
            //////////
            try {
                final TrainStop stop = processLine(line);
                stop.save();
                Log.d(TAG, "Saving with dbflow");
                //final SqlResult sqlResult = manager.saveTrainStop(stop);
                //Log.d(TAG, String.format("Result: %s | %s", sqlResult.toString(), stop.toString()));
            } catch (Exception e) {
                Log.e(TAG, "Error when trying to parse a CSV line!", e);
            }
        }
        Log.d(TAG, "Finished with TrainStop Import Job!");
    }

    private TrainStop processLine(final String[] line) {
        return new TrainStop.Builder()
                .setStopId(line[get(HEADER_STOPID)])
                .setDirectionId(line[get(HEADER_DIRECTION)])
                .setStopName(line[get(HEADER_STOPNAME)])
                .setStationName(line[get(HEADER_STATIONNAME)])
                .setStationDescription(line[get(HEADER_STATIONDESC)])
                .setMapId(line[get(HEADER_MAPID)])
                .setIsAdaAccessible(line[get(HEADER_ADA)])
                .setRed(line[get(HEADER_RED)])
                .setBlue(line[get(HEADER_BLUE)])
                .setGreen(line[get(HEADER_GREEN)])
                .setBrown(line[get(HEADER_BROWN)])
                .setPurple(line[get(HEADER_PURPLE)])
                .setPurpleExpress(line[get(HEADER_PURPLEEXP)])
                .setYellow(line[get(HEADER_YELLOW)])
                .setPink(line[get(HEADER_PINK)])
                .setOrange(line[get(HEADER_ORANGE)])
                .setLocation(line[get(HEADER_LOCATION)])
                .build();
    }

    private int get(final String key) {
        return headerRowMap.get(key);
    }

}
