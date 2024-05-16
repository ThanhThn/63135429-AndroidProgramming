package ntu.lhqthanh_63135429.slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {
    List<SliderData> sliderData;
    Context context;

    public SliderAdapter(List<SliderData> sliderData, Context context) {
        this.sliderData = sliderData;
        this.context = context;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater cai_bom = LayoutInflater.from(context);
        View viewItem  = cai_bom.inflate(R.layout.slider_item, parent, false);
        SliderAdapterViewHolder holderItem = new SliderAdapterViewHolder(viewItem);
        return holderItem;
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {
        SliderData data = sliderData.get(position);
        Glide.with(viewHolder.itemView)
                .load(data.getUrlImage())
                .fitCenter()
                .into(viewHolder.imageViewBackground);
    }

    @Override
    public int getCount() {
        return sliderData.size();
    }

    class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView imageViewBackground;


        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.slide_image);
        }
    }
}
