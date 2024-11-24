package com.example.m3_4_13_buddyappzip.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.activities.CourseForumActivity;
import com.example.m3_4_13_buddyappzip.components.Course;
import com.example.m3_4_13_buddyappzip.components.StudyProgram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
	private List<Course> courses = new ArrayList<>();
	private Map<String, StudyProgram> studyPrograms = new HashMap<>();

	@NonNull
	@Override
	public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
		return new CourseViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
		Course course = courses.get(position);
		holder.titleButton.setText(course.getCourseName());

		holder.titleButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), CourseForumActivity.class);
				intent.putExtra("course_forum", course);

				view.getContext().startActivity(intent);
			}
		});

	}

	public void setCourses(List<Course> courses) {

		this.courses = new ArrayList<>(courses);
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return courses.size();
	}

	public class CourseViewHolder extends RecyclerView.ViewHolder {
		Button titleButton;

		CourseViewHolder(View itemView) {
			super(itemView);
			titleButton = itemView.findViewById(R.id.course_name_text_view);
		}
	}
}
