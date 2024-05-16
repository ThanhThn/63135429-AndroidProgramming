package ntu.lhqthanh_63135429.thi_cuoi_ki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import ntu.lhqthanh_63135429.Song.Song;
import ntu.lhqthanh_63135429.Song.SongAdapter;
import ntu.lhqthanh_63135429.api.ZingMP3Api;
import ntu.lhqthanh_63135429.new_released.NewReleasedFragment;
import ntu.lhqthanh_63135429.slider.SilderFragment;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterRecycleView adapter;
    List<Fragment> fragmentList;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentList = new ArrayList<>();
        fragmentList.add(new SilderFragment());
        fragmentList.add(new NewReleasedFragment());
        fragmentManager = getSupportFragmentManager();
        recyclerView = findViewById(R.id.mainRecyclerView);
        RecyclerView.LayoutManager linear = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linear);

        adapter = new AdapterRecycleView(fragmentList, this, fragmentManager);
        recyclerView.setAdapter(adapter);
    }

}