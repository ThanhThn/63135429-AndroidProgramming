package ntu.lhqthanh_63135429.new_released;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import ntu.lhqthanh_63135429.Song.Song;
import ntu.lhqthanh_63135429.Song.SongAdapter;
import ntu.lhqthanh_63135429.api.ZingMP3Api;
import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class NewReleasedFragment extends Fragment {
    ZingMP3Api api = ZingMP3Api.getInstance();
    RecyclerView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_released, container, false);

        ArrayList<Song> listSong = fetchSongs();
        listView = view.findViewById(R.id.listNewReleased);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        SongAdapter adapter = new SongAdapter(listSong, getActivity());
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(adapter);
        return view;
    }
    private ArrayList<Song> fetchSongs() {
        ArrayList<Song> listSong = new ArrayList<>();
        try {
            String jsonString = api.getHome();
            JsonElement element = JsonParser.parseString(jsonString);
            JsonObject object = element.getAsJsonObject();
            JsonObject data = object.get("data").getAsJsonObject();
            JsonArray items = data.get("items").getAsJsonArray();
            if (items.size() > 0) {
                JsonObject newReleased = items.get(2).getAsJsonObject();
                JsonObject category = newReleased.get("items").getAsJsonObject();
                JsonArray listSongAll = category.get("all").getAsJsonArray();
                int numberSong = Math.min(listSongAll.size(), 10);
                for (int i = 0; i < numberSong; i++) {
                    JsonObject obj = listSongAll.get(i).getAsJsonObject();
                    String thumbnail = obj.get("thumbnail").getAsString();
                    String nameSong = obj.get("title").getAsString();
                    String nameArtist = obj.get("artistsNames").getAsString();
                    String id = obj.get("encodeId").getAsString();
                    listSong.add(new Song(nameSong, nameArtist, thumbnail, id));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSong;
    }
}