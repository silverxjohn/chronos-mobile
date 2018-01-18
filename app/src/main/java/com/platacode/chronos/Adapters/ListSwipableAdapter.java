package com.platacode.chronos.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.platacode.chronos.R;

public abstract class ListSwipableAdapter extends BaseAdapter {

    private Context context;

    public ListSwipableAdapter(Context context) {
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_2, null);

        final View v = convertView;

        TextView text1 = (TextView) convertView.findViewById(R.id.text1);
        TextView text2 = (TextView) convertView.findViewById(R.id.text2);

        setDisplayItem(position, text1, text2);

        LinearLayout item = (LinearLayout) convertView.findViewById(R.id.item_container);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                onItemClicked(position);
            }
        });

        RelativeLayout edit = (RelativeLayout) convertView.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditActionButtonClicked(position);
            }
        });

        RelativeLayout delete = (RelativeLayout) convertView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                onDeleteActionButtonClicked(position);
            }
        });

        return convertView;
    }

    protected Context getContext() {
        return context;
    }

    abstract void setDisplayItem(int position, TextView heading, TextView subheading);

    abstract void onItemClicked(int position);

    abstract void onEditActionButtonClicked(int position);

    abstract void onDeleteActionButtonClicked(int position);
}
