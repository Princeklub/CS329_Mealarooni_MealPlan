package com.zareenk94yahoo.mealarooni;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import android.widget.Toast;
import com.squareup.picasso.Picasso;
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
    private List<String> imageURLArray = new ArrayList<String>();
    private List<String[]> mealIngredientsArray = new ArrayList<String []>();

    //zareen
    //Bundle b=this.getIntent().getExtras();
    //String[] preferencesArray=b.getStringArray("preferencesArray");

    //Intent intent = getIntent();
    //String[] preferencesArray = intent.getStringArrayExtra("preferencesArray");

    //String[] preferencesArray = getIntent().getExtras().getStringArray("preferencesArray");

    //ArrayList<String> preferencesArray = getIntent().getStringArrayListExtra("preferencesArray");

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

        //Add toasts to ListView
        myMealListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), imageURLArray.get(0),Toast.LENGTH_SHORT).show();
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.meal_toast,
                        (ViewGroup) findViewById(R.id.toast_layout_root));

                ImageView image = (ImageView) layout.findViewById(R.id.mealImageView);
                try {
                    String imageURL = imageURLArray.get(i);
                    /*Bitmap bmp = BitmapFactory.decodeFile(new java.net.URL(imageURL).openStream());
                    image.setImageBitmap(bmp);*/
                    Picasso.with(getBaseContext()).load(imageURL).into(image);
                }
                catch (Exception x){
                }
                //Sets toaster ingredients view
                TextView text = (TextView) layout.findViewById(R.id.ingredientsListTextView);
                String ingredients = "";
                for(int j = 0; j < mealIngredientsArray.get(i).length - 1; j++){
                    ingredients += mealIngredientsArray.get(i)[j] + "\n";
                }
                text.setText("Ingredients: " + ingredients);

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });

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

        //zareen
        //for (String preference : preferencesArray) {
            //breakfastTest += "&excludedIngredientp[]=" + preference;
        //}

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
                String foodItem = food.getString("recipeName");
                String imageURL = food.getJSONArray("smallImageUrls").getString(0);
                String[] ingredientsList = new String[food.getJSONArray("ingredients").length()];
                for(int i=0; i < food.getJSONArray("ingredients").length() - 1; i++){
                    ingredientsList[i] = food.getJSONArray("ingredients").getString(i);
                    Log.d("Meal Ingredients", ingredientsList[i]);
                }
                mealsArray.add(foodItem);
                imageURLArray.add(imageURL);
                mealIngredientsArray.add(ingredientsList);

                result += foodItem + '\n';
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