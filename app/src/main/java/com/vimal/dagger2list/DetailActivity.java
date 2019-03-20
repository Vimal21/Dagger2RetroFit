package com.vimal.dagger2list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.vimal.dagger2list.Interface.APInterface;
import com.vimal.dagger2list.components.ApplicationComponent;
import com.vimal.dagger2list.components.DaggerDetailActivityComponent;
import com.vimal.dagger2list.components.DetailActivityComponent;
import com.vimal.dagger2list.data.Film;
import com.vimal.dagger2list.di.qualifier.ActivityContext;
import com.vimal.dagger2list.di.qualifier.ApplicationContext;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    DetailActivityComponent detailActivityComponent;

    @Inject
    @ApplicationContext
    public Context context;

    @Inject
    public APInterface apInterface;

    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textView = findViewById(R.id.textView);

        String url = getIntent().getStringExtra("url");

        ApplicationComponent applicationComponent = AppController.get(this).getApplicationComponent();

        detailActivityComponent = DaggerDetailActivityComponent.builder()
                                    .applicationComponent(applicationComponent)
                                    .build();

        detailActivityComponent.injectDetailActivity(this);

        apInterface.getFilmData(url, "json").enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                Film films = response.body();
                String text = "Film name:\n" + films.title + "\nDirector:\n" + films.director;
                textView.setText(text);
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });
    }
}
