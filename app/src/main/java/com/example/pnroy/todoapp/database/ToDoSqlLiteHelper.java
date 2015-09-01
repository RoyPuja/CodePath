package com.example.pnroy.todoapp.database;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by pnroy on 8/30/15.
 */
public class ToDoSqlLiteHelper extends SQLiteOpenHelper {
    public ToDoSqlLiteHelper(Context context){
        //DATABASE NAME :todo_data,VERSION :1
        super(context,"todos_data",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table todos(_id INTEGER PRIMARY KEY AUTOINCREMENT,todo TEXT NOT NULL,priority TEXT NOT NULL,date TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldver,int newver){
        db.execSQL("DROP TABLE IF EXISTS todos");
        onCreate(db);

    }

}




