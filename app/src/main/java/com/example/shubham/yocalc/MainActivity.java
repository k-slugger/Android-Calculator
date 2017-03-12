package com.example.shubham.yocalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup group = (ViewGroup)findViewById(R.id.calcBtns);
        ArrayList<View> childrenViews = new ArrayList<>();
        childrenViews = getAllChildren(group);
        for(View v: childrenViews) {
            if(v instanceof Button) v.setOnClickListener(btnHandler);
        }
    }

    private ArrayList<View> getAllChildren(View v) {
        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            return viewArrayList;
        }
        ArrayList<View> result = new ArrayList<>();
        ViewGroup viewGroup = (ViewGroup) v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.addAll(getAllChildren(child));
            result.addAll(viewArrayList);
        }
        return result;
    }

    View.OnClickListener btnHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button)v;
            TextView numberContainer = (TextView) findViewById(R.id.numContainer);
            if (b.getId() == R.id.clr) {
                numberContainer.setText("0");
                return;
            }
            else if (b.getId() == R.id.ans) {
                String expression = numberContainer.getText().toString();
                String answer = evaluate(expression);
                numberContainer.setText(answer);
                return;
            }
            else {
                String token = numberContainer.getText().toString();
                if(token == "0")
                    token = "";
                token = token + "" + b.getText().toString();
                numberContainer.setText(token);
            }
        }
    };

    public String evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();

        Stack<Double> values = new Stack<>();

        Stack<Character> operators = new Stack<Character>();

        try {
            for (int i = 0; i < tokens.length; i++) {
                if ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.') {
                    StringBuffer sbuf = new StringBuffer();
                    while (i < tokens.length && (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.'))
                        sbuf.append(tokens[i++]);
                    i--;
                    values.push(Double.parseDouble(sbuf.toString()));
                } else if (tokens[i] == '(') {
                    operators.push(tokens[i]);
                } else if (tokens[i] == ')') {
                    while (operators.peek() != '(') {
                        values.push(applyOp(operators.pop(), values.pop(), values.pop()));
                    }
                    operators.pop();
                } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {

                    while (!operators.empty() && hasPrecedence(tokens[i], operators.peek())) {
                        values.push(applyOp(operators.pop(), values.pop(), values.pop()));
                    }
                    operators.push(tokens[i]);
                }
            }

            while (!operators.empty()) {
                values.push(applyOp(operators.pop(), values.pop(), values.pop()));
            }

            return values.pop().toString();
        } catch (EmptyStackException e) {
            return "ERROR";
        } catch (UnsupportedOperationException e) {
            return "ERROR";
        }
    }

    public boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    public double applyOp(char op, double b, double a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}
