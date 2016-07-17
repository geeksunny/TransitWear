package com.radicalninja.transitwear.ui.home;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.radicalninja.transitwear.R;
import com.radicalninja.transitwear.data.model.Route;

import java.util.ArrayList;
import java.util.List;

public class HomeGridAdapter extends RecyclerView.Adapter<HomeGridAdapter.ViewHolder> {

    static final int TYPE_UNKNOWN = -1;
    static final int TYPE_HEADER = 0;
    static final int TYPE_BUS_ROUTE = 1;
    static final int TYPE_TRAIN_ROUTE = 2;

    final Context context;
    final LayoutInflater inflater;
    final List<Route> busRoutes = new ArrayList<>();
    final List<Route> trainRoutes = new ArrayList<>();

    private int spanCount = 1;

    public HomeGridAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addBusRoutes(final List<Route> busRoutes) {
        this.busRoutes.addAll(busRoutes);
        notifyDataSetChanged();
    }

    public void addTrainRoutes(final List<Route> trainRoutes) {
        this.trainRoutes.addAll(trainRoutes);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return busRoutes.size() + trainRoutes.size() +
                getHeaderOffset(TYPE_BUS_ROUTE | TYPE_TRAIN_ROUTE);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return TYPE_HEADER;
        } else if (isBusRoute(position)) {
            return TYPE_BUS_ROUTE;
        } else if (isTrainRoute(position)) {
            return TYPE_TRAIN_ROUTE;
        } else {
            return TYPE_UNKNOWN;
        }
    }

    private int getHeaderOffset(final int itemViewType) {
        int headers = 0;
        if ((itemViewType & TYPE_BUS_ROUTE) == TYPE_BUS_ROUTE) {
            headers += (busRoutes.size() > 0) ? 1 : 0;
        }
        if ((itemViewType & TYPE_TRAIN_ROUTE) == TYPE_TRAIN_ROUTE) {
            headers += (trainRoutes.size() > 0) ? 1 : 0;
        }
        return headers;
    }

    public Route getItem(int position) {
        if (isBusRoute(position)) {
            // tODO: make the -1 offset programmatically calculated
            return busRoutes.get(position - getHeaderOffset(TYPE_BUS_ROUTE));
        } else if (isTrainRoute(position)) {
            return trainRoutes.get(position - busRoutes.size() - getHeaderOffset(TYPE_BUS_ROUTE | TYPE_TRAIN_ROUTE));
        } else {
            return null;
        }
    }

    public String getHeader(int position) {
        if (position == 0 && getHeaderOffset(TYPE_BUS_ROUTE) == 1) {
            return context.getString(R.string.header_bus_routes);
        } else if (position == busRoutes.size() + getHeaderOffset(TYPE_BUS_ROUTE)) {
            return context.getString(R.string.header_train_routes);
        } else {
            return context.getString(R.string.header_unknown);
        }
    }

    public boolean isHeader(int position) {
        // Bus header: 1st item
        // Train header: After bus header + bus routes
        return position == 0 || position == (busRoutes.size() + getHeaderOffset(TYPE_BUS_ROUTE));
    }

    public boolean isBusRoute(int position) {
        final int headers = getHeaderOffset(TYPE_BUS_ROUTE);
        return position >= headers && position < (busRoutes.size() + headers);
    }

    public boolean isTrainRoute(int position) {
        final int headers = getHeaderOffset(TYPE_BUS_ROUTE | TYPE_TRAIN_ROUTE);
        return position >= (busRoutes.size() + headers) &&
                position < (busRoutes.size() + trainRoutes.size() + headers);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final int layoutResId;
        switch (viewType) {
            case TYPE_BUS_ROUTE:
                layoutResId = R.layout.route_list_item;
                break;
            case TYPE_TRAIN_ROUTE:
                layoutResId = R.layout.route_grid_item;
                break;
            case TYPE_HEADER:
            default:
                layoutResId = R.layout.route_header_item;
        }
        final View view = inflater.inflate(layoutResId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_BUS_ROUTE:
            case TYPE_TRAIN_ROUTE:
                holder.setupView(getItem(position));
                break;
            case TYPE_HEADER:
            case TYPE_UNKNOWN:
            default:
                holder.setupView(getHeader(position));
        }
    }

    public void makeCircleView() {
        // TODO: Programmatically create the Circle drawable
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    public HomeGridSpanSizeLookup spanSizeLookup() {
        return new HomeGridSpanSizeLookup();
    }

    class HomeGridSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

        @Override
        public int getSpanSize(int position) {
            switch (getItemViewType(position)) {
                case TYPE_TRAIN_ROUTE:
                    return 1;
                case TYPE_BUS_ROUTE:
                case TYPE_HEADER:
                case TYPE_UNKNOWN:
                    return spanCount;
                default:
                    return -1;
            }
        }
    }

    // TODO: Separate ViewHolder classes for each type

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView title, name;
        final View circle;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.route_title);
            name = (TextView) itemView.findViewById(R.id.route_name);
            circle = itemView.findViewById(R.id.route_circle);
        }

        void setupView(final Route route) {
            // TODO: Set up view data for routes
            switch (getItemViewType()) {
                case TYPE_BUS_ROUTE:
                    title.setText(route.getShortName());
                    name.setText(route.getLongName());
                    break;
                case TYPE_TRAIN_ROUTE:
                    title.setText(route.getShortName());
                    name.setText(route.getLongName());
                    circle.getBackground().setColorFilter(route.getColorHex(), PorterDuff.Mode.MULTIPLY);
                    break;
                default:
                    // TODO: handle this.
                    break;
            }
        }

        void setupView(final String title) {
            // TODO: Set up view data for header.
            this.title.setText(title);
        }

    }

}
