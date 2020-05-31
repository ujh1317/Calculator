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

        Button getButton = findViewById(view.getId());

        Log.e("buttonClick", "buttonClick 시작 : " + getButton.getText().toString() + " 버튼이클릭되었습니다.");
        Log.d("buttonClick", "resultNumber = " + resultNumber);

        switch (view.getId()) {
            case R.id.all_clear_button:
                isFirstInput = true;
                resultNumber = 0;
                operator = '+';
                resultText.setTextColor(0xFFB8B7B7);
                resultText.setText(CLEAR_INPUT_TEXT);
                break;

            case R.id.clear_entry_button:
                isFirstInput = true;
                resultText.setText(CLEAR_INPUT_TEXT);
                break;

            case R.id.back_space_button:
                if (resultText.getText().toString().length() > 1) {
                    String getResultText = resultText.getText().toString();
                    String subString = getResultText.substring(0, getResultText.length() -1);
                    resultText.setText(subString);
                }else {
                    resultText.setTextColor(0xFFB8B7B7);
                    resultText.setText(CLEAR_INPUT_TEXT);
                    isFirstInput = true;
                }
                break;

            case R.id.addition_button:
            case R.id.subtraction_button:
            case R.id.division_button:
            case R.id.multiply_button:

                int lastNum = Integer.parseInt(resultText.getText().toString());
                if (operator == '+') {
                    resultNumber = resultNumber + lastNum;
                } else if (operator == '-') {
                    resultNumber = resultNumber - lastNum;
                } else if (operator == '÷') {
                    resultNumber = resultNumber / lastNum;
                } else if (operator == '×') {
                    resultNumber = resultNumber * lastNum;
                }
                operator = getButton.getText().toString().charAt(0);
                resultText.setText(resultNumber + "");
                isFirstInput = true;
                Log.d("buttonClick", "add resultNumber = " + resultNumber);
                break;

            case R.id.result_button:
                if (operator == '+') {
                    resultNumber = resultNumber + Integer.parseInt(resultText.getText().toString());
                } else if (operator == '-') {
                    resultNumber = resultNumber - Integer.parseInt(resultText.getText().toString());
                } else if (operator == '÷') {
                    resultNumber = resultNumber / Integer.parseInt(resultText.getText().toString());
                } else if (operator == '×') {
                    resultNumber = resultNumber * Integer.parseInt(resultText.getText().toString());
                }
                resultText.setText(resultNumber + "");
                isFirstInput = true;
                break;

            case R.id.number_zero_button:
            case R.id.number_one_button:
            case R.id.number_two_button:
            case R.id.number_three_button:
            case R.id.number_four_button:
            case R.id.number_five_button:
            case R.id.number_six_button:
            case R.id.number_seven_button:
            case R.id.number_eight_button:
            case R.id.number_nine_button:

                if (isFirstInput) {
                    resultText.setTextColor(0xFF000000);
                    resultText.setText(getButton.getText().toString());
                    isFirstInput = false;
                } else {
                    resultText.append(getButton.getText().toString());
                }
                break;

            default:
//                Toast.makeText(getApplicationContext(), getButton.getText().toString() + " 버튼이클릭되었습니다.", Toast.LENGTH_LONG).show();
                Log.e("buttonClick", "default : " + getButton.getText().toString() + " 버튼이클릭되었습니다.");
                break;
        }
    }
}