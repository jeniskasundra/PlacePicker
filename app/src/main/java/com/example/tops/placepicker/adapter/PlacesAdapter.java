package com.example.tops.placepicker.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tops.placepicker.R;
import com.example.tops.placepicker.model.PlaceData;
import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    private List<PlaceData> placeDataList;
    private Activity activity;

    public PlacesAdapter(Activity activity, List<PlaceData> placeDataList) {
        this.activity = activity;
        this.placeDataList = placeDataList;
    }

    @NonNull
    @Override
    public PlacesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_item_list_places, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesAdapter.ViewHolder viewHolder, int i) {
        final PlaceData orderData = placeDataList.get(i);
        viewHolder.tvPName.setText(orderData.getPname());
    }


    @Override
    public int getItemCount() {
        return placeDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPNo, tvPName, tvPDis, tvPQty;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPName = (TextView) itemView.findViewById(R.id.tvProductName);
        }
    }
}
