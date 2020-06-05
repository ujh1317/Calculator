package com.example.doublecalculator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class OperatorButton extends AppCompatButton {

    int operatorButtonDefault = R.drawable.operator_button_default;
    int operatorButtonClick = R.drawable.operator_button_click;

    public OperatorButton(Context context) {
        super(context);
        setBackgroundResource(operatorButtonDefault);
    }

    public OperatorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundResource(operatorButtonDefault);
    }

    public OperatorButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundResource(operatorButtonDefault);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(operatorButtonClick);
                setPadding(10,10,0,0);
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundResource(operatorButtonDefault);
                setPadding(0,0,0,0);
                break;
        }
        return true;
    }
}
