package com.allebd.dispatchsystemadmin.data;


import com.allebd.dispatchsystemadmin.data.models.Ambulance;
import com.allebd.dispatchsystemadmin.data.models.Hospital;
import com.allebd.dispatchsystemadmin.data.models.RequestObject;

import java.util.ArrayList;

public interface DataManager {

    interface Operations {
        void storeUserInfo(Hospital user, String uid);

        void queryForUserInfo(String uid);

        void setUserListener(UserListener listener);

        void updateLocation(double[] point, String uid);

        void setRequestListener(RequestListener listener);

        void setAmbulanceListener(AmbulanceListener listener);

        void queryForRequests(String uid);

        void listenForRequests(String uid);

        void respondToRequest(RequestObject requestObject);

        void createAmbulances(Ambulance ambulance);

        void listAmbulances(String uid);
    }

    interface RequestListener {
        void onRequestsLoaded(ArrayList<RequestObject> requests);

        void onRequestAdded(RequestObject request);
    }

    interface AmbulanceListener {
        void onAmbulancesLoaded(ArrayList<Ambulance> ambulances);
    }

    interface UserListener {
        void onUserInfoLoaded(Hospital hospital);
    }
}