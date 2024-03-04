package com.example.ex4_addsubmuldiv_onclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText txt1,txt2, txtKQ;
    int num1,num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txtKQ = findViewById(R.id.txtKQ);
    }
    private void readText(){
        num1 = Integer.parseInt(txt1.getText().toString());
        num2 = Integer.parseInt(txt2.getText().toString());
    }
    public void handleCong(View view){
        readText();
        txtKQ.setText(String.valueOf(num1 + num2));
    }
    public void handleTru(View view){
        readText();
        txtKQ.setText(String.valueOf(num1 - num2));
    }
    public void handleNhan(View view){
        readText();
        txtKQ.setText(String.valueOf(num1 * num2));
    }
    public void handleChia(View view){
        readText();
        if(num2 != 0) txtKQ.setText(String.valueOf(num1 / num2));
        else txtKQ.setText("Không thể chia một số cho 0");
    }
}