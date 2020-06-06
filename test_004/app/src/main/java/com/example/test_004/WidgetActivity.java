package com.example.test_004;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

public class WidgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);

        RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton4);
        boolean checked = radioButton.isChecked();
    }
}
