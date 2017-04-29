package com.allebd.dispatchsystemadmin.data;

import com.allebd.dispatchsystemadmin.data.models.Ambulance;
import com.allebd.dispatchsystemadmin.data.models.RequestObject;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AppDataManager implements DataManager {

    private DatabaseReference reference;
    private RequestListener requestListener;
    private AmbulanceListener ambulanceListener;

    public AppDataManager() {
        reference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void setRequestListener(RequestListener listener) {
        this.requestListener = listener;
    }

    @Override
    public void setAmbulanceListener(AmbulanceListener listener) {
        this.ambulanceListener = listener;
    }

    @Override
    public void queryForRequests(String hospitalId) {
        reference.child("hospitals").child("requests").child(hospitalId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<RequestObject> requests = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    RequestObject request = snapshot.getValue(RequestObject.class);
                    requests.add(request);
                }
                requestListener.onRequestsLoaded(requests);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void listenForRequests(String hospitalId) {
        reference.child("hospitals").child("requests").child(hospitalId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                RequestObject request = dataSnapshot.getValue(RequestObject.class);
                requestListener.onRequestAdded(request);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void respondToRequest(RequestObject request) {
        reference.child("hospitals").child("requests").child(request.getHospitalId()).child(request.getUid()).setValue(request);
        reference.child("users").child("requests").child(request.getUserId()).child(request.getUid()).setValue(request);
    }

    @Override
    public void createAmbulances(Ambulance ambulance) {
        String key = reference.child("ambulances").child(ambulance.getHospitalId()).push().getKey();
        ambulance.setId(key);
        reference.child("ambulances").child(ambulance.getHospitalId()).child(key).setValue(ambulance);
    }

    @Override
    public void listAmbulances(String hospitalId) {
        reference.child("ambulances").child(hospitalId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Ambulance> ambulances = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Ambulance ambulance = snapshot.getValue(Ambulance.class);
                    ambulances.add(ambulance);
                }

                ambulanceListener.onAmbulancesLoaded(ambulances);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
