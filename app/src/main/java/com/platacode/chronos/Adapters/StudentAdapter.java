package com.platacode.chronos.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.platacode.chronos.Models.Student;
import com.platacode.chronos.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by John on 16/01/2018.
 */

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Student> students;

    public StudentAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecyclerView.ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_2, null);
        } else {
            holder = (RecyclerView.ViewHolder) convertView.getTag();
        }

        Student student = students.get(position);
        TextView text1 = (TextView) convertView.findViewById(R.id.text1);
        text1.setText(student.getFirst_name() + " " + student.getLast_name());

        TextView text2 = (TextView) convertView.findViewById(R.id.text2);
        text2.setText(student.getId_number());

        return convertView;
    }
}
