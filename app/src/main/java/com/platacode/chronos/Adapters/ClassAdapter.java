package com.platacode.chronos.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.platacode.chronos.App;
import com.platacode.chronos.EditClassActivity;
import com.platacode.chronos.Interfaces.SingleCollector;
import com.platacode.chronos.Models.Class;
import com.platacode.chronos.Models.Subject;
import com.platacode.chronos.Models.Teacher;
import com.platacode.chronos.R;

import java.util.List;

public class ClassAdapter extends ListSwipableAdapter {
    private List<Class> classess;

    public ClassAdapter(Context context, List<Class> classes) {
        super(context);
        this.classess = classes;
    }

    @Override
    public int getCount() {
        return classess.size();
    }

    @Override
    public Object getItem(int position) {
        return classess.get(position);
    }

    @Override
    void setDisplayItem(int position, final TextView heading, final TextView subheading) {
        Class mclass = classess.get(position);

        mclass.getSubject(new SingleCollector<Subject>() {
            @Override
            public void collect(Subject subject) {
                heading.setText(subject.getName());
            }
        });

        mclass.getTeacher(new SingleCollector<Teacher>() {
            @Override
            public void collect(Teacher teacher) {
                subheading.setText(teacher.toString());
            }
        });
    }

    @Override
    void onItemClicked(int position) {
        Class mclass = classess.get(position);

        Intent intent = new Intent(getContext(), EditClassActivity.class);
        intent.putExtra(EditClassActivity.EXTRA_CLASS_ID, mclass.getClass_id());

        getContext().startActivity(intent);
    }

    @Override
    void onEditActionButtonClicked(int position) {
        Class mclass = classess.get(position);

        Intent intent = new Intent(getContext(), EditClassActivity.class);
        intent.putExtra(EditClassActivity.EXTRA_CLASS_ID, mclass.getClass_id());

        getContext().startActivity(intent);
    }

    @Override
    void onDeleteActionButtonClicked(int position) {
        final Class mclass = classess.get(position);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        mclass.delete();
                        Toast.makeText(getContext(), App.getContext().getString(R.string.class_deleted), Toast.LENGTH_SHORT).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                    default:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(App.getContext().getString(R.string.class_delete_confirm))
                .setPositiveButton(App.getContext().getString(R.string.yes), dialogClickListener)
                .setNegativeButton(App.getContext().getString(R.string.cancel), dialogClickListener)
                .show();
    }
}
