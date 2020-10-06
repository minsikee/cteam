package com.example.cteam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cteam.Dto.BoardDTO;
import com.example.cteam.Login;
import com.example.cteam.R;
import com.example.cteam.board.BoardDetail;
import com.example.cteam.board.WalkBoard;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    Context mcontext;
    ArrayList<BoardDTO> list;

    public BoardAdapter(Context mcontext, ArrayList<BoardDTO> list) {
        this.mcontext = mcontext;
        this.list = list;

    }

    public BoardAdapter(ArrayList<BoardDTO> list) {
        this.list = list;
    }

    // ViewHolder 생성
    // row layout을 화면에 뿌려주고 holder에 연결
    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.board_list_item, parent, false);
        BoardViewHolder holder = new BoardViewHolder(itemView);
        return holder;
//        return new BoardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.board_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder,final int position) {
        BoardDTO dto = list.get(position);
        holder.setBoard(dto);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BoardViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout parentLayout;
        public TextView msubject;
        public TextView mtitle;
        public TextView mid;
        public TextView mcity;
        public TextView mdate;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);

            //클릭했을때 Detail로 변경
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(mcontext, BoardDetail.class);
                        String member_id = Login.loginDTO.getMember_id();
                        String num = list.get(position).getNum();
                        intent.putExtra("member_id", member_id);
                        intent.putExtra("num", num);
                        Log.d("member_id", "onClick: member_"+member_id);
                        Log.d("num", "onClick: num"+num);
                        mcontext.startActivity(intent);
                    }
                }
            });

            parentLayout = itemView.findViewById(R.id.parentLayout);
            msubject = itemView.findViewById(R.id.msubject);
            mtitle = itemView.findViewById(R.id.mtitle);
            mid = itemView.findViewById(R.id.mid);
            mcity = itemView.findViewById(R.id.mcity);
            mdate = itemView.findViewById(R.id.mdate);
        }

        public void setBoard(BoardDTO dto) {
            msubject.setText(dto.getSubject());
            mtitle.setText(dto.getTitle());
            mid.setText(dto.getId());
            mcity.setText(dto.getCity()+ " " +dto.getRegion());
            mdate.setText(dto.getDate());
        }

    }

}
