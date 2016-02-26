package com.example.taras.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv_firstField, tv_secondField, tv_result;
    EditText et_action;
    Button btn_calculate;
    Switch swtch_theme;
    int start_as_tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Values.counter % 2 == 0) {
            setTheme(R.style.l_my_style1);
        } else {
            setTheme(R.style.l_my_style2);
        }

        setContentView(R.layout.activity_main);

        tv_firstField = (TextView) findViewById(R.id.tv_first_field);
        tv_secondField = (TextView) findViewById(R.id.tv_second_field);
        tv_result = (TextView) findViewById(R.id.tv_result);
        et_action = (EditText) findViewById(R.id.et_action);
        btn_calculate = (Button) findViewById(R.id.btn_calculate);
        swtch_theme = (Switch) findViewById(R.id.swtch_theme);

        tv_firstField.setText(Values.firstField);
        tv_secondField.setText(Values.secondField);
        tv_result.setText(Values.resultField);

        if (Values.counter % 2 == 1) {
            swtch_theme.setChecked(true);
        }

        tv_firstField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_as_tag = 1;
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        tv_secondField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_as_tag = 2;
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        swtch_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Values.counter++;
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a, b, result;
                char action = '#';

                if (tv_firstField.getText().length() == 0 || tv_secondField.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, R.string.empty_field_message, Toast.LENGTH_LONG).show();
                    return;
                }

                boolean correct_action_field = true;

                if (et_action.getText().length() == 0) {
                    correct_action_field = false;
                } else {
                    action = et_action.getText().toString().charAt(0);
                }

                if (action != '+' && action != '-' && action != '/' && action != '*') {
                    correct_action_field = false;
                }

                if (!correct_action_field) {
                    Toast.makeText(MainActivity.this, R.string.incorrect_action_field_message, Toast.LENGTH_LONG).show();
                    return;
                }

                a = Integer.parseInt(tv_firstField.getText().toString());
                b = Integer.parseInt(tv_secondField.getText().toString());

                if (action == '/' && b == 0) {
                    Toast.makeText(MainActivity.this, R.string.dividing_on_zero_message, Toast.LENGTH_LONG).show();
                    return;
                }

                result = action == '+' ? a + b : action == '-' ? a - b : action == '*' ? a * b : a / b;
                Values.resultField = Integer.toString(result);
                tv_result.setText(Values.resultField);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String s = data.getStringExtra("field");

        if (start_as_tag == 1) {
            Values.firstField = s;
            tv_firstField.setText(s);
        } else {
            Values.secondField = s;
            tv_secondField.setText(s);
        }
    }
}
