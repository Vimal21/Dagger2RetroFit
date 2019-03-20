package com.vimal.dagger2list.di.module;

import android.content.Context;

import com.vimal.dagger2list.MainActivity;
import com.vimal.dagger2list.di.qualifier.ActivityContext;
import com.vimal.dagger2list.di.scope.ActivityScope;
import com.vimal.dagger2list.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {

    private MainActivity activity;
    public Context context;

    public MainActivityContextModule(MainActivity activity) {
        this.activity = activity;
        this.context = activity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity(){
        return activity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context providesContext(){
        return context;
    }
}
