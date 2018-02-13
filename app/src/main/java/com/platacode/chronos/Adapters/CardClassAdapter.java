package com.platacode.chronos.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.platacode.chronos.Interfaces.SingleCollector;
import com.platacode.chronos.Models.Class;
import com.platacode.chronos.Models.College;
import com.platacode.chronos.Models.Day;
import com.platacode.chronos.Models.Room;
import com.platacode.chronos.Models.Subject;
import com.platacode.chronos.Models.Time;
import com.platacode.chronos.R;

import java.util.List;

public class CardClassAdapter extends RecyclerView.Adapter<CardClassAdapter.ViewHolder> {

    private Context mContext;
    private List<Class> classes;

    public CardClassAdapter(Context context, List<Class> classes) {
        mContext = context;
        this.classes = classes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_class, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Class mClass = classes.get(position);

        mClass.getSubject(new SingleCollector<Subject>() {
            @Override
            public void collect(Subject subject) {
                holder.subject.setText(subject.getName());
                holder.time.setText(Time.getTimeString(Integer.parseInt(mClass.getTime_id())));
                holder.day.setText(Day.getDayString(Integer.parseInt(mClass.getDay_id())));
                holder.units.setText(subject.getUnits() + " Units");
            }
        });

        mClass.getRoom(new SingleCollector<Room>() {
            @Override
            public void collect(final Room room) {
                room.getCollege(new SingleCollector<College>() {
                    @Override
                    public void collect(College college) {
                        holder.room.setText(college.getInitials() + " " + room.getNumber());
                    }
                });
            }
        });

        holder.btnViewClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView subject;
        protected TextView time;
        protected TextView day;
        protected TextView units;
        protected TextView room;
        protected Button btnViewClass;

        public ViewHolder(View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.subjectName);
            time = itemView.findViewById(R.id.txtTime);
            day = itemView.findViewById(R.id.txtDay);
            units = itemView.findViewById(R.id.txtUnits);
            room = itemView.findViewById(R.id.txtRoom);
            btnViewClass = itemView.findViewById(R.id.btnViewClass);
        }
    }
}
