package com.example.calculatorx;

import androidx.annotation.NonNull;
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
    private TextView txtOperation;

    private static final int base = 10;
    private Double numOne = 0.0;
    private Double numTwo = 0.0;
    private String oper = null;
    private Double result = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        txtResult = findViewById(R.id.txt_result);
        txtNumberOne = findViewById(R.id.number_one);
        txtNumberTwo = findViewById(R.id.number_two);
        txtOperation = findViewById(R.id.operation);

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
                onNumberPressed(numbers.get(v.getId()), oper, result);
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

        Map<Integer, String> operations = new HashMap<>();
        operations.put(R.id.but_div, "/");
        operations.put(R.id.but_mult, "*");
        operations.put(R.id.but_sub, "-");
        operations.put(R.id.but_sum, "+");

        View.OnClickListener operationClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperationPressed(operations.get(v.getId()));
            }
        };

        findViewById(R.id.but_div).setOnClickListener(operationClickListener);
        findViewById(R.id.but_mult).setOnClickListener(operationClickListener);
        findViewById(R.id.but_sub).setOnClickListener(operationClickListener);
        findViewById(R.id.but_sum).setOnClickListener(operationClickListener);

        Button butEqual = findViewById(R.id.but_eql);
        butEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult(doOperation(numOne, numTwo, oper));
            }
        });

        Button butC = findViewById(R.id.but_c);
        butC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doResrart();
            }
        });
    }

    private void doResrart() {
        numOne = 0.0;
        numTwo = 0.0;
        result = 0.0;
        txtNumberOne.setText(numOne.toString());
        txtNumberTwo.setText(numTwo.toString());
        txtResult.setText(result.toString());
        txtOperation.setText(null);
        oper = null;
    }

    private double doOperation(Double numOne, Double numTwo, String oper) {
        if (numOne == 0 || numTwo == 0 || oper == null) {
            return 0.0;
        } else if (result != 0.0) {
            if (oper == "/") {
                return result = result / numTwo;
            } else if (oper == "*") {
                return result = result * numTwo;
            } else if (oper == "-") {
                return result = result - numTwo;
            } else if (oper == "+") {
                return result = result + numTwo;
            }
        } else if (oper == "/") {
            return result = numOne / numTwo;
        } else if (oper == "*") {
            return result = numOne * numTwo;
        } else if (oper == "-") {
            return result = numOne - numTwo;
        } else if (oper == "+") {
            return result = numOne + numTwo;
        }
        return 0.0;
    }

    public void onOperationPressed(String operation) {
        if (result != 0.0) {
            double tempResult = result;
            doResrart();
            numOne = tempResult;
            showNumOne(numOne);
        }
        oper = operation;
        showOperation(operation);
    }

    public void onNumberPressed(int number, String oper, double result) {
        if (result != 0.0) {
            doResrart();
            numOne = numOne * base + number;
            showNumOne(numOne);
        } else if (oper == null) {
            numOne = numOne * base + number;
            showNumOne(numOne);
        } else if (oper != null) {
            numTwo = numTwo * base + number;
            showNumTwo(numTwo);
        }
    }

    public void showOperation(String operation) {
        txtOperation.setText(operation);
    }

    public void showNumOne(Double numOne) {
        txtNumberOne.setText(numOne.toString());
    }

    public void showNumTwo(Double numTwo) {
        txtNumberTwo.setText(numTwo.toString());
    }

    public void showResult(Double result) {
        txtResult.setText(result.toString());
    }


}