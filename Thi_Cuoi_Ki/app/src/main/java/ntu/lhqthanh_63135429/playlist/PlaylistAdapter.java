package ntu.lhqthanh_63135429.playlist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import ntu.lhqthanh_63135429.thi_cuoi_ki.R;
import ntu.lhqthanh_63135429.thi_cuoi_ki.TopActivity;

public class PlaylistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Playlist> playlists;
    Context context;

    public PlaylistAdapter(List<Playlist> playlists, Context context) {
        this.playlists = playlists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.playlist_item, parent, false);
        return new PlaylistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PlaylistHolder playlistHolder = (PlaylistHolder) holder;
        Playlist playlist = playlists.get(position);
        playlistHolder.title.setText(playlist.getNameList());
        playlistHolder.artists.setText(playlist.getNameArtist());
        Glide.with(context).load(playlist.getThumbnail()).fitCenter().into(playlistHolder.thumbnail);
        playlistHolder.context = context;
        playlistHolder.urlThumbnail = playlist.getThumbnail();
        playlistHolder.id = playlist.getIdList();
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    static class PlaylistHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView thumbnail;
        TextView title, artists;
        Context context;
        String urlThumbnail, id;
        public PlaylistHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnailPlaylist);
            title = itemView.findViewById(R.id.titlePlaylist);
            artists = itemView.findViewById(R.id.artitsPlaylist);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, TopActivity.class);
            intent.putExtra("thumbnail", urlThumbnail);
            intent.putExtra("name", title.getText().toString());
            intent.putExtra("artists", artists.getText().toString());
            intent.putExtra("id", id);
            context.startActivity(intent);
        }
    }
}
