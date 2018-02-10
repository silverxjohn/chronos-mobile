package com.platacode.chronos.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.platacode.chronos.Models.Class;
import com.platacode.chronos.Models.Student;
import com.platacode.chronos.Models.StudentWrapper;
import com.platacode.chronos.R;

import java.util.List;

public class StudentSelectionAdapter extends ArrayAdapter<StudentWrapper> {

    private List<StudentWrapper> students;
    private Context mContext;
    private Class mClass;

    public StudentSelectionAdapter(List<StudentWrapper> mStudents, Context context, Class mClass) {
        super(context, R.layout.list_item_checkbox, mStudents);

        this.mClass = mClass;
        this.students = mStudents;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_checkbox, null);

        final View v = convertView;

        StudentWrapper unwrap = students.get(position);

        TextView text1 = (TextView) convertView.findViewById(R.id.text1);
        TextView text2 = (TextView) convertView.findViewById(R.id.text2);

        text1.setText(unwrap.getStudent().getFirst_name() + " " + unwrap.getStudent().getLast_name());
        text2.setText(unwrap.getStudent().getId_number());

        CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
        checkbox.setOnCheckedChangeListener(null);
        checkbox.setChecked(unwrap.isSelected());
        checkbox.setOnCheckedChangeListener(onCheckedChangeListener(unwrap.getStudent()));

        return convertView;
    }

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener(final Student student) {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mClass.addStudent(student);
                } else {
                    mClass.removeStudent(student);
                }
            }
        };
    }
}
