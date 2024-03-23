package com.example.customadapter.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customadapter.R;
import com.example.customadapter.models.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends BaseAdapter {
    //Nguon du lieu cho Adapter nay
    ArrayList<Country> listQG;
    // Context hoat dong
    Context mContext;
    // Layout
    LayoutInflater mInflater;

    public CountryAdapter(Context mContext, ArrayList<Country> listQG) {
        this.listQG = listQG;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return listQG.size();
    }

    @Override
    public Object getItem(int position) {
        return listQG.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CountryViewHolder viewItem;
        viewItem = new CountryViewHolder();
        if (convertView==null) {
            convertView=  mInflater.inflate(R.layout.country_item,null);

            viewItem.textViewNationName = convertView.findViewById(R.id.nameCountry);
            viewItem.textViewPopulation = convertView.findViewById(R.id.quanlity);
            viewItem.imageViewFlag = convertView.findViewById(R.id.imgFlag);
            convertView.setTag(viewItem);
        }
        else {
            viewItem= (CountryViewHolder)convertView.getTag();
        }
        // đặt dữ liệu lên view
        Country quocGiaHienThi = listQG.get(position);
        String tenQG= quocGiaHienThi.getTenQG();
        int soDan = quocGiaHienThi.getSoLuongDan();
        int tenLaCo = quocGiaHienThi.getTenFileAnhLaCo();
        viewItem.textViewNationName.setText(tenQG);
        viewItem.textViewPopulation.setText(String.valueOf(soDan));
        viewItem.imageViewFlag.setImageResource(tenLaCo);
        return convertView;
    }
    static class CountryViewHolder {
        ImageView imageViewFlag;
        TextView textViewNationName;
        TextView textViewPopulation;
    }
}
