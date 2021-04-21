package com.example.trickapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trick_table")
public class Trick {

    @PrimaryKey(autoGenerate = true)
    private int id;

    // Name of the trick
    private String title;

    // Any notes they have about the attempt
    private String notes;

    // Was it clean, sketch, or did you not land it
    private String style;

    // TODO: add date and time?


    public Trick(String title, String notes, String style) {
        this.title = title;
        this.notes = notes;
        this.style = style;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

    public String getStyle() {
        return style;
    }
}
