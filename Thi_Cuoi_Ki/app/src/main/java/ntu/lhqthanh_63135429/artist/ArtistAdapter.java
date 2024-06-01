package ntu.lhqthanh_63135429.artist;

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
import ntu.lhqthanh_63135429.thi_cuoi_ki.PlaylistActivity;

public class ArtistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Artist> list;
    Context context;

    public ArtistAdapter(List <Artist> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.artist_item, parent, false);
        return new ArtistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArtistHolder artistHolder = (ArtistHolder) holder;
        Artist artist = list.get(position);
        artistHolder.name.setText(artist.getNameArtist());
        Glide.with(context).load(artist.getUrlThumbnail()).fitCenter().into(artistHolder.avatar);
        artistHolder.id = artist.getIdArtist();
        artistHolder.context = context;
        artistHolder.urlThumbnail = artist.getUrlThumbnail();
        artistHolder.follower = artist.getFollower();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ArtistHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView avatar;
        TextView name;
        String id;
        String urlThumbnail;
        Context context;
        int follower;

        public ArtistHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatarArtist);
            name = itemView.findViewById(R.id.tenNgheSi);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            Intent intent = new Intent(context, PlaylistActivity.class);
            intent.putExtra("thumbnail", urlThumbnail);
            intent.putExtra("title", name.getText().toString());
            intent.putExtra("source", "artist");
            intent.putExtra("follower", follower);
            intent.putExtra("id", id);
            context.startActivity(intent);
        }
    }
}
