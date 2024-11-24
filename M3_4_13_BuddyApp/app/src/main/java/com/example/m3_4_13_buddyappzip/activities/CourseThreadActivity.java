package com.example.m3_4_13_buddyappzip.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.adapter.CourseAdapter;
import com.example.m3_4_13_buddyappzip.adapter.ForumMessageAdapter;
import com.example.m3_4_13_buddyappzip.adapter.PostAdapter;
import com.example.m3_4_13_buddyappzip.components.Course;
import com.example.m3_4_13_buddyappzip.components.ForumMessage;
import com.example.m3_4_13_buddyappzip.components.LearnBuddy;
import com.example.m3_4_13_buddyappzip.components.Post;
import com.example.m3_4_13_buddyappzip.components.StudyProgram;
import com.example.m3_4_13_buddyappzip.components.User;
import com.example.m3_4_13_buddyappzip.utility.CourseJsonParser;
import com.example.m3_4_13_buddyappzip.utility.ForumMessageParser;
import com.example.m3_4_13_buddyappzip.utility.PostJsonParser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CourseThreadActivity extends AppCompatActivity {
	private RecyclerView recyclerView_messages;
	private ForumMessageAdapter adapter;
	private PostJsonParser postJsonParser;
	private ForumMessageParser forumMessageParser;
	private TextView course;
	private TextView description;
	private TextView postTitle;
	private TextView upvote;
	private TextView messagesCount;
	private TextView downvote;
	private TextView date;
	private Post post;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_thread);


		Toolbar toolbar = findViewById(R.id.toolBar);
		adapter = new ForumMessageAdapter();
		forumMessageParser = new ForumMessageParser();
		recyclerView_messages = findViewById(R.id.recycler_view_messages);

		Bundle extras = getIntent().getExtras();

		if(extras != null && extras.containsKey("post")){
			course = findViewById(R.id.post_user_text_view);
			postTitle = findViewById(R.id.post_title_text_view);
			upvote = findViewById(R.id.upvote_count_text_view);
			downvote = findViewById(R.id.downvote_count_text_view);
			description = findViewById(R.id.post_description_text_view);
			messagesCount = findViewById(R.id.message_count_text_view);

			ImageView downvoteButton = findViewById(R.id.downvote_icon);
			ImageView upvoteButton = findViewById(R.id.upvote_icon);

			post = (Post) extras.getSerializable("post");
			course.setText(post.getCourse().getCourseName());
			postTitle.setText(post.getPostTitle());
			description.setText(post.getPostDescription());
			upvote.setText(String.valueOf(post.getUpvote()));
			downvote.setText(String.valueOf(post.getDownvote()));
			if(post.getMessages() != null){
				messagesCount.setText(String.valueOf(post.getMessages().size()));

			}
			upvoteButton.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view){
					int upvotes = post.getUpvote();
					++upvotes;
					post.setUpvote(upvotes);
					upvote.setText(String.valueOf(post.getUpvote()));
					upvote.invalidate();
					upvoteButton.setOnClickListener(null); // Disable further upvotes

				}
			});

			downvoteButton.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view){
					int downvotes = post.getDownvote();
					++downvotes;
					post.setDownvote(downvotes);
					downvote.setText(String.valueOf(post.getDownvote()));
					downvote.invalidate();
					downvoteButton.setOnClickListener(null); // Disable further upvotes
				}
			});

			try {
				InputStream inputStream = getAssets().open("posts.json");
				List<ForumMessage> messages = forumMessageParser.parseMessagesOfPost(inputStream, post.getPostTitle());
				adapter.setMessages(messages);

				recyclerView_messages = findViewById(R.id.recycler_view_messages);
				recyclerView_messages.setLayoutManager(new LinearLayoutManager(this));
				recyclerView_messages.setAdapter(adapter);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		setUpSendMessage();

	}

	public void setUpSendMessage() {
		Button sendButton = findViewById(R.id.button_send);
		EditText messageEditText = findViewById(R.id.edit_text_message);
		RecyclerView recyclerView = findViewById(R.id.recycler_view_messages);

		sendButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Get user input from the EditText
				String messageText = messageEditText.getText().toString();

				// Create a new ForumMessage object
				User user = new User("You"); // Replace with the actual User object
				ForumMessage newMessage = new ForumMessage(user, messageText, 0, 0);
				post.getMessages().add(newMessage);
				messagesCount.setText(String.valueOf(post.getMessages().size()));
				messagesCount.invalidate();

				// Add the new message to your list or data source
				adapter.addMessage(newMessage); // Add this method to your ForumMessageAdapter class

				// Notify the adapter that a new message is added
				adapter.notifyItemInserted(adapter.getItemCount() - 1);

				// Scroll the RecyclerView to the last item
				recyclerView.scrollToPosition(adapter.getItemCount() - 1);

				// Clear the EditText after sending the message
				messageEditText.setText("");
			}
		});
	}


}
