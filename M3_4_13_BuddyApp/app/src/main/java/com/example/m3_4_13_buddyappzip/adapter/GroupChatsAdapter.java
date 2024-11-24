package com.example.m3_4_13_buddyappzip.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.m3_4_13_buddyappzip.components.Message;
import com.example.m3_4_13_buddyappzip.R;

import com.example.m3_4_13_buddyappzip.R;
import com.example.m3_4_13_buddyappzip.components.Message;


import java.util.List;

public class GroupChatsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> messages;

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED_1 = 2;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED_2 = 3;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED_3 = 4;

    public GroupChatsAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);

        if (message.getSender() == Message.Sender.USER) {
            // Wenn die aktuelle Nachricht vom Benutzer stammt, geben Sie den Code für den "gesendeten" Nachrichtentyp zurück.
            return VIEW_TYPE_MESSAGE_SENT;
        } else if (message.getSender() == Message.Sender.BOT1) {
            return VIEW_TYPE_MESSAGE_RECEIVED_1;
        } else if (message.getSender() == Message.Sender.BOT2) {
            return VIEW_TYPE_MESSAGE_RECEIVED_2;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED_3;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_user_message, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED_1) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_bot1_message, parent, false);
            return new ReceivedMessageHolder1(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED_2) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_bot2_message, parent, false);
            return new ReceivedMessageHolder2(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_bot3_message, parent, false);
            return new ReceivedMessageHolder3(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED_1:
                ((ReceivedMessageHolder1) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED_2:
                ((ReceivedMessageHolder2) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED_3:
                ((ReceivedMessageHolder3) holder).bind(message);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_view_message_sent);
        }

        void bind(Message message) {
            messageText.setText(message.getText());
        }
    }

    private class ReceivedMessageHolder1 extends RecyclerView.ViewHolder {
        TextView messageText;

        ReceivedMessageHolder1(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_view_message_received_bot1);
        }

        void bind(Message message) {
            messageText.setText(message.getText());
        }
    }

    private class ReceivedMessageHolder2 extends RecyclerView.ViewHolder {
        TextView messageText;

        ReceivedMessageHolder2(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_view_message_received_bot2);
        }

        void bind(Message message) {
            messageText.setText(message.getText());
        }
    }
    private class ReceivedMessageHolder3 extends RecyclerView.ViewHolder {
        TextView messageText;

        ReceivedMessageHolder3(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_view_message_received_bot3);
        }

        void bind(Message message) {
            messageText.setText(message.getText());
        }
    }
}
