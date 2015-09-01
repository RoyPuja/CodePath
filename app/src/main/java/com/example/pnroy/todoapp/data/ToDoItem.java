package com.example.pnroy.todoapp.data;

/**
 * Created by pnroy on 8/30/15.
 */
public class ToDoItem {
    private int id;
    private String text;
    private String priority;
    private String dateofcompletion;


    public  ToDoItem(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDateofcompletion() {
        return dateofcompletion;
    }

    public void setDateofcompletion(String dateofcompletion) {
        this.dateofcompletion = dateofcompletion;
    }
}
