package ntu.lhqthanh_63135429.Top100;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ntu.lhqthanh_63135429.EmptyViewHolder;
import ntu.lhqthanh_63135429.thi_cuoi_ki.AdapterRecycleView;
import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class Top100CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Top100Category> top100Categories;
    Context context;
    Fragment fragment;
    int numberCate;
    boolean buttonGo;
    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_NORMAL = 1;

    public Top100CategoryAdapter(List<Top100Category> top100Categories, Context context, Fragment fragment, int numberCate, boolean buttonGo) {
        this.top100Categories = top100Categories;
        this.context = context;
        this.fragment = fragment;
        this.buttonGo = buttonGo;
        this.numberCate = numberCate;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == top100Categories.size() - 1) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == VIEW_TYPE_EMPTY ){
            View view = inflater.inflate(R.layout.empty_item,parent, false);
            return new EmptyViewHolder(view);
        }else {
            View view = inflater.inflate(R.layout.top100_category_item, parent, false);
            return new TopCategoryHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EmptyViewHolder) {

        } else {
            TopCategoryHolder holderCate = (TopCategoryHolder) holder;
            Top100Category category = top100Categories.get(position);
            holderCate.number = numberCate;
            holderCate.nameCategoryTop.setText(category.getTitle());
            holderCate.context = context;
            holderCate.list = category.getTop100List();
            holderCate.isCreateButtonGo(buttonGo);
            holderCate.fragment = (Top100Fragment) fragment;
            holderCate.setRecycleView(category.getTop100List());
        }

    }


    @Override
    public int getItemCount() {
        return top100Categories.size();
    }

    static class TopCategoryHolder extends RecyclerView.ViewHolder{
        TextView nameCategoryTop;
        RecyclerView topCategory;
        ImageButton button;
        Context context;
        ConstraintLayout layout;
        Top100Fragment fragment;
        List<Top100> list;
        int number;
        public TopCategoryHolder(@NonNull View itemView) {
            super(itemView);
            nameCategoryTop = itemView.findViewById(R.id.nameCategoryTop);
            topCategory = itemView.findViewById(R.id.topCategory);
            layout = (ConstraintLayout) itemView;
        }
        public void setRecycleView(List<Top100> list){
            int size = Math.min(list.size(), number);
            List<Top100>top = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                top.add(list.get(i));
            }
            Top100Adapter adapter = new Top100Adapter(top, context);
            RecyclerView.LayoutManager grid = new GridLayoutManager(context, 2);
            topCategory.setAdapter(adapter);
            topCategory.setLayoutManager(grid);
            topCategory.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    int position = parent.getChildAdapterPosition(view);
                    int column = position % 2;
                    int spacing = 40;
                    outRect.left = column * spacing / 2;
                    outRect.right = spacing - (column + 1) * spacing / 2;
                    if (position >= 2) {
                        outRect.top = spacing;
                    }
                }
            });
        }

        public void isCreateButtonGo(boolean isCreate){
            if (isCreate){
                createButtonGo();
            }
        }
        public void createButtonGo(){
            button = new ImageButton(context);

            button.setId(View.generateViewId());

            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.bottomMargin = -22;
            layoutParams.leftMargin = 20;
            button.setLayoutParams(layoutParams);
            button.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.go));
            button.setBackgroundColor(0x00FFFFFF);
            layout.addView(button);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(layout);

            constraintSet.connect(button.getId(), ConstraintSet.BOTTOM, nameCategoryTop.getId(), ConstraintSet.BOTTOM);
            constraintSet.connect(button.getId(), ConstraintSet.START, nameCategoryTop.getId(), ConstraintSet.END);

            constraintSet.applyTo(layout);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Top100Category> categories = new ArrayList<>();
                    categories.add(new Top100Category(nameCategoryTop.getText().toString(), list));
                    categories.add(new Top100Category(null, null));
                    fragment.onImageButtonClickListener(categories);
                }
            });
        }
    }
}
