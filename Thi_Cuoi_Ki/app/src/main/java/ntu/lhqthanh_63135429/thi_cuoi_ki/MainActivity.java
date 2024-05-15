package ntu.lhqthanh_63135429.thi_cuoi_ki;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ntu.lhqthanh_63135429.api.ZingMP3Api;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ZingMP3Api api = new ZingMP3Api();
        try {
            String str = api.getHome();
            System.out.printf(str);
        }catch (Exception e){
            System.out.printf(e.toString());
        }
    }
}