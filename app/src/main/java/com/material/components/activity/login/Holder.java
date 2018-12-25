package com.material.components.activity.login;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.material.components.R;

public class Holder extends RecyclerView.ViewHolder {
    public TextView text;
    public Holder(View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.et_search);
    }
}
