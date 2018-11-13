package com.smartcamp.aua.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class WorkshopListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_list);

        final ListView lvWorkshopList = (ListView) findViewById(R.id.lvWorkshopList);
        String[] array = {"Workshop1", "Workshop2","Workshop3", "Workshop4","Workshop5", "Workshop6",
                            "Workshop7", "Workshop8","Workshop9", "Workshop10","Workshop11", "Workshop12",
                            "Workshop13", "Workshop14","Workshop15", "Workshop16","Workshop17", "Workshop18"};
        //in order be able to update the list dynamically
        final ArrayList<String> list = new ArrayList<String>(Arrays.asList(array));
        //now update the listview
        //1st argument is the activity instance
        //2nd argument is the layout of the listview, there is inbuild layout in android android.R.layout.simple_list_item_1
        //3rd argument is the arraylist
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        //now set the view
        lvWorkshopList.setAdapter(adapter);
        //to get which element was selected we use OnItemClickListener
        lvWorkshopList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                Intent intent = new Intent(WorkshopListActivity.this, WorkshopActivity1.class);
                intent.putExtra("WorkshopList",lvWorkshopList.getItemAtPosition(position).toString());
                WorkshopListActivity.this.startActivity(intent);
                ;
            }

        });


    }
}
