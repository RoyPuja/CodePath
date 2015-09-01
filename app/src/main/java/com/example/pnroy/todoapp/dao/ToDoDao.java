package com.example.pnroy.todoapp.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.example.pnroy.todoapp.data.ToDoItem;

import com.example.pnroy.todoapp.database.ToDoSqlLiteHelper;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by pnroy on 8/30/15.
 */
public class ToDoDao {

    private SQLiteDatabase db;
    private ToDoSqlLiteHelper dbhelper;

    public ToDoDao(Context context){
        dbhelper=new ToDoSqlLiteHelper(context);
        db= dbhelper.getWritableDatabase();

    }

    public void close(){
        db.close();
    }

    public void createToDo(ToDoItem todo){
        ContentValues contentValues=new ContentValues();
        contentValues.put("todo",todo.getText());
        contentValues.put("priority",todo.getPriority());
        contentValues.put("date",todo.getDateofcompletion());
        db.insert("todos", null, contentValues);
    }

    public void deleteToDo(int todoId){
        db.delete("todos", "_id=" + todoId, null);

    }
    public void updateToDo(ToDoItem todo){
        ContentValues contentValues=new ContentValues();
        contentValues.put("todo",todo.getText());
        contentValues.put("priority",todo.getPriority());
        contentValues.put("date",todo.getDateofcompletion());
        db.update("todos", contentValues, "_id=" + todo.getId(),null);
    }

    public List<ToDoItem> getToDo(){
        List<ToDoItem> todoList=new ArrayList<ToDoItem>();
        //Columns to select
        String[] tablecol=new String[]{"_id","todo","priority","date"};
        Cursor cursor=db.query("todos",tablecol,null,null,null,null,null);
        cursor.moveToFirst();

        //loop through the results
        while(!cursor.isAfterLast()){
            ToDoItem todoitem=new ToDoItem();
            todoitem.setId(cursor.getInt(0));
            todoitem.setText(cursor.getString(1));
            todoitem.setPriority(cursor.getString(2));
            todoitem.setDateofcompletion(cursor.getString(3));
            todoList.add(todoitem);
            cursor.moveToNext();
        }
        return todoList;

    }
}
