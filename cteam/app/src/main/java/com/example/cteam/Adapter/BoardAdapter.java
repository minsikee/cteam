package com.example.cteam.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cteam.Dto.BoardDTO;
import com.example.cteam.R;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    ArrayList<BoardDTO> list;

    public BoardAdapter(ArrayList<BoardDTO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.board_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        BoardDTO dto = list.get(position);
        holder.setBoard(dto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BoardViewHolder extends RecyclerView.ViewHolder {

        private TextView mtitle;
        private TextView mid;
        private TextView mdate;
        private TextView mcomment;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);

            mtitle = itemView.findViewById(R.id.mtitle);
            mid = itemView.findViewById(R.id.mid);
            mdate = itemView.findViewById(R.id.mdate);
            mcomment = itemView.findViewById(R.id.mcomment);
        }

        void setBoard(BoardDTO dto) {
            mtitle.setText(dto.getTitle());
            mid.setText(dto.getId());
            mdate.setText(dto.getDate());
            mcomment.setText(dto.getComment());
        }

    }
}
