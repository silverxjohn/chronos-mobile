package com.platacode.chronos.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.platacode.chronos.App;
import com.platacode.chronos.EditSubjectActivity;
import com.platacode.chronos.Models.Subject;
import com.platacode.chronos.R;

import java.util.List;

public class SubjectAdapter extends ListSwipableAdapter {

    private List<Subject> subjects;

    public SubjectAdapter(Context context, List<Subject> subjects) {
        super(context);
        this.subjects = subjects;
    }

    @Override
    void setDisplayItem(int position, TextView heading, TextView subheading) {
        Subject subject = subjects.get(position);
        int units = Integer.valueOf(subject.getUnits());

        heading.setText(subject.getName());
        subheading.setText(subject.getUnits() + (units > 1 ? " units" : " unit"));
    }

    @Override
    void onItemClicked(int position) {
        Subject subject = subjects.get(position);

        Intent intent = new Intent(getContext(), EditSubjectActivity.class);
        intent.putExtra(EditSubjectActivity.EXTRA_SUBJECT_ID, subject.getSubject_id());

        getContext().startActivity(intent);
    }

    @Override
    void onEditActionButtonClicked(int position) {
        Subject subject = subjects.get(position);

        Intent intent = new Intent(getContext(), EditSubjectActivity.class);
        intent.putExtra(EditSubjectActivity.EXTRA_SUBJECT_ID, subject.getSubject_id());

        getContext().startActivity(intent);
    }

    @Override
    void onDeleteActionButtonClicked(int position) {
        final Subject subject = subjects.get(position);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        subject.delete();
                        Toast.makeText(getContext(), App.getContext().getString(R.string.subject_deleted), Toast.LENGTH_SHORT).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                    default:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(App.getContext().getString(R.string.subject_delete_confirm))
                .setPositiveButton(App.getContext().getString(R.string.yes), dialogClickListener)
                .setNegativeButton(App.getContext().getString(R.string.cancel), dialogClickListener)
                .show();
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int position) {
        return subjects.get(position);
    }
}
