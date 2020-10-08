package com.example.cteam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cteam.Dto.CommentDTO;
import com.example.cteam.Dto.PetDTO;
import com.example.cteam.Login;
import com.example.cteam.R;
import com.example.cteam.board.BoardDetail;

import java.util.ArrayList;

import static com.example.cteam.Login.loginDTO;
import static com.example.cteam.board.BoardDetail.commentDTO;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ItemViewHolder> {
    Context context;
    ArrayList<CommentDTO> commentList;
    Button btnCommentUpdate, btnCommentDelete;

    public CommentAdapter(Context context, ArrayList<CommentDTO> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.comment_list,parent,false);

        btnCommentUpdate = itemView.findViewById(R.id.CommentUpdate);
        btnCommentDelete = itemView.findViewById(R.id.CommentDelete);

        return new CommentAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ItemViewHolder holder, int position) {
        CommentDTO dto = commentList.get(position);

        if(dto.getMember_id().equals(loginDTO.getMember_id())){
            btnCommentUpdate.setVisibility(View.VISIBLE);
            btnCommentDelete.setVisibility(View.VISIBLE);
        }else{
            btnCommentUpdate.setVisibility(View.GONE);
            btnCommentDelete.setVisibility(View.GONE);
        }

        holder.setComment(dto);


/*
        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petAddDto = petList.get(position);
                Log.d("main:petaddadapter", "onClick: " + petAddDto.getPetname());
                Intent intent = new Intent(context, petUpdate.class);
                context.startActivity(intent);
            }
        });
/*
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dto != null) {
                    String member_id = loginDTO.getMember_id();
                    String petname = dto.getPetname();
                    ListDelete listDelete = new ListDelete(member_id, petname);
                    listDelete.execute();

                    Intent refresh = new Intent(context, PetAdd.class);
                    context.startActivity(refresh);
                }else {
                    Log.d("TAG", "onClick: "+dto.getMember_id());
                }
            }
        });*/

    }
    // 어댑터에 매소드 만들기

    // 리사이클러뷰 내용 모두 지우기
    public void removeAllItem(){
        commentList.clear();
    }

    // 특정 인덱스 항목 가져오기
    public CommentDTO dto(int position) {
        return commentList.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, CommentDTO dto){
        commentList.set(position, dto);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<CommentDTO> commentList){
        this.commentList = commentList;
    }
    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        public RelativeLayout CommentParentLayout;
        public TextView comment_id, comment_date, TVcomment;
        public Button CommentUpdate, CommentDelete;
        public ImageView comment_writer_img;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            CommentParentLayout = itemView.findViewById(R.id.CommentParentLayout);
            comment_id = itemView.findViewById(R.id.comment_id);
            comment_date = itemView.findViewById(R.id.comment_date);
            TVcomment = itemView.findViewById(R.id.TVcomment);
            CommentUpdate = itemView.findViewById(R.id.CommentUpdate);
            CommentDelete = itemView.findViewById(R.id.CommentDelete);
            comment_writer_img = itemView.findViewById(R.id.comment_writer_img);
        }

        public void setComment(CommentDTO dto){
            comment_id.setText(dto.getMember_id());
            comment_date.setText(dto.getWritedate());
            TVcomment.setText(dto.getContent());

            Glide.with(itemView).load(dto.getWriter_image()).into(comment_writer_img);
        }
    }
}

