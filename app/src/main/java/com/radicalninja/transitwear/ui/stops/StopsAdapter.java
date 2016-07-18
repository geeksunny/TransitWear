package com.radicalninja.transitwear.ui.stops;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.radicalninja.transitwear.R;
import com.radicalninja.transitwear.data.model.Stop;

import java.util.ArrayList;
import java.util.List;

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.ViewHolder> {

    // TODO: add list sorting

    final Context context;
    final LayoutInflater inflater;
    final List<Stop> stops = new ArrayList<>();

    public StopsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void add(final List<Stop> stops) {
        this.stops.addAll(stops);
        notifyDataSetChanged();
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
        holder.direction.setText(stop.getDirectionId().toString());
        holder.name.setText(stop.getStopName());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView direction, name;

        public ViewHolder(View itemView) {
            super(itemView);
            direction = (TextView) itemView.findViewById(R.id.stopDirection);
            name = (TextView) itemView.findViewById(R.id.stopName);
        }
    }

}
