package com.example.m3_4_13_buddyappzip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.components.LearnBuddy;
import com.example.m3_4_13_buddyappzip.components.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private Button discoverLearnGroup;
    private Button discoverLearnBuddy;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        Bundle extras = getIntent().getExtras();

        if(extras != null && extras.containsKey("user")){
            user = (String) extras.getSerializable("user");
            TextView username = findViewById(R.id.username);
            username.setText("Hi, " + user);
        }

        // Set the state of the BottomNavigationView based on the "selected_tab" extra
        int selectedTab = getIntent().getIntExtra("selected_tab", R.id.action_home);
        bottomNav.setSelectedItemId(selectedTab);

        //initialize discoverLearnGroup
        discoverLearnGroup = findViewById(R.id.discover_learngroup_button);
        //initialize discoverLearnBuddy
        discoverLearnBuddy = findViewById(R.id.discover_learnbuddy_button);
        this.setUpDiscoverLearnGroupButton();
        this.setUpDiscoverLearnBuddyButton();

        Button discover_buddy_points = findViewById(R.id.discover_buddy_points);
        discover_buddy_points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(HomeActivity.this, "Not implemented", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 170);
                toast.show();
            }
        });


        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.action_chat:
                            intent = new Intent(HomeActivity.this, SingleChatOverviewActivity.class);
                            intent.putExtra("selected_tab", R.id.action_chat);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        case R.id.action_courses:
                            intent = new Intent(HomeActivity.this, CoursesActivity.class);
                            intent.putExtra("selected_tab", R.id.action_courses);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        case R.id.action_account:
                            intent = new Intent(HomeActivity.this, AccountActivity.class);
                            intent.putExtra("selected_tab", R.id.action_account);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        default:
                            intent = new Intent(HomeActivity.this, HomeActivity.class);
                            intent.putExtra("selected_tab", R.id.action_home);
                            intent.putExtra("user", user); // Add this line to pass the user object
                    }
                    startActivity(intent);
                    return true;
                }
            };


    private void setUpDiscoverLearnGroupButton() {
        discoverLearnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create an intent to start the DiscoverLearnGroupActivity
                Intent intent = new Intent(HomeActivity.this, DiscoverLearnGroupActivity.class);

                //start the discoverLearnGroupActivity
                startActivity(intent);
            }
        });
    }

    private void setUpDiscoverLearnBuddyButton() {
        discoverLearnBuddy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create an intent to start the DiscoverLearnBuddyActivity
                Intent intent = new Intent(HomeActivity.this, DiscoverLearnBuddyActivity.class);

                //start the discoverLearnBuddyActivity
                startActivity(intent);
            }
        });
    }
}
