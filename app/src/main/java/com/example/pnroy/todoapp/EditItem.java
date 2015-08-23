package com.example.pnroy.todoapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;



/**
 * Created by pnroy on 8/22/15.
 */
public class EditItem extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setContentView(R.layout.activity_edit_item);
        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.blue_tick);
        String text;
        final EditText txtShow=(EditText)findViewById(R.id.TVEdit);
        final Button btnSave=(Button) findViewById(R.id.btnSave);
        //get values passed from the previous activity
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            text= null;

        } else {
            text= extras.getString("text");
            txtShow.setText(text);
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pass back the updated text back to the calling activity
                Intent data = new Intent();
                data.putExtra("editedText", txtShow.getText().toString());
                // Activity finished ok, return the data
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish();

            }
        });
    }



}
