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
import android.widget.Toast;

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
                showActionMealDialog(meal.get_Id(), meal.get_name(), meal.get_price(), meal.get_description());
                return true;
            }
        });
    }


        private void showActionMealDialog(final String mealId,  String mealPrice,String description, String mealName){

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.activity_meal_description, null);
            dialogBuilder.setView(dialogView);

            final TextView txtMealPrice = (TextView) dialogView.findViewById(R.id.textViewPrice);
            final TextView txtMealDescription = (TextView) dialogView.findViewById(R.id.textViewDescription);
            final TextView txtMealName=(TextView) dialogView.findViewById(R.id.textViewName);
            final Button btnDelete = (Button) dialogView.findViewById(R.id.buttonDelete);


            String title = "Meal name: " + mealId;
            dialogBuilder.setTitle(title);
            txtMealName.setText(mealName);
            txtMealPrice.setText(mealPrice);
            txtMealDescription.setText(description);

            final AlertDialog builder = dialogBuilder.create();
            builder.show();

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteMeal(mealId);
                    builder.dismiss();
                }
            });
        }

    private boolean deleteMeal(String id) {

        DatabaseReference dR = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Meals").child(id);

        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Meal has been deleted", Toast.LENGTH_LONG).show();
        return true;
    }


    }
}