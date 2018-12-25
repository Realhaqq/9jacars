package com.material.components.activity.login;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.material.components.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MemberAdapter extends BaseAdapter
{

    ArrayList<MemberList> itemsList;
    Context context;
    DrawerItemHolder drawerHolder;

    public MemberAdapter(Context context,ArrayList<MemberList> itemsList) {
        this.itemsList = itemsList;
        this.context=context;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            if (view == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                drawerHolder = new DrawerItemHolder();
                view = inflater.inflate(R.layout.cars_items,
                        viewGroup, false);

                drawerHolder.name = (TextView) view.findViewById(R.id.name);
                drawerHolder.worth = (TextView) view.findViewById(R.id.worth);
                drawerHolder.imgIcon = (ImageView) view.findViewById(R.id.image);
                drawerHolder.inyear = (TextView) view.findViewById(R.id.year);
                drawerHolder.source = (TextView) view.findViewById(R.id.source);


                drawerHolder.name.setText(itemsList.get(i).getName());
                drawerHolder.source.setText(itemsList.get(i).getSource());
                drawerHolder.worth.setText(itemsList.get(i).getWorth());
                drawerHolder.inyear.setText(itemsList.get(i).getInyear());
                Picasso.with(context)
                        .load(itemsList.get(i).getImage())
                        .resize(40, 40)                        // optional
                        .into(drawerHolder.imgIcon);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return view;
    }

    private class DrawerItemHolder
    {
        TextView name;
        TextView source;
        TextView inyear;
        TextView worth;
        ImageView imgIcon;

    }
}
