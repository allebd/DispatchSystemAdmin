package com.allebd.dispatchsystemadmin.ui.ambulance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.allebd.dispatchsystemadmin.R;
import com.allebd.dispatchsystemadmin.data.DataManager;
import com.allebd.dispatchsystemadmin.data.models.Ambulance;
import com.allebd.dispatchsystemadmin.ui.ambulance.adapter.AmbulanceListAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

public class ManageAmbulanceActivity extends AppCompatActivity implements DataManager.AmbulanceListener, View.OnClickListener {

    @Inject
    public DataManager.Operations dataManager;
    private String userId;
    private RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_ambulance);

        rv = (RecyclerView) findViewById(R.id.ambulance_rv);
        userId = getIntent().getStringExtra("id");
        dataManager.setAmbulanceListener(this);
        dataManager.listAmbulances(userId);


        Button addAmbulance = (Button) findViewById(R.id.addAmbulance);
        addAmbulance.setOnClickListener(this);

    }


    @Override
    public void onAmbulancesLoaded(ArrayList<Ambulance> ambulances) {
        doRest(ambulances);
    }

    @Override
    public void onClick(View v) {

    }

    private void doRest(final ArrayList<Ambulance> ambulances) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);

        if (ambulances != null) {
            if (ambulances.size() > 0) {
                AmbulanceListAdapter ambulanceListAdapter = new AmbulanceListAdapter(ambulances, this);
                rv.setAdapter(ambulanceListAdapter);

                ambulanceListAdapter.setOnItemClickListener(new AmbulanceListAdapter.MyClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {


                    }
                });
            }
        }
    }
}
