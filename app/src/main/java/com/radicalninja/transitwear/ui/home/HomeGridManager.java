package com.radicalninja.transitwear.ui.home;

import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.radicalninja.transitwear.data.model.Route;
import com.radicalninja.transitwear.ui.UiManager;

import java.util.List;

public class HomeGridManager {

    // TODO: Refactor Class-> abstract class ListGridManager & ListGridAdapter :: set map of viewtypes and span counts.

    private final static int DEFAULT_SPAN_COUNT = 2;

    private final Context context;
    private final RecyclerView recyclerView;
    private final HomeGridAdapter adapter;

    private GridLayoutManager layoutManager;

    public HomeGridManager(final Context context, final RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        adapter = new HomeGridAdapter(context);
        defaultConfig();
    }

    /**
     * Using a default configuration until this class is built out further.
     */
    private void defaultConfig() {
        layoutManager = new GridLayoutManager(context, DEFAULT_SPAN_COUNT);
        adapter.setSpanCount(DEFAULT_SPAN_COUNT);
        layoutManager.setSpanSizeLookup(adapter.spanSizeLookup());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new HomeGridItemDecoration(24, 12, 24, 6));
        recyclerView.addOnItemTouchListener(new HomeGridItemClickListener(context));
        // recyclerView.setItemAnimator();
    }

    public void setSpanCount(final int spanCount) {
        layoutManager.setSpanCount(spanCount);
        adapter.setSpanCount(spanCount);
    }

    public void addBusRoutes(final List<Route> routes) {
        adapter.addBusRoutes(routes);
    }

    public void addTrainRoutes(final List<Route> routes) {
        adapter.addTrainRoutes(routes);
    }

    public Route getItem(final int position) {
        return adapter.getItem(position);
    }

    class HomeGridItemClickListener extends RecyclerItemClickListener {

        // TODO: Implement long-click + drag for grid item movement and list rearrangement.

        public HomeGridItemClickListener(Context context) {
            super(context);
        }

        String getTitle(int position) {
            switch (adapter.getItemViewType(position)) {
                case HomeGridAdapter.TYPE_BUS_ROUTE:
                case HomeGridAdapter.TYPE_TRAIN_ROUTE:
                    final Route route = getItem(position);
                    return (null != route) ? route.getLongName() : "Null Route";
                case HomeGridAdapter.TYPE_HEADER:
                case HomeGridAdapter.TYPE_UNKNOWN:
                    return adapter.getHeader(position);
                default:
                    return "(None of the above!)";
            }
        }

        @Override
        void onItemClick(View view, int position) {
            final Route route = getItem(position);
            if (null != route) {
                UiManager.INSTANCE.toStopsFragment(route);
            }
        }

        @Override
        void onItemLongClick(View view, int position) {
            Snackbar.make(view, "Item long clicked: "+getTitle(position), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    class HomeGridItemDecoration extends RecyclerView.ItemDecoration {
        final int left, top, right, bottom;

        // TODO: Implement logic for only inner margins based on layout / adapter position
        public HomeGridItemDecoration(final int padding) {
            left = top = right = bottom = padding;
        }

        public HomeGridItemDecoration(final int left, final int top, final int right, final int bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //parent.getChildAdapterPosition(view);
            //super.getItemOffsets(outRect, view, parent, state);
            outRect.set(left, top, right, bottom);
        }
    }
}
