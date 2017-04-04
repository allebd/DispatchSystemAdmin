package com.allebd.dispatchsystemadmin.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.allebd.dispatchsystemadmin.R;
import com.allebd.dispatchsystemadmin.data.DataManager;
import com.allebd.dispatchsystemadmin.data.models.RequestObject;
import com.allebd.dispatchsystemadmin.ui.adapter.RequestListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import javax.inject.Inject;

public class ManageRequestsActivity extends AppCompatActivity implements DataManager.requestListener{

    @Inject
    private DataManager dataManager;
    private String  userid;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_requests);

        userid = getIntent().getStringExtra("id");
        dataManager.queryForRequests(userid);
    }

    private void doRest(ArrayList<RequestObject> requests) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        if (requests != null) {
            if (requests.size() > 0) {
                RequestListAdapter reminderListAdapter = new RequestListAdapter(requests);
                rv.setAdapter(reminderListAdapter);

                reminderListAdapter.setOnItemClickListener(new RequestListAdapter.MyClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {

                    }
                });
            }
        }
    }

    @Override
    public void onRequestsLoaded(ArrayList<RequestObject> requests) {
        doRest(requests);
    }

    @Override
    public void onRequestAdded(RequestObject request) {

    }
}
