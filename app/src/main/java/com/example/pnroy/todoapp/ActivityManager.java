package com.example.pnroy.todoapp;

import android.app.ListActivity;

import com.example.pnroy.todoapp.dao.ToDoDao;
import com.example.pnroy.todoapp.data.ToDoItem;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;
import android.widget.AdapterView;
import android.content.Intent;
import java.util.Date;
import java.text.DateFormat;

/**
 * Created by pnroy on 8/30/15.
 */
public class ActivityManager extends ListActivity {

    private ToDoDao dao;
    int REQUEST_CODE=400;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText txtAdd=(EditText)findViewById(R.id.txtAdd);
        final Button btnAdd=(Button)findViewById(R.id.btnAdd);
        final ListView list=(ListView)findViewById(android.R.id.list);
         //TextView dateview=(TextView)findViewById(R.id.dateDisplay);
        //String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        //dateview.setText(currentDateTimeString);
        //Create dao object
        dao=new ToDoDao(this);

        final ListAdapter ls=new ListAdapter(this,dao.getToDo());
        setListAdapter(ls);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoItem todo = new ToDoItem();
                todo.setText(txtAdd.getText().toString());
                todo.setPriority("Low");
                todo.setDateofcompletion("0000-00-00");
                txtAdd.setText("");
                //Add text to database
                dao.createToDo(todo);
                //Display information on successfull add to the database
                setListAdapter(ls);
                ls.add(todo);
                ls.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "New TODO item added", Toast.LENGTH_SHORT).show();
            }

        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override

            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ToDoItem todoitem = (ToDoItem) getListAdapter().getItem(position);
                dao.deleteToDo(todoitem.getId());
                ls.remove(todoitem);
                setListAdapter(ls);
                ls.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Item Deleted!", Toast.LENGTH_SHORT).show();
                return true;
            }

        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToDoItem todoitem = (ToDoItem) getListAdapter().getItem(position);
                Intent intent = new Intent(view.getContext(), EditItem.class);
                intent.putExtra("text", todoitem.getText());
                intent.putExtra("priority", todoitem.getPriority());
                intent.putExtra("date", todoitem.getDateofcompletion());
                pos = position;
                startActivityForResult(intent, REQUEST_CODE);


            }

        });


    }

    @Override
//    protected void onListItemClick(ListView l,View v,int position,long id){
//        Intent intent=new Intent(v.getContext()  ,EditItem.class);
//                intent.putExtra("text", String.valueOf(l.getItemAtPosition(position)));
//                intent.putExtra("pos", position);
//                pos=position;
//                startActivityForResult(intent, REQUEST_CODE);
//
//    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        dao=new ToDoDao(this);
        ListAdapter ls;
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String name = data.getExtras().getString("editedText");
            String priority=data.getExtras().getString("editedPriority");
            String date=data.getExtras().getString("editedDate");
            //int pos = data.getExtras().getInt("position");
            ToDoItem todoitem = (ToDoItem) getListAdapter().getItem(pos);
            todoitem.setText(name);
            todoitem.setPriority(priority);
            todoitem.setDateofcompletion(date);
            dao.updateToDo(todoitem);
           ls=new ListAdapter(this,dao.getToDo());
            setListAdapter(ls);
            ls.notifyDataSetChanged();

        }
    }


}
