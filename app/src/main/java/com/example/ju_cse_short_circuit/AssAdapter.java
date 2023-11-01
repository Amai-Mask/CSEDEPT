package com.example.ju_cse_short_circuit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssAdapter extends RecyclerView.Adapter<AssAdapter.AssViewHolder> {

    private Context context;
    private List<Ass> asslist;

    public AssAdapter(Context context, List<Ass> asslist) {
        this.context = context;
        this.asslist = asslist;
    }

    @NonNull
    @Override
    public AssViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ass_item, parent, false);
        return new AssViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssViewHolder holder, int position) {
        Ass notice = asslist.get(position);
        holder.idTextView.setText("Serial No : "+(position+1));
        holder.nameTextView.setText("Exam Name : "+notice.getDetails());
        holder.courseTextView.setText("Course Name : "+notice.getCourse());
        holder.dateTextView.setText("Time & Date  : "+notice.getDue());
    }

    @Override
    public int getItemCount() {
        return asslist.size();
    }

    public static class AssViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, courseTextView, dateTextView, idTextView;

        public AssViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.categoryTextView);
            courseTextView = itemView.findViewById(R.id.detailsTextView);
            dateTextView = itemView.findViewById(R.id.uploaderEmailTextView);
            idTextView=itemView.findViewById(R.id.indexTextView);
        }
    }
}
