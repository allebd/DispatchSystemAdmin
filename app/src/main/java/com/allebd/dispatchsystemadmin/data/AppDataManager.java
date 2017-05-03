package com.allebd.dispatchsystemadmin.data;

import com.allebd.dispatchsystemadmin.data.models.Ambulance;
import com.allebd.dispatchsystemadmin.data.models.Hospital;
import com.allebd.dispatchsystemadmin.data.models.RequestObject;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AppDataManager implements DataManager.Operations {

    private DatabaseReference reference;
    private DataManager.RequestListener requestListener;
    private DataManager.AmbulanceListener ambulanceListener;
    private DataManager.UserListener userListener;

    public AppDataManager() {
        reference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void storeUserInfo(Hospital hospital, String uid) {
        reference.child("hospitals").child("details").child(uid).setValue(hospital);
    }

    @Override
    public void queryForUserInfo(String uid) {
        reference.child("hospitals").child("details").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Hospital hospital = dataSnapshot.getValue(Hospital.class);
                userListener.onUserInfoLoaded(hospital);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void setUserListener(DataManager.UserListener listener) {
        this.userListener = listener;
    }

    @Override
    public void updateLocation(double[] latLng, String uid) {
        HashMap<String, Double> locationObject = new HashMap<>();
        locationObject.put("latitude", latLng[0]);
        locationObject.put("longitude", latLng[1]);
        reference.child("hospitals").child("location").child("uid").setValue(locationObject);
    }

    @Override
    public void setRequestListener(DataManager.RequestListener listener) {
        this.requestListener = listener;
    }

    @Override
    public void setAmbulanceListener(DataManager.AmbulanceListener listener) {
        this.ambulanceListener = listener;
    }

    @Override
    public void queryForRequests(String uid) {
        reference.child("hospitals").child("requests").child(uid).addValueEventListener(new ValueEventListener() {
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
    public void listenForRequests(String uid) {
        reference.child("hospitals").child("requests").child(uid).addChildEventListener(new ChildEventListener() {
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
    public void listAmbulances(String uid) {
        reference.child("ambulances").child(uid).addValueEventListener(new ValueEventListener() {
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
