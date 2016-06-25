package com.radicalninja.transitwear.ui.predictions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.radicalninja.transitwear.R;
import com.radicalninja.transitwear.data.api.TrainApi;
import com.radicalninja.transitwear.data.api.train.ArrivalResponse;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PredictionListFragment extends Fragment {

    private final TrainApi api = new TrainApi();

    private RecyclerView predictionListView;
    private PredictionsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.prediction_list_fragment, container, false);

        predictionListView = (RecyclerView) layout.findViewById(R.id.predictionRecyclerView);
        predictionListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PredictionsAdapter(getActivity());
        predictionListView.setAdapter(adapter);

        return layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        api.getArrivals(new Callback<ArrivalResponse>() {
            @Override
            public void onResponse(Call<ArrivalResponse> call, Response<ArrivalResponse> response) {
                adapter.add(response.body().getArrivalPredictions());
                final String msg = String.format(Locale.US, "%d items received.", response.body().getArrivalPredictions().size());
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrivalResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
