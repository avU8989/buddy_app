// CoursesActivity.java
package com.example.m3_4_13_buddyappzip.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m3_4_13_buddyappzip.R;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_4_13_buddyappzip.adapter.CourseAdapter;
import com.example.m3_4_13_buddyappzip.adapter.PostAdapter;
import com.example.m3_4_13_buddyappzip.components.Course;
import com.example.m3_4_13_buddyappzip.components.LearnGroup;
import com.example.m3_4_13_buddyappzip.components.Post;
import com.example.m3_4_13_buddyappzip.components.StudyProgram;
import com.example.m3_4_13_buddyappzip.utility.CourseJsonParser;
import com.example.m3_4_13_buddyappzip.utility.PostJsonParser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {
    private RecyclerView recyclerView_courses;
    private RecyclerView recyclerView_posts;

    private CourseAdapter courseAdapter;
    private PostAdapter postAdapter;
    private CourseJsonParser courseJsonParser;
    private PostJsonParser postJsonParser;

    private String user;
    private TextView studyField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);


        Toolbar toolbar = findViewById(R.id.toolBar);
        courseAdapter = new CourseAdapter();
        postAdapter = new PostAdapter();
        courseJsonParser = new CourseJsonParser();
        postJsonParser = new PostJsonParser();

        Bundle extras = getIntent().getExtras();

        if(extras != null && extras.containsKey("user")){
            user = (String) extras.getSerializable("user");
        }


        // Load the JSON file from the assets folder
        try {
            InputStream inputStream = getAssets().open("courses.json");
            StudyProgram userStudyField = new StudyProgram("Computer Science");
            List<Course> courses = courseJsonParser.parseCourseBasedOnStudyFieldFromJson(inputStream, userStudyField.getStudyField());
            courseAdapter.setCourses(courses);

            // Find the RecyclerView in the navigation view and set its adapter
            NavigationView navigationView = findViewById(R.id.nav_view);

            studyField = navigationView.findViewById(R.id.studyfield_text_view);

            studyField.setText(userStudyField.getStudyField());
            setStudySwitchButtons();


            // If your RecyclerView is not in the header, but directly in the included layout
            recyclerView_courses = navigationView.findViewById(R.id.sidebar_recyclerview);

            recyclerView_courses.setLayoutManager(new LinearLayoutManager(this)); // Set a layout manager to the RecyclerView
            recyclerView_courses.setAdapter(courseAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //set up the recyclerView with the adapter and the layout manager
        // Load the JSON file from the assets folder
        try {
            InputStream inputStream = getAssets().open("posts.json");
            List<Post> posts = postJsonParser.parsePostsFromJson(inputStream);
            postAdapter.setPosts(posts);

            recyclerView_posts = findViewById(R.id.recycler_view_posts);
            recyclerView_posts.setLayoutManager(new LinearLayoutManager(this));
            recyclerView_posts.setAdapter(postAdapter);

        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageView add_thread_icon = findViewById(R.id.add_thread_icon);
        add_thread_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Toast toast = Toast.makeText(CoursesActivity.this, "Not implemented", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 170);
                    toast.show();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                if(!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });

        NestedScrollView nestedScrollView = findViewById(R.id.nested_scroll_view);

        nestedScrollView.post(new Runnable() {
            @Override
            public void run() {
                // Scroll to the top of the NestedScrollView
                nestedScrollView.smoothScrollTo(0, 0);
            }
        });


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        int selectedTab = getIntent().getIntExtra("selected_tab", R.id.action_courses);
        bottomNav.setSelectedItemId(selectedTab);

        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private void setStudySwitchButtons(){
        Button switchStudyLeft = findViewById(R.id.button_switch1);
        Button switchStudyRight = findViewById(R.id.button_switch2);

        switchStudyRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream inputStream = null;
                try {
                    inputStream = getAssets().open("courses.json");
                    StudyProgram userStudyField = new StudyProgram("Psychology");
                    studyField.setText(userStudyField.getStudyField());
                    List<Course> courses = courseJsonParser.parseCourseBasedOnStudyFieldFromJson(inputStream, userStudyField.getStudyField());
                    courseAdapter.setCourses(courses);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        });

        switchStudyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream inputStream = null;
                try {
                    inputStream = getAssets().open("courses.json");
                    StudyProgram userStudyField = new StudyProgram("Computer Science");
                    studyField.setText(userStudyField.getStudyField());
                    List<Course> courses = courseJsonParser.parseCourseBasedOnStudyFieldFromJson(inputStream, userStudyField.getStudyField());
                    courseAdapter.setCourses(courses);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.action_chat:
                            intent = new Intent(CoursesActivity.this, SingleChatOverviewActivity.class);
                            intent.putExtra("selected_tab", R.id.action_chat);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        case R.id.action_courses:
                            intent = new Intent(CoursesActivity.this, CoursesActivity.class);
                            intent.putExtra("selected_tab", R.id.action_courses);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        case R.id.action_account:
                            intent = new Intent(CoursesActivity.this, AccountActivity.class);
                            intent.putExtra("selected_tab", R.id.action_account);
                            intent.putExtra("user", user); // Add this line to pass the user object
                            break;
                        default:
                            intent = new Intent(CoursesActivity.this, HomeActivity.class);
                            intent.putExtra("selected_tab", R.id.action_home);
                            intent.putExtra("user", user); // Add this line to pass the user object
                    }
                    startActivity(intent);
                    return true;
                }
            };
}
