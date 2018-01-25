package com.platacode.chronos.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.platacode.chronos.Models.Class;
import com.platacode.chronos.R;

import java.util.List;

import moe.feng.common.stepperview.IStepperAdapter;
import moe.feng.common.stepperview.VerticalStepperItemView;

public class ClassStepperAdapter implements IStepperAdapter {
    List<Class> classes;

    @NonNull
    @Override
    public CharSequence getTitle(int i) {
        return "Test Title";
    }

    @Nullable
    @Override
    public CharSequence getSummary(int i) {
        return "Test Summary";
    }

    @Override
    public int size() {
        return 3;
//        return classes.size();
    }

    @Override
    public View onCreateCustomView(int i, Context context, VerticalStepperItemView verticalStepperItemView) {

        verticalStepperItemView.setAlwaysShowSummary(true);

        View inflateView = LayoutInflater.from(context).inflate(R.layout.vertical_stepper_item_view_layout, verticalStepperItemView, false);

        return inflateView;
    }

    @Override
    public void onShow(int i) {

    }

    @Override
    public void onHide(int i) {

    }
}
