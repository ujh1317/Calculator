package com.example.doublecalculator;

import java.text.DecimalFormat;

public class Calculator {

    final String CLEAR_INPUT_TEXT = "0";
    double mResultNumber = 0;
    double mLastInputNumber = 0;
    String operator = "+";

    // 3자리 마다 (,)표시, 소수점 5자리 까지 표시
    DecimalFormat decimalFormat = new DecimalFormat("###,###.#####");

    public String getDecimalString(String changeString) {
        // 문자열의 (,)를 삭제
        String setChangeString = changeString.replace(",", "");
        return decimalFormat.format(Double.parseDouble(setChangeString));
    }

    // method overloding
    public String getDecimalString(double changeNumber) {
        return decimalFormat.format(changeNumber);
    }

    public String getclearInputText() {
        return CLEAR_INPUT_TEXT;
    }

    public void setAllClear() {
        mResultNumber = 0;
        mLastInputNumber = 0;
        operator = "+";
    }

    public double doubleCalculator(double result, double lastNumber, String operator) {
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

    public String getResult(boolean isFirstInput, String getResultString, String lastOperator) {
        mLastInputNumber = Double.parseDouble(getResultString.replace(",", ""));
        mResultNumber = doubleCalculator(mResultNumber, mLastInputNumber, operator);
        if (!lastOperator.equals("=")) {
            operator = lastOperator;
        }
        return getDecimalString(mResultNumber);
    }
}
