package com.example.customadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.customadapter.adapters.CountryAdapter;
import com.example.customadapter.models.Country;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Country> countries = new ArrayList<Country>();
    ArrayList<String> dsTenCacTinhThanh = new ArrayList<String>();
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);
        countries.add(new Country("Việt Nam", 99298380, R.drawable.vn ));
        countries.add(new Country("Trung Quốc", 1425323950, R.drawable.cn ));
        CountryAdapter adapter = new CountryAdapter(this,countries);
        dsTenCacTinhThanh.add("Hà Nội");
        dsTenCacTinhThanh.add("Thành phố Hồ Chí Minh");
        dsTenCacTinhThanh.add("Khánh Hòa");
        dsTenCacTinhThanh.add("Đà Nẵng");
        dsTenCacTinhThanh.add("Lâm Đồng");

        ArrayAdapter<String> adapterTinhThanh = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsTenCacTinhThanh);

        list.setAdapter(adapter);

    }
}