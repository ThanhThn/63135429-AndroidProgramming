package ntu.lhqthanh_63135429.Top100;

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

public class Top100Adapter extends RecyclerView.Adapter<Top100Adapter.Top100Holder> {

    List<Top100> top100List;
    Context context;

    public Top100Adapter(List<Top100> top100List, Context context) {
        this.top100List = top100List;
        this.context = context;
    }

    @NonNull
    @Override
    public Top100Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.top100_item, parent, false);
        return new Top100Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Top100Holder holder, int position) {
        Top100 top100 = top100List.get(position);
        holder.nameTop.setText(top100.getNameTop());
        holder.nameArtists.setText(top100.getNameArtist());
        holder.context = context;
        holder.id = top100.getIdTop();
        holder.urlThumbnail = top100.getThumbnail();
        Glide.with(context).load(top100.getThumbnail()).fitCenter().into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return top100List.size();
    }

    static class Top100Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView thumbnail;
        TextView nameTop, nameArtists;
        Context context;
        String urlThumbnail, id;
        public Top100Holder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnailTop);
            nameTop = itemView.findViewById(R.id.nameTop);
            nameArtists = itemView.findViewById(R.id.nameArtists);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            Intent intent = new Intent(context, PlaylistActivity.class);
            intent.putExtra("thumbnail", urlThumbnail);
            intent.putExtra("id", id);
            context.startActivity(intent);
        }
    }
}
