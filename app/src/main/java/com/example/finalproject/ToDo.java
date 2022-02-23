package com.example.finalproject;

public class ToDo {

    private String title;
    private String info;
    private String Due;
    private String Do;
    private String Id;

    public ToDo(String title, String info, String Due,String Do,String Id) {
        this.title = title;
        this.info = info;
        this.Due = Due;
        this.Do = Do;
        this.Id = Id;
    }

    String getTitle() {
        return title;
    }
    String getInfo() {
        return info;
    }
    String getDue(){return Due;}
    String getDo(){return Do;}
    String getId(){return Id;}
}
