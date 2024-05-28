package ntu.lhqthanh_63135429.thi_cuoi_ki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import ntu.lhqthanh_63135429.Song.Song;
import ntu.lhqthanh_63135429.api.ZingMP3Api;

public class PlayActivity extends AppCompatActivity {
    ImageButton playButton, backButton, prevButton, nextButton, shuffleButton, repeatButton;
    ImageView cdImage;
    TextView songName, artistsName, txtStatusTime, txtMaxTime;
    ProgressBar progressMusic;
    boolean isPlaying = true;
    boolean completed = false;
    volatile boolean isPaused = false;
    boolean isRepeat;
    boolean isShuffle;
    ZingMP3Api api = ZingMP3Api.getInstance();
    int maxProgress;
    int progressStatus = 0;
    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Date statusTime = new Date();
    String strStatusTime = "00:00";
    Song prev, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        backButton = findViewById(R.id.backButton);
        playButton = findViewById(R.id.playButton);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        shuffleButton = findViewById(R.id.shuffleButton);
        repeatButton = findViewById(R.id.repeatButton);
        cdImage = findViewById(R.id.imageThumbnail);
        songName = findViewById(R.id.topName);
        artistsName = findViewById(R.id.artistName);
        progressMusic = findViewById(R.id.progressMusic);
        txtStatusTime = findViewById(R.id.statusTime);
        txtMaxTime = findViewById(R.id.maxTime);

        Intent intent = getIntent();

        prev = (Song) intent.getSerializableExtra("prevSong");
        next = (Song) intent.getSerializableExtra("nextSong");

        if(next == null){
            nextButton.setAlpha(0.3f);
            nextButton.setEnabled(false);
        }
        if(prev == null){
            prevButton.setAlpha(0.3f);
            prevButton.setEnabled(false);
        }

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
                if(isRepeat){
                    progressStatus = 0;
                    progressMusic.setProgress(0);
                    statusTime.setMinutes(0);
                    statusTime.setSeconds(0);
                    txtStatusTime.setText("00:00");
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                    completed = false;
                }else {
                    if(isShuffle){
                        mediaPlayer.release();
                        Intent randomSongIntent = new Intent(PlayActivity.this, PlayActivity.class);
                        ArrayList<Song> listSong = (ArrayList<Song>) intent.getSerializableExtra("listSong");
                        Random random = new Random();
                        Song songRandom = null;
                        while (songRandom == null){
                            Song song = listSong.get(random.nextInt(listSong.size()));
                            if(song.getIdSong() != idSong){
                                songRandom = song;
                            }
                        }
                        randomSongIntent.putExtra("idSong", songRandom.getIdSong());
                        randomSongIntent.putExtra("nameSong", songRandom.getNameSong());
                        randomSongIntent.putExtra("nameArtist", songRandom.getNameArtist());
                        randomSongIntent.putExtra("duration", songRandom.getDuration());
                        randomSongIntent.putExtra("thumbnail", songRandom.getThumbnail());
                        randomSongIntent.putExtra("nextSong", songRandom.getNextSong());
                        randomSongIntent.putExtra("prevSong", songRandom.getPrevSong());
                        randomSongIntent.putExtra("repeat", isRepeat);
                        randomSongIntent.putExtra("shuffle", isShuffle);
                        randomSongIntent.putExtra("listSong", listSong);
                        startActivity(randomSongIntent);
                        finish();
                    }
                    else {
                        playButton.setImageResource(R.drawable.play);
                        isPlaying = false;
                        isPaused = true;
                        completed = true;
                    }
                }
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
        isRepeat = intent.hasExtra("repeat")? intent.getBooleanExtra("repeat", false) : false;
        isShuffle = intent.hasExtra("shuffle")? intent.getBooleanExtra("shuffle", false):false;
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
                Intent nextSongIntent = new Intent(PlayActivity.this, PlayActivity.class);
                nextSongIntent.putExtra("idSong", next.getIdSong());
                nextSongIntent.putExtra("nameSong", next.getNameSong());
                nextSongIntent.putExtra("nameArtist", next.getNameArtist());
                nextSongIntent.putExtra("duration", next.getDuration());
                nextSongIntent.putExtra("thumbnail", next.getThumbnail());
                nextSongIntent.putExtra("nextSong", next.getNextSong());
                nextSongIntent.putExtra("prevSong", next.getPrevSong());
                nextSongIntent.putExtra("repeat", isRepeat);
                startActivity(nextSongIntent);
                finish();
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
                Intent prevSongIntent = new Intent(PlayActivity.this, PlayActivity.class);
                prevSongIntent.putExtra("idSong", prev.getIdSong());
                prevSongIntent.putExtra("nameSong", prev.getNameSong());
                prevSongIntent.putExtra("nameArtist", prev.getNameArtist());
                prevSongIntent.putExtra("duration", prev.getDuration());
                prevSongIntent.putExtra("thumbnail", prev.getThumbnail());
                prevSongIntent.putExtra("nextSong", prev.getNextSong());
                prevSongIntent.putExtra("prevSong", prev.getPrevSong());
                prevSongIntent.putExtra("repeat", isRepeat);
                startActivity(prevSongIntent);
                finish();
            }
        });

        if(isShuffle){
            shuffleButton.setAlpha(1f);
        }else {
            shuffleButton.setAlpha(0.3f);
        }

        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShuffle){
                    shuffleButton.setAlpha(0.3f);
                }else {
                    shuffleButton.setAlpha(1f);
                    if(isRepeat){
                        isRepeat = false;
                        repeatButton.setAlpha(0.3f);
                    }
                }
                isShuffle = !isShuffle;
            }
        });

        if(isRepeat){
            repeatButton.setAlpha(1f);
        }else {
            repeatButton.setAlpha(0.3f);
        }
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRepeat){
                    repeatButton.setAlpha(0.3f);
                }else {
                    repeatButton.setAlpha(1f);
                    if(isShuffle){
                        isShuffle = false;
                        shuffleButton.setAlpha(0.3f);
                    }
                }
                isRepeat = !isRepeat;
            }
        });

        progressMusic.setMax(maxProgress);
        progressMusic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
                    int width = progressMusic.getWidth();
                    int x = event.getX() > width? width : (int) event.getX();
                    int progress = (int) (x / (float) width * progressMusic.getMax());
                    if(x != width) completed = false;
                    progressStatus = progress;
                    progressMusic.setProgress(progressStatus);
                    mediaPlayer.seekTo(progress * 1000);
                    statusTime.setSeconds(progressStatus % 60);
                    statusTime.setMinutes(progressStatus / 60);
                    strStatusTime = String.format("%02d:%02d", statusTime.getMinutes(), statusTime.getSeconds());
                    txtStatusTime.setText(strStatusTime);
                    return true;
                }
                return false;
            }
        });
        handler.post(updateTimeRunnable);
    }

    private final Runnable updateTimeRunnable = new Runnable() {
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