package com.example.mealer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

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
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users").child(meal.get_cookID());
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Cook_Class cook = (Cook_Class) snapshot.getValue(Cook_Class.class);
                holder.chefRating.setText("NOT IMPL.");
                holder.chefName.setText(cook.get_name());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
