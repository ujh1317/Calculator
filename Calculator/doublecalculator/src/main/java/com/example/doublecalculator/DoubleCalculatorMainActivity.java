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
    Button decimalButton;

    Button[] numberButton = new Button[10];
    ImageButton[] operatorButton = new ImageButton[5];
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
        for (ImageButton imageButton : operatorButton) {
            imageButton.setOnClickListener(new View.OnClickListener() {
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
            String sudString = getResultString.substring(0, getResultString.length() - 1);
            String decimalString = calculator.getDecimalString(sudString);
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
        resultTextView.setText(calculator.getclearInputText());
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
            resultTextView.setText(view.getTag().toString());
            isFirstInput = false;
        } else {
            // 10,000  ->  10000
            String getResultText = resultTextView.getText().toString().replace(",", "");
            getResultText = getResultText + view.getTag().toString();
            String getDecimalString = calculator.getDecimalString(getResultText);
            resultTextView.setText(getDecimalString);
        }
    }
}
