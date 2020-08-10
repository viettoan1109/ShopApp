package com.actvn.shopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.actvn.shopapp.activity.RegisterActivity;

public class WellcomeActivity extends AppCompatActivity {

    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        innit();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WellcomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void innit() {
        btnlogin = (Button) findViewById(R.id.btn_login);

    }

}