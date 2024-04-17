package ntu.com.thuchanh_recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamHolder> {

    Context context;
    ArrayList<Exam> listExam;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy");

    public ExamAdapter(Context context, ArrayList<Exam> listExam) {
        this.context = context;
        this.listExam = listExam;
    }

    @NonNull
    @Override
    public ExamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewItem = inflater.inflate(R.layout.exam_item, parent, false);
        return new ExamHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamHolder holder, int position) {
        Exam examShow = listExam.get(position);
        String title = examShow.getTitleExam();
        String description = examShow.getDescriptionExam();
        String dateStart = simpleDateFormat.format(examShow.getDateStart());
        holder.title.setText(title);
        holder.description.setText(description);
        holder.dateStart.setText(dateStart);
    }

    @Override
    public int getItemCount() {
        return listExam.size();
    }

    class ExamHolder extends RecyclerView.ViewHolder{
        TextView title, description, dateStart;
        public ExamHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_exam);
            description = itemView.findViewById(R.id.description_exam);
            dateStart = itemView.findViewById(R.id.time_start);
        }
    }
}
