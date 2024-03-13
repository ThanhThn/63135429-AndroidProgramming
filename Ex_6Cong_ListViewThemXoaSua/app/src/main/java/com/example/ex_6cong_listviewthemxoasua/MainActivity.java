package com.example.ex_6cong_listviewthemxoasua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> dsTenCacTinhThanh = new ArrayList<String>();
    ArrayAdapter<String> adapterTinhThanh;
    EditText tenTinh;
    int positionInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tenTinh = findViewById(R.id.tenTinh);
        dsTenCacTinhThanh.add("Hà Nội");
        dsTenCacTinhThanh.add("Thành phố Hồ Chí Minh");
        dsTenCacTinhThanh.add("Khánh Hòa");
        dsTenCacTinhThanh.add("Đà Nẵng");
        dsTenCacTinhThanh.add("Lâm Đồng");

        adapterTinhThanh = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsTenCacTinhThanh);
        ListView lvTenTinhThanh = findViewById(R.id.lvDanhSachTT);
        lvTenTinhThanh.setAdapter(adapterTinhThanh);

        lvTenTinhThanh.setOnItemClickListener(BoLangNgheSuKienVaXL);
    }

    AdapterView.OnItemClickListener BoLangNgheSuKienVaXL = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String strTinhThanhChon = dsTenCacTinhThanh.get(position);
            tenTinh.setText(strTinhThanhChon);
            positionInt = position;
        }
    };

    public void  Add(View view){

        String name = tenTinh.getText().toString();
        if(!name.isEmpty()){
            dsTenCacTinhThanh.add(name);
            if (adapterTinhThanh != null) {
                adapterTinhThanh.notifyDataSetChanged();
            }
        }
    }
    public void Delete(View view){
        String name = tenTinh.getText().toString();
        if(!name.isEmpty() && !dsTenCacTinhThanh.isEmpty()) {
            dsTenCacTinhThanh.remove(positionInt);
            adapterTinhThanh.notifyDataSetChanged();
        }
    }
    public void Edit(View view){
        String name = tenTinh.getText().toString();
        if(!name.isEmpty() && !String.valueOf(positionInt).isEmpty() && !dsTenCacTinhThanh.isEmpty()) {
            dsTenCacTinhThanh.set(positionInt, name);
            adapterTinhThanh.notifyDataSetChanged();
        }
    }
}