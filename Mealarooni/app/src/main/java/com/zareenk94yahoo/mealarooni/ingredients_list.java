package com.zareenk94yahoo.mealarooni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ingredients_list extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_list);
    }

    public void onButtonClick(View v){

        if(v.getId() == R.id.GenerateButton)
        {
            Intent i = new Intent(ingredients_list.this, meal_plan.class);
            startActivity(i);
        }
    }

}
