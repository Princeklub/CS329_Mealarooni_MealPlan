package com.zareenk94yahoo.mealarooni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.R.layout;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ingredients_list extends Activity implements View.OnClickListener{

    public Button btnToGroceryList;
    public ListView IngredientView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_list);

        btnToGroceryList = (Button) findViewById(R.id.btnToGroceryList);
        btnToGroceryList.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> ingredients = extras.getStringArrayList("Ingredient_List");

        //ingredients.size() == 0
        if (false){

        }
        else {

            LayoutInflater inflater = getLayoutInflater();
        /*SimpleAdapter myAdapter=new
                SimpleAdapter(
                this, ingredients,
                layout.simple_list_item_multiple_choice, new int[] {android.R.id.text1,
                android.R.id.text2}); */
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout.simple_list_item_multiple_choice, ingredients);
            Log.i("PROGRESS", "adapter succesffully created with info");
            IngredientView = (ListView) findViewById(R.id.IngredientListView);
            IngredientView.setAdapter(adapter);
            Log.i("PROGRESS", "adapted set to list view");

            //IngredientView.setOnItemClickListener(Adapter);
            IngredientView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // change the checkbox state
                    CheckedTextView checkedTextView = ((CheckedTextView) view);
                    checkedTextView.setChecked(!checkedTextView.isChecked());
                }

            });
        }

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

        case R.id.menu_preferences:
            Intent i = new Intent(ingredients_list.this, restrictions.class);
            startActivity(i);
            break;
        case R.id.menu_grocerylist:
            //Intent intent = new Intent(ingredients_list.this, grocery_list.class);
            break;
        case R.id.menu_mealplan:
            Intent j = new Intent(ingredients_list.this, meal_plan.class);
            startActivity(j);
            break;
        case R.id.exit:
            finish();
            break;
    }
        return(super.onOptionsItemSelected(item));
    }


        @Override
    public void onClick(View v){

        if(v.getId() == R.id.btnToGroceryList)
        {
            //Intent i = new Intent(ingredients_list.this, meal_plan.class);
            //startActivity(i);
        }


    }

}
