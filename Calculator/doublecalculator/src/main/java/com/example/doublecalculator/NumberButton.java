package com.example.doublecalculator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class NumberButton extends AppCompatButton {

    int numberButtonDefault = R.drawable.number_button_default;
    int numberButtonClick = R.drawable.number_button_click;

    public NumberButton(Context context) {
        super(context);
        setBackgroundResource(numberButtonDefault);
    }

    public NumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundResource(numberButtonDefault);
    }

    public NumberButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundResource(numberButtonDefault);
    }

    // button 을 눌렀을 때/땠을 때 이미지 변환
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            // button 을 눌렀을 때 이미지
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(numberButtonClick);
                // button 을 눌렀을 때 text 위치 변화
                setPadding(10,10,0,0);
                break;
            // button 에서 손을 땠을 때 이미지
            case MotionEvent.ACTION_UP:
                setBackgroundResource(numberButtonDefault);
                // button 에서 손을 땠을 때 text 위치 복구
                setPadding(0,0,0,0);
                break;

        }
        return true;
    }
}
