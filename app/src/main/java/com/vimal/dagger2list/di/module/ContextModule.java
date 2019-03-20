package com.vimal.dagger2list.di.module;

import android.content.Context;

import com.vimal.dagger2list.di.qualifier.ApplicationContext;
import com.vimal.dagger2list.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationContext
    @ApplicationScope
    public Context provideContext(){
        return context;
    }
}
