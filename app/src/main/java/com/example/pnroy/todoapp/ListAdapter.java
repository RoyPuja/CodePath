package com.example.pnroy.todoapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import java.util.List;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.pnroy.todoapp.data.ToDoItem;
/**
 * Created by pnroy on 8/30/15.
 */
public class ListAdapter extends ArrayAdapter<ToDoItem> {

    private final Context context;
    private final List<ToDoItem> todolist;

    public ListAdapter(Context context,List<ToDoItem> todolist){
        super(context,R.layout.todolist,todolist);
        this.context=context;
        this.todolist=todolist;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){

        final ToDoItem toDoItem = (ToDoItem)getItem(position);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       // View rowView=inflater.inflate(R.layout.todolist,parent,false);
        RelativeLayout itemLayout = (RelativeLayout)inflater.inflate(R.layout.todolist, null);
        final TextView titleView = (TextView) itemLayout.findViewById(R.id.todoText);
        titleView.setText(toDoItem.getText());
        final TextView priority = (TextView) itemLayout.findViewById(R.id.priorityView);
        priority.setText(toDoItem.getPriority());
        final TextView date = (TextView) itemLayout.findViewById(R.id.dateView);
        date.setText(toDoItem.getDateofcompletion());
        return itemLayout;
    }

}
