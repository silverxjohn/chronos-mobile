package com.platacode.chronos.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class TimeLineModel implements Parcelable {
    private String mMessage;
    private String mDate;
    private OrderStatus mStatus;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public OrderStatus getStatus() {
        return mStatus;
    }

    public void setStatus(OrderStatus status) {
        mStatus = status;
    }

    public TimeLineModel(String message, String date, OrderStatus status) {

        mMessage = message;
        mDate = date;
        mStatus = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getMessage());
        dest.writeString(this.getDate());
        dest.writeInt(this.getStatus() == null ? -1 : this.getStatus().ordinal());
    }

    protected TimeLineModel(Parcel in) {
        this.setMessage(in.readString());
        this.setDate(in.readString());
        int tmpStatus = in.readInt();
        this.setStatus(tmpStatus == -1 ? null : OrderStatus.values()[tmpStatus]);
    }

    public static final Parcelable.Creator<TimeLineModel> CREATOR = new Parcelable.Creator<TimeLineModel>() {

        @Override
        public TimeLineModel createFromParcel(Parcel source) {
            return new TimeLineModel(source);
        }

        @Override
        public TimeLineModel[] newArray(int size) {
            return new TimeLineModel[size];
        }
    };
}
