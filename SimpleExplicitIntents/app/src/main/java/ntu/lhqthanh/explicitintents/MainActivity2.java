package ntu.lhqthanh.explicitintents;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void chuyenManHinh(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}