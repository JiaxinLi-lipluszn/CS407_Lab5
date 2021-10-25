package com.example.notes;

public class Note {
    private String data;
    private String username;
    private String title;
    private String content;
    public Note(String data, String username, String title, String content){
        this.data=data;
        this.username=username;
        this.title=title;
        this.content=content;
    }

    public String getData(){
        return data;
    }

    public String getUsername(){
        return username;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

}
