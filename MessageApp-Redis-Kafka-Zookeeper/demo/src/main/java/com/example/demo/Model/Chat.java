package com.example.demo.Model;
import java.util.ArrayList;
import java.util.List;

public class Chat {
    private String roomName;
    private List<String > users;
    public Chat(String roomName)
    {
        this.roomName=roomName;
        this.users=new ArrayList<>();
    }
}