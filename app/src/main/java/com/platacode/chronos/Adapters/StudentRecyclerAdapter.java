package com.platacode.chronos.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platacode.chronos.Models.Student;
import com.platacode.chronos.R;

import java.util.List;

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.ViewHolder> {

    private List<Student> students;
    private Context mContext;

    public StudentRecyclerAdapter(List<Student> students, Context context) {
        this.students = students;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_2, null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = students.get(position);

        holder.heading.setText(student.getFirst_name() + " " + student.getLast_name());
        holder.subHeading.setText(student.getId_number());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView heading;
        protected TextView subHeading;

        public ViewHolder(View view) {
            super(view);

            this.heading = (TextView) view.findViewById(R.id.text1);
            this.subHeading = (TextView) view.findViewById(R.id.text2);

        }
    }
}
