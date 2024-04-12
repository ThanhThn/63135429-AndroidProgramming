package ntu.com.usingrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LandScapeAdapter landScapeAdapter;
    ArrayList<LandScape> dsLandScape;

    RecyclerView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dsLandScape = getDataForCyclerView();
        view = findViewById(R.id.cyclerLand);

//        RecyclerView.LayoutManager linear = new LinearLayoutManager(this);
//        view.setLayoutManager(linear);
//        RecyclerView.LayoutManager linearHorizontal = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//        view.setLayoutManager(linearHorizontal);
        RecyclerView.LayoutManager grid = new GridLayoutManager(this , 2);
        view.setLayoutManager(grid);

        landScapeAdapter = new LandScapeAdapter(this, dsLandScape);
        view.setAdapter(landScapeAdapter);
    }


    ArrayList<LandScape> getDataForCyclerView(){
        ArrayList<LandScape> dsDuLieu = new ArrayList<>();
        LandScape item1 = new LandScape("boat", "Boat");
        LandScape item2 = new LandScape("hills", "Hills");
        LandScape item3 = new LandScape("huts", "Huts");
        LandScape item4 = new LandScape("pagoda", "Pagoda");
        LandScape item5 = new LandScape("trees", "Trees");
        dsDuLieu.add(item1);
        dsDuLieu.add(item2);
        dsDuLieu.add(item3);
        dsDuLieu.add(item4);
        dsDuLieu.add(item5);
        return dsDuLieu;
    }
}