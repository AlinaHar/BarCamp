package com.smartcamp.aua.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RetreiveWorkshopList1 extends AppCompatActivity {
    ImageView ivBack2;
    ListView lvWorkshops;
    TextView tvLogout1;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_workshop_list1);

        lvWorkshops = (ListView) findViewById(R.id.lvWorkshops);
        tvLogout1 = (TextView) findViewById(R.id.tvLogout1);
        firebaseAuth = FirebaseAuth.getInstance();
        tvLogout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(RetreiveWorkshopList1.this, LoginActivity.class));
            }
        });

        ivBack2=(ImageView) findViewById(R.id.ivBack2);
        ivBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RetreiveWorkshopList1.this, WelcomePageActivity.class));
            }
        });

        String[] array = {"Workshop1", "Workshop2","Workshop3", "Workshop4","Workshop5", "Workshop6",
                            "Workshop7", "Workshop8","Workshop9", "Workshop10","Workshop11", "Workshop12",
                            "Workshop13"};
        //in order be able to update the list dynamically
        final ArrayList<String> list = new ArrayList<String>(Arrays.asList(array));
        //now update the listview
        //1st argument is the activity instance
        //2nd argument is the layout of the listview, there is inbuild layout in android android.R.layout.simple_list_item_1
        //3rd argument is the arraylist
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        //now set the view
        lvWorkshops.setAdapter(adapter);
        //to get which element was selected we use OnItemClickListener
      lvWorkshops.setClickable(true);
      lvWorkshops.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              if (position == 0) {
                  Toast.makeText(RetreiveWorkshopList1.this, "item 0 is clicked", Toast.LENGTH_SHORT).show();
                  startActivity(new Intent(RetreiveWorkshopList1.this, RetreiveWorkshopList.class));
              }
          }
      }
    );


    }

    }




