package com.allebd.dispatchsystemadmin;

import android.app.Application;

import com.allebd.dispatchsystemadmin.dagger.AppComponent;
import com.allebd.dispatchsystemadmin.dagger.AppModule;
import com.allebd.dispatchsystemadmin.dagger.DaggerAppComponent;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Digz on 04/04/2017.
 */

public class DispatchAdminApp extends Application {
    private static DispatchAdminApp instance = new DispatchAdminApp();
    private static AppComponent appComponent;

    public static DispatchAdminApp getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        getAppComponent();
        FirebaseAuth.getInstance();
    }

    public AppComponent getAppComponent(){
        if (appComponent ==null){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }
}
