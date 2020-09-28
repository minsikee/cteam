package com.example.cteam.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cteam.Dto.PetPhotoDTO;
import com.example.cteam.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.cteam.Login.loginDTO;
import static com.example.cteam.PetPhoto.selectPetPhoto;


public class PetPhotoAdapter extends RecyclerView.Adapter<PetPhotoAdapter.ItemViewHolder>{
    private static final String TAG = "MyRecyclerviewAdapter";

    Context mContext;
    ArrayList<PetPhotoDTO> petPhotos;

    public PetPhotoAdapter(Context mContext, ArrayList<PetPhotoDTO> petPhotos) {
        this.mContext = mContext;
        this.petPhotos = petPhotos;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.petphotoitem_view, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        Log.d("main:adater", "" + position);

        PetPhotoDTO petPhoto = petPhotos.get(position);
        holder.setItem(petPhoto);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + position);

                selectPetPhoto = petPhotos.get(position);

              //  Toast.makeText(mContext, "Onclick " + arrayList.get(position).getId(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return petPhotos.size();
    }


    // 어댑터에 매소드 만들기

    // 리사이클러뷰 내용 모두 지우기
    public void removeAllItem(){
        petPhotos.clear();
    }

    // 특정 인덱스 항목 가져오기
    public PetPhotoDTO getItem(int position) {
        return petPhotos.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, PetPhotoDTO petPhoto){
        petPhotos.set(position, petPhoto);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<PetPhotoDTO> petPhotos){
        this.petPhotos = petPhotos;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout parentLayout;
        public TextView petPhoto_petName;
        public ImageView petPhoto_profile;
        public TextView petPhoto_date;
        public ImageView petPhoto_image;
        public TextView petPhoto_content;
        public ProgressBar progressBar;

        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            petPhoto_petName = itemView.findViewById(R.id.petPhoto_petName);
            petPhoto_date = itemView.findViewById(R.id.petPhoto_date);
            petPhoto_profile = itemView.findViewById(R.id.petPhoto_profile);
            petPhoto_image = itemView.findViewById(R.id.petPhoto_image);
            petPhoto_content=itemView.findViewById(R.id.petPhoto_content);
            progressBar = itemView.findViewById(R.id.progressBar);

        }

        public void setItem(PetPhotoDTO petPhoto){
            if(petPhoto!=null){
                Log.d("yyyy", "setItem: d"+petPhoto.getPetPhoto_content());
            }
           // id.setText(item.getId());
            petPhoto_petName.setText(petPhoto.getPetName());
            petPhoto_date.setText(petPhoto.getPetPhoto_date());
            petPhoto_content.setText(petPhoto.getPetPhoto_content()
            );

            Glide.with(itemView).load(petPhoto.getPetPhoto_imgpath()).into(petPhoto_image);
        }
    }

}



