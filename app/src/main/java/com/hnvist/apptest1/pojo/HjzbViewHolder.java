package com.hnvist.apptest1.pojo;

import android.widget.LinearLayout;
import android.widget.TextView;

public class HjzbViewHolder {
    private LinearLayout bgClick;
    private TextView number;
    private TextView state;

    public LinearLayout getBgClick() {
        return bgClick;
    }

    public void setBgClick(LinearLayout bgClick) {
        this.bgClick = bgClick;
    }

    public TextView getNumber() {
        return number;
    }

    public void setNumber(TextView number) {
        this.number = number;
    }

    public TextView getState() {
        return state;
    }

    public void setState(TextView state) {
        this.state = state;
    }
}
