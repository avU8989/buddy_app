package com.example.m3_4_13_buddyappzip.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.components.LearnGroup;
import com.squareup.picasso.Picasso;

public class DetailsLearnGroupActivity extends AppCompatActivity {
	private TextView course;
	private TextView description;
	private TextView university;
	private ImageView imageUrl;
	private TextView memberCount;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_learngroup);

		Bundle extras = getIntent().getExtras();
		if (extras != null && extras.containsKey("learnGroup_details")) {
			LearnGroup learnGroup = (LearnGroup) extras.getSerializable("learnGroup_details");
			course = findViewById(R.id.learn_group_title_text_view);
			description = findViewById(R.id.learngroup_description_text_view);
			university = findViewById(R.id.learngroup_university_name_text_view);
			memberCount = findViewById(R.id.learngroup_members_text_view);
			imageUrl = findViewById(R.id.learn_group_image_url_image_view);

			course.setText(learnGroup.getCourse().getCourseName());
			description.setText(learnGroup.getDescription());
			university.setText(learnGroup.getUniverstity());
			memberCount.setText("Members: " + String.valueOf(learnGroup.getMemberCount()));

			if(learnGroup.getImageUrl() != null){
				Picasso.get()
						.load(learnGroup.getImageUrl())
						.fit()
						.centerInside()
						.into(imageUrl);
			}

		}


	}
}
