package com.example.m3_4_13_buddyappzip.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.adapter.PostAdapter;
import com.example.m3_4_13_buddyappzip.components.Course;
import com.example.m3_4_13_buddyappzip.components.CourseDetails;
import com.example.m3_4_13_buddyappzip.components.Post;
import com.example.m3_4_13_buddyappzip.components.StudyProgram;
import com.example.m3_4_13_buddyappzip.utility.PostJsonParser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CourseForumActivity extends AppCompatActivity {
	private PostJsonParser postJsonParser;
	private RecyclerView recyclerView_posts;
	private PostAdapter postAdapter;
	boolean isInfoCardVisible = false; // Initial visibility state



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_forum);

		Bundle extras = getIntent().getExtras();

		Toolbar toolbar = findViewById(R.id.toolBar);
		postAdapter = new PostAdapter();
		postJsonParser = new PostJsonParser();

		//toolbar.setTitle(course.getCourseName());
		try {
			if (extras != null && extras.containsKey("course_forum")) {
				Course course = (Course) extras.getSerializable("course_forum");
				InputStream inputStream = getAssets().open("posts.json");
				List<Post> posts = postJsonParser.parsePostsBasedOnStudyFieldFromJson(inputStream, course.getCourseName());
				Log.d("CourseForumActivity", posts.toString());
				postAdapter.setPosts(posts);
				TextView textView = new TextView(this);
				textView.setText(course.getCourseName());
				textView.setTextSize(20);
				textView.setTextColor(Color.WHITE);
				toolbar.addView(textView);

				recyclerView_posts = findViewById(R.id.recycler_view_posts);
				recyclerView_posts.setLayoutManager(new LinearLayoutManager(this));
				recyclerView_posts.setAdapter(postAdapter);

				if(course.getCourseDetails() != null){
					CourseDetails details = course.getCourseDetails();
					CardView infoCard = findViewById(R.id.info_card);
					TextView difficulty = findViewById(R.id.difficultyTextView);
					TextView preparation = findViewById(R.id.preparationTextView);
					TextView requiredKnowledge = findViewById(R.id.knowledgeTextView);
					TextView tips = findViewById(R.id.tipsTextView);

					difficulty.setText("Difficulty: " + details.getCourseDifficulty());
					preparation.setText("Preparation: " + String.valueOf(details.getPreparation()) + " Weeks");
					requiredKnowledge.setText("Required knowledge: " + details.getRequiredKnowledge());
					tips.setText("Tips and tricks: " + details.getTips());

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		setUpInfoCard();
		BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
		int selectedTab = getIntent().getIntExtra("selected_tab", R.id.action_courses);
		bottomNav.setSelectedItemId(selectedTab);

		bottomNav.setOnNavigationItemSelectedListener(navListener);


	}

	private BottomNavigationView.OnNavigationItemSelectedListener navListener =
			new BottomNavigationView.OnNavigationItemSelectedListener() {
				@Override
				public boolean onNavigationItemSelected(@NonNull MenuItem item) {
					Intent intent;
					switch (item.getItemId()) {
						case R.id.action_home:
							intent = new Intent(CourseForumActivity.this, HomeActivity.class);
							intent.putExtra("selected_tab", R.id.action_home);
							break;
						case R.id.action_courses:
							intent = new Intent(CourseForumActivity.this, CoursesActivity.class);
							intent.putExtra("selected_tab", R.id.action_courses);
							break;
						case R.id.action_chat:
							intent = new Intent(CourseForumActivity.this, SingleChatOverviewActivity.class);
							intent.putExtra("selected_tab", R.id.action_chat);
							break;
						default:
							intent = new Intent(CourseForumActivity.this, AccountActivity.class);
							intent.putExtra("selected_tab", R.id.action_account);
					}
					startActivity(intent);
					return true;
				}
			};

	public void setUpInfoCard() {
		ImageView infoIcon = findViewById(R.id.infoIcon);
		final CardView infoCard = findViewById(R.id.info_card);

		infoIcon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isInfoCardVisible) {
					infoCard.setVisibility(View.GONE);
					isInfoCardVisible = false;
				} else {
					infoCard.setVisibility(View.VISIBLE);
					isInfoCardVisible = true;
				}
			}
		});
	}


}
