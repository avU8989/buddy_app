package com.example.m3_4_13_buddyappzip.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.components.BuddyRank;
import com.example.m3_4_13_buddyappzip.components.LearnBuddy;
import com.example.m3_4_13_buddyappzip.components.StudyProgram;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsLearnBuddyActivity extends AppCompatActivity {
	private TextView username;
	private TextView buddyPoints;
	private TextView buddyRank;
	private TextView description;

	private TextView studies;
	private ImageView imageUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_learnbuddy);

		Bundle extras = getIntent().getExtras();

		if(extras != null && extras.containsKey("learnBuddy_details")){
			LearnBuddy learnBuddy = (LearnBuddy) extras.getSerializable("learnBuddy_details");
			username = findViewById(R.id.learn_buddy_username_text_view);
			buddyPoints = findViewById(R.id.learn_buddy_points_text_view);
			description = findViewById(R.id.learnbuddy_description_text_view);
			studies = findViewById(R.id.learn_buddy_study_text_view);
			imageUrl = findViewById(R.id.learn_buddy_image_url_image_view);
			buddyRank = findViewById(R.id.learn_buddy_rank_text_view);

			username.setText(learnBuddy.getUsername());
			buddyPoints.setText(String.valueOf(learnBuddy.getBuddyPoints()));
			buddyRank.setText(learnBuddy.getBuddyRank().toString());
			description.setText(learnBuddy.getDescription());


			StringBuilder builder = new StringBuilder();

			for(int i = 0; i < learnBuddy.getStudies().size(); ++i){
				builder.append(learnBuddy.getStudies().get(i));
				builder.append("\n");
			}

			studies.setText(builder);

			if(learnBuddy.getImage() != null){
				Picasso.get()
						.load(learnBuddy.getImage())
						.fit()
						.centerInside()
						.into(imageUrl);
			}

		}
	}
}
