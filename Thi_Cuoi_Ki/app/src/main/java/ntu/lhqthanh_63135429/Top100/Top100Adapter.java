package ntu.lhqthanh_63135429.Top100;

import android.content.Context;
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
        Glide.with(context).load(top100.getThumbnail()).fitCenter().into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return top100List.size();
    }

    static class Top100Holder extends RecyclerView.ViewHolder{
        ImageView thumbnail;
        TextView nameTop, nameArtists;

        public Top100Holder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnailTop);
            nameTop = itemView.findViewById(R.id.nameTop);
            nameArtists = itemView.findViewById(R.id.nameArtists);
        }
    }
}
