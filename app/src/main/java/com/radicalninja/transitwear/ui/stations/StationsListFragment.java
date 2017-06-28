package com.radicalninja.transitwear.ui.stations;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.radicalninja.transitwear.data.db.QueryCallback;
import com.radicalninja.transitwear.data.db.TransitDB;
import com.radicalninja.transitwear.data.model.Route;
import com.radicalninja.transitwear.data.model.Stop;
import com.radicalninja.transitwear.data.model.TrainStop_Route;
import com.radicalninja.transitwear.ui.UiManager;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StationsListFragment extends Fragment {

    private static final String KEY_ROUTE_ID = "RouteIdNum";

    public static StationsListFragment newInstance(final Route route) {
        final StationsListFragment fragment = new StationsListFragment();
        final Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_ROUTE_ID, route);
        fragment.setArguments(bundle);
        return fragment;
    }

    private StationsAdapter adapter;

    private Route route;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        adapter = new StationsAdapter(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        route = getArguments().getParcelable(KEY_ROUTE_ID);

        final RecyclerView view = new RecyclerView(getActivity());
        final RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        view.setLayoutManager(new LinearLayoutManager(getActivity()));
        view.setAdapter(adapter);
        view.addOnItemTouchListener(adapter.clickListener());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UiManager.INSTANCE.setTitle(route.getLongName());
        retrieveStopList();
    }

    private void retrieveStopList() {
        switch (route.getType()) {
            case BUS:
                retrieveBusStopList();
                break;
            case TRAIN:
                retrieveTrainStopList();
        }
    }

    private void retrieveBusStopList() {

    }

    private QueryCallback.ListResultCallback<TrainStop_Route> trainQueryCallback = new QueryCallback.SimpleListCallback<TrainStop_Route>() {
        @Override
        public void onError(Transaction transaction, Throwable error) {
            Log.e("abc", "trainStop_Route ERROR");
        }

        @Override
        public void onListQueryResult(QueryTransaction transaction, @Nullable List<TrainStop_Route> tResult) {
            if (null == tResult || tResult.size() == 0) {
                // TODO: handle this case? This shouldn't happen though.
                return;
            }
            final List<Stop> stops = new ArrayList<>(tResult.size());
            for (final TrainStop_Route stop : tResult) {
                stops.add(stop.getTrainStop());
            }
            Collections.sort(stops, new Comparator<Stop>() {
                @Override
                public int compare(Stop o1, Stop o2) {
                    // TODO: try/catch?
                    return Integer.compare(o1.getStopId(), o2.getStopId());
                }
            });
            adapter.add(stops);
        }

        @Override
        public void onSuccess(Transaction transaction) {
            Log.e("abc", "trainStop_Route SUCCESS");
        }
    };

    private void retrieveTrainStopList() {
        TransitDB.getStationsForRoute(route.get_id(), trainQueryCallback);
    }

}
