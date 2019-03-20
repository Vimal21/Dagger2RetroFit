package com.vimal.dagger2list.components;

import com.vimal.dagger2list.DetailActivity;
import com.vimal.dagger2list.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component( dependencies = ApplicationComponent.class)
public interface DetailActivityComponent {

    void injectDetailActivity(DetailActivity activity);
}
