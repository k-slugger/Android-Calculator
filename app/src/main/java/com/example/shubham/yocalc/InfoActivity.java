package com.example.shubham.yocalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent thisIntent = getIntent();
        String finalAns = thisIntent.getStringExtra("finalAns");
        TextView tv = (TextView) findViewById(R.id.finalAnsText);
        tv.setText("Final answer: " + finalAns);
    }
}
