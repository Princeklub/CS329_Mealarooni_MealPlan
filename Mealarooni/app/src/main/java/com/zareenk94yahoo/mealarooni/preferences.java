package com.zareenk94yahoo.mealarooni;

import android.os.Bundle;
import android.view.View;

import android.app.Activity;
import android.content.Intent;
import android.widget.CheckBox;

import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class preferences extends Activity implements View.OnClickListener {

    private Button savePreferencesButton;
    private ArrayList<String> preferencesArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        savePreferencesButton = (Button) findViewById(R.id.savePreferencesButton);
        savePreferencesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        CheckBox peanutCheckBox = (CheckBox) findViewById(R.id.peanutCheckBox);

        //not in use
        if (peanutCheckBox.isChecked())
        {
            preferencesArray.add("peanut");
        }

        //zareen
        //Bundle b=new Bundle();
        //b.putStringArray("preferencesArray", new String[] { "peanut" }); //need to make this applicable
        Intent intent = new Intent(preferences.this, meal_plan.class);
        //intent.putExtras(b);
        intent.putStringArrayListExtra("preferencesArray", preferencesArray);
        startActivity(intent);


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
