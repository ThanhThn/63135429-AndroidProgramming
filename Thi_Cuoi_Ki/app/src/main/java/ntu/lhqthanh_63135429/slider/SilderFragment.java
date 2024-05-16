package ntu.lhqthanh_63135429.slider;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import ntu.lhqthanh_63135429.api.ZingMP3Api;
import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class SilderFragment extends Fragment {
    ZingMP3Api api = ZingMP3Api.getInstance();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_silder, container, false);
        List<SliderData> imageList = new ArrayList<>();
        try {
            String str = api.getHome();
            JsonElement element = JsonParser.parseString(str);
            JsonObject object = element.getAsJsonObject();
            JsonObject data = object.get("data").getAsJsonObject();
            JsonArray items = data.get("items").getAsJsonArray();
            if (items.size() > 0) {
                JsonObject banner = items.get(0).getAsJsonObject();
                JsonArray itemsBanner = banner.get("items").getAsJsonArray();
                for (int i = 0; i < itemsBanner.size(); i++) {
                    JsonObject obj = itemsBanner.get(i).getAsJsonObject();
                    String bannerName = obj.get("banner").getAsString();
                    String id = obj.get("encodeId").getAsString();
                    imageList.add(new SliderData(id, bannerName));
                }
            }
        }catch (Exception e){
            System.out.printf(e.toString());
        }
        SliderView imageSlider = mView.findViewById(R.id.slider);
        SliderAdapter adapterSlider = new SliderAdapter(imageList, getActivity());
        imageSlider.setSliderAdapter(adapterSlider);
        imageSlider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        imageSlider.setScrollTimeInSec(3);
        imageSlider.setAutoCycle(true);
        imageSlider.startAutoCycle();
        return mView;
    }
}