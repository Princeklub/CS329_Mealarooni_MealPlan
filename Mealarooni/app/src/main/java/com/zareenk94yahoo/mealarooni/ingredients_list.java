package com.zareenk94yahoo.mealarooni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class ingredients_list extends Activity implements View.OnClickListener{

    public Button btnToMealPlan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_list);

        btnToMealPlan = (Button) findViewById(R.id.btnToMealPlan);
        btnToMealPlan.setOnClickListener(this);
    }

    public void onButtonClick(View v){

        if(v.getId() == R.id.btnToMealPlan)
        {
            Intent i = new Intent(ingredients_list.this, meal_plan.class);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(ingredients_list.this, meal_plan.class);
        startActivity(intent);


    }

}
