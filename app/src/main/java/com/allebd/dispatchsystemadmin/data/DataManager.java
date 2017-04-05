package com.allebd.dispatchsystemadmin.data;


import com.allebd.dispatchsystemadmin.data.models.Ambulance;
import com.allebd.dispatchsystemadmin.data.models.RequestObject;
import com.allebd.dispatchsystemadmin.data.models.Users;

import java.util.ArrayList;

public interface DataManager {

    void setRequestListener(RequestListener listener);

    void setAmbulanceListener(AmbulanceListener listener);

    void queryForRequests(String hospitalId);

    void listenForRequests(String hospitalId);

    void respondToRequest(RequestObject requestObject);

    void createAmbulances(Ambulance ambulance);

    void listAmbulances(String hospitalId);

    interface RequestListener {
        void onRequestsLoaded(ArrayList<RequestObject> requests);

        void onRequestAdded(RequestObject request);
    }

    interface AmbulanceListener {
        void onAmbulancesLoaded(ArrayList<Ambulance> ambulances);
    }

    interface UserListener {
        void onUserInfoLoaded(Users user);

        void onRequestsLoaded(ArrayList<RequestObject> requestObject);
    }

    interface Operations {
        void storeUserInfo(Users user, String uid);

        void queryForUserInfo(String userId);

        void queryForRequests(String userId);

        void setUserListener(UserListener listener);
    }
}
