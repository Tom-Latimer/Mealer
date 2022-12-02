package com.example.mealer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    Context context;
    ArrayList<Meal_Class> list;

    public SearchAdapter(Context context, ArrayList<Meal_Class> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.search_result,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meal_Class meal = list.get(position);
        holder.mealName.setText(meal.get_name());
        holder.mealPrice.setText(meal.get_price());
        holder.chefRating.setText("NOT IMPL.");
        holder.chefName.setText("NOT IMPL.");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mealName, mealPrice, chefName, chefRating;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.mealNameSearch);
            mealPrice = itemView.findViewById(R.id.mealPriceSearch);
            chefName = itemView.findViewById(R.id.chefNameSearch);
            chefRating = itemView.findViewById(R.id.chefRatingSearch);
        }
    }
}
