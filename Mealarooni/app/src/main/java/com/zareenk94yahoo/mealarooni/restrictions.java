package com.zareenk94yahoo.mealarooni;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.app.Activity;
import android.content.Intent;
import android.widget.CheckBox;

import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class restrictions extends Activity implements View.OnClickListener {

    private Button savePreferencesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restrictions);
        savePreferencesButton = (Button) findViewById(R.id.savePreferencesButton);
        savePreferencesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        CheckBox peanutCheckBox = (CheckBox) findViewById(R.id.peanutCheckBox);
        ArrayList<String> preferencesArray = new ArrayList<String>();


        if (peanutCheckBox.isChecked())
        {
            preferencesArray.add("peanut");
        }

        //zareen
        //Bundle b=new Bundle();
        //b.putStringArray("preferencesArray", new String[] { "peanut" }); //need to make this applicable
        Intent intent = new Intent(restrictions.this, meal_plan.class);
        //intent.putExtras(b);
        intent.putStringArrayListExtra("preferencesArray", preferencesArray);
        startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.menu_mainmenu:
            //add the function to perform here
            break;

        case R.id.menu_mealplan:
            Intent i = new Intent(restrictions.this, meal_plan.class);
            startActivity(i);
            break;
        case R.id.menu_grocerylist:
            //Intent intent = new Intent(ingredients_list.this, grocery_list.class);
            break;

    }
        return(super.onOptionsItemSelected(item));
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }


}
