package ntu.lhqthanh_63135429.thi_cuoi_ki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ntu.lhqthanh_63135429.HomeFragment;
import ntu.lhqthanh_63135429.Top100.Top100Fragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottonNav);
        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, new HomeFragment()).commit();
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.navHome){
                    getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, new HomeFragment()).commit();
                    return true;
                }else if (id == R.id.navTop100){
                    getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, new Top100Fragment()).commit();
                    return true;
                }
                return false;
            }
        });
    }

}