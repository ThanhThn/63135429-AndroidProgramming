package com.example.ex5_addsubmuldiv_var;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txt1, txt2, txtKQ;
    Button btnCong, btnTru, btnNhan, btnChia;
    int num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txtKQ = findViewById(R.id.txtKQ);
        btnNhan = findViewById(R.id.btnNhan);
        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnChia = findViewById(R.id.btnChia);
        btnCong.setOnClickListener(xulyCong);
        btnTru.setOnClickListener(xulyTru);
        btnNhan.setOnClickListener(xulyNhan);
        btnChia.setOnClickListener(xulyChia);
    }
    View.OnClickListener xulyCong = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleCong(v);
        }
    };
    View.OnClickListener xulyTru = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleTru(v);
        }
    };
    View.OnClickListener xulyNhan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleNhan(v);
        }
    };
    View.OnClickListener xulyChia = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleChia(v);
        }
    };
    private void readText() {
        num1 = Integer.parseInt(txt1.getText().toString());
        num2 = Integer.parseInt(txt2.getText().toString());
    }

    public void handleCong(View view) {
        readText();
        txtKQ.setText(String.valueOf(num1 + num2));
    }

    public void handleTru(View view) {
        readText();
        txtKQ.setText(String.valueOf(num1 - num2));
    }

    public void handleNhan(View view) {
        readText();
        txtKQ.setText(String.valueOf(num1 * num2));
    }

    public void handleChia(View view) {
        readText();
        if (num2 != 0) txtKQ.setText(String.valueOf(num1 / num2));
        else txtKQ.setText("Không thể chia một số cho 0");
    }
}