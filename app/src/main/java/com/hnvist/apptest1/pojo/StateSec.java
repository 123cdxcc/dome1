package com.hnvist.apptest1.pojo;

public class StateSec {
    private int id;
    private int number;
    private String time;

    public StateSec() {
    }

    public StateSec(int id, int number, String time) {
        this.id = id;
        this.number = number;
        this.time = time;
    }

    public StateSec(int number, String time) {
        this.number = number;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
