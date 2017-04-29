package com.allebd.dispatchsystemadmin.dagger;

import android.content.Context;

import com.allebd.dispatchsystemadmin.DispatchAdminApp;
import com.allebd.dispatchsystemadmin.data.AppDataManager;
import com.allebd.dispatchsystemadmin.data.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final DispatchAdminApp app;

    public AppModule(DispatchAdminApp app){
        this.app = app;
    }


    @Provides
    @Singleton
    public DataManager providesDataManagerOps(){
        return new AppDataManager();
    }
}
