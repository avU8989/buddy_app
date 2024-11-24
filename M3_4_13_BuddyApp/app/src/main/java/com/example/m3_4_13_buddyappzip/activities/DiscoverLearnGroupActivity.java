package com.example.m3_4_13_buddyappzip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.adapter.LearnBuddyCardAdapter;
import com.example.m3_4_13_buddyappzip.adapter.LearnGroupCardAdapter;
import com.example.m3_4_13_buddyappzip.adapter.utility.LearnGroupJsonParser;
import com.example.m3_4_13_buddyappzip.adapter.utility.StackLayoutManager;
import com.example.m3_4_13_buddyappzip.adapter.utility.SwipeCallback;
import com.example.m3_4_13_buddyappzip.components.LearnGroup;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DiscoverLearnGroupActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LearnGroupCardAdapter adapter;

    private LearnGroupJsonParser parser;

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_learngroups);

        recyclerView = findViewById(R.id.recyclerView_learnGroups);
        adapter = new LearnGroupCardAdapter(DiscoverLearnGroupActivity.this);
        parser = new LearnGroupJsonParser();
        Bundle extras = getIntent().getExtras();

        if(extras != null && extras.containsKey("user")){
            user = (String) extras.getSerializable("user");
        }


        //set up the recyclerView with the adapter and the layout manager
        // Load the JSON file from the assets folder
        try {
            InputStream inputStream = getAssets().open("learngroup.json");
            List<LearnGroup> learnGroupList = parser.parseLearnGroupsFromJson(inputStream);
            adapter.setLearnGroupList(learnGroupList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //set up the recyclerView with the adapter and layout manager
        recyclerView.setAdapter(adapter);
        StackLayoutManager layoutManager = new StackLayoutManager();
        recyclerView.setLayoutManager(layoutManager);

        //set up the swipe functionality
        ItemTouchHelper.Callback callback = new SwipeCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // Disable scrolling
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    recyclerView.stopScroll();
                }
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        int selectedTab = getIntent().getIntExtra("selected_tab", R.id.action_home);
        bottomNav.setSelectedItemId(selectedTab);

        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@androidx.annotation.NonNull MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.action_chat:
                            intent = new Intent(DiscoverLearnGroupActivity.this, SingleChatOverviewActivity.class);
                            intent.putExtra("selected_tab", R.id.action_chat);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        case R.id.action_courses:
                            intent = new Intent(DiscoverLearnGroupActivity.this, CoursesActivity.class);
                            intent.putExtra("selected_tab", R.id.action_courses);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        case R.id.action_account:
                            intent = new Intent(DiscoverLearnGroupActivity.this, AccountActivity.class);
                            intent.putExtra("selected_tab", R.id.action_account);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        default:
                            intent = new Intent(DiscoverLearnGroupActivity.this, HomeActivity.class);
                            intent.putExtra("selected_tab", R.id.action_home);
                            intent.putExtra("user", user); // Add this line to pass the user object
                    }
                    startActivity(intent);
                    return true;
                }
            };

}
