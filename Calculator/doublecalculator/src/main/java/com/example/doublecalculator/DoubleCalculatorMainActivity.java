package com.example.doublecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

public class DoubleCalculatorMainActivity extends AppCompatActivity {

    boolean isFirstInput = true;

    ScrollView scrollView;
    TextView resultOperatorTextView;
    TextView resultTextView;

    ImageButton allClearButton;
    ImageButton clearEntryButton;
    ImageButton backSpaceButton;
    ImageButton decimalButton;

    Button[] numberButton = new Button[10];
    Button[] operatorButton = new Button[5];
    // instance variable
    Calculator calculator = new Calculator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_calculator_main);

        scrollView = findViewById(R.id.scroll_view);
        resultOperatorTextView = findViewById(R.id.result_operator_text_view);
        resultTextView = findViewById(R.id.result_text_view);

        allClearButton = findViewById(R.id.all_clear_button);
        clearEntryButton = findViewById(R.id.clear_entry_button);
        backSpaceButton = findViewById(R.id.back_space_button);
        decimalButton = findViewById(R.id.decimal_button);

        // i 를 활용할 때
        for (int i = 0; i < numberButton.length; i++) {
            numberButton[i] = findViewById(R.id.number_button_0 + i);

        }
        for (int i = 0; i < operatorButton.length; i++) {
            operatorButton[i] = findViewById(R.id.operator_button_0 + i);
        }

        for (Button button : numberButton) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberButtonClick(view);
                }
            });
        }
        for (Button button : operatorButton) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    operatorButtonClick(view);
                }
            });
        }

        allClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allClearButtonClick(view);
            }
        });

        clearEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEntryButtonClick(view);
            }
        });

        backSpaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backSpaceButtonClick(view);
            }
        });

        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decimalButtonClick(view);
            }
        });
    }

    private void decimalButtonClick(View view) {
        if (isFirstInput) {
            resultTextView.setTextColor(0xFF000000);
            resultTextView.setText("0.");
            isFirstInput = false;
        } else {
            if (resultTextView.getText().toString().contains(".")) {

            } else {
                resultTextView.append(".");
            }
        }
    }

    private void backSpaceButtonClick(View view) {
        if (resultTextView.getText().toString().length() > 1) {
            String getResultString = resultTextView.getText().toString().replace(",", "");
            String subString = getResultString.substring(0, getResultString.length() - 1);
            String decimalString = calculator.getDecimalString(subString);
            resultTextView.setText(decimalString);
        } else {
            clearText();
        }
    }

    private void clearEntryButtonClick(View view) {
        clearText();
    }

    private void allClearButtonClick(View view) {
        calculator.setAllClear();
        clearText();
    }

    private void clearText() {
        isFirstInput = true;
        resultTextView.setTextColor(0xFF676767);
        // 클래스에 있는 변수를 직접 가져오거나 제어하지 말고 getter and setter 메소드를 만들어서 접근
        resultTextView.setText(calculator.getClearInputText());
    }

    private void operatorButtonClick(View view) {
        String getResultString = resultTextView.getText().toString();
        String operator = view.getTag().toString();
        String getResult = calculator.getResult(isFirstInput, getResultString, operator);
        resultTextView.setText(getResult);
        isFirstInput = true;
    }

    private void numberButtonClick(View view) {
        if (isFirstInput) {
            resultTextView.setTextColor(0xFF000000);
            // tag 는 view 자체에서 받아옴
            resultTextView.setText(view.getTag().toString());
            isFirstInput = false;
        } else {
            // resultTextView 안에 있는 text 를 가져와서 문자로 변환하고 ","을 ""로 바꿈 10,000  ->  10000
            String getResultText = resultTextView.getText().toString().replace(",", "");
            getResultText = getResultText + view.getTag().toString();
            String getDecimalString = calculator.getDecimalString(getResultText);
            resultTextView.setText(getDecimalString);
        }
    }
}
