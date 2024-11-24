package com.example.m3_4_13_buddyappzip.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.components.Course;
import com.example.m3_4_13_buddyappzip.components.ForumMessage;
import com.example.m3_4_13_buddyappzip.components.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ForumMessageAdapter extends RecyclerView.Adapter<ForumMessageAdapter.ForumMessageViewHolder>{
	private List<ForumMessage> messages = new ArrayList<>();

	@NonNull
	@Override
	public ForumMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.forum_message_item, parent, false);
		return new ForumMessageViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ForumMessageViewHolder holder, int position) {
		ForumMessage message = messages.get(position);
		holder.setData(message);

		holder.upvoteButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				int upvotes = message.getUpvote();
				++upvotes;
				message.setUpvote(upvotes);
				holder.setData(message);
				holder.upvoteButton.setOnClickListener(null); // Disable further upvotes
			}
		});

		holder.downvoteButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				int downvotes = message.getDownvote();
				++downvotes;
				message.setDownvote(downvotes);
				holder.setData(message);
				holder.downvoteButton.setOnClickListener(null); // Disable further downvotes
			}
		});
	}

	@Override
	public int getItemCount() {
		return messages.size();
	}

	public void setMessages(List<ForumMessage> messages){
		this.messages = new ArrayList<>(messages);
		notifyDataSetChanged();
	}

	public void addMessage(ForumMessage newMessage) {
		this.messages.add(newMessage);
		notifyDataSetChanged();
	}

	public class ForumMessageViewHolder extends RecyclerView.ViewHolder{
		private TextView user;
		private TextView messageContent;
		private TextView upvote;
		private TextView downvote;
		private TextView date;
		private ImageView image;
		private ImageView upvoteButton;
		private ImageView downvoteButton;


		public ForumMessageViewHolder(@NonNull View itemView) {
			super(itemView);

			user = itemView.findViewById(R.id.text_view_username);
			image = itemView.findViewById(R.id.imageUrl);
			upvote = itemView.findViewById(R.id.upvote_count_text_view);
			downvote = itemView.findViewById(R.id.downvote_count_text_view);
			messageContent = itemView.findViewById(R.id.text_view_message);
			upvoteButton = itemView.findViewById(R.id.upvote_icon);
			downvoteButton = itemView.findViewById(R.id.downvote_icon);
		}

		public void setData (ForumMessage message){
			if(message.getUser().getAvatarUrl() != null){
				Picasso.get()
						.load(message.getUser().getAvatarUrl())
						.fit()
						.centerInside()
						.into(image);
			}
			user.setText(message.getUser().getUsername());
			messageContent.setText(message.getPostDescription());
			upvote.setText(String.valueOf(message.getUpvote()));
			downvote.setText(String.valueOf(message.getDownvote()));

		}

	}

}