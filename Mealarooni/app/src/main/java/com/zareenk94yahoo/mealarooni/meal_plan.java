package com.zareenk94yahoo.mealarooni;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.util.Log;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




public class meal_plan extends Activity{

    private Exception exception;
    private TextView meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_plan);

        meals = (TextView) findViewById(R.id.mealTextView);

        new RetrieveFeedTask().execute();
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
        meals.setText(response);


    }
}
}