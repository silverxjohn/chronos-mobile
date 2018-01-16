package com.platacode.chronos.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

public class TimeLineViewHolder extends RecyclerView.ViewHolder {
    TextView mDate;
    TextView mMessage;
    TimelineView mTimelineView;

    public TimeLineViewHolder(View itemView, int viewType) {
        super(itemView);

        mTimelineView.initLine(viewType);
    }
}
