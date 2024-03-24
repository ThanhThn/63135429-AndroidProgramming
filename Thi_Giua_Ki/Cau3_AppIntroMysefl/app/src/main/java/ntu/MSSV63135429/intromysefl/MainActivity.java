package ntu.MSSV63135429.intromysefl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logout(View view){
        finish();
    }
    public void redirectionFacebook(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/lehoangquocthan"));
        startActivity(intent);
    }

    public void redirectionCau1(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ThanhThn/63135429-AndroidProgramming/tree/main/Thi_Giua_Ki/Cau1_AppCurrencyConvert"));
        startActivity(intent);
    }

    public void redirectionCau2(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("app://cau2"));
        startActivity(intent);
    }
}