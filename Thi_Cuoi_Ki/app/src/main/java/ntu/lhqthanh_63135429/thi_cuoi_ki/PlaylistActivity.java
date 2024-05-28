package ntu.lhqthanh_63135429.thi_cuoi_ki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import ntu.lhqthanh_63135429.Song.Song;
import ntu.lhqthanh_63135429.Song.SongAdapter;
import ntu.lhqthanh_63135429.api.ZingMP3Api;

public class PlaylistActivity extends AppCompatActivity {
    ImageView thumbnail;
    ImageButton backButton;
    TextView namePlaylist, artists;
    RecyclerView songRecyclerView;
    ZingMP3Api api = ZingMP3Api.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        thumbnail = findViewById(R.id.topThumbnail);
        backButton = findViewById(R.id.backButton);
        namePlaylist = findViewById(R.id.topName);
        artists = findViewById(R.id.artistName);
        songRecyclerView = findViewById(R.id.songList);
        Intent intent = getIntent();
        Glide.with(this).load(intent.getStringExtra("thumbnail")).fitCenter().into(thumbnail);
        try {
            String jsonString = api.getDetailPlaylist(intent.getStringExtra("id"));
            JsonElement element = JsonParser.parseString(jsonString);
            JsonObject object = element.getAsJsonObject();
            JsonObject data = object.get("data").getAsJsonObject();
            namePlaylist.setText(data.get("title").getAsString());
            artists.setText(data.get("artistsNames").getAsString());
            ArrayList<Song> listSong = fetchSongs(data.get("song").getAsJsonObject());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            SongAdapter adapter = new SongAdapter(listSong, this);
            songRecyclerView.setLayoutManager(layoutManager);
            songRecyclerView.setAdapter(adapter);
            LinearSnapHelper snapHelper = new LinearSnapHelper();
            snapHelper.attachToRecyclerView(songRecyclerView);
        }catch (Exception e){
            e.printStackTrace();
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private ArrayList<Song> fetchSongs(JsonObject songs) {
        ArrayList<Song> listSong = new ArrayList<>();
        JsonArray items = songs.get("items").getAsJsonArray();
        Song prevSong = null;
        Song currentSong = null;
        for (int i = 0; i < items.size(); i++) {
            JsonObject obj = items.get(i).getAsJsonObject();
            String thumbnail = obj.get("thumbnailM").getAsString();
            String nameSong = obj.get("title").getAsString();
            String nameArtist = obj.get("artistsNames").getAsString();
            String id = obj.get("encodeId").getAsString();
            int duration = obj.get("duration").getAsInt();
            currentSong = new Song(nameSong, nameArtist, thumbnail, id, duration, prevSong);
            if (i > 0) listSong.get(i - 1).setNextSong(currentSong);
            listSong.add(currentSong);
            prevSong = currentSong;
        }
        return listSong;
    }
}