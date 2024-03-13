package com.example.ex_6_listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> dsTenCacTinhThanh = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dsTenCacTinhThanh.add("Hà Nội");
        dsTenCacTinhThanh.add("Thành phố Hồ Chí Minh");
        dsTenCacTinhThanh.add("Khánh Hòa");
        dsTenCacTinhThanh.add("Đà Nẵng");
        dsTenCacTinhThanh.add("Lâm Đồng");

        ArrayAdapter<String> adapterTinhThanh = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsTenCacTinhThanh);
        ListView lvTenTinhThanh = findViewById(R.id.lvDanhSachTT);
        lvTenTinhThanh.setAdapter(adapterTinhThanh);

        lvTenTinhThanh.setOnItemClickListener(BoLangNgheSuKienVaXL);
    }

    AdapterView.OnItemClickListener BoLangNgheSuKienVaXL = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String strTinhThanhChon = dsTenCacTinhThanh.get(position);
            Toast.makeText(MainActivity.this, strTinhThanhChon, Toast.LENGTH_LONG).show();
        }
    };
}