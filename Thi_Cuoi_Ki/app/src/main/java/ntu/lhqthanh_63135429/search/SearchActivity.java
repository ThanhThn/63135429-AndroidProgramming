package ntu.lhqthanh_63135429.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import ntu.lhqthanh_63135429.Song.Song;
import ntu.lhqthanh_63135429.Song.SongFragment;
import ntu.lhqthanh_63135429.api.ZingMP3Api;
import ntu.lhqthanh_63135429.artist.Artist;
import ntu.lhqthanh_63135429.artist.ArtistsFragment;
import ntu.lhqthanh_63135429.playlist.Playlist;
import ntu.lhqthanh_63135429.playlist.PlaylistFragment;
import ntu.lhqthanh_63135429.slider.SilderFragment;
import ntu.lhqthanh_63135429.thi_cuoi_ki.AdapterRecycleView;
import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class SearchActivity extends AppCompatActivity {
    ZingMP3Api api = ZingMP3Api.getInstance();
    RecyclerView searchView;
    List<Fragment> fragmentListSearch;
    AdapterRecycleView adapterSearch;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.recycleViewSearch);
        fragmentManager = this.getSupportFragmentManager();
        fragmentListSearch = new ArrayList<>();
        RecyclerView.LayoutManager linear = new LinearLayoutManager(this);
        searchView.setLayoutManager(linear);

        Intent intent = getIntent();
        try {
            JsonElement element = JsonParser.parseString(api.search(intent.getStringExtra("search")));
            JsonObject objects = element.getAsJsonObject();
            JsonObject data = objects.get("data").getAsJsonObject();
            List<Artist> artistList = fetchArtist(data.get("artists").getAsJsonArray());
            fragmentListSearch.add(new SearchFragment(intent.getStringExtra("search")));
            if(artistList.size() > 0){
                fragmentListSearch.add(new ArtistsFragment(artistList, "Nghệ sĩ/OA"));
            }

            fragmentListSearch.add(new SongFragment(fetchSong(data.get("songs").getAsJsonArray()), "Bài hát", new GridLayoutManager(this, 2)));

            List<Playlist> playlistList = fetchPlaylist(data.get("playlists").getAsJsonArray());
            if(playlistList.size() > 0){
                fragmentListSearch.add(new PlaylistFragment(playlistList, "Playlist/Album"));
            }
            fragmentListSearch.add(new Fragment());

        }catch (Exception e){

        }
        adapterSearch = new AdapterRecycleView(fragmentListSearch, this, fragmentManager, 25);
        searchView.setAdapter(adapterSearch);
    }
    private ArrayList<Song> fetchSong(JsonArray arr){
        ArrayList<Song> list = new ArrayList<>();
        Song prevSong = null;
        Song currentSong = null;
        for (int i = 0; i < arr.size(); i++) {
            JsonObject obj = arr.get(i).getAsJsonObject();
            String thumbnail = obj.get("thumbnailM").getAsString();
            String nameSong = obj.get("title").getAsString();
            String nameArtist = obj.get("artistsNames").getAsString();
            String id = obj.get("encodeId").getAsString();
            int duration = obj.get("duration").getAsInt();
            currentSong = new Song(nameSong, nameArtist, thumbnail, id, duration, prevSong);
            if(i > 0) list.get(i - 1).setNextSong(currentSong);
            list.add(currentSong);
            prevSong = currentSong;
        }
        return  list;
    }
    private ArrayList<Artist> fetchArtist(JsonArray arr){
        ArrayList<Artist> list = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            JsonObject obj = arr.get(i).getAsJsonObject();
            String thumbnail = obj.get("thumbnailM").getAsString();
            String nameArtist = obj.get("name").getAsString();
            String id = obj.get("id").getAsString();
            String playlistId = obj.has("playlistId") ? obj.get("playlistId").getAsString() : "";
            list.add(new Artist(thumbnail, id, nameArtist, playlistId));
        }
        return  list;
    }

    private ArrayList<Playlist> fetchPlaylist(JsonArray arr){
        ArrayList<Playlist> list = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            JsonObject obj = arr.get(i).getAsJsonObject();
            String thumbnail = obj.get("thumbnailM").getAsString();
            String nameArtist = obj.get("artistsNames").getAsString();
            String title = obj.get("title").getAsString();
            String id = obj.get("encodeId").getAsString();
            list.add(new Playlist(thumbnail, title, nameArtist, id));
        }
        return  list;
    }
}