package com.zareenk94yahoo.mealarooni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    //create variables for buttons
    Button mealPlanButton;
    Button preferencesButton;

    //override onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        mealPlanButton = (Button) findViewById(R.id.mealPlanButton);
        preferencesButton = (Button) findViewById(R.id.preferencesButton);
    }

    @Override
    //create intents for new activities when buttons are clicked
    public void onClick(View v){
        if (v.getId()== R.id.mealPlanButton) {
            Intent intent = new Intent(MainMenuActivity.this, meal_plan.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.preferencesButton) {
            Intent intent = new Intent(MainMenuActivity.this, preferences.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.signUpButton) {
            Intent intent = new Intent(MainMenuActivity.this, SignUpActivity.class);
            startActivity(intent);
        }

    }
}
