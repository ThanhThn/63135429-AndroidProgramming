package ntu.lhqthanh_63135429.Song;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

import ntu.lhqthanh_63135429.thi_cuoi_ki.AdapterRecycleView;
import ntu.lhqthanh_63135429.thi_cuoi_ki.PlayActivity;
import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    ArrayList<Song> listSong;
    Context mcontext;
    public SongAdapter(ArrayList<Song> listSong, Context mcontext) {
        this.listSong = listSong;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View viewItem = inflater.inflate(R.layout.song_item, parent, false);
        return new SongAdapter.SongHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        Song song = listSong.get(position);
        holder.list = listSong;
        holder.nameSong.setText(song.getNameSong());
        holder.nameArtist.setText(song.getNameArtist());
        holder.idSong = song.getIdSong();
        holder.urlThumbnail = song.getThumbnail();
        holder.duration = song.getDuration();
        holder.context = mcontext;
        holder.prevSong = song.getPrevSong();
        holder.nextSong = song.getNextSong();
        Glide.with(mcontext).load(song.getThumbnail()).fitCenter().into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return listSong.size();
    }


    static class SongHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView thumbnail;
        TextView nameSong, nameArtist;
        String idSong, urlThumbnail;
        int duration;
        String prevSong, nextSong;
        Context context;
        ArrayList<Song> list;

        public SongHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            nameArtist = itemView.findViewById(R.id.nameArtist);
            nameSong = itemView.findViewById(R.id.nameSong);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, PlayActivity.class);
            saveSongList(context, list);
            intent.putExtra("idSong", idSong);
            intent.putExtra("nameSong", nameSong.getText().toString());
            intent.putExtra("nameArtist", nameArtist.getText().toString());
            intent.putExtra("thumbnail", urlThumbnail);
            intent.putExtra("duration", duration);
            intent.putExtra("nextSong", nextSong);
            intent.putExtra("prevSong", prevSong);
            context.startActivity(intent);
        }
        private void saveSongList(Context context, ArrayList<Song> songList) {
            SharedPreferences prefs = context.getSharedPreferences("dataListSong", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(songList);
            editor.putString("songList", json);

            editor.apply();
        }
    }


}
