package com.platacode.chronos.Adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platacode.chronos.App;
import com.platacode.chronos.Models.Student;
import com.platacode.chronos.Models.StudentTimeWrapper;
import com.platacode.chronos.R;

import java.util.List;

public class StudentTimeAdapter extends RecyclerView.Adapter<StudentTimeAdapter.ViewHolder> {

    private List<StudentTimeWrapper> studentTimes;

    public StudentTimeAdapter(List<StudentTimeWrapper> studentTimes) {
        this.studentTimes = studentTimes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_student_time, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StudentTimeWrapper wrapper = studentTimes.get(position);
        Student student = wrapper.getStudent();

        holder.text1.setText(student.getFirst_name() + " " + student.getLast_name());
        holder.text2.setText(student.getId_number());
        holder.text3.setText(wrapper.isPresent() ? wrapper.getTime() : "Absent");
        holder.text3.setTextColor(wrapper.isPresent() ? ContextCompat.getColor(App.getContext(), R.color.colorBlack) : ContextCompat.getColor(App.getContext(), R.color.colorPrimary));
    }

    @Override
    public int getItemCount() {
        return studentTimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView text1;
        protected TextView text2;
        protected TextView text3;

        public ViewHolder(View itemView) {
            super(itemView);

            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
            text3 = itemView.findViewById(R.id.text3);
        }
    }
}
