package com.zareenk94yahoo.mealarooni;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.util.Log;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.widget.Button;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/* MEAL LIST VIEW IS CURRENTLY INVISIBLE UNDER PROPERTIES*/


public class meal_plan extends Activity implements View.OnClickListener{

    private Exception exception;
    private TextView meals;
    private Button btnToIngredients;
    private ListView myMealListView;
    private List<String> mealsArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_plan);

        meals = (TextView) findViewById(R.id.mealTextView);
        btnToIngredients = (Button) findViewById(R.id.btnToMealPlan);
        new RetrieveFeedTask().execute();
        btnToIngredients.setOnClickListener(this);

    }

    public void fillListView(){
        Log.d("MEALAROONI", "Inside fillListView()");
        //ListView Map and List data structures
        ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>();


        //Loads the data from the API into a Map to load into ListView Parameter 2
        int i = 0;
        while(i < mealsArray.size())
        {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("Day", "Day " + String.valueOf(i + 1));
            datum.put("FoodItem", mealsArray.get(i));
            data.add(datum);

            //Iterate through map
            /*for (String key: datum.keySet()){
                Log.d("DATUM", key + " - " + datum.get(key));
            }*/
            Log.d("Data", String.valueOf(data.get(i)));
            i++;

        }

        //Attaches the data to the list view
        SimpleAdapter myAdapter=new
                SimpleAdapter(
                this, data,
                android.R.layout.simple_list_item_2,
                new String [] {"Day", "FoodItem"}, new int[] {android.R.id.text1,
                android.R.id.text2});
        ListView myMealListView=(ListView) findViewById(R.id.mealsListView);
        myMealListView.setAdapter(myAdapter);

    }
    @Override
    protected void onStart(){
        super.onStart();


    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(meal_plan.this, ingredients_list.class);
        startActivity(intent);
    }



class RetrieveFeedTask extends AsyncTask<Void, Void, String> {  //https://developer.yummly.com/documentation

    private Exception exception;
    static final String API_KEY = "8a52d238a9192607535d4a89356e2add";
    static final String APP_ID = "a8315253";
    static final String API_URL = "http://api.yummly.com/v1/api/recipes?";
    private String breakfastTest;
    private String maxCount;

    protected void onPreExecute() {
        //progressBar.setVisibility(View.VISIBLE);
        //will ultimately build the string here!!! with params
        // supported courses = Main Dishes, Desserts, Side Dishes, Lunch and Snacks, Appetizers, Salads, Breads, Breakfast and Brunch, Soups, Beverages, Condiments and Sauces, Cocktails
        breakfastTest = "&allowedIngredient[]=eggs&allowedIngredient[]=pepper&allowedIngredient[]=salt&allowedCourse[]=course^course-Breakfast";
        maxCount = "&maxResult=10";
    }

    protected String doInBackground(Void... urls) {
        //String email = emailText.getText().toString();
        // Do some validation here

        try {
            URL url = new URL(API_URL + "_app_id=" + APP_ID + "&_app_key=" + API_KEY + breakfastTest + maxCount);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }


        //progressBar.setVisibility(View.GONE);
        Log.i("INFO", response);
        //responseView.setText(response);

        String result = "";
        try {
            JSONObject object = new JSONObject(response);
            JSONArray matches = object.getJSONArray("matches");
            int counter = 0;
            JSONObject food = matches.getJSONObject(counter);
            while(food != null){
                String name = food.getString("recipeName");
                mealsArray.add(name);

                result += name + '\n';
                counter++;
                food = matches.getJSONObject(counter);
            }



        } catch (JSONException e) {
            // Appropriate error handling code
        }
        meals.setText(result);

        //populates list view
        fillListView();
    }
}
}