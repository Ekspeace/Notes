package com.ekspeace.notes.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note {
    private String Message;
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String Date;

    public Note() {
    }

    public Note(String message, int id, String date) {
        Message = message;
        Id = id;
        Date = date;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
