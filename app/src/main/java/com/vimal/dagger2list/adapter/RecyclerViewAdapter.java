package com.vimal.dagger2list.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vimal.dagger2list.R;
import com.vimal.dagger2list.data.StarWars;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private RecyclerViewAdapter.ClickListener clickListener;
    private List<StarWars.People> data;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener){
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_list_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.txtName.setText(data.get(position).name);
        viewHolder.txtBirthYear.setText(data.get(position).birthYear);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName;
        private TextView txtBirthYear;
        private ConstraintLayout constraintLayoutContainer;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtBirthYear = itemView.findViewById(R.id.txtBirthYear);
            constraintLayoutContainer = itemView.findViewById(R.id.constraintLayout);
            constraintLayoutContainer.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            clickListener.launchIntent(data.get(getAdapterPosition()).films.get(0));
                        }
                    }
            );
        }
    }

    public interface ClickListener {
        void launchIntent(String filmName);
    }

    public void setData(List<StarWars.People> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
