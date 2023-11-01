package com.example.ju_cse_short_circuit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {

    private Context context;
    private List<Exam> examlist;

    public ExamAdapter(Context context, List<Exam> examlist) {
        this.context = context;
        this.examlist = examlist;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exam_item, parent, false);
        return new ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Exam notice = examlist.get(position);
        holder.idTextView.setText("Serial No : "+(position+1));
        holder.nameTextView.setText("Exam Name : "+notice.getName());
        holder.courseTextView.setText("Course Name : "+notice.getCourse());
        holder.dateTextView.setText("Time & Date  : "+notice.getDate());
    }

    @Override
    public int getItemCount() {
        return examlist.size();
    }

    public static class ExamViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, courseTextView, dateTextView, idTextView;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.categoryTextView);
            courseTextView = itemView.findViewById(R.id.detailsTextView);
            dateTextView = itemView.findViewById(R.id.uploaderEmailTextView);
            idTextView=itemView.findViewById(R.id.indexTextView);
        }
    }
}
