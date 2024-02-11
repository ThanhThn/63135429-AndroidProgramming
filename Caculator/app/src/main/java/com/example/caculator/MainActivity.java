package com.example.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements OnClickListener {
    Button plus, minus, division, equal, num1, num2, num3, num4, num5, num6, num7, num8, num9, num0, clear, delete;

    int idPlus, idMinus, idDivision, idMulitiply, idEqual,
    idNum1, idNum2, idNum3, idNum4, idNum5, idNum6, idNum7, idNum8, idNum9, idNum0,
    idClear, idDelete, idDecimal;
    ImageButton multiply, decimal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idPlus = R.id.plus;
        plus = findViewById(idPlus);
        idMinus = R.id.minus;
        minus = findViewById(idMinus);
        idDivision = R.id.division;
        division =  findViewById(idDivision);
        idMulitiply = R.id.multiply;
        multiply = findViewById(idMulitiply);
        idEqual = R.id.equal;
        equal = findViewById(idEqual);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);
        num0 = findViewById(R.id.num0);
        clear = findViewById(R.id.clear);
        delete = findViewById(R.id.delete);
        decimal = findViewById(R.id.decimal);

        //Set Click
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        division.setOnClickListener(this);
        multiply.setOnClickListener(this);
        equal.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        num0.setOnClickListener(this);
        clear.setOnClickListener(this);
        delete.setOnClickListener(this);
        decimal.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == idPlus) {
            Toast.makeText(this, "Đây là dấu cộng", Toast.LENGTH_LONG).show();
        } else if (viewId == idMinus) {
            Toast.makeText(this, "Đây là dấu trừ", Toast.LENGTH_LONG).show();
        } else if (viewId == idDivision) {
            Toast.makeText(this, "Đây là dấu chia", Toast.LENGTH_LONG).show();
        } else if (viewId == idMulitiply) {
            Toast.makeText(this, "Đây là dấu nhân", Toast.LENGTH_LONG).show();
        }
    }
}