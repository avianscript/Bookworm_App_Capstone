package com.techelevator.model.User;

public class Reading {

    private int user_id;
    private int book_id;
    private int minutes_read;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getMinutes_read() {
        return minutes_read;
    }

    public void setMinutes_read(int minutes_read) {
        this.minutes_read = minutes_read;
    }
}
