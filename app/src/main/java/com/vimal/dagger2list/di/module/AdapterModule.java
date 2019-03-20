package com.vimal.dagger2list.di.module;

import com.vimal.dagger2list.MainActivity;
import com.vimal.dagger2list.adapter.RecyclerViewAdapter;
import com.vimal.dagger2list.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getRecyclerViewList(RecyclerViewAdapter.ClickListener clickListener){
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainActivity mainActivity){
        return mainActivity;
    }
}
