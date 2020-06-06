package com.example.test_004;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CallIntentActivity extends AppCompatActivity {

    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_intent);

        editText2 = (EditText) findViewById(R.id.editText2);

        Button button = (Button) findViewById(R.id.button20);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String receiver = editText2.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+receiver));
                startActivity(intent);

                /*//다른방식  menuactivity(객체)를 지정하는게 아니라 문자열로 activity를 지정할 수 있는 방법
                Intent intent = new Intent();
                ComponentName name = new ComponentName("com.example.test_004","com.example.test_004.MenuActivity");
                intent.setComponent(name);
                startActivity(intent);
                 */

            }
        });
    }
}
