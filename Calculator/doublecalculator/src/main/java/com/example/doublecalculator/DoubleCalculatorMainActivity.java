package com.example.doublecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
        // backspacebutton 이 결과값을 삭제하지 못하게
        if (isFirstInput && !calculator.getOperatorString().equals("")) {

        } else {
            if (resultTextView.getText().toString().length() > 1) {
                String getResultString = resultTextView.getText().toString().replace(",", "");
                String subString = getResultString.substring(0, getResultString.length() - 1);
<<<<<<< HEAD

                // 입력값이 소숫점이 있는 실수인 경우 calculator.getDecimalString() 의 DecimalFormat 의 패턴에 맞추어 0이 삭제되는 오류 수정 - 6/05

                // calculator.getDecimalString() 의 리턴값을 받을 String 변수 선언 - 6/05
                String decimalString;
                // 마지막 글자가 삭제된 subString 에 소숫점이 있는지 확인 - 6/05
                if (subString.contains(".")) {
                    // 소숫점이 있으면 소숫점을 기준으로 String 배열에 분리해서 저장 - 6/05
                    String[] subDecimal = subString.split("\\.");
                    // 소숫점을 기준으로 분리한 String 배열의 갯수가 1이면 소숫점 이하에 숫자가 없다고 판단 - 6/05
                    if (subDecimal.length == 1) {
                        // 소숫점 이하의 숫자가 없으므로 소숫점도 같이 삭제 - 6/05
                        subString = subString.replace(".", "");
                    }
                    // calculator.getDecimalString() 패턴을 적용하지 않고 바로 저장 - 6/05
                    decimalString = subString;
                    // 소숫점이 없을 때 calculator.getDecimalString() 패턴 적용 - 6/05
                }else {
                    decimalString = calculator.getDecimalString(subString);
                }
                // backspacebutton 으로 글자수가 줄어들면 텍스트사이즈도 원래대로
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                    resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getStringSize(decimalString));
                }
=======
                String decimalString = calculator.getDecimalString(subString);
>>>>>>> parent of 9d19d63... 6/05 ui 수정
                resultTextView.setText(decimalString);
            } else {
                clearText();
            }
        }
    }

    private void clearEntryButtonClick(View view) {
        clearText();
    }

    private void allClearButtonClick(View view) {
        calculator.setAllClear();
        // all clear 버튼을 눌렀을 때 scrollview 수식 초기화
        resultOperatorTextView.setText(calculator.getOperatorString());
        clearText();
    }

    private void clearText() {
        isFirstInput = true;
        resultTextView.setTextColor(0xFF676767);
        // all clear 했을 때 텍스트 사이즈 복구
        resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
        // 클래스에 있는 변수를 직접 가져오거나 제어하지 말고 getter and setter 메소드를 만들어서 접근
        resultTextView.setText(calculator.getClearInputText());
    }

    private void operatorButtonClick(View view) {
        String getResultString = resultTextView.getText().toString();
        String operator = view.getTag().toString();
        String getResult = calculator.getResult(isFirstInput, getResultString, operator);
        resultTextView.setText(getResult);
        resultOperatorTextView.setText(calculator.getOperatorString());
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
            // 입력된 글자의 수가 16자리 초과일 때 입력 제한
            if (getResultText.length() > 15){
                Toast.makeText(getApplicationContext(), "16자리 까지 입력 가능합니다.", Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
            } else {
                String getDecimalString;
                if (view.getTag().toString().equals("0") && resultTextView.getText().toString().contains(".")) {
                    getDecimalString = resultTextView.getText().toString() + view.getTag().toString();
                } else {
                    // 12,00005 -> 1,200,005
                    getResultText = getResultText + view.getTag().toString();
                    getDecimalString = calculator.getDecimalString(getResultText);
                }
                // 자동 크기 조정이 안되는 SDK ver.26이하 일때
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                    resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getStringSize(getDecimalString));
=======
            }else {
                // 12,00005 -> 1,200,005
                getResultText = getResultText + view.getTag().toString();
                String getDecimalString = calculator.getDecimalString(getResultText);
                // 자동 크기 조정이 안되는 SDK 버전이 26이하 일때 문자열을 전달받아 텍스트 사이즈를 리턴
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
                    if (getDecimalString.length() > 18){
                        resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
                    }else if (getDecimalString.length() > 14){
                        resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
                    }
>>>>>>> parent of 9d19d63... 6/05 ui 수정
                }
                resultTextView.setText(getDecimalString);
            }
        }
    }
}
