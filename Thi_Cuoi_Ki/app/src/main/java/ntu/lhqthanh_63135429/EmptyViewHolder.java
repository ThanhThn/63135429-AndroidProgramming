package ntu.lhqthanh_63135429;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class EmptyViewHolder extends RecyclerView.ViewHolder {
    View spacing;
    public EmptyViewHolder(@NonNull View itemView, int height, Context context) {
        super(itemView);
        spacing = itemView.findViewById(R.id.spacingView);
        int heightPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                height,
                context.getResources().getDisplayMetrics());
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) spacing.getLayoutParams();
        params.height = heightPx;
        spacing.setLayoutParams(params);
    }

    public View getSpacing() {
        return spacing;
    }

    public void setSpacing(View spacing) {
        this.spacing = spacing;
    }
}
