package com.example.friendmanager;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View v)
    {
        // Retrieve name and email
        EditText firstNameET = (EditText) findViewById(R.id.input_firstname);
        EditText lastNameET = (EditText) findViewById(R.id.input_lastname);
        EditText emailET = (EditText) findViewById(R.id.input_email);
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String email = emailET.getText().toString();

        // insert new friend into the database
        Friend friend = new Friend(0, firstName, lastName, email);
        dbManager.insert(friend);
        Toast.makeText(this, "Friend added", Toast.LENGTH_SHORT);

        // clear data
        firstNameET.setText("");
        lastNameET.setText("");
        emailET.setText("");
    }

    public void goBack(View v)
    {
        this.finish();
    }
}
