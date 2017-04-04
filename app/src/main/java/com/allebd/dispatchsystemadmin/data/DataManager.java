package com.allebd.dispatchsystemadmin.data;


import com.allebd.dispatchsystemadmin.data.models.Ambulance;
import com.allebd.dispatchsystemadmin.data.models.RequestObject;

import java.util.ArrayList;

public interface DataManager {

    void queryForRequests(String hospitalId);

    void respondToRequest(RequestObject requestObject);

    void createAmbulances(Ambulance ambulance);

    void listAmbulances(String hospitalId);

    interface requestListener {
        void onRequestsLoaded(ArrayList<RequestObject> requests);

        void onRequestAdded(RequestObject request);
    }

    interface ambulanceListener {
        void onAmbulancesLoaded(ArrayList<Ambulance> ambulances);
    }
}
