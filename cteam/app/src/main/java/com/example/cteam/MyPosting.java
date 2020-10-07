package com.example.cteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cteam.ATask.BoardselectList;
import com.example.cteam.ATask.MyPostingSelect;
import com.example.cteam.Adapter.BoardAdapter;
import com.example.cteam.Dto.BoardDTO;

import java.util.ArrayList;

import static com.example.cteam.Common.CommonMethod.isNetworkConnected;
import static com.example.cteam.Login.loginDTO;

public class MyPosting extends Fragment {

    PetSelect activity;
    Bundle bundle = null;

    ArrayList<BoardDTO> myItemArrayList;
    RecyclerView board_list;
    BoardAdapter boardAdapter;
    MyPostingSelect myPostingSelect;
    String member_id;

    Button myposting_text, myposting_comment;
    TextView board_comment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_myposting,
                container, false);

        activity = (PetSelect) getActivity();

        // 버튼
        myposting_text = rootView.findViewById(R.id.myposting_text);
        myposting_comment = rootView.findViewById(R.id.myposting_comment);
        board_comment = rootView.findViewById(R.id.board_comment);

        // 내 글 클릭시
        myposting_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board_list.setVisibility(View.VISIBLE);
                board_comment.setVisibility(View.GONE);
            }
        });

        // 내 댓글 클릭시
        myposting_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board_comment.setVisibility(View.VISIBLE);
                board_list.setVisibility(View.GONE);
            }
        });

        // 게시글 리사이클러뷰 셋팅
        board_list = rootView.findViewById(R.id.board_list);
        myItemArrayList = new ArrayList<>();
        boardAdapter = new BoardAdapter(rootView.getContext(), myItemArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext(),
                RecyclerView.VERTICAL, false);
        board_list.setLayoutManager(layoutManager);
        board_list.setAdapter(boardAdapter);

        // 게시글 데이터 가져오기
        if(isNetworkConnected(rootView.getContext()) == true){
            member_id = loginDTO.getMember_id();
            myPostingSelect = new MyPostingSelect(myItemArrayList, boardAdapter, member_id);
            myPostingSelect.execute();
        }else {
            Toast.makeText(rootView.getContext(), "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

        return rootView;

    } //onCreateView()

}