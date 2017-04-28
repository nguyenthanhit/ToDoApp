package com.thanhit.todoapp.model;

import java.io.Serializable;

import static android.icu.text.Normalizer.NO;

/**
 * Created by Admin on 4/27/2017.
 */

public class Note implements Serializable {

    private int id;
    private String taskName;
    private String taskNote;
    private String dueDate;
    private String priority;
    private String status;

    public Note() {}

    public Note(String taskName, String dueDate, String taskNote, String priority, String status) {

        this.taskName = taskName;
        this.taskNote = taskNote;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }
    public Note(int id, String taskName,  String dueDate, String taskNote,String priority, String status) {
        this.id = id;
        this.taskName = taskName;
        this.taskNote = taskNote;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskNote() {
        return taskNote;
    }

    public void setTaskNote(String taskNote) {
        this.taskNote = taskNote;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
