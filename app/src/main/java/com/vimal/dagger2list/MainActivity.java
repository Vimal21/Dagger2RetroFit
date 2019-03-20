package com.vimal.dagger2list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.vimal.dagger2list.Interface.APInterface;
import com.vimal.dagger2list.adapter.RecyclerViewAdapter;
import com.vimal.dagger2list.components.ApplicationComponent;
import com.vimal.dagger2list.components.DaggerApplicationComponent;
import com.vimal.dagger2list.components.DaggerMainActivityComponent;
import com.vimal.dagger2list.components.MainActivityComponent;
import com.vimal.dagger2list.data.StarWars;
import com.vimal.dagger2list.di.module.MainActivityContextModule;
import com.vimal.dagger2list.di.qualifier.ActivityContext;
import com.vimal.dagger2list.di.qualifier.ApplicationContext;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener {

    private RecyclerView recyclerView;
    MainActivityComponent mainActivityComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    public APInterface apInterface;

    @Inject
    @ApplicationContext
    public Context context;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ApplicationComponent applicationComponent = AppController.get(this).getApplicationComponent();

        mainActivityComponent = DaggerMainActivityComponent.builder()
                                .mainActivityContextModule(new MainActivityContextModule(this))
                                .applicationComponent(applicationComponent)
                                .build();

        mainActivityComponent.injectMainActivity(this);

        recyclerView.setAdapter(recyclerViewAdapter);

        apInterface.getPeople("json").enqueue(new Callback<StarWars>() {
            @Override
            public void onResponse(@EverythingIsNonNull Call<StarWars> call,@EverythingIsNonNull Response<StarWars> response) {
                    populateRecyclerView(response.body().results);
            }

            @Override
            public void onFailure(Call<StarWars> call, Throwable t) {

            }
        });
    }

    private void populateRecyclerView(List<StarWars.People> response) {
        recyclerViewAdapter.setData(response);
    }

    @Override
    public void launchIntent(String url) {
        Toast.makeText(context, "RecyclerView Row selected", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(activityContext, DetailActivity.class).putExtra("url", url));
    }
}
