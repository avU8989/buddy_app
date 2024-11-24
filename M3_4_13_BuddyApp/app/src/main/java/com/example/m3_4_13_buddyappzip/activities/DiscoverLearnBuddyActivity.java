package com.example.m3_4_13_buddyappzip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.adapter.LearnBuddyCardAdapter;
import com.example.m3_4_13_buddyappzip.components.LearnBuddy;
import com.example.m3_4_13_buddyappzip.adapter.utility.LearnBuddyJsonParser;
import com.example.m3_4_13_buddyappzip.adapter.utility.StackLayoutManager;
import com.example.m3_4_13_buddyappzip.adapter.utility.SwipeCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DiscoverLearnBuddyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LearnBuddyCardAdapter adapter;

    private LearnBuddyJsonParser parser;

    private String user;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_learnbuddy);

        recyclerView = findViewById(R.id.recyclerView_learnBuddies);
        adapter = new LearnBuddyCardAdapter(DiscoverLearnBuddyActivity.this);
        parser = new LearnBuddyJsonParser();
        Bundle extras = getIntent().getExtras();

        if(extras != null && extras.containsKey("user")){
            user = (String) extras.getSerializable("user");
        }


        //set up the recyclerView with the adapter and the layout manager
        // Load the JSON file from the assets folder
        try {
            InputStream inputStream = getAssets().open("learnbuddy.json");
            List<LearnBuddy> learnBuddyList = parser.parseLearnBuddiesFromJson(inputStream);
            adapter.setLearnBuddyList(learnBuddyList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(adapter);
        StackLayoutManager layoutManager = new StackLayoutManager();
        recyclerView.setLayoutManager(layoutManager);

        //set up the swipe functionality
        ItemTouchHelper.Callback callback = new SwipeCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        int selectedTab = getIntent().getIntExtra("selected_tab", R.id.action_home);
        bottomNav.setSelectedItemId(selectedTab);

        bottomNav.setOnNavigationItemSelectedListener(navListener);


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.action_chat:
                            intent = new Intent(DiscoverLearnBuddyActivity.this, SingleChatOverviewActivity.class);
                            intent.putExtra("selected_tab", R.id.action_chat);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        case R.id.action_courses:
                            intent = new Intent(DiscoverLearnBuddyActivity.this, CoursesActivity.class);
                            intent.putExtra("selected_tab", R.id.action_courses);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        case R.id.action_account:
                            intent = new Intent(DiscoverLearnBuddyActivity.this, AccountActivity.class);
                            intent.putExtra("selected_tab", R.id.action_account);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        default:
                            intent = new Intent(DiscoverLearnBuddyActivity.this, HomeActivity.class);
                            intent.putExtra("selected_tab", R.id.action_home);
                            intent.putExtra("user", user); // Add this line to pass the user object
                    }
                    startActivity(intent);
                    return true;
                }
            };
}
