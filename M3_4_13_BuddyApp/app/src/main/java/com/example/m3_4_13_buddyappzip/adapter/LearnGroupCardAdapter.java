package com.example.m3_4_13_buddyappzip.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.activities.DetailsLearnGroupActivity;
import com.example.m3_4_13_buddyappzip.activities.DiscoverLearnBuddyActivity;
import com.example.m3_4_13_buddyappzip.activities.DiscoverLearnGroupActivity;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.m3_4_13_buddyappzip.components.LearnGroup;
import com.example.m3_4_13_buddyappzip.adapter.utility.ItemTouchHelperAdapter;

public class LearnGroupCardAdapter extends RecyclerView.Adapter<LearnGroupCardAdapter.LearnGroupViewHolder> implements ItemTouchHelperAdapter {

    private List<LearnGroup> learnGroupList = new ArrayList<>();
    private WeakReference<DiscoverLearnGroupActivity> activityRef;

    public LearnGroupCardAdapter(DiscoverLearnGroupActivity activity) {
        this.activityRef = new WeakReference<DiscoverLearnGroupActivity>(activity);
    }

    @NonNull
    @Override
    public LearnGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.learn_group_card, parent, false);
        return new LearnGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnGroupViewHolder holder, int position) {
        LearnGroup learnGroup = learnGroupList.get(position);
        holder.setData(learnGroup);
        holder.arrow_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), DetailsLearnGroupActivity.class);
                intent.putExtra("learnGroup_details", learnGroup);

                view.getContext().startActivity(intent);
            }
        });

        if(!learnGroupList.isEmpty()) {
            holder.likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    learnGroupList.remove(holder.getBindingAdapterPosition());
                    notifyItemRemoved(holder.getBindingAdapterPosition());

                    Activity activity = activityRef.get();
                    if (activity != null && learnGroupList.isEmpty()) {
                        TextView noCardsLeftTextView = activity.findViewById(R.id.nocards_left_text_view);
                        noCardsLeftTextView.setVisibility(View.VISIBLE);
                    }
                }

            });

            holder.rejectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    learnGroupList.remove(holder.getBindingAdapterPosition());
                    notifyItemRemoved(holder.getBindingAdapterPosition());

                    Activity activity = activityRef.get();
                    if (activity != null && learnGroupList.isEmpty()) {
                        TextView noCardsLeftTextView = activity.findViewById(R.id.nocards_left_text_view);
                        noCardsLeftTextView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    public void setLearnGroupList(List<LearnGroup> learnGroupList){
        this.learnGroupList = new ArrayList<>(learnGroupList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return learnGroupList.size();
    }

    @Override
    public boolean onItemMove(int fromPos, int toPos) {
       //implement the action to be taken when an item is moved
        if(fromPos > learnGroupList.size() || fromPos < 0){
            throw new IllegalArgumentException("invalid from position");
        }

        if(toPos > learnGroupList.size() || toPos < 0){
            throw new IllegalArgumentException("invalid to position");
        }

        Collections.swap(learnGroupList, fromPos, toPos);
        notifyItemMoved(fromPos, toPos);
        return true;
    }

    @Override
    public void onItemSwiped(int pos) {
        //implement the action to be taken when an item is swiped
        learnGroupList.remove(pos);
        notifyItemRemoved(pos);

        Activity activity = activityRef.get();
        if (activity != null && learnGroupList.isEmpty()) {
            TextView noCardsLeftTextView = activity.findViewById(R.id.nocards_left_text_view);
            noCardsLeftTextView.setVisibility(View.VISIBLE);
        }
    }

    public class LearnGroupViewHolder extends RecyclerView.ViewHolder{
        private ImageView learnGroupImageView;
        private TextView learnGroupUniversityTextView;
        private TextView learnGroupTitleTextView;
        private TextView learnGroupMembersTextView;
        private TextView courseTextView;

        private ImageView likeButton;
        private ImageView rejectButton;

        private ImageView arrow_icon;

        public LearnGroupViewHolder(@NonNull View itemView) {
            super(itemView);

            learnGroupImageView = itemView.findViewById(R.id.learn_group_image_url_image_view);
            learnGroupMembersTextView = itemView.findViewById(R.id.learngroup_members_text_view);
            learnGroupTitleTextView = itemView.findViewById(R.id.learn_group_title_text_view);
            learnGroupUniversityTextView = itemView.findViewById(R.id.learngroup_university_name_text_view);
            courseTextView = itemView.findViewById(R.id.learngroup_course_text_view);
            likeButton = itemView.findViewById(R.id.like_button);
            rejectButton = itemView.findViewById(R.id.reject_button);
            arrow_icon = itemView.findViewById(R.id.arrow_icon);

        }

        public void setData (LearnGroup learnGroup){
            Picasso.get()
                    .load(learnGroup.getImageUrl())
                    .fit()
                    .centerInside()
                    .into(learnGroupImageView);

            learnGroupTitleTextView.setText(learnGroup.getName());
            learnGroupUniversityTextView.setText(learnGroup.getUniverstity());
            courseTextView.setText(learnGroup.getCourse().getCourseName());
            learnGroupMembersTextView.setText("Members: " + String.valueOf(learnGroup.getMemberCount()));
        }

    }






}
