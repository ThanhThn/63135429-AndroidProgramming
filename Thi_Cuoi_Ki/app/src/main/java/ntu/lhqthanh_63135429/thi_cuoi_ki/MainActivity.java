package ntu.lhqthanh_63135429.thi_cuoi_ki;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import ntu.lhqthanh_63135429.slider.SilderFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout = findViewById(R.id.frame);
        getSupportFragmentManager().beginTransaction().add(R.id.frame, new SilderFragment()).commit();

    }
}