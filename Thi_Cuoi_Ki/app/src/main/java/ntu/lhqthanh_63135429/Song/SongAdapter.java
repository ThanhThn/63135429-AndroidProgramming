package ntu.lhqthanh_63135429.Song;

import android.content.Intent;
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
        holder.nameSong.setText(song.getNameSong());
        holder.nameArtist.setText(song.getNameArtist());
        holder.idSong = song.getIdSong();
        holder.context = mcontext;
        Glide.with(mcontext).load(song.getThumbnail()).fitCenter().into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return listSong.size();
    }


    static class SongHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView thumbnail;
        TextView nameSong, nameArtist;
        String idSong;
        Context context;

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
            intent.putExtra("idSong", idSong);
            context.startActivity(intent);

        }
    }
}
