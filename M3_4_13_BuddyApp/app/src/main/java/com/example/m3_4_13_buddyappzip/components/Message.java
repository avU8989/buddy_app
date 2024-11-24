package com.example.m3_4_13_buddyappzip.components;

import java.io.Serializable;

public class Message implements Serializable {
    public enum Sender { USER, BOT1, BOT2, BOT3 }

    private String text;
    private Sender sender;

    // Konstruktor, Getter und Setter
    public Message(String text, Sender sender){
        this.text = text;
        this.sender = sender;
    }

    public String getText(){
        return text;
    }

    public Sender getSender(){
        return sender;
    }
}


