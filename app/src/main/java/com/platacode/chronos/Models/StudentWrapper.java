package com.platacode.chronos.Models;

public class StudentWrapper {
    private Student mStudent;
    private boolean isSelected;

    public StudentWrapper(Student student, boolean isSelected) {
        mStudent = student;
        this.isSelected = isSelected;
    }

    public Student getStudent() {
        return mStudent;
    }

    public void setStudent(Student student) {
        mStudent = student;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
