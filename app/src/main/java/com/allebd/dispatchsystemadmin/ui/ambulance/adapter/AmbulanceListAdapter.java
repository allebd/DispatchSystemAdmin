package com.allebd.dispatchsystemadmin.ui.ambulance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allebd.dispatchsystemadmin.R;
import com.allebd.dispatchsystemadmin.data.models.Ambulance;

import java.util.ArrayList;

/**
 * Created by Digz on 02/04/2017.
 */

public class AmbulanceListAdapter extends RecyclerView.Adapter<AmbulanceListAdapter.ViewHolder> {
    private static MyClickListener myClickListener;
    ArrayList<Ambulance> ambulances;
    private Context context;

    public AmbulanceListAdapter(ArrayList<Ambulance> ambulances, Context context) {
        this.ambulances = ambulances;
        this.context = context;
    }

    public Ambulance getItem(int position) {
        return ambulances.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Ambulance ambulance = getItem(position);

        holder.driverName.setText(ambulance.getDriverName());
        holder.ambulanceDescription.setText(ambulance.getDescription());
        if (ambulance.isAssigned())holder.ambulanceStatus.setTextColor(context.getResources().getColor(R.color.colorAccent));
        holder.ambulanceStatus.setText(ambulance.getAssignedStatus());

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return ambulances.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }


    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView driverName, ambulanceDescription, ambulanceStatus;

        ViewHolder(View itemView) {
            super(itemView);
            driverName = (TextView) itemView.findViewById(R.id.driverName);
            ambulanceDescription = (TextView) itemView.findViewById(R.id.ambulanceDescription);
            ambulanceStatus = (TextView) itemView.findViewById(R.id.ambulanceStatus);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);

        }


    }
}


