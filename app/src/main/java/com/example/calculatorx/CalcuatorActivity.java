package com.example.calculatorx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class CalcuatorActivity extends AppCompatActivity {

    private TextView txtResult;
    private TextView txtNumberOne;
    private TextView txtNumberTwo;

    private static final int base = 10;
    private Double numOne = 0.0;
    private Double numTwo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        txtResult = findViewById(R.id.txt_result);
        txtNumberOne = findViewById(R.id.number_one);
        txtNumberTwo = findViewById(R.id.number_two);

        Map<Integer, Integer> numbers = new HashMap<>();
        numbers.put(R.id.but_0, 0);
        numbers.put(R.id.but_1, 1);
        numbers.put(R.id.but_2, 2);
        numbers.put(R.id.but_3, 3);
        numbers.put(R.id.but_4, 4);
        numbers.put(R.id.but_5, 5);
        numbers.put(R.id.but_6, 6);
        numbers.put(R.id.but_7, 7);
        numbers.put(R.id.but_8, 8);
        numbers.put(R.id.but_9, 9);

        View.OnClickListener numbersClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberPressed(numbers.get(v.getId()));
            }
        };

        findViewById(R.id.but_0).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_1).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_2).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_3).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_4).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_5).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_6).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_7).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_8).setOnClickListener(numbersClickListener);
        findViewById(R.id.but_9).setOnClickListener(numbersClickListener);


        Button butC = findViewById(R.id.but_c);
        butC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double numZero = 0.0;
                numOne = 0.0;
                txtNumberOne.setText(numOne.toString());
//                txtNumberTwo.setText(null);
//                txtNumberTwo = null;
//                txtResult = null;
            }

        });
    }

        public void onNumberPressed ( int number){
            if (numTwo == null) {
                numOne = numOne * base + number;
                showNumOne(numOne);
            } else {
                numTwo = numTwo * base + number;
                showNumTwo(numTwo);
            }
        }


        public void showNumOne (Double numOne){
            txtNumberOne.setText(numOne.toString());
        }

        public void showNumTwo (Double numTwo){
            txtNumberTwo.setText(numTwo.toString());
        }
    }