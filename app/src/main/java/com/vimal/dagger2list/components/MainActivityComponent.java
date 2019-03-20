package com.vimal.dagger2list.components;

import android.content.Context;

import com.vimal.dagger2list.MainActivity;
import com.vimal.dagger2list.di.module.AdapterModule;
import com.vimal.dagger2list.di.qualifier.ActivityContext;
import com.vimal.dagger2list.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component( modules = AdapterModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();

    void injectMainActivity(MainActivity activity);
}
