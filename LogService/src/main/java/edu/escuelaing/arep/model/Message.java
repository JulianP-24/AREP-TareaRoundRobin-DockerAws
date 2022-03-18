package edu.escuelaing.arep.model;

import java.util.Date;

public class Message {
    private Date date;
    private String message;

    public Message(String message) {
        this.date = new Date();
        this.message = message;
    }

    public Message(String message, Date date ) {
        System.out.println("++++++++++++++++++++++");
        System.out.println(message);
        System.out.println(date);
        this.date = date;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
