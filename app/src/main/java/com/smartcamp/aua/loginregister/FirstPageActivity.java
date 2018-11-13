package com.smartcamp.aua.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        ImageView ivLogo = (ImageView) findViewById(R.id.ivLogo);
        TextView tvFirstWelcomeMsg = (TextView) findViewById(R.id.tvFirstWelcome);
        Button bContinue = (Button) findViewById(R.id.bContinue);

        bContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(FirstPageActivity.this, LoginActivity.class);
                FirstPageActivity.this.startActivity(continueIntent);
            }
        });

    }
}
