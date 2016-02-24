package com.example.taras.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    Button btn_ok;
    EditText et_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn_ok = (Button) findViewById(R.id.btn_ok);
        et_field = (EditText) findViewById(R.id.et_as_field);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("field", et_field.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
