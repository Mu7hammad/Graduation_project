package com.example.graduationproject.Models;

public class TimeTableItem {

    private String title;
    private String date;
    private String time;
    private String room;
    private String instructor_name;

    public TimeTableItem(String title, String date, String time, String room, String instructor_name) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.room = room;
        this.instructor_name = instructor_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getInstructor_name() {
        return instructor_name;
    }

    public void setInstructor_name(String instructor_name) {
        this.instructor_name = instructor_name;
    }
}
