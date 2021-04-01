package com.example.friendmanager;

import android.graphics.Point;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity{

    DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    // Build view dynamically with all friends
    public void updateView()
    {
        ArrayList<Friend> friends = dbManager.selectAll();
        if(friends.size() > 0)
        {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(friends.size());
            grid.setColumnCount(5);

            // create arrays of components
            TextView[] ids = new TextView[friends.size()];
            EditText[][] namesAndEmails = new EditText[friends.size()][3];
            Button[] buttons = new Button[friends.size()];
            ButtonHandler bh = new ButtonHandler();

            // retrieve width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;

            for (Friend friend : friends)
            {
                // create the TextView for the friend's id
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + friend.getId());

                // create the three EditTexts for the first name, last name, and email
                namesAndEmails[i][0] = new EditText(this);
                namesAndEmails[i][1] = new EditText(this);
                namesAndEmails[i][2] = new EditText(this);
                namesAndEmails[i][0].setText(friend.getFirstName());
                namesAndEmails[i][1].setText(friend.getLastName());
                namesAndEmails[i][2].setText(friend.getEmail());
                namesAndEmails[i][0].setId(10 * friend.getId());
                namesAndEmails[i][1].setId(10 * friend.getId() + 1);
                namesAndEmails[i][2].setId(10 * friend.getId() + 2);

                // create the button
                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(friend.getId());

                // set up event handling
                buttons[i].setOnClickListener(bh);

                // add elements to the grid
                grid.addView(ids[i], (int) (width * .1), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndEmails[i][0], (int) (width * .2), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndEmails[i][1], (int) (width * .2), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndEmails[i][2], (int) (width * .3), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(buttons[i], (int) (width * .2), ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;
            }

            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }

    private class ButtonHandler implements View.OnClickListener
    {
        public void onClick(View v)
        {
            // retrieve first name, last name, and email
            int friendId = v.getId();
            EditText firstNameET = (EditText) findViewById(10 * friendId);
            EditText lastNameET = (EditText) findViewById(10 * friendId + 1);
            EditText emailET = (EditText) findViewById(10 * friendId + 2);
            String firstName = firstNameET.getText().toString();
            String lastName = lastNameET.getText().toString();
            String email = emailET.getText().toString();

            // update friend in database
            dbManager.updateById(friendId, firstName, lastName, email);
            Toast.makeText(UpdateActivity.this, "Friend updated", Toast.LENGTH_SHORT).show();
        }
    }

}
