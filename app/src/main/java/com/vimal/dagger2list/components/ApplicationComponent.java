package com.vimal.dagger2list.components;

import android.content.Context;

import com.vimal.dagger2list.AppController;
import com.vimal.dagger2list.Interface.APInterface;
import com.vimal.dagger2list.MainActivity;
import com.vimal.dagger2list.di.module.ContextModule;
import com.vimal.dagger2list.di.module.RetrofitModule;
import com.vimal.dagger2list.di.qualifier.ApplicationContext;
import com.vimal.dagger2list.di.scope.ApplicationScope;

import dagger.Component;
import dagger.Module;

@ApplicationScope
@Component (modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    public APInterface getAPInterface();

    @ApplicationContext
    public Context getContext();

    public void InjectApplication(AppController appController);
}
