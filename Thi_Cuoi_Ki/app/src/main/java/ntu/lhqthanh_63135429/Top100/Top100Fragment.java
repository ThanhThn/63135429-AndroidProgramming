package ntu.lhqthanh_63135429.Top100;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import ntu.lhqthanh_63135429.api.ZingMP3Api;
import ntu.lhqthanh_63135429.new_released.NewReleasedFragment;
import ntu.lhqthanh_63135429.slider.SilderFragment;
import ntu.lhqthanh_63135429.thi_cuoi_ki.AdapterRecycleView;
import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class Top100Fragment extends Fragment implements OnImageButtonClickListener{
    ZingMP3Api api = ZingMP3Api.getInstance();
    RecyclerView listViewTop;
    List<Top100Category> top100Categories;
    Top100CategoryAdapter adapterTop;
    FragmentManager fragmentManager;
    public Top100Fragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top100, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();
        top100Categories = fetchTop();
        top100Categories.add(new Top100Category(null, null));

        listViewTop = view.findViewById(R.id.topRecycleView);
        RecyclerView.LayoutManager linear = new LinearLayoutManager(getActivity());
        listViewTop.setLayoutManager(linear);

        adapterTop = new Top100CategoryAdapter(top100Categories, getActivity(), this, 4, true);
        listViewTop.setAdapter(adapterTop);
        return view;
    }

    private ArrayList<Top100Category> fetchTop(){
        ArrayList<Top100Category> topList = new ArrayList<>();
        try {
            JsonElement element = JsonParser.parseString(api.getTop100());
            JsonObject object = element.getAsJsonObject();
            JsonArray data = object.get("data").getAsJsonArray();
            for(int i = 0;  i < data.size(); i++){
                JsonObject list = data.get(i).getAsJsonObject();
                String title = list.get("title").getAsString();
                JsonArray items = list.get("items").getAsJsonArray();
                List<Top100> tp = new ArrayList<>();
                for (int j = 0; j < items.size(); j++){
                    JsonObject item = items.get(j).getAsJsonObject();
                    String thumbnail = item.get("thumbnailM").getAsString();
                    String nameTop = item.get("title").getAsString();
                    String nameArtists = item.get("artistsNames").getAsString();
                    String id = item.get("encodeId").getAsString();
                    tp.add(new Top100(thumbnail, nameTop, nameArtists,id));
                }
                topList.add(new Top100Category(title, tp));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return topList;
    }

    @Override
    public void onImageButtonClickListener(ArrayList<Top100Category> top100Categories) {

        adapterTop = new Top100CategoryAdapter(top100Categories, getActivity(), this, 999, false);
        listViewTop.setAdapter(adapterTop);
    }
}