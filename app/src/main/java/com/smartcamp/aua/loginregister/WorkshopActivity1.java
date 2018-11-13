package com.smartcamp.aua.loginregister;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

public class WorkshopActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop1);

        final TextView tvWorkshop1Details = (TextView) findViewById(R.id.tvWorkshop1Details);
        final TextView tvWorkshop1Guest = (TextView) findViewById(R.id.tvWorkshop1GUest);
        final TextView tvWorkshop1Topic = (TextView) findViewById(R.id.tvWorkshop1Topic);

       /* getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }*/
    }
}
