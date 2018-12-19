package com.smartcamp.aua.loginregister;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WelcomePageActivity extends AppCompatActivity {
    ImageView ivWelcome;
    TextView tvAbout;
    TextView tvTimetable;
    TextView tvLogout;
    TextView tvAddress;
    FirebaseAuth firebaseAuth;
    DatabaseReference dbRef;//firebase reference
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        tvAddress = (TextView) findViewById(R.id.tvAddress);
        firebaseAuth = FirebaseAuth.getInstance();
        tvAbout = (TextView) findViewById(R.id.tvAbout);
        ivWelcome = (ImageView) findViewById(R.id.ivWelcome);
        tvTimetable = (TextView) findViewById(R.id.tvTimetable);
        tvLogout = (TextView) findViewById(R.id.tvLogout);

        tvAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomePageActivity.this, AboutActivity.class));
            }
        });


        tvTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomePageActivity.this, RetreiveWorkshopList1.class));
            }
        });
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(WelcomePageActivity.this, LoginActivity.class));
            }
        });
        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomePageActivity.this, AddressActivity.class));
            }
        });


    }
}
