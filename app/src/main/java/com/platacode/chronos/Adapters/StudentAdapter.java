package com.platacode.chronos.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.platacode.chronos.App;
import com.platacode.chronos.Models.Role;
import com.platacode.chronos.Models.Student;
import com.platacode.chronos.R;

import java.util.ArrayList;

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

        final View v = convertView;

        final Student student = students.get(position);
        TextView text1 = (TextView) convertView.findViewById(R.id.text1);
        text1.setText(student.getFirst_name() + " " + student.getLast_name());

        TextView text2 = (TextView) convertView.findViewById(R.id.text2);
        text2.setText(student.getId_number());

        LinearLayout item = (LinearLayout) convertView.findViewById(R.id.item_container);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        RelativeLayout edit = (RelativeLayout) convertView.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        RelativeLayout delete = (RelativeLayout) convertView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                student.delete();
                                Role.getRoleInstance().deleteRole(student.getStudent_id());
                                Toast.makeText(v.getContext(), App.getContext().getString(R.string.student_deleted), Toast.LENGTH_SHORT).show();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                            default:

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage(App.getContext().getString(R.string.student_delete_confirm))
                        .setPositiveButton(App.getContext().getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(App.getContext().getString(R.string.cancel), dialogClickListener)
                        .show();
            }
        });

        return convertView;
    }
}
