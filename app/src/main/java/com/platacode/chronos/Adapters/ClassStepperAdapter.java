package com.platacode.chronos.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.platacode.chronos.Models.Class;
import com.platacode.chronos.R;

import java.util.List;

import moe.feng.common.stepperview.IStepperAdapter;
import moe.feng.common.stepperview.VerticalStepperItemView;

public class ClassStepperAdapter implements IStepperAdapter {
    List<Class.ClassCache> classes;

    public ClassStepperAdapter(List<Class.ClassCache> classes) {
        this.classes = classes;
    }

    @NonNull
    @Override
    public CharSequence getTitle(int i) {
        Class.ClassCache classCache = classes.get(i);

        return classCache.getClasss().getSubject_name();
    }

    @Nullable
    @Override
    public CharSequence getSummary(int i) {
        Class.ClassCache classCache = classes.get(i);

        return classCache.getTime();
    }

    @Override
    public int size() {
        return classes.size();
    }

    @Override
    public View onCreateCustomView(int i, Context context, VerticalStepperItemView verticalStepperItemView) {

        verticalStepperItemView.setAlwaysShowSummary(true);

        verticalStepperItemView.setActivatedColor(ContextCompat.getColor(context, R.color.colorPrimary));
        verticalStepperItemView.setNormalColor(ContextCompat.getColor(context, R.color.colorGray));

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
