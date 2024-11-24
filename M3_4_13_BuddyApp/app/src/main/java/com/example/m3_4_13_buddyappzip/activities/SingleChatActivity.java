package com.example.m3_4_13_buddyappzip.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.m3_4_13_buddyappzip.components.Message;
import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.adapter.SingleChatAdapter;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.adapter.SingleChatAdapter;
import com.example.m3_4_13_buddyappzip.components.Message;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SingleChatActivity extends AppCompatActivity {
    private List<Message> messages = new ArrayList<>();
    private RecyclerView chatRecyclerView;
    private SingleChatAdapter adapter;
    private EditText inputMessage;

    private TextView name_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat);

        String username = getIntent().getStringExtra("username");
        String avatarUrl = getIntent().getStringExtra("avatarUrl");


        ImageView profilePicImageView = findViewById(R.id.profilePicImageView);
        Picasso.get().load(avatarUrl).fit().centerCrop().into(profilePicImageView);

        name_user = (TextView)findViewById(R.id.name_user);
        name_user.setText(username);

        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SingleChatAdapter(messages);
        chatRecyclerView.setAdapter(adapter);

        inputMessage = findViewById(R.id.inputMessage);

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = inputMessage.getText().toString();
                if (!messageText.isEmpty()) {
                    Message userMessage = new Message(messageText, Message.Sender.USER);
                    messages.add(userMessage);
                    Message botMessage = new Message(generateBotResponse(), Message.Sender.BOT1);
                    messages.add(botMessage);
                    adapter.notifyDataSetChanged();
                    chatRecyclerView.scrollToPosition(messages.size() - 1);
                    inputMessage.setText("");
                }
            }
        });

    }

    private String generateBotResponse() {
        List<String> myList = Arrays.asList(
                "Hello!",
                "Wow, that's so interesting!",
                "Nice, but have you heard of the amazing course HCI?",
                "Aha, go on..",
                "Hahaha that's mad",
                "Dudeeee... :O",
                "for real?",
                "Hey there!",
                "Madness, absolute madness.",
                "I love you",
                "K.",
                "Damn, you are so clever, aren't you :)",
                "Attack on Titan is goat",
                "You're so nice :)",
                "How are you doing?",
                "Amazing",
                "Absolutely bonkers",
                "Side eye",
                "Bombastic side eye, criminal offensive side eye"
                );

        Random r = new Random();

        int randomitem = r.nextInt(myList.size());
        String randomElement = myList.get(randomitem);


        return randomElement;
    }
}
