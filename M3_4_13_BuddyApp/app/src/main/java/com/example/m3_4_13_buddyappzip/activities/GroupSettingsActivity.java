package com.example.m3_4_13_buddyappzip.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m3_4_13_buddyappzip.R;

public class GroupSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_settings);

        // Get the group name passed from GroupChatActivity
        String groupchat = getIntent().getStringExtra("groupchat");

        // Set the group name as the title
        TextView groupNameTitle = findViewById(R.id.group_name_title);
        groupNameTitle.setText(groupchat);

        // Get the LinearLayouts and the ImageViews for all users
        final LinearLayout requestUser1Layout = findViewById(R.id.layout_request_user1);
        ImageView addUser1 = findViewById(R.id.add_user1);
        ImageView declineUser1 = findViewById(R.id.decline_user1);
        ImageView userInfo1 =findViewById(R.id.user_info1);

        final LinearLayout requestUser2Layout = findViewById(R.id.layout_request_user2);
        ImageView addUser2 = findViewById(R.id.add_user2);
        ImageView declineUser2 = findViewById(R.id.decline_user2);
        ImageView userInfo2 =findViewById(R.id.user_info2);


        final LinearLayout requestUser3Layout = findViewById(R.id.layout_request_user3);
        ImageView addUser3 = findViewById(R.id.add_user3);
        ImageView declineUser3 = findViewById(R.id.decline_user3);
        ImageView userInfo3 =findViewById(R.id.user_info3);


        final LinearLayout requestUser4Layout = findViewById(R.id.layout_request_user4);
        ImageView addUser4 = findViewById(R.id.add_user4);
        ImageView declineUser4 = findViewById(R.id.decline_user4);
        ImageView userInfo4 =findViewById(R.id.user_info4);


        final LinearLayout requestUser5Layout = findViewById(R.id.layout_request_user5);
        ImageView addUser5 = findViewById(R.id.add_user5);
        ImageView declineUser5 = findViewById(R.id.decline_user5);
        ImageView userInfo5 =findViewById(R.id.user_info5);


        // Set click listeners for the ImageViews for all users


        addUser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUser1Layout.setVisibility(View.GONE);
            }
        });

        declineUser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUser1Layout.setVisibility(View.GONE);
            }
        });

        userInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(GroupSettingsActivity.this, "Not implemented", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        addUser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUser2Layout.setVisibility(View.GONE);
            }
        });

        declineUser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUser2Layout.setVisibility(View.GONE);
            }
        });
        userInfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(GroupSettingsActivity.this, "Not implemented", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        addUser3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUser3Layout.setVisibility(View.GONE);
            }
        });

        declineUser3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUser3Layout.setVisibility(View.GONE);
            }
        });

        userInfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(GroupSettingsActivity.this, "Not implemented", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        addUser4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUser4Layout.setVisibility(View.GONE);
            }
        });

        userInfo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(GroupSettingsActivity.this, "Not implemented", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        declineUser4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUser4Layout.setVisibility(View.GONE);
            }
        });

        addUser5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUser5Layout.setVisibility(View.GONE);
            }
        });

        declineUser5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUser5Layout.setVisibility(View.GONE);
            }
        });

        userInfo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(GroupSettingsActivity.this, "Not implemented", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
