package ntu.lhqthanh_63135429.Top100;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import ntu.lhqthanh_63135429.api.ZingMP3Api;
import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class Top100Fragment extends Fragment {
    ZingMP3Api api = ZingMP3Api.getInstance();
    RecyclerView listViewTop;
    public Top100Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top100, container, false);
        List<Top100> top100List = fetchTop();
        listViewTop = view.findViewById(R.id.listTop100Category);
        RecyclerView.LayoutManager grid = new GridLayoutManager(getActivity(), 2);
        listViewTop.setLayoutManager(grid);
        listViewTop.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                int column = position % 2;
                int spacing = 40;
                outRect.left = column * spacing / 2;
                outRect.right = spacing - (column + 1) * spacing / 2;
                if (position >= 2) {
                    outRect.top = spacing;
                }
            }
        });
        Top100Adapter adapter = new Top100Adapter(top100List, getActivity());
        listViewTop.setAdapter(adapter);
        return view;
    }

    private ArrayList<Top100> fetchTop(){
        ArrayList<Top100> topList = new ArrayList<>();
        try {
            JsonElement element = JsonParser.parseString(api.getTop100());
            JsonObject object = element.getAsJsonObject();
            JsonArray data = object.get("data").getAsJsonArray();
            JsonObject listTopVN = data.get(1).getAsJsonObject();
            JsonArray items = listTopVN.get("items").getAsJsonArray();
            for (int i = 0; i < items.size(); i++){
                JsonObject item = items.get(i).getAsJsonObject();
                String thumbnail = item.get("thumbnailM").getAsString();
                String nameTop = item.get("title").getAsString();
                String nameArtists = item.get("artistsNames").getAsString();
                String id = item.get("encodeId").getAsString();
                topList.add(new Top100(thumbnail, nameTop, nameArtists,id));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return topList;
    }
}