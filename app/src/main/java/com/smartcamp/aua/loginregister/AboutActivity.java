package com.smartcamp.aua.loginregister;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AboutActivity extends AppCompatActivity {

   ImageView ivAbout ;
   TextView tvAbout1;
   ImageView ivBack4 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ivAbout = (ImageView) findViewById(R.id.ivAbout);
        tvAbout1 = (TextView) findViewById(R.id.tvAbout1);
        ivBack4 = (ImageView) findViewById(R.id.ivBack4);
        ivBack4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutActivity.this, WelcomePageActivity.class));
            }
        });



    }
}
