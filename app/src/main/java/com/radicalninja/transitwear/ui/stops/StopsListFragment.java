package com.radicalninja.transitwear.ui.stops;

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

import com.radicalninja.transitwear.data.model.Route;
import com.radicalninja.transitwear.data.model.Stop;
import com.radicalninja.transitwear.data.model.TrainStop_Route;
import com.radicalninja.transitwear.data.model.TrainStop_Route_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StopsListFragment extends Fragment {

    private static final String KEY_ROUTE_ID = "RouteIdNum";

    public static StopsListFragment newInstance(final Route route) {
        final StopsListFragment fragment = new StopsListFragment();
        final Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_ROUTE_ID, route);
        fragment.setArguments(bundle);
        return fragment;
    }

    private StopsAdapter adapter;

    private Route route;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        adapter = new StopsAdapter(getActivity());
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

    private void retrieveTrainStopList() {
        SQLite.select()
                .from(TrainStop_Route.class)
                .where(TrainStop_Route_Table.route__id.eq(route.get_id()))
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<TrainStop_Route>() {
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
                        adapter.add(stops);
                    }
                }).error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        Log.e("abc", "trainStop_Route ERROR");
                    }
                }).success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        Log.e("abc", "trainStop_Route SUCCESS");
                    }
                }).execute();
    }

}
