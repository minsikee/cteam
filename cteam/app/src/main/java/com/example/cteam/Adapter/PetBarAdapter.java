package com.example.cteam.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cteam.Dto.PetBarItem;
import com.example.cteam.R;

import java.util.ArrayList;

import static com.example.cteam.PetBar.selItem;

public class PetBarAdapter extends RecyclerView.Adapter<PetBarAdapter.ItemViewHolder>{
    private static final String TAG = "MyRecyclerviewAdapter";

    Context mContext;
    ArrayList<PetBarItem> arrayList;

    public PetBarAdapter(Context mContext, ArrayList<PetBarItem> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        arrayList.clear();
    }




    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.petbar_item_view, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        Log.d("main:adater", "" + position);


        PetBarItem item = arrayList.get(position);
        holder.setItem(item);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + position);

                selItem = arrayList.get(position);

                Toast.makeText(mContext, "Onclick " + arrayList.get(position).getHour(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    // 어댑터에 매소드 만들기

    // 리사이클러뷰 내용 모두 지우기
    public void removeAllItem(){
        arrayList.clear();
    }

    // 특정 인덱스 항목 가져오기
    public PetBarItem getItem(int position) {
        return arrayList.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, PetBarItem item){
        arrayList.set(position, item);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<PetBarItem> arrayList){
        this.arrayList = arrayList;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout parentLayout;
        public TextView memo;
        public TextView time;
        public Button icon;
        public ProgressBar progressBar;

        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            memo = itemView.findViewById(R.id.piv_tv_memo);
            time = itemView.findViewById(R.id.piv_tv_time);
            icon = itemView.findViewById(R.id.piv_img);
            progressBar = itemView.findViewById(R.id.progressBar);

        }

        public void setItem(PetBarItem item){

            memo.setText(item.getMemo());

            String res=item.getIcon();
            if(!res.equals("null")){
              //  icon.setBackgroundResource(Integer.parseInt(res.trim()));

            }

            if(item.getIcon()!=""&&item.getIcon()!=""){

                if(Integer.parseInt(item.getIcon().trim())==1&&Integer.parseInt(item.getIcon().trim())==11&&Integer.parseInt(item.getIcon().trim())==21&&
                        Integer.parseInt(item.getIcon().trim())==31) {
                    icon.setBackgroundResource(R.drawable.sample_icon_1);
                }else if(Integer.parseInt(item.getIcon().trim())==2){
                    icon.setBackgroundResource(R.drawable.sample_icon_2);
                }else if(Integer.parseInt(item.getIcon().trim())==3) {
                    icon.setBackgroundResource(R.drawable.sample_icon_3);
                }else if(Integer.parseInt(item.getIcon().trim())==4) {
                    icon.setBackgroundResource(R.drawable.sample_icon_4);
                }else if(Integer.parseInt(item.getIcon().trim())==5) {
                    icon.setBackgroundResource(R.drawable.sample_icon_5);
                }else if(Integer.parseInt(item.getIcon().trim())==6) {
                    icon.setBackgroundResource(R.drawable.sample_icon_6);
                }else if(Integer.parseInt(item.getIcon().trim())==7) {
                    icon.setBackgroundResource(R.drawable.sample_icon_7);
                } else if(Integer.parseInt(item.getIcon().trim())==8) {
                    icon.setBackgroundResource(R.drawable.sample_icon_8);
                }
            }

            if(item.getHour()!=""&&item.getMinute()!="") {
                time.setText(item.getHour() + "시" + item.getMinute() + "분");
            }
           // Glide.with(itemView).load(item.getIcon()).into(icon);
        }
    }

}



