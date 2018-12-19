package com.smartcamp.aua.loginregister;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class RetreiveWorkshopList extends AppCompatActivity {

     ListView lvWorkshopList;
     ImageView ivBack1;
     DatabaseReference dbRef;//firebase reference
     ArrayList<String> arrayList;
     ArrayAdapter<String> adapter;
    TextView tvLogout2;
    FirebaseAuth firebaseAuth;
    Button switch1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retreive_workshop_list);

        tvLogout2 = (TextView) findViewById(R.id.tvLogout2);
        firebaseAuth = FirebaseAuth.getInstance();
        tvLogout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(RetreiveWorkshopList.this, LoginActivity.class));
            }
        });
        //associate the workshops' list with thwe corresponding ListView
        lvWorkshopList = (ListView) findViewById(R.id.lvWorkshopList);
        ivBack1 = (ImageView) findViewById(R.id.ivBack1);

        ivBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RetreiveWorkshopList.this, WelcomePageActivity.class));
            }
        });

        //define a String arraylist for the workshops
        arrayList = new ArrayList<>();
        //Associate the WorkshopList Firebase Database Reference with the database's WorkshopList object
        dbRef = FirebaseDatabase.getInstance().getReference().child("WorkshopList");

        lvWorkshopList.setClickable(true);//make the items clickable

        lvWorkshopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(RetreiveWorkshopList.this, "item 0 is clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RetreiveWorkshopList.this, TimePickActivity.class);
                    RetreiveWorkshopList.this.startActivity(intent);
                }
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Attach a ChildEventListener to the WorkshopList database, so we can retrieve its' entries
       dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //map the snapshot to the WorkshopList class and add to an adapter.
                WorkshopList workshops = (WorkshopList) dataSnapshot.getValue(WorkshopList.class);
                String workshopsString = String.valueOf(workshops);
                arrayList.add(workshopsString);
                adapter = new ArrayAdapter<String>(RetreiveWorkshopList.this, android.R.layout.simple_list_item_1, arrayList);
                lvWorkshopList.setAdapter(adapter);



               /*//What was working before

                String workshopsString=dataSnapshot.getValue(String.class);
                arrayList.add(workshopsString);
                //set the arrayAdapter to the listview
                adapter = new ArrayAdapter<String>(RetreiveWorkshopList.this, android.R.layout.simple_list_item_1, arrayList);
                lvWorkshopList.setAdapter(adapter);
                //adapter.add(workshopsString);
               // adapter.notifyDataSetChanged();*/

            }

            @Override
            public void onChildChanged( DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved( DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved( DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    }

