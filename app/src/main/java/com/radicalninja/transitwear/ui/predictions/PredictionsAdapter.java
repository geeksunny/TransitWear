package com.radicalninja.transitwear.ui.predictions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.radicalninja.transitwear.R;
import com.radicalninja.transitwear.data.api.train.ArrivalPrediction;

import java.util.ArrayList;
import java.util.List;

public class PredictionsAdapter extends RecyclerView.Adapter<PredictionsAdapter.ViewHolder> {

    final Context context;
    final LayoutInflater inflater;
    final List<ArrivalPrediction> predictions = new ArrayList<>();

    public PredictionsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void set(final List<ArrivalPrediction> predictions) {
        this.predictions.clear();
        this.predictions.addAll(predictions);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return predictions.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.prediction_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ArrivalPrediction prediction = predictions.get(position);
        final String arrivalTime = PredictionUtils.getArrivalTimeString(prediction);
        holder.time.setText(arrivalTime);
        holder.title.setText(prediction.getStationName());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView title, time;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.predictionTitle);
            time = (TextView) itemView.findViewById(R.id.predictionTime);
        }
    }

}
