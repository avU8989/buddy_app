package com.example.m3_4_13_buddyappzip.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.m3_4_13_buddyappzip.components.Group;
import com.example.m3_4_13_buddyappzip.R;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.components.Group;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    private List<Group> groups;
    private OnGroupClickListener listener;

    public interface OnGroupClickListener {
        void onGroupClick(Group group);
    }

    public GroupAdapter(List<Group> groups, OnGroupClickListener listener) {
        this.groups = groups;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.groupchat_item, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
       Group currentGroup = groups.get(position);
        holder.bind(currentGroup, listener);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    static class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView groupname;
        ImageView avatar;

        GroupViewHolder(View itemView) {
            super(itemView);
            groupname = itemView.findViewById(R.id.groupchat);
            avatar = itemView.findViewById(R.id.avatar);
        }

        void bind(final Group group, final OnGroupClickListener listener) {
            groupname.setText(group.getGroupname());
            Picasso.get().load(group.getAvatarUrl()).fit().centerCrop().into(avatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onGroupClick(group);
                }
            });
        }

    }
}
