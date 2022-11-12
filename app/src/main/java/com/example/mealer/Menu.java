package com.example.mealer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    ListView listViewMeals;
    DatabaseReference databaseMeals;
    List<Meal_Class> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        databaseMeals = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Meals");

        listViewMeals = (ListView) findViewById(R.id.menuListView);

        meals = new ArrayList<>();

        listViewMeals.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int item, long l) {
                Meal_Class meal = meals.get(item);
                showActionMealDialog(meal.get_name(), meal.get_price());
                return true;
            }
        });


        private void showActionMealDialog(final String mealName, final String mealPrice){

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.activity_meal_description, null);
            dialogBuilder.setView(dialogView);

            final TextView txtComplaint = (TextView) dialogView.findViewById(R.id.txtComplaint);
            final Button buttonDismissComplaint = (Button) dialogView.findViewById(R.id.buttonDismissComplaint);
            final Button btnPermanentSuspension = (Button) dialogView.findViewById(R.id.btnPermanentSuspension);
            final Button btnTempSuspension = (Button) dialogView.findViewById(R.id.btnTempSuspension);

            String title = "Meal name: " + mealName;
            dialogBuilder.setTitle(title);
            txtComplaint.setText(mealPrice);

            final AlertDialog builder = dialogBuilder.create();
            builder.show();
        }


    }
}