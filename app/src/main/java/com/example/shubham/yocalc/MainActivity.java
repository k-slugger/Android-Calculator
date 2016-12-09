package com.example.shubham.yocalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.one)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.two)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.three)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.four)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.five)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.six)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.seven)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.eight)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.nine)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.zero)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.add)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.sub)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.mul)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.div)).setOnClickListener(btnHandler);
        ((Button) findViewById(R.id.ans)).setOnClickListener(ansBtnHandler);
        ((Button) findViewById(R.id.clr)).setOnClickListener(clrBtnHandler);
        ((Button) findViewById(R.id.done)).setOnClickListener(doneBtnHandler);
    }

    View.OnClickListener btnHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView numCon = (TextView) findViewById(R.id.numContainer);
            String temp1 = numCon.getText().toString();
            Button b = (Button)v;
            if(temp1 == "0")
                temp1 = "";
            temp1 = temp1 + "" + b.getText().toString();
            numCon.setText(temp1);
        }
    };

    View.OnClickListener ansBtnHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView numCon = (TextView) findViewById(R.id.numContainer);
            String temp1 = numCon.getText().toString();
            int answer=0;
            try {
                if (temp1.contains("+")) {
                    String num[] = temp1.split("\\+");
                    answer = Integer.parseInt(num[0]) + Integer.parseInt(num[1]);
                }
                else if (temp1.contains("−")) {
                    String num[] = temp1.split("\\−");
                    answer = Integer.parseInt(num[0]) - Integer.parseInt(num[1]);
                }
                else if (temp1.contains("×")) {
                    String num[] = temp1.split("\\×");
                    answer = Integer.parseInt(num[0]) * Integer.parseInt(num[1]);
                }
                else if (temp1.contains("÷")) {
                    String num[] = temp1.split("\\÷");
                    answer = Integer.parseInt(num[0]) / Integer.parseInt(num[1]);
                }
                numCon.setText(String.valueOf(answer));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                //Log.e("YoCalc","Exception: " + e.getClass().getName());;
            }
        }
    };
    View.OnClickListener clrBtnHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView numCon = (TextView) findViewById(R.id.numContainer);
            numCon.setText("0");
        }
    };

    View.OnClickListener doneBtnHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView numCon = (TextView) findViewById(R.id.numContainer);
            String temp1 = numCon.getText().toString();
            Intent infoAct = new Intent(getBaseContext(), InfoActivity.class);
            infoAct.putExtra("finalAns",temp1);
            startActivity(infoAct);
        }
    };
}
