package com.radicalninja.transitwear.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.radicalninja.transitwear.data.model.Route;
import com.radicalninja.transitwear.ui.UiManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import java.util.List;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private HomeGridManager gridManager;

    public HomeFragment() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final RecyclerView view = new RecyclerView(getContext());
        final ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);

        gridManager = new HomeGridManager(getContext(), view);
        gridManager.setSpanCount(3);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        retrieveRouteList();
    }

    private void displayRoutes(final List<Route> routes) {
        if (null == routes || routes.size() == 0) {
            // toast no routes found
        }

        gridManager.addBusRoutes(routes);
        gridManager.addTrainRoutes(routes);
        UiManager.INSTANCE.stopLoading();
    }

    private void retrieveRouteList() {
        SQLite.select()
                .from(Route.class)
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<Route>() {
                    @Override
                    public void onListQueryResult(QueryTransaction transaction, @Nullable List<Route> tResult) {
                        displayRoutes(tResult);
                    }
                }).execute();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
