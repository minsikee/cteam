package com.example.cteam.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class petAddAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }


    public class petAddViewHolder{
        public ImageView imageIcon;
        public TextView TV_Name,TV_Age,TV_Weigth,TV_Gender;
        public Button UpdateBtn,signBtn;

    }
}
