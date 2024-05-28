package ntu.lhqthanh_63135429;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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

import ntu.lhqthanh_63135429.Song.Song;
import ntu.lhqthanh_63135429.Song.SongFragment;
import ntu.lhqthanh_63135429.api.ZingMP3Api;
import ntu.lhqthanh_63135429.search.SearchFragment;
import ntu.lhqthanh_63135429.slider.SilderFragment;
import ntu.lhqthanh_63135429.slider.SliderData;
import ntu.lhqthanh_63135429.thi_cuoi_ki.AdapterRecycleView;
import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class HomeFragment extends Fragment {
    ZingMP3Api api = ZingMP3Api.getInstance();
    List<Fragment> fragmentListHome;
    AdapterRecycleView adapterHome;
    RecyclerView recyclerView;
    FragmentManager fragmentManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentListHome = new ArrayList<>();
        fragmentListHome.add(new SearchFragment());
        try {
            String jsonString = api.getHome();
            JsonElement element = JsonParser.parseString(jsonString);
            JsonObject object = element.getAsJsonObject();
            JsonObject data = object.get("data").getAsJsonObject();
            JsonArray items = data.get("items").getAsJsonArray();
            if(items.size() > 0){
                fragmentListHome.add(new SilderFragment(fetchBanner(items.get(0).getAsJsonObject())));
                fragmentListHome.add(new SongFragment(fetchSongs(items.get(2).getAsJsonObject()), "Mới phát hành", new LinearLayoutManager(getActivity())));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        fragmentListHome.add(new Fragment());

        recyclerView = view.findViewById(R.id.HomeRecyclerView);
        RecyclerView.LayoutManager linear = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linear);

        adapterHome = new AdapterRecycleView(fragmentListHome, getActivity(), fragmentManager);
        recyclerView.setAdapter(adapterHome);

        return view;
    }
    private ArrayList<Song> fetchSongs(JsonObject newReleased) {
        ArrayList<Song> listSong = new ArrayList<>();
        JsonObject category = newReleased.get("items").getAsJsonObject();
        JsonArray listSongAll = category.get("all").getAsJsonArray();
        int numberSong = Math.min(listSongAll.size(), 10);
        Song prevSong = null;
        Song currentSong = null;
        for (int i = 0; i < numberSong; i++) {
            JsonObject obj = listSongAll.get(i).getAsJsonObject();
            String thumbnail = obj.get("thumbnailM").getAsString();
            String nameSong = obj.get("title").getAsString();
            String nameArtist = obj.get("artistsNames").getAsString();
            String id = obj.get("encodeId").getAsString();
            int duration = obj.get("duration").getAsInt();
            currentSong = new Song(nameSong, nameArtist, thumbnail, id, duration, prevSong);
            if(i > 0) listSong.get(i - 1).setNextSong(currentSong);
            listSong.add(currentSong);
            prevSong = currentSong;
        }
        return listSong;
    }
    private ArrayList<SliderData> fetchBanner(JsonObject banner) {
        ArrayList<SliderData> list = new ArrayList<>();
        JsonArray itemsBanner = banner.get("items").getAsJsonArray();
        for (int i = 0; i < itemsBanner.size(); i++) {
            JsonObject obj = itemsBanner.get(i).getAsJsonObject();
            String bannerName = obj.get("banner").getAsString();
            String id = obj.get("encodeId").getAsString();
            list.add(new SliderData(id, bannerName));
        }
        return list;
    }
}