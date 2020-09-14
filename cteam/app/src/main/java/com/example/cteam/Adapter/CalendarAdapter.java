package com.example.cteam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cteam.Dto.CalendarDTO;
import com.example.cteam.R;

import java.util.ArrayList;

import static com.example.cteam.CalendarAdd.selectIcon;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.IconViewHolder> {
    private static final String TAG = "calendarAdapter";

    Context context;
    ArrayList<CalendarDTO> icons;

    public CalendarAdapter(Context context, ArrayList<CalendarDTO> icons) {
        this.context = context;
        this.icons = icons;
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View iconView = inflater.inflate(R.layout.fragment_calendar_add_view, parent, false);

        return new IconViewHolder(iconView);

    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, final int position) {

        CalendarDTO icon = icons.get(position);
        holder.setIcon(icon);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIcon = icons.get(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return icons.size();
    }

    // 어댑터에 매소드 만들기

    // 리사이클러뷰 내용 모두 지우기
    public void removeAllItem(){
        icons.clear();
    }

    // 특정 인덱스 항목 가져오기
    public CalendarDTO getIcon(int position) {
        return icons.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setIcon(int position, CalendarDTO icon){
        icons.set(position, icon);
    }

    // 동적으로 인덱스 항목 추가
    public void addIcon(CalendarDTO icon) {
        icons.add(icon);
    }

    // arrayList 통째로 셋팅하기
    public void setIcon(ArrayList<CalendarDTO> arrayList){
        this.icons = arrayList;
    }

    public static class IconViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout parentLayout;
        public ImageView c_icon;
        //public TextView c_icon;
        public TextView c_memo;

        public IconViewHolder(@NonNull final View iconView) {
            super(iconView);

            parentLayout = iconView.findViewById(R.id.parentLayout);
            c_icon = iconView.findViewById(R.id.CalendarAdd_icon);
            c_memo = iconView.findViewById(R.id.CalendarAdd_memo);
        }

        public void setIcon(CalendarDTO icon) {
            c_icon.setImageResource(Integer.parseInt(icon.getCalendar_icon()));
            //c_icon.setText(icon.getCalendar_icon());
            c_memo.setText(icon.getCalendar_memo());
        }

    }
}
