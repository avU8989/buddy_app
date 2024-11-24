package com.example.m3_4_13_buddyappzip.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.components.User;
import com.example.m3_4_13_buddyappzip.activities.CourseForumActivity;
import com.example.m3_4_13_buddyappzip.activities.CourseThreadActivity;
import com.example.m3_4_13_buddyappzip.activities.DetailsLearnBuddyActivity;
import com.example.m3_4_13_buddyappzip.components.Course;
import com.example.m3_4_13_buddyappzip.components.Post;
import com.example.m3_4_13_buddyappzip.components.StudyProgram;
import com.squareup.picasso.Picasso;
import com.example.m3_4_13_buddyappzip.components.Post;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{
    private List<Post> posts = new ArrayList<>();

   @NonNull
   @Override
   public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
       LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       View view = inflater.inflate(R.layout.post_item, parent, false);
       return new PostViewHolder(view);
   }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.setData(post);
        holder.messageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), CourseThreadActivity.class);
                intent.putExtra("post", post);

                view.getContext().startActivity(intent);
            }
        });

        holder.upvoteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int upvotes = post.getUpvote();
                ++upvotes;
                post.setUpvote(upvotes);
                holder.setData(post);
                holder.upvoteButton.setOnClickListener(null); // Disable further upvotes
            }
        });

        holder.downvoteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int downvotes = post.getDownvote();
                ++downvotes;
                post.setDownvote(downvotes);
                holder.setData(post);
                holder.downvoteButton.setOnClickListener(null); // Disable further downvotes
            }
        });


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts){
       this.posts = new ArrayList<>(posts);
       notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        private TextView user;
        private TextView course;
        private TextView description;
        private TextView postTitle;
        private TextView upvote;
        private TextView messagesCount;
        private TextView downvote;
        private TextView messages;
        private TextView date;
        private ImageView messageButton;
        private ImageView imageUrl;
        private ImageView upvoteButton;
        private ImageView downvoteButton;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            user = itemView.findViewById(R.id.post_user_text_view);
            postTitle = itemView.findViewById(R.id.post_title_text_view);
            upvote = itemView.findViewById(R.id.upvote_count_text_view);
            downvote = itemView.findViewById(R.id.downvote_count_text_view);
            description = itemView.findViewById(R.id.post_description_text_view);
            messagesCount = itemView.findViewById(R.id.message_count_text_view);
            messageButton = itemView.findViewById(R.id.message_icon);
            upvoteButton = itemView.findViewById(R.id.upvote_icon);
            downvoteButton = itemView.findViewById(R.id.downvote_icon);
        }

        public void setData (Post post){
            /*
            if(post.getImageUrl() != null || !post.getImageUrl().equals(" ")){
                Picasso.get()
                        .load(post.getImageUrl())
                        .fit()
                        .centerInside()
                        .into(postImageView);
            }*/

            user.setText(post.getCourse().getCourseName());
            postTitle.setText(post.getPostTitle());
            description.setText(post.getPostDescription());
            upvote.setText(String.valueOf(post.getUpvote()));
            downvote.setText(String.valueOf(post.getDownvote()));
            messagesCount.setText(String.valueOf(post.getMessages().size()));

        }

    }

}
