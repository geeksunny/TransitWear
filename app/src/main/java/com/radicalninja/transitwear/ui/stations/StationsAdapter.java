package com.radicalninja.transitwear.ui.stations;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.radicalninja.transitwear.R;
import com.radicalninja.transitwear.data.model.Stop;
import com.radicalninja.transitwear.ui.RecyclerItemClickListener;
import com.radicalninja.transitwear.ui.UiManager;

import java.util.ArrayList;
import java.util.List;

public class StationsAdapter extends RecyclerView.Adapter<StationsAdapter.ViewHolder> {

    // TODO: add list sorting

    final Context context;
    final LayoutInflater inflater;
    final List<Stop> stops = new ArrayList<>();

    public StationsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void add(final List<Stop> stops) {
        this.stops.addAll(stops);
        notifyDataSetChanged();
    }

    public Stop getItem(final int position) {
        return stops.get(position);
    }

    @Override
    public int getItemCount() {
        return stops.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.stop_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Stop stop = stops.get(position);
        holder.name.setText(stop.getStationName());
    }

    public StopsItemClickListener clickListener() {
        return new StopsItemClickListener(context);
    }

    class StopsItemClickListener extends RecyclerItemClickListener {

        public StopsItemClickListener(Context context) {
            super(context);
        }

        @Override
        public void onItemClick(View view, int position) {
            final Stop stop = getItem(position);
            UiManager.INSTANCE.toPredictionsFragment(stop);
        }

        @Override
        public void onItemLongClick(View view, int position) {

        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.stopName);
        }
    }

}
