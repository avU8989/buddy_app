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


import com.example.m3_4_13_buddyappzip.components.User;

import com.example.m3_4_13_buddyappzip.utility.RecyclerItemClickListener;


import com.example.m3_4_13_buddyappzip.adapter.UserAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import com.example.m3_4_13_buddyappzip.R;

public class SingleChatOverviewActivity extends AppCompatActivity {

    private EditText searchBar;
    private EditText add_friend_bar;

    private String user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat_overview);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_SC);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        int selectedTab = getIntent().getIntExtra("selected_tab", R.id.action_chat);
        bottomNav.setSelectedItemId(selectedTab);

        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Button singleChatButton = findViewById(R.id.buttonSingleChat);
        Button groupChatButton = findViewById(R.id.buttonGroupChat);


        Bundle extras = getIntent().getExtras();

        if(extras != null && extras.containsKey("user")){
            user = (String) extras.getSerializable("user");
        }

        singleChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleChatOverviewActivity.this, SingleChatOverviewActivity.class);
                startActivity(intent);
            }
        });

        groupChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleChatOverviewActivity.this, GroupChatOverviewActivity.class);
                startActivity(intent);
            }
        });

        searchBar = findViewById(R.id.search_bar);
        ImageView searchIcon = findViewById(R.id.image_search);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast toast = Toast.makeText(SingleChatOverviewActivity.this, "Search not implemented", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 200);
                    toast.show();

            }
        });



        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH ||
                        (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    Toast toast = Toast.makeText(SingleChatOverviewActivity.this, "Search not implemented", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 200);
                    toast.show();
                    return true;
                }
                return false;
            }
        });

        ImageView add_friend_button = findViewById(R.id.add_friend_button);

        add_friend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast toast = Toast.makeText(SingleChatOverviewActivity.this, "Not implemented", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 170);
                    toast.show();

            }
        });



        List<User> users = getUsers();
        UserAdapter adapter = new UserAdapter(users, new UserAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(User user) {
                Intent intent = new Intent(SingleChatOverviewActivity.this, SingleChatActivity.class);
                intent.putExtra("username", user.getUsername());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Handle item click
                User user = users.get(position);
                Intent intent = new Intent(SingleChatOverviewActivity.this, SingleChatActivity.class);
                intent.putExtra("username", user.getUsername());
                intent.putExtra("avatarUrl", user.getAvatarUrl());
                startActivity(intent);
            }
        }));

    }



    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("Sam_u", "https://images.pexels.com/photos/12906203/pexels-photo-12906203.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"));
        users.add(new User("JohnnyPeach", "https://images.pexels.com/photos/13703422/pexels-photo-13703422.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2/photos/12906203/pexels-photo-12906203.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"));
        users.add(new User("AlexxelA", "https://images.pexels.com/photos/16599968/pexels-photo-16599968.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"));
        users.add(new User("Firelord", "https://images.pexels.com/photos/16773707/pexels-photo-16773707.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"));
        users.add(new User("AndyG.", "https://images.pexels.com/photos/14281981/pexels-photo-14281981.jpeg?auto=compress&cs=tinysrgb&w=1600&lazy=load"));
        users.add(new User("Omnicron", "https://images.pexels.com/photos/15052339/pexels-photo-15052339.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"));
        users.add(new User("Eazyyy66", "https://images.pexels.com/photos/7841717/pexels-photo-7841717.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"));
        users.add(new User("Ben11", "https://images.pexels.com/photos/16377882/pexels-photo-16377882.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"));
        users.add(new User("Beyzee0101", "https://images.pexels.com/photos/16482625/pexels-photo-16482625.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"));
        users.add(new User("CiaoKakao", "https://images.pexels.com/photos/16526712/pexels-photo-16526712.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&d"));

        return users;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.action_chat:
                            intent = new Intent(SingleChatOverviewActivity.this, SingleChatOverviewActivity.class);
                            intent.putExtra("selected_tab", R.id.action_chat);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        case R.id.action_courses:
                            intent = new Intent(SingleChatOverviewActivity.this, CoursesActivity.class);
                            intent.putExtra("selected_tab", R.id.action_courses);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        case R.id.action_account:
                            intent = new Intent(SingleChatOverviewActivity.this, AccountActivity.class);
                            intent.putExtra("selected_tab", R.id.action_account);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        default:
                            intent = new Intent(SingleChatOverviewActivity.this, HomeActivity.class);
                            intent.putExtra("selected_tab", R.id.action_home);
                            intent.putExtra("user", user); // Add this line to pass the user object
                    }
                    startActivity(intent);
                    return true;
                }
            };

}