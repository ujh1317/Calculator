package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true;  // 입력 값이 처음 입력되는가를 체크
    int resultNumber = 0; // 계산된 결과 값을 저장하는 변수
    char operator = '+'; // 입력된 연산자를 저장하는 변수

    final String CLEAR_INPUT_TEXT = "0";

    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = findViewById(R.id.result_text);
    }

    public void buttonClick(View view) {

        switch (view.getId()) {
            case R.id.all_clear_button:
                resultNumber = 0;
                operator = '+';
                setClearText(CLEAR_INPUT_TEXT);
                break;

            case R.id.clear_entry_button:
                setClearText(CLEAR_INPUT_TEXT);
                break;

            case R.id.back_space_button:
                if (resultText.getText().toString().length() > 1) {
                    String getResultText = resultText.getText().toString();
                    String subString = getResultText.substring(0, getResultText.length() - 1);
                    resultText.setText(subString);
                } else {
                    setClearText(CLEAR_INPUT_TEXT);
                }
                break;

            case R.id.decimal_button:
                Log.e("buttonClick", "decimal_button 버튼이클릭되었습니다.");
                break;
        }
    }

    public void numButtonClick(View view) {
        Button getButton = findViewById(view.getId());
        if (isFirstInput) {
            resultText.setTextColor(0xFF000000);
            resultText.setText(getButton.getText().toString());
            isFirstInput = false;
        } else {
            resultText.append(getButton.getText().toString());
        }

    }

    public void setClearText(String clearText){
        isFirstInput = true;
        resultText.setTextColor(0xFFB8B7B7);
        resultText.setText(clearText);
    }

    public int intcal(int result, int lastNum, char operator){
        if (operator == '+') {
            result += lastNum;
        } else if (operator == '-') {
            result -= lastNum;
        } else if (operator == '÷') {
            result /= lastNum;
        } else if (operator == '×') {
            result *= lastNum;
        }
        return result;
    }

    public void operatorClick(View view){
        Button getButton = findViewById(view.getId());

        if (view.getId() == R.id.result_button){
            resultNumber = intcal(resultNumber, Integer.parseInt(resultText.getText().toString()), operator);
            resultText.setText(resultNumber + "");
            isFirstInput = true;
        }else {
            int lastNum = Integer.parseInt(resultText.getText().toString());
            resultNumber = intcal(resultNumber, lastNum, operator);
            operator = getButton.getText().toString().charAt(0);
            resultText.setText(resultNumber + "");
            isFirstInput = true;
        }

    }
}