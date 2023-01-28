package com.driver;

import com.fasterxml.jackson.annotation.JsonTypeId;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.Date;
@Component
public class Message {

    private int id;
    private String content;
    private Date timestamp;

    public Message(int id, String content) {
        this.id = id;
        this.content = content;
        this.timestamp = new Date(2023,01,28);
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
