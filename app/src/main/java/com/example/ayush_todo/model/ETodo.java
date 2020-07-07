package com.example.ayush_todo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.ayush_todo.util.DateConverter;

import java.util.Date;

@Entity(tableName = "Notes_table")

public class ETodo {


    @PrimaryKey(autoGenerate = true)
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @ColumnInfo(name="title")
    private String title;

    @ColumnInfo(name = "Description")
    private String Description;

    @ColumnInfo(name = "todo_date")
    @TypeConverters({DateConverter.class})
    private Date todo_date;

    @ColumnInfo(name = "priority")
    private int priority;

    @ColumnInfo(name = "is_completed")
    private boolean is_completed;

    @Ignore
    public ETodo(){


    }

    public ETodo(@NonNull String title,String Description, Date todo_date, int priority, boolean is_completed)
    {
        this.title = title;
        this.Description = Description;
        this. todo_date = todo_date;
        this.priority = priority;
        this.is_completed = is_completed;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getTodo_date() {
        return todo_date;
    }

    public void setTodo_date(Date todo_date) {
        this.todo_date = todo_date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isIs_completed() {
        return is_completed;
    }

    public void setIs_completed(boolean is_completed) {
        this.is_completed = is_completed;
    }
}

