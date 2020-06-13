package com.example.doublecalculator;

import java.text.DecimalFormat;

public class Calculator {

    private final String CLEAR_INPUT_TEXT = "0";
    private double resultNumber = 0;
    private double lastInputNumber = 0;
    private String operator = "+";
    private String operatorString = "";

    // 천단위 마다 (,)표시, 소수점 5자리 까지 표시
    DecimalFormat decimalFormat;

    public Calculator() {
        new DecimalFormat("###,###.#####");
    }

    public Calculator(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    // method overloading
    public String getDecimalString(String changeString) {
        // 문자열의 (,)를 삭제
        String setChangeString = changeString.replace(",", "");
        return decimalFormat.format(Double.parseDouble(setChangeString));
    }

    // method overloading
    public String getDecimalString(double changeNumber) {
        return decimalFormat.format(changeNumber);
    }

    public String getOperatorString() {
        return operatorString;
    }

    public String getClearInputText() {
        return CLEAR_INPUT_TEXT;
    }

    public void setAllClear() {
        resultNumber = 0;
        lastInputNumber = 0;
        operator = "+";
        // all clear 버튼을 눌렀을 때 scrollview 수식 초기화
        operatorString = "";
    }

    private double doubleCalculator(double result, double lastNumber, String operator) {
        switch (operator) {
            case "+":
                result += lastNumber;
                break;
            case "-":
                result -= lastNumber;
                break;
            case "*":
                result *= lastNumber;
                break;
            case "/":
                result /= lastNumber;
                break;
        }
        return result;
    }

    // 연산자 버튼이 연속으로 클릭된 때
    public String getResult(boolean isFirstInput, String getResultString, String lastOperator) {
        if (isFirstInput) {
            if (lastOperator.equals("=")) {
                resultNumber = doubleCalculator(resultNumber, lastInputNumber, operator);
                operatorString = "";
            } else {
                operator = lastOperator;
                // 공백 상태에서 연산자 버튼을 눌렀을 때
                if (operatorString.equals("")) {
                    operatorString = getResultString + " " + lastOperator;
                } else {
                    // 연사자를 잘못 눌렀을 때
                    operatorString = operatorString.substring(0, operatorString.length() - 1);
                    operatorString = operatorString + lastOperator;
                }
            }

        } else {
            lastInputNumber = Double.parseDouble(getResultString.replace(",", ""));
            resultNumber = doubleCalculator(resultNumber, lastInputNumber, operator);
            if (lastOperator.equals("=")) {
                operatorString = "";
            } else {
                operatorString = operatorString + " " + getResultString + " " + lastOperator;
                operator = lastOperator;

            }
        }
        return getDecimalString(resultNumber);
    }
}
