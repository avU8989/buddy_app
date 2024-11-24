package com.example.m3_4_13_buddyappzip.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m3_4_13_buddyappzip.components.Group;
import com.example.m3_4_13_buddyappzip.adapter.GroupAdapter;


import com.example.m3_4_13_buddyappzip.utility.RecyclerItemClickListener;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import com.example.m3_4_13_buddyappzip.R;

public class GroupChatOverviewActivity extends AppCompatActivity {

    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat_overview);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_GC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        int selectedTab = getIntent().getIntExtra("selected_tab", R.id.action_chat);
        bottomNav.setSelectedItemId(selectedTab);

        bottomNav.setOnNavigationItemSelectedListener(navListener);

        searchBar = findViewById(R.id.search_bar);

        ImageView searchIcon = findViewById(R.id.image_search);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast toast = Toast.makeText(GroupChatOverviewActivity.this, "Search not implemented", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 200);
                    toast.show();

            }
        });

        Button singleChatButton = findViewById(R.id.buttonSingleChat);
        Button groupChatButton = findViewById(R.id.buttonGroupChat);


        singleChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupChatOverviewActivity.this, SingleChatOverviewActivity.class);
                startActivity(intent);
            }
        });

        groupChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupChatOverviewActivity.this, GroupChatOverviewActivity.class);
                startActivity(intent);
            }
        });

        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH ||
                        (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    Toast toast = Toast.makeText(GroupChatOverviewActivity.this, "Search not implemented", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 200);
                    toast.show();
                    return true;
                }
                return false;
            }
        });

        ImageView create_group_button = findViewById(R.id.create_group_button);

        create_group_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast toast = Toast.makeText(GroupChatOverviewActivity.this, "Not implemented", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 170);
                    toast.show();

            }
        });




        List<Group> groups = getGroups();
        GroupAdapter adapter = new GroupAdapter(groups, new GroupAdapter.OnGroupClickListener() {
            @Override
            public void onGroupClick(Group group) {
                Intent intent = new Intent(GroupChatOverviewActivity.this, GroupChatActivity.class);
                intent.putExtra("groupchat", group.getGroupname());
                startActivity(intent);
            }

        });
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Handle item click
                Group group = groups.get(position);
                Intent intent = new Intent(GroupChatOverviewActivity.this, GroupChatActivity.class);
                intent.putExtra("groupchat", group.getGroupname());
                intent.putExtra("avatarUrl", group.getAvatarUrl());
                startActivity(intent);
            }
        }));


    }



    private List<Group> getGroups() {
        List<Group> groups = new ArrayList<>();
        groups.add(new Group("HCI Projekt", "https://cdn.whatgadget.net/wp-content/uploads/2021/03/22223506/HUMAN-COMPUTER-INTERACTION-1000x500-1.jpg"));
        groups.add(new Group("ADS Selbsthilfe", "https://images.klipfolio.com/website/public/11f3da89-351a-4ca1-a59d-b6806b0fcec1/algorithm.jpg"));
        groups.add(new Group("C++ Übung", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/ISO_C%2B%2B_Logo.svg/1200px-ISO_C%2B%2B_Logo.svg.png"));
        groups.add(new Group("Lernen und Saufen", "https://i0.wp.com/klwightman.com/wp-content/uploads/2014/05/noisworlddotcom-wordpress-com.jpg"));
        groups.add(new Group("Lerngruppe", "https://cdn.dribbble.com/users/1180873/screenshots/16502837/media/a555456b3f7d77276b70587f8afd93ea.png?compress=1&resize=400x300&vertical=top"));
        groups.add(new Group("MGI Friends", "https://auca.kg/uploads/1580997788577.jpg"));
        groups.add(new Group("Winf4Win", "https://www.phc.ox.ac.uk/images/research/clinical-informatics/responsive_image?ratio=image&scale=w760"));
        groups.add(new Group("HackMack", "https://img.freepik.com/vektoren-kostenlos/farbiger-realistischer-aufbau-des-hackercodes-mit-person-schafft-codes-fuer-das-zerhacken-und-diebstahl-von-informationsvektorillustration_1284-18005.jpg"));
        groups.add(new Group("Blub", "https://upload.wikimedia.org/wikipedia/commons/6/6c/Blub_logo.png"));
        return groups;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            intent = new Intent(GroupChatOverviewActivity.this, HomeActivity.class);
                            intent.putExtra("selected_tab", R.id.action_home);
                            break;
                        case R.id.action_courses:
                            intent = new Intent(GroupChatOverviewActivity.this, CoursesActivity.class);
                            intent.putExtra("selected_tab", R.id.action_courses);
                            break;
                        case R.id.action_account:
                            intent = new Intent(GroupChatOverviewActivity.this, AccountActivity.class);
                            intent.putExtra("selected_tab", R.id.action_account);
                            break;
                        case R.id.action_chat:
                            intent = new Intent(GroupChatOverviewActivity.this, SingleChatOverviewActivity.class);
                            intent.putExtra("selected_tab", R.id.action_chat);
                            break;
                        default:
                            intent = new Intent(GroupChatOverviewActivity.this, GroupChatOverviewActivity.class);
                            intent.putExtra("selected_tab", R.id.action_chat);
                    }
                    startActivity(intent);
                    return true;
                }
            };

}