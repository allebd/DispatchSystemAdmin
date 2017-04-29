package com.allebd.dispatchsystemadmin.ui.request;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.allebd.dispatchsystemadmin.DispatchAdminApp;
import com.allebd.dispatchsystemadmin.R;
import com.allebd.dispatchsystemadmin.data.DataManager;
import com.allebd.dispatchsystemadmin.data.models.RequestObject;
import com.allebd.dispatchsystemadmin.ui.request.adapter.RequestListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

public class ManageRequestsActivity extends AppCompatActivity implements DataManager.RequestListener {

    @Inject
    public DataManager dataManager;
    private RecyclerView rv;
    private ProgressDialog progressDialog;
    private String userid;
    private FirebaseAuth.AuthStateListener listener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {
                return;
            }
            userid = user.getUid();
            dataManager.queryForRequests(userid);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_requests);

        ((DispatchAdminApp) getApplication()).getAppComponent().inject(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading requests");
        progressDialog.show();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(listener);

        rv = (RecyclerView) findViewById(R.id.request_rv);
        dataManager.setRequestListener(this);
    }

    private void doRest(final ArrayList<RequestObject> requests) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);

        if (requests == null || requests.size() == 0) return;

        RequestListAdapter requestListAdapter = new RequestListAdapter(requests);
        rv.setAdapter(requestListAdapter);

        requestListAdapter.setOnItemClickListener(new RequestListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                RequestObject requestObject = requests.get(position);

                if (requestObject.getStatus() == 0) {
                    showRequestInfo(requestObject);
                }
            }
        });
    }

    @Override
    public void onRequestsLoaded(ArrayList<RequestObject> requests) {
        Collections.reverse(requests);
        progressDialog.dismiss();
        doRest(requests);
    }

    @Override
    public void onRequestAdded(RequestObject request) {
        showRequestInfo(request);
    }

    private void showRequestInfo(final RequestObject request) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You have a new request, would you like to accept this request?")
                .setTitle("New Request");
        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.setStatus(1);
                dataManager.respondToRequest(request);
            }
        });

        builder.setNegativeButton("Reject", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.setStatus(-1);
                dataManager.respondToRequest(request);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
