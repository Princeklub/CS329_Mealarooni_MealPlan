package com.zareenk94yahoo.mealarooni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    // declare the variables needed to manipulate the widgets
    Button signUpButton;
    EditText emailEditText, passwordEditText, nameEditText;

    // create an instance of the DatabaseHelper class here
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // this will call the constructor of the DatabaseHelper class which will create the DB
        myDb = new DatabaseHelper(this);

        // this section gets all the GUI widget ID's for use in the Listeners
        emailEditText = (EditText)findViewById(R.id.emailEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        nameEditText = (EditText)findViewById(R.id.nameEditText);
        signUpButton = (Button)findViewById(R.id.signUpButton);

        //call methods
        add();
    }

    public void add() {
        signUpButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(emailEditText.getText().toString(), passwordEditText.getText().toString(), nameEditText.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(SignUpActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(SignUpActivity.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();
                    }

                }
        );
    }




}
