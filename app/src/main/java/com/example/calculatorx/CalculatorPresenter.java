package com.example.calculatorx;

import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CalculatorPresenter {

    private static final int base = 10;
    private Double numOne;
    private Double numTwo;
    private String oper;
    private Double result;
    private boolean onDotPressed;
    private int divider;

    public CalculatorPresenter() {
        this.numOne = 0.0;
        this.numTwo = 0.0;
        this.oper = null;
        this.result = 0.0;
        this.onDotPressed = false;
        this.divider = 10;
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
        divider = 10;
        onDotPressed = false;
        calculatorView.showNumOne(numOne);
        calculatorView.showNumTwo(numTwo);
        calculatorView.showResult(result);
        calculatorView.showOperation(null);
        oper = null;
    }

    public double doOperation(Double numOne, Double numTwo, String oper) {
        if (numTwo == 0 || oper == null) {
            return 0.0;
        } else if (result != 0.0) {
            if (oper == "/") {
                result = result / numTwo;
                toDecimalFormat(result);
            } else if (oper == "*") {
                result = result * numTwo;
                return toDecimalFormat(result);
            } else if (oper == "-") {
                result = result - numTwo;
                return toDecimalFormat(result);
            } else if (oper == "+") {
                result = result + numTwo;
                return toDecimalFormat(result);
            }
        } else if (oper == "/") {
            result = numOne / numTwo;
            return toDecimalFormat(result);
        } else if (oper == "*") {
            result = numOne * numTwo;
            return toDecimalFormat(result);
        } else if (oper == "-") {
            result = numOne - numTwo;
            return toDecimalFormat(result);
        } else if (oper == "+") {
            result = numOne + numTwo;
            return toDecimalFormat(result);
        }
        return 0.0;
    }

    public void onOperationPressed(String operation, CalcuatorActivity calculatorView) {
        if (result != 0.0) {
            double tempResult = result;
            doResrart(calculatorView);

            numOne = toDecimalFormat(tempResult);
            calculatorView.showNumOne(numOne);
        }
        oper = operation;
        onDotPressed = false;
        divider = 10;
        calculatorView.showOperation(operation);
    }

    public double onNumberPressed(int number, String oper, double result, CalcuatorActivity calculatorView) {
        if (result != 0.0) {
            doResrart(calculatorView);
            numOne = numOne * base + number;
            return numOne;

        } else if (oper == null) {
            if (onDotPressed == false) {
                numOne = numOne * base + number;
                return numOne;
            } else {
                numOne = numOne + number / (double) divider;
                divider *= base;
                numOne = toDecimalFormat(numOne);
            }
            return numOne;

        } else if (oper != null) {
            if (onDotPressed == false) {
                numTwo = numTwo * base + number;
            } else {
                numTwo = numTwo + number / (double) divider;
                divider *= base;
                numTwo = toDecimalFormat(numTwo);
            }
            return numTwo;
        }
        return 0.0;
    }

    public void onDotPressed(CalcuatorActivity calculatorView) {
        onDotPressed = true;
    }

    public double toDecimalFormat(double result) {
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String tempRes = df.format(result);
        result = Double.parseDouble(tempRes);
        return result;
    }
}
