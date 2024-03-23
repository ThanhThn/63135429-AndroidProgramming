package ntu.MSSV63135429.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

import ntu.MSSV63135429.caculator.GroupEvalute.GroupEvaluteString;


public class MainActivity extends AppCompatActivity  implements OnClickListener {
    boolean check =true;
    DecimalFormat decimalFormat = new DecimalFormat("#.######");
    Button plus, minus, division, equal, num1, num2, num3, num4, num5, num6, num7, num8, num9, num0, clear, delete, group, sin, cos, deg;
    int idPlus, idMinus, idDivision, idMultiply, idEqual,
            idNum1, idNum2, idNum3, idNum4, idNum5, idNum6, idNum7, idNum8, idNum9, idNum0,
            idClear, idDelete, idDecimal, idGroup, idSin, idCos, idDeg;
    ImageButton multiply, decimal;
    TextView inputMath, outputMath;
    String input = "", output = "";
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
        idMultiply = R.id.multiply;
        multiply = findViewById(idMultiply);
        idEqual = R.id.equal;
        equal = findViewById(idEqual);
        idNum1 = R.id.num1;
        num1 = findViewById(idNum1);
        idNum2 = R.id.num2;
        num2 = findViewById(idNum2);
        idNum3 = R.id.num3;
        num3 = findViewById(idNum3);
        idNum4 = R.id.num4;
        num4 = findViewById(idNum4);
        idNum5 = R.id.num5;
        num5 = findViewById(idNum5);
        idNum6 = R.id.num6;
        num6 = findViewById(idNum6);
        idNum7 = R.id.num7;
        num7 = findViewById(idNum7);
        idNum8 = R.id.num8;
        num8 = findViewById(idNum8);
        idNum9 = R.id.num9;
        num9 = findViewById(idNum9);
        idNum0 = R.id.num0;
        num0 = findViewById(idNum0);
        idClear = R.id.clear;
        clear = findViewById(idClear);
        idDelete = R.id.delete;
        delete = findViewById(idDelete);
        idDecimal = R.id.decimal;
        decimal = findViewById(idDecimal);
        idGroup = R.id.group;
        group = findViewById(idGroup);
        idSin = R.id.sin;
        sin = findViewById(idSin);
        idCos = R.id.cos;
        cos = findViewById(idCos);
        idDeg = R.id.deg;
        deg = findViewById(idDeg);
        inputMath = findViewById(R.id.inputMath);
        outputMath = findViewById(R.id.ouputMath);
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
        group.setOnClickListener(this);
        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        deg.setOnClickListener(this);
    }

    private void addOperation(String op) {
        if (input.length() > 0 && Character.isDigit(input.charAt(input.length() - 1))) {
            input += op;
        };
        if (input.length() == 0) input = "0";
    }


    private void addTrigonometric(String trigonometric) {
        int index = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            if (!Character.isDigit(input.charAt(i))) {
                index = i + 1;
                break;
            }
        }
        String firstPart = input.substring(0, index);
        String secondPart = input.substring(index);
        if (trigonometric.equals("cos") || trigonometric.equals("sin")) {
            input = firstPart + trigonometric + "(" + secondPart + ")";
        } else {
            input = firstPart + "(" + secondPart + ")" + trigonometric;
        }
    }

    private void deleteGroup(){
        check = check ? false : true;
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(input.length() == 1 && input.equals("0")) input = "";
        if (viewId == idPlus) {
            addOperation("+");
        } else if (viewId == idMinus) {
            addOperation("-");
        } else if (viewId == idDivision) {
            addOperation("/");
        } else if (viewId == idMultiply) {
            addOperation("*");
        } else if (viewId == idNum1) {
            input += "1";
        } else if (viewId == idNum2) {
            input += "2";
        } else if (viewId == idNum3) {
            input += "3";
        } else if (viewId == idNum4) {
            input += "4";
        } else if (viewId == idNum5) {
            input += "5";
        } else if (viewId == idNum6) {
            input += "6";
        } else if (viewId == idNum7) {
            input += "7";
        } else if (viewId == idNum8) {
            input += "8";
        } else if (viewId == idNum9) {
            input += "9";
        } else if (viewId == idNum0) {
            input += "0";
        } else if (viewId == idDecimal) {
            if (input.length() == 0) input = "0";
            if(Character.isDigit(input.charAt(input.length() - 1))){
                input += ".";
            }
        }
        else if (viewId == idSin) {
            addTrigonometric("sin");
        }
        else if (viewId == idCos) {
            addTrigonometric("cos");
        }
        else if (viewId == idDeg) {
            addTrigonometric("deg");
        }else if (viewId == idDelete) {
            deleteGroup();
            if(input.length() > 1) input = input.substring(0, input.length()-1);
            else input = "0";
            outputMath.setText("");
        } else if (viewId == idClear){
            input = "0";
            check = true;
            outputMath.setText("");
        } else if (viewId == idGroup) {
            if(check){
                input += "(";
                check = false;
            }else {
                input += ")";
                check = true;
            }
        } else if (viewId == idEqual){
            if(!check){
                input += ")";
                check = true;
            }
            if(input.length() == 0) input = "0";
            if(input.charAt(input.length() - 1) == '.') input = input.substring(0, input.length() - 1);
            output = "= " + decimalFormat.format(new GroupEvaluteString(input).evaluate());
            outputMath.setText(output);
        }
        inputMath.setText(input);
    }
}

