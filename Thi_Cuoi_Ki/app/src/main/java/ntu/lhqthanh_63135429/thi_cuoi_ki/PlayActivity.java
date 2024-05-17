package ntu.lhqthanh_63135429.thi_cuoi_ki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ntu.lhqthanh_63135429.api.ZingMP3Api;

public class PlayActivity extends AppCompatActivity {
    ImageButton playButton, backButton;
    ImageView cdImage;
    TextView songName, artistsName;
    boolean isPlaying = true;
    ZingMP3Api api = ZingMP3Api.getInstance();
    float currentRotate = 0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        backButton = findViewById(R.id.backButton);
        playButton = findViewById(R.id.playButton);
        cdImage = findViewById(R.id.imageThumbnail);
        songName = findViewById(R.id.songName);
        artistsName = findViewById(R.id.artistName);
        Intent intent = getIntent();
        String idSong = intent.getStringExtra("idSong");
        String urlThumbnail = "";
        String nameSong = "";
        String artistName = "";
        try {
            JsonElement element = JsonParser.parseString(api.getInfoSong(idSong));
            JsonObject object = element.getAsJsonObject();
            JsonObject data = object.get("data").getAsJsonObject();
            nameSong = data.get("title").getAsString();
            songName.setText(nameSong);
            artistName = data.get("artistsNames").getAsString();
            artistsName.setText(artistName);
            urlThumbnail = data.get("thumbnailM").getAsString();
        } catch (Exception e) {}
        if (!urlThumbnail.isEmpty()) {
            Glide.with(this)
                    .load(urlThumbnail)
                    .fitCenter()
                    .into(cdImage);
        }

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying) {
                    playButton.setImageResource(R.drawable.play);
                    isPlaying = false;
                } else {
                    playButton.setImageResource(R.drawable.pause);
                    isPlaying = true;
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}