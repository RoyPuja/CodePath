package com.example.pnroy.todoapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.io.IOException;
import java.util.ArrayList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.EditText;
import java.io.File;
import org.apache.commons.io.FileUtils;
import android.content.Intent;
import android.view.Window;


public class MainActivity extends Activity {

    //declare the items
    ArrayList<String> todoItems;
    ArrayAdapter<String> toDoAdapter;
    ListView lvDisplayItems;
    EditText txtInput;
    int REQUEST_CODE=400;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.blue_tick);
        populateArrayItems();
        lvDisplayItems=(ListView)findViewById(R.id.lvShowItems);
        lvDisplayItems.setAdapter(toDoAdapter);
        txtInput=(EditText)findViewById(R.id.txtAdd);

        //OnItemClickListener for the listview opens up the page to edit the item
        lvDisplayItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                Intent intent=new Intent(view.getContext()  ,EditItem.class);
                intent.putExtra("text", String.valueOf(parent.getItemAtPosition(position)));
                intent.putExtra("pos", position);
                pos=position;
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
        //OnItemLongClickListener for the list view removes the item
        lvDisplayItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override

            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                toDoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }

        });
    }

    //populates the listview with the items after reading from the file
    public void populateArrayItems(){
        readItems();
        toDoAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todoItems);
    }
    private void readItems() {
        File filesDir=getFilesDir();
        File file=new File(filesDir,"todo.txt");
        try{
            todoItems=new ArrayList<String>(FileUtils.readLines(file));
        }
        catch (IOException e){

        }

    }
// writes data to the file
    private void writeItems() {
        File filesDir=getFilesDir();
        File file=new File(filesDir,"todo.txt");
        try{
            FileUtils.writeLines(file, todoItems);
        }
        catch (IOException e){

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // Adds items to the listview on clicking the add button
    public void onAddItem(View view) {
        toDoAdapter.add(txtInput.getText().toString());
        txtInput.setText("");
        writeItems();


    }
//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String name = data.getExtras().getString("editedText");
            //int pos = data.getExtras().getInt("position");
            todoItems.set(pos,name);
            toDoAdapter.notifyDataSetChanged();
            writeItems();
        }
    }
}
