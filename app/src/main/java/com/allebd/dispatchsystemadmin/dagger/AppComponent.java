package com.allebd.dispatchsystemadmin.dagger;

import com.allebd.dispatchsystemadmin.ui.auth.RegisterActivity;
import com.allebd.dispatchsystemadmin.ui.request.DashboardActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Digz on 04/04/2017.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(DashboardActivity activity);
    void inject(RegisterActivity target);
}
