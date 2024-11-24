package com.example.m3_4_13_buddyappzip.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.activities.DetailsLearnBuddyActivity;

import com.example.m3_4_13_buddyappzip.activities.DetailsLearnGroupActivity;
import com.example.m3_4_13_buddyappzip.activities.DiscoverLearnBuddyActivity;
import com.example.m3_4_13_buddyappzip.components.AcademicTitle;
import com.example.m3_4_13_buddyappzip.components.LearnBuddy;
import com.example.m3_4_13_buddyappzip.adapter.utility.ItemTouchHelperAdapter;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LearnBuddyCardAdapter extends RecyclerView.Adapter<LearnBuddyCardAdapter.LearnBuddyViewHolder> implements ItemTouchHelperAdapter {

    private List<LearnBuddy> learnBuddyList = new ArrayList<>();
    private WeakReference<DiscoverLearnBuddyActivity> activityRef;
    public LearnBuddyCardAdapter(DiscoverLearnBuddyActivity activity) {
        this.activityRef = new WeakReference<DiscoverLearnBuddyActivity>(activity);
    }
    @NonNull
    @Override
    public LearnBuddyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.learn_buddy_card, parent, false);
        return new LearnBuddyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnBuddyViewHolder holder, int position) {
        LearnBuddy learnBuddy = learnBuddyList.get(position);
        holder.setData(learnBuddy);

        holder.buddyImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), DetailsLearnBuddyActivity.class);
                intent.putExtra("learnBuddy_details", learnBuddy);

                view.getContext().startActivity(intent);
            }
        });

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learnBuddyList.remove(holder.getBindingAdapterPosition());
                notifyItemRemoved(holder.getBindingAdapterPosition());

                Activity activity = activityRef.get();
                if (activity != null && learnBuddyList.isEmpty()) {
                    TextView noCardsLeftTextView = activity.findViewById(R.id.nocards_left_text_view);
                    noCardsLeftTextView.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learnBuddyList.remove(holder.getBindingAdapterPosition());
                notifyItemRemoved(holder.getBindingAdapterPosition());

                Activity activity = activityRef.get();
                if (activity != null && learnBuddyList.isEmpty()) {
                    TextView noCardsLeftTextView = activity.findViewById(R.id.nocards_left_text_view);
                    noCardsLeftTextView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return learnBuddyList.size();
    }

    @Override
    public boolean onItemMove(int fromPos, int toPos) {
        //implement the action to be taken when an item is moved
        if(fromPos > learnBuddyList.size() || fromPos < 0){
            throw new IllegalArgumentException("invalid from position");
        }

        if(toPos > learnBuddyList.size() || toPos < 0){
            throw new IllegalArgumentException("invalid to position");
        }

        Collections.swap(learnBuddyList, fromPos, toPos);
        notifyItemMoved(fromPos, toPos);
        return true;
    }

    @Override
    public void onItemSwiped(int pos) {
        //implement the action to be taken when an item is swiped
        learnBuddyList.remove(pos);
        notifyItemRemoved(pos);

        Activity activity = activityRef.get();
        if (activity != null && learnBuddyList.isEmpty()) {
            TextView noCardsLeftTextView = activity.findViewById(R.id.nocards_left_text_view);
            noCardsLeftTextView.setVisibility(View.VISIBLE);
        }
    }

    public void setLearnBuddyList(List<LearnBuddy> learnBuddyList){
        this.learnBuddyList = new ArrayList<>(learnBuddyList);
        notifyDataSetChanged();
    }

    public List<LearnBuddy> getLearnBuddies(){
        return this.learnBuddyList;
    }

    public class LearnBuddyViewHolder extends RecyclerView.ViewHolder{
        private ImageView buddyImageView;
        private TextView buddyUserNameTextView;
        private TextView buddyRankTextView;
        private TextView buddyPointsTextView;
        private TextView buddyStudiesTextView;
        private TextView buddyDegree;
        private TextView buddyDescriptionTextView;

        private ImageView likeButton;
        private ImageView rejectButton;

        public LearnBuddyViewHolder(@NonNull View itemView){
            super(itemView);

            buddyImageView = itemView.findViewById(R.id.learn_buddy_image_url_image_view);
            buddyDegree = itemView.findViewById(R.id.learn_buddy_degree_text_view);
            buddyUserNameTextView = itemView.findViewById(R.id.learn_buddy_username_text_view);
            buddyPointsTextView = itemView.findViewById(R.id.learn_buddy_points_text_view);
            buddyRankTextView = itemView.findViewById(R.id.learn_buddy_rank_text_view);
            buddyStudiesTextView = itemView.findViewById(R.id.learn_buddy_study_text_view);
            buddyDescriptionTextView = itemView.findViewById(R.id.learnbuddy_description_text_view);
            likeButton = itemView.findViewById(R.id.like_button);
            rejectButton = itemView.findViewById(R.id.reject_button);
        }

        public void setData (LearnBuddy buddy){
            Picasso.get()
                    .load(buddy.getImage())
                    .fit()
                    .centerInside()
                    .into(buddyImageView);

            buddyUserNameTextView.setText(buddy.getUsername());
            buddyDescriptionTextView.setText(buddy.getDescription());
            buddyPointsTextView.setText("BuddyPoints: " + String.valueOf(buddy.getBuddyPoints()));
            buddyRankTextView.setText(buddy.getBuddyRank().toString());

            StringBuilder builder = new StringBuilder();

            for(int i = 0; i < buddy.getStudies().size(); ++i){
               builder.append(buddy.getStudies().get(i));
               builder.append(" (" + buddy.getStudies().get(i).getAcademicTitle().toString() + ")");
               builder.append("\n");
                Log.d("LearnBuddyCardAdapter", builder.toString());

            }

            buddyStudiesTextView.setText(builder);

        }
    }



}
