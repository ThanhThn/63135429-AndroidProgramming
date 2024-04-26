package ntu.lhqthanh.bottomnavigationwithfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottomNav);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.navHome){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
                    return true;
                } else if (id == R.id.navSearch) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new SearchFragment()).commit();
                    return true;
                }else if (id == R.id.navNotification) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new NotifiFragment()).commit();
                    return true;
                }else if (id == R.id.navProfile) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ProfileFragment()).commit();
                    return true;
                }
                return false;
            }
        });
    }
}