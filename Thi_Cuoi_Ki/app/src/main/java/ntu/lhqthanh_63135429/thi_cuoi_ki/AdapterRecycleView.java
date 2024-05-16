package ntu.lhqthanh_63135429.thi_cuoi_ki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterRecycleView extends RecyclerView.Adapter<AdapterRecycleView.ViewHolder> {
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;

    public AdapterRecycleView(List<Fragment> fragmentList, Context context, FragmentManager fragmentManager) {
        this.fragmentList = fragmentList;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fragment fragment = fragmentList.get(position);
        int fragmentContainerId = View.generateViewId();
        holder.fragmentContainer.setId(fragmentContainerId);
        fragmentManager.beginTransaction()
                .replace(fragmentContainerId, fragment)
                .commit();
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout fragmentContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fragmentContainer = itemView.findViewById(R.id.frameItem);
        }
    }
}
