package com.example.bailv_congdiem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> dsBH;
    ArrayAdapter<String> adapterBH;

    ListView lvBaiHat;
    EditText nameSong,positionSong;
    private int positionInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvBaiHat = findViewById(R.id.lvBaiHat);
        nameSong = findViewById(R.id.nameSong);
        positionSong =findViewById(R.id.position);

        dsBH = new ArrayList<String>();
        dsBH.add("Thủy triều");
        dsBH.add("Chúng ta của tương lai");
        dsBH.add("Làm dâu trăm họ");
        adapterBH = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsBH);
        lvBaiHat.setAdapter(adapterBH);
        lvBaiHat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nameSong.setText(dsBH.get(position));
                positionSong.setText(String.valueOf(position + 1));
                positionInt =position;
                positionSong.setEnabled(false);
            }
        });
    }

    public void  Add(View view){

        String name = nameSong.getText().toString();
        if(!name.isEmpty()){
            dsBH.add(name);
            if (adapterBH != null) {
                adapterBH.notifyDataSetChanged();
            }
        }
    }
    public void Delete(View view){
        String name = nameSong.getText().toString();
        int position = this.positionInt;
        if(!name.isEmpty() && !positionSong.getText().toString().isEmpty() && !dsBH.isEmpty()) {
            dsBH.remove(position);
            adapterBH.notifyDataSetChanged();
        }
    }
    public void Edit(View view){
        String name = nameSong.getText().toString();
        int position = this.positionInt;
        if(!name.isEmpty() && !positionSong.getText().toString().isEmpty()) {
            dsBH.set(position, name);
            adapterBH.notifyDataSetChanged();
        }
    }
}