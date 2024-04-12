package ntu.com.usingrecycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.ItemLandHolder> {
    Context context;
    ArrayList<LandScape> listData;

    public LandScapeAdapter(Context context, ArrayList<LandScape> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ItemLandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater cai_bom = LayoutInflater.from(context);
        View viewItem  = cai_bom.inflate(R.layout.item_land, parent, false);
        ItemLandHolder holderLand = new ItemLandHolder(viewItem);
        return holderLand;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLandHolder holder, int position) {
        LandScape landScapeHienThi = listData.get(position);
        String landCaption = landScapeHienThi.getLandCaption();
        String imgFileNameLand = landScapeHienThi.getImgLandFileName();

        holder.textCaption.setText(landCaption);
        String packageName = holder.itemView.getContext().getPackageName();
        int imgID = holder.itemView.getResources().getIdentifier(imgFileNameLand, "mipmap", packageName );
        holder.imgLandScape.setImageResource(imgID);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ItemLandHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textCaption;
        ImageView imgLandScape;

        public ItemLandHolder(@NonNull View itemView) {
            super(itemView);
            textCaption = itemView.findViewById(R.id.textViewCaption);
            imgLandScape = itemView.findViewById(R.id.imageViewLand);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int viTriClick = getAdapterPosition();
            LandScape landScape = listData.get(viTriClick);
            String ten = landScape.getLandCaption();
            String tenFile = landScape.getImgLandFileName();

            String chuoiThongBao = "Bạn đã click chọn " + ten + " có tên file là " + tenFile;

            Toast.makeText(v.getContext(), chuoiThongBao, Toast.LENGTH_SHORT).show();
        }
    }
}
