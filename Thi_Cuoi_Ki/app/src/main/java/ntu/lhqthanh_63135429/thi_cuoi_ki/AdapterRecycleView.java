package ntu.lhqthanh_63135429.thi_cuoi_ki;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ntu.lhqthanh_63135429.EmptyViewHolder;

public class AdapterRecycleView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;
    private Context context;
    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    int heightEmpty;


    public AdapterRecycleView(List<Fragment> fragmentList, Context context, FragmentManager fragmentManager, int height) {
        this.fragmentList = fragmentList;
        this.fragmentManager = fragmentManager;
        this.heightEmpty = height;
        this.context = context;
    }

    public AdapterRecycleView(List<Fragment> fragmentList, Context context, FragmentManager fragmentManager){
        this(fragmentList, context, fragmentManager, 80);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == VIEW_TYPE_EMPTY ){
            View view = inflater.inflate(R.layout.empty_item,parent, false);
            return new EmptyViewHolder(view, heightEmpty, context);
        }else {
            View view = inflater.inflate(R.layout.frame_item, parent, false);
            return new ViewHolderHome(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EmptyViewHolder) {
        } else {
            Fragment fragment = fragmentList.get(position);
            ViewHolderHome holderHome = (ViewHolderHome) holder;
            int fragmentContainerId = View.generateViewId();
            holderHome.fragmentContainer.setId(fragmentContainerId);
            fragmentManager.beginTransaction()
                    .replace(fragmentContainerId, fragment)
                    .commit();
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == fragmentList.size() - 1) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    static class ViewHolderHome extends RecyclerView.ViewHolder {
        FrameLayout fragmentContainer;

        public ViewHolderHome(@NonNull View itemView) {
            super(itemView);
            fragmentContainer = itemView.findViewById(R.id.frameItem);
        }

    }
}