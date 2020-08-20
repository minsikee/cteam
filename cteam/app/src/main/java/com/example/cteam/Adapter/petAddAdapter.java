package com.example.cteam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cteam.Dto.MyItem;
import com.example.cteam.R;

import java.util.ArrayList;

public class petAddAdapter extends BaseAdapter {

    Context context;
    ArrayList<MyItem> dtos;

    LayoutInflater inflater;

    public petAddAdapter(Context context, ArrayList<MyItem> dtos) {
        this.context = context;
        this.dtos = dtos;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void addDto(MyItem dto){
        dtos.add(dto);
    }

    public void removeDtos(){
        dtos.clear();
    }

    @Override
    public int getCount() {
        return dtos.size();
    }

    @Override
    public Object getItem(int position) {
        return dtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        PetAddViewHolder viewHolder;

        if(view == null){
            view = inflater.inflate(R.layout.petlistviewcard,
                    viewGroup, false);
            viewHolder = new PetAddViewHolder();
            viewHolder.TV_Name = view.findViewById(R.id.TV_Name);
            viewHolder.TV_Age = view.findViewById(R.id.TV_Age);
            viewHolder.tv_weigth = view.findViewById(R.id.tv_weigth);
            viewHolder.imageIcon = view.findViewById(R.id.imageIcon);
            viewHolder.TV_Gender = view.findViewById(R.id.TV_Gender);
            viewHolder.UpdateBtn = view.findViewById(R.id.UpdateBtn);
            viewHolder.signBtn = view.findViewById(R.id.signBtn);

            view.setTag(viewHolder);
        }else{
            viewHolder = (PetAddViewHolder) view.getTag();
        }

        MyItem dto = dtos.get(position);
        String name = dto.getName();
        int age = dto.getAge();
        int weigth = dto.getWeight();
        String gender = dto.getGender();
        int resId = dto.getImage_path();

        viewHolder.TV_Name.setText(name);
        viewHolder.TV_Gender.setText(gender);
        viewHolder.TV_Age.setText(age);
        viewHolder.tv_weigth.setText(weigth);
        viewHolder.imageIcon.setImageResource(resId);

        viewHolder.imageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"선택 :" + position
                + ", 이름 :" + dtos.get(position).getName(), Toast.LENGTH_SHORT);
            }
        });



        return view;
    }




    public class PetAddViewHolder{
        public ImageView imageIcon;
        public TextView TV_Name,TV_Age,tv_weigth,TV_Gender;
        public Button UpdateBtn,signBtn;

    }
}
