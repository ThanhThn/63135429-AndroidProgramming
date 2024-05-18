package ntu.lhqthanh_63135429.thi_cuoi_ki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import ntu.lhqthanh_63135429.api.ZingMP3Api;

public class PlayActivity extends AppCompatActivity {
    ImageButton playButton, backButton;
    ImageView cdImage;
    TextView songName, artistsName, txtStatusTime, txtMaxTime;
    ProgressBar progressMusic;
    boolean isPlaying = true;
    boolean completed = false;
    volatile boolean isPaused = false;
    ZingMP3Api api = ZingMP3Api.getInstance();
    int maxProgress;
    int progressStatus = 0;
    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Date statusTime = new Date();
    String strStatusTime = "00:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        backButton = findViewById(R.id.backButton);
        playButton = findViewById(R.id.playButton);
        cdImage = findViewById(R.id.imageThumbnail);
        songName = findViewById(R.id.songName);
        artistsName = findViewById(R.id.artistName);
        progressMusic = findViewById(R.id.progressMusic);
        txtStatusTime = findViewById(R.id.statusTime);
        txtMaxTime = findViewById(R.id.maxTime);

        Intent intent = getIntent();
        String idSong = intent.getStringExtra("idSong");
        songName.setText(intent.getStringExtra("nameSong"));
        artistsName.setText(intent.getStringExtra("nameArtist"));
        maxProgress = intent.getIntExtra("duration", 0);
        int minutes = maxProgress / 60;
        int seconds = maxProgress % 60;
        String strMaxTime = String.format("%02d:%02d", minutes, seconds);
        txtMaxTime.setText(strMaxTime);
        txtStatusTime.setText("00:00");

        statusTime.setMinutes(0);
        statusTime.setSeconds(0);

        Glide.with(this)
                .load(intent.getStringExtra("thumbnail"))
                .fitCenter()
                .into(cdImage);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playButton.setImageResource(R.drawable.play);
                isPlaying = false;
                completed = true;
            }
        });
        try {
            JsonElement element = JsonParser.parseString(api.getSong(idSong));
            JsonObject json = element.getAsJsonObject();
            JsonObject data = json.get("data").getAsJsonObject();
            String songUrl= data.get("128").getAsString();
            mediaPlayer.setDataSource(songUrl);
            mediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying) {
                    mediaPlayer.pause();
                    playButton.setImageResource(R.drawable.play);
                    isPaused = true;
                } else {
                    if(completed){
                        progressStatus = 0;
                        progressMusic.setProgress(0);
                        statusTime.setMinutes(0);
                        statusTime.setSeconds(0);
                        txtStatusTime.setText("00:00");
                        mediaPlayer.seekTo(0);
                        mediaPlayer.start();
                        completed = false;
                    }
                    mediaPlayer.start();
                    playButton.setImageResource(R.drawable.pause);
                    isPaused = false;
                }
                isPlaying = !isPlaying;
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
                finish();
            }
        });

        progressMusic.setMax(maxProgress);
        handler.post(updateTimeRunnable);
    }

    private Runnable updateTimeRunnable = new Runnable() {
        @Override
        public void run() {
                if (progressStatus < maxProgress && !isPaused) {
                    statusTime.setSeconds(statusTime.getSeconds() + 1);
                    if (statusTime.getSeconds() >= 60) {
                        statusTime.setMinutes(statusTime.getMinutes() + 1);
                        statusTime.setSeconds(0);
                    }
                    strStatusTime = String.format("%02d:%02d", statusTime.getMinutes(), statusTime.getSeconds());
                    txtStatusTime.setText(strStatusTime);

                    progressStatus += 1;
                    progressMusic.setProgress(progressStatus);
                }
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateTimeRunnable);
    }

}