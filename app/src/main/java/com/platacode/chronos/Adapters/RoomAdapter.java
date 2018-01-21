package com.platacode.chronos.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.platacode.chronos.App;
import com.platacode.chronos.EditRoomActivity;
import com.platacode.chronos.Models.College;
import com.platacode.chronos.Models.Room;
import com.platacode.chronos.R;

import java.util.List;
import java.util.Map;

public class RoomAdapter extends ListSwipableAdapter {

    private List<Room> rooms;
    private Map<Integer, College> colleges;

    public RoomAdapter(Context context, List<Room> rooms, Map<Integer, College> colleges) {
        super(context);
        this.rooms = rooms;
        this.colleges = colleges;
    }

    @Override
    void setDisplayItem(int position, TextView heading, TextView subheading) {
        Room room = rooms.get(position);

        int id = Integer.valueOf(room.getCollege_id());
        College college = colleges.get(id);

        heading.setText(college.getInitials() + " " + room.getNumber());
        subheading.setText(college.getName());
    }

    @Override
    void onItemClicked(int position) {
        Room room = rooms.get(position);

        Intent intent = new Intent(getContext(), EditRoomActivity.class);
        intent.putExtra(EditRoomActivity.EXTRA_ROOM_ID, room.getRoom_id());

        getContext().startActivity(intent);
    }

    @Override
    void onEditActionButtonClicked(int position) {
        Room room = rooms.get(position);

        Intent intent = new Intent(getContext(), EditRoomActivity.class);
        intent.putExtra(EditRoomActivity.EXTRA_ROOM_ID, room.getRoom_id());

        getContext().startActivity(intent);
    }

    @Override
    void onDeleteActionButtonClicked(int position) {
        final Room room = rooms.get(position);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        room.delete();
                        Toast.makeText(getContext(), App.getContext().getString(R.string.room_deleted), Toast.LENGTH_SHORT).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                    default:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(App.getContext().getString(R.string.room_delete_confirm))
                .setPositiveButton(App.getContext().getString(R.string.yes), dialogClickListener)
                .setNegativeButton(App.getContext().getString(R.string.cancel), dialogClickListener)
                .show();
    }

    @Override
    public int getCount() {
        return rooms.size();
    }

    @Override
    public Object getItem(int position) {
        return rooms.get(position);
    }
}
