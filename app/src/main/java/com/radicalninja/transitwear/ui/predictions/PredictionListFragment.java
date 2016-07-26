package com.radicalninja.transitwear.ui.predictions;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.radicalninja.transitwear.R;
import com.radicalninja.transitwear.data.api.TrainApi;
import com.radicalninja.transitwear.data.api.train.ArrivalResponse;
import com.radicalninja.transitwear.data.db.QueryCallback;
import com.radicalninja.transitwear.data.db.TransitDB;
import com.radicalninja.transitwear.data.model.Stop;
import com.radicalninja.transitwear.data.model.TrainStop;
import com.radicalninja.transitwear.ui.UiManager;
import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PredictionListFragment extends Fragment {

    private final static String TAG = PredictionListFragment.class.getSimpleName();
    private static final String KEY_STOP = "Stop";

    public static PredictionListFragment newInstance(final Stop stop) {
        final PredictionListFragment fragment = new PredictionListFragment();
        final Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_STOP, (Parcelable) stop);
        fragment.setArguments(bundle);
        return fragment;
    }

    private final TrainApi api = new TrainApi();

    private StopDetailsView stopDetailsView;
    private RecyclerView predictionListView;
    private PredictionsAdapter adapter;
    private Stop stop;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        stop = getArguments().getParcelable(KEY_STOP);

        final View layout = inflater.inflate(R.layout.prediction_list_fragment, container, false);
        stopDetailsView = (StopDetailsView) layout.findViewById(R.id.stopDetails);

        predictionListView = (RecyclerView) layout.findViewById(R.id.predictionRecyclerView);
        predictionListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PredictionsAdapter(getActivity());
        predictionListView.setAdapter(adapter);

        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        UiManager.INSTANCE.setTitle(stop.getStopName());
        stopDetailsView.setStop(stop);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_prediction_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_reverse) {
            getReverseArrivals();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        getTrainArrivals();
    }

    private void getTrainArrivals() {
        UiManager.INSTANCE.startLoading();
        api.getTrainArrivals(stop, apiResponseCallback);
    }

    private void getReverseArrivals() {
        TransitDB.getTrainStopReverseDirection((TrainStop) stop, reverseQueryCallback);
    }

    final Callback<ArrivalResponse> apiResponseCallback = new Callback<ArrivalResponse>() {
        @Override
        public void onResponse(Call<ArrivalResponse> call, Response<ArrivalResponse> response) {
            if (null != response && null != response.body() && null != response.body().getArrivalPredictions() && response.body().getArrivalPredictions().size() > 0) {
                adapter.set(response.body().getArrivalPredictions());
                stopDetailsView.setStop(stop);
                final String msg = String.format(Locale.US, "%d items received.", response.body().getArrivalPredictions().size());
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "No response objects!", Toast.LENGTH_SHORT).show();
            }
            UiManager.INSTANCE.stopLoading();
        }

        @Override
        public void onFailure(Call<ArrivalResponse> call, Throwable t) {
            Log.e(TAG, "API Error", t);
            Toast.makeText(getActivity(), "API ERROR", Toast.LENGTH_SHORT).show();
            UiManager.INSTANCE.stopLoading();
        }
    };

    private QueryCallback.ResultCallback<TrainStop> reverseQueryCallback = new QueryCallback.SimpleCallback<TrainStop>() {
        @Override
        public void onError(Transaction transaction, Throwable error) {
            super.onError(transaction, error);
        }

        @Override
        public void onQueryResult(QueryTransaction transaction, @NonNull CursorResult<TrainStop> tResult) {
            super.onQueryResult(transaction, tResult);
            final TrainStop result = tResult.toModel();
            if (null != tResult.toModel()) {
                stop = result;
                getTrainArrivals();
            }
        }

        @Override
        public void onSuccess(Transaction transaction) {
            super.onSuccess(transaction);
        }
    };

}
