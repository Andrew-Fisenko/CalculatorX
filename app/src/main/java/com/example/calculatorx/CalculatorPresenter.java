package com.example.calculatorx;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CalculatorPresenter {

    private static final int base = 10;
    private Double numOne;
    private Double numTwo;
    private String oper;
    private Double result;

    public CalculatorPresenter() {
        this.numOne = 0.0;
        this.numTwo = 0.0;
        this.oper = null;
        this.result = 0.0;
    }

    public Double getResult() {
        return result;
    }

    public String getOper() {
        return oper;
    }

    public Double getNumOne() {
        return numOne;
    }

    public Double getNumTwo() {
        return numTwo;
    }

    public void setNumOne(Double numOne) {
        this.numOne = numOne;
    }

    public void setNumTwo(Double numTwo) {
        this.numTwo = numTwo;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public void doResrart(CalcuatorActivity calculatorView) {
        numOne = 0.0;
        numTwo = 0.0;
        result = 0.0;
        calculatorView.showNumOne(numOne);
        calculatorView.showNumTwo(numTwo);
        calculatorView.showResult(result);
        calculatorView.showOperation(null);
        oper = null;
    }

    public double doOperation(Double numOne, Double numTwo, String oper) {
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

    public void onOperationPressed(String operation, CalcuatorActivity calculatorView) {
        if (result != 0.0) {
            double tempResult = result;
            doResrart(calculatorView);
            numOne = tempResult;
            calculatorView.showNumOne(numOne);
        }
        oper = operation;
        calculatorView.showOperation(operation);
    }

    public double onNumberPressed(int number, String oper, double result, CalcuatorActivity calculatorView) {
        if (result != 0.0) {
            doResrart(calculatorView);
            numOne = numOne * base + number;
            return numOne;
        } else if (oper == null) {
            numOne = numOne * base + number;
            return numOne;
        } else if (oper != null) {
            numTwo = numTwo * base + number;
            return numTwo;
        }
        return 0.0;
    }


}
