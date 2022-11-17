package com.example.mealer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MealList extends ArrayAdapter<Meal_Class> {
    private Activity context;
    List<Meal_Class> meals;

    public MealList(Activity context, List<Meal_Class> meals) {
        super(context,R.layout.meal_list,meals);
        this.context = context;
        this.meals = meals;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.meal_list,null,true);

        TextView mealName = listViewItem.findViewById(R.id.listMealName);
        ImageView offeredIcon = listViewItem.findViewById(R.id.offeredIcon);

        Meal_Class meal = meals.get(position);
        mealName.setText(String.valueOf(meal.get_name()));

        if (meal.isOffered()) {
            offeredIcon.setVisibility(View.VISIBLE);
        }
        return listViewItem;
    }
}
