package com.platacode.chronos.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.platacode.chronos.App;
import com.platacode.chronos.EditTeacherActivity;
import com.platacode.chronos.Models.Role;
import com.platacode.chronos.Models.Teacher;
import com.platacode.chronos.R;

import java.util.ArrayList;

public class TeacherAdapter extends ListSwipableAdapter {

    private ArrayList<Teacher> teachers;

    public TeacherAdapter(Context context, ArrayList<Teacher> teachers) {
        super(context);
        this.teachers = teachers;
    }

    @Override
    void setDisplayItem(int position, TextView heading, TextView subheading) {
        Teacher teacher = teachers.get(position);

        heading.setText(teacher.getFirst_name() + " " + teacher.getLast_name());
        subheading.setText(teacher.getEmail());
    }

    @Override
    void onItemClicked(int position) {
        Teacher teacher = teachers.get(position);

        Intent intent = new Intent(getContext(), EditTeacherActivity.class);
        intent.putExtra(EditTeacherActivity.EXTRA_TEACHER_ID, teacher.getTeacher_id());

        getContext().startActivity(intent);
    }

    @Override
    void onEditActionButtonClicked(int position) {
        Teacher teacher = teachers.get(position);

        Intent intent = new Intent(getContext(), EditTeacherActivity.class);
        intent.putExtra(EditTeacherActivity.EXTRA_TEACHER_ID, teacher.getTeacher_id());

        getContext().startActivity(intent);
    }

    @Override
    void onDeleteActionButtonClicked(int position) {
        final Teacher teacher = teachers.get(position);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        teacher.delete();
                        Role.getRoleInstance().deleteRole(teacher.getTeacher_id());
                        Toast.makeText(getContext(), App.getContext().getString(R.string.teacher_deleted), Toast.LENGTH_SHORT).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                    default:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(App.getContext().getString(R.string.teacher_delete_confirm))
                .setPositiveButton(App.getContext().getString(R.string.yes), dialogClickListener)
                .setNegativeButton(App.getContext().getString(R.string.cancel), dialogClickListener)
                .show();
    }

    @Override
    public int getCount() {
        return teachers.size();
    }

    @Override
    public Object getItem(int position) {
        return teachers.get(position);
    }
}
