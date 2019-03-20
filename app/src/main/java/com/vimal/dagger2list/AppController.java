package com.vimal.dagger2list;

import android.app.Activity;
import android.app.Application;

import com.vimal.dagger2list.components.ApplicationComponent;
import com.vimal.dagger2list.components.DaggerApplicationComponent;
import com.vimal.dagger2list.di.module.ContextModule;

public class AppController extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                                .contextModule(new ContextModule(this)).build();
        applicationComponent.InjectApplication(this);
    }

    public static AppController get(Activity activity){
        return (AppController) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
