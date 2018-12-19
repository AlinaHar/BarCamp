package com.smartcamp.aua.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AddressActivity extends AppCompatActivity {
    ImageView ivBack6;
    ImageView ivAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        ivAddress=(ImageView) findViewById(R.id.ivAddress);
        ivBack6 = (ImageView) findViewById(R.id.ivBack6);
        ivBack6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this, WelcomePageActivity.class));
            }
        });
    }
}
