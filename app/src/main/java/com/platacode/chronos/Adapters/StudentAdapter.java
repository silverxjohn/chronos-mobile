package com.platacode.chronos.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.platacode.chronos.App;
import com.platacode.chronos.EditStudentActivity;
import com.platacode.chronos.Models.Role;
import com.platacode.chronos.Models.Student;
import com.platacode.chronos.R;

import java.util.ArrayList;

public class StudentAdapter extends ListSwipableAdapter {
    private ArrayList<Student> students;

    public StudentAdapter(Context context, ArrayList<Student> students) {
        super(context);
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
    void setDisplayItem(int position, TextView heading, TextView subheading) {
        Student student = students.get(position);

        heading.setText(student.getFirst_name() + " " + student.getLast_name());
        subheading.setText(student.getId_number());
    }

    @Override
    void onItemClicked(int position) {
        Student student = students.get(position);

        Intent intent = new Intent(getContext(), EditStudentActivity.class);
        intent.putExtra(EditStudentActivity.EXTRA_STUDENT_ID, student.getStudent_id());

        getContext().startActivity(intent);
    }

    @Override
    void onEditActionButtonClicked(int position) {
        Student student = students.get(position);

        Intent intent = new Intent(getContext(), EditStudentActivity.class);
        intent.putExtra(EditStudentActivity.EXTRA_STUDENT_ID, student.getStudent_id());

        getContext().startActivity(intent);
    }

    @Override
    void onDeleteActionButtonClicked(int position) {
        final Student student = students.get(position);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        student.delete();
                        Role.getRoleInstance().deleteRole(student.getStudent_id());
                        Toast.makeText(getContext(), App.getContext().getString(R.string.student_deleted), Toast.LENGTH_SHORT).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                    default:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(App.getContext().getString(R.string.student_delete_confirm))
                .setPositiveButton(App.getContext().getString(R.string.yes), dialogClickListener)
                .setNegativeButton(App.getContext().getString(R.string.cancel), dialogClickListener)
                .show();
    }
}
