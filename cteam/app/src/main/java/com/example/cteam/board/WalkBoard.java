package com.example.cteam.board;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cteam.ATask.BoardselectList;
import com.example.cteam.Adapter.BoardAdapter;
import com.example.cteam.Dto.BoardDTO;
import com.example.cteam.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.example.cteam.Common.CommonMethod.isNetworkConnected;
import static com.example.cteam.R.array.board_SpinnerArray;

public class WalkBoard extends Fragment {

    Spinner spinner_board, spinner_City, spinner_Sigungu;
    ArrayAdapter<CharSequence> City_spinner, Sigungu_spinner;

    //ATask
    BoardselectList boardselectList;
    //DTO
    ArrayList<BoardDTO> myItemArrayList;
    //RecyclerView
    RecyclerView recyclerView;
    //Adapter
    BoardAdapter boardAdapter;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_walk_board,container, false);

        spinner_board = rootView.findViewById(R.id.board_spinner_board);
        ArrayList<String> list = new ArrayList<>();

        //게시판 선택창
        ArrayAdapter spinnerAdapter =
                ArrayAdapter.createFromResource(rootView.getContext(), board_SpinnerArray, android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_board.setAdapter(spinnerAdapter);

        spinner_board.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        //도시 선택
        spinner_City = rootView.findViewById(R.id.board_spinnercity);
        spinner_Sigungu = rootView.findViewById(R.id.board_spinnersigungu);
        Button board_search = rootView.findViewById(R.id.board_search);
        City_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region, android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(City_spinner);

        spinner_City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(City_spinner.getItem(i).equals("서울특별시")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_seoul,android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        }

                        public void onNothingSelected(AdapterView<?> adapterView) {//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                        }
                    });
                }else if(City_spinner.getItem(i).equals("부산광역시")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_incheon,android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        }

                        public void onNothingSelected(AdapterView<?> adapterView) {//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        // 게시판 리사이클러 뷰 찾기
        recyclerView = rootView.findViewById(R.id.board_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext(),
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // 리사이클러뷰 어댑터 선언
        myItemArrayList = new ArrayList<>();
        boardAdapter = new BoardAdapter(rootView.getContext(), myItemArrayList);

        recyclerView.setAdapter(boardAdapter);

        if(isNetworkConnected(rootView.getContext()) == true){
            boardselectList = new BoardselectList(myItemArrayList, boardAdapter, progressDialog);
            boardselectList.execute();
        }else {
            Toast.makeText(rootView.getContext(), "인터넷이 연결되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
        }
        // 임의의 데이터 만들기 (이 부분에서 게시판 정보를 가져와야 함)
//        BoardDTO dto = new BoardDTO();
//        dto.setTitle("글제목");
//        dto.setId("아이디");
//        dto.setDate("20200822");
//        dto.setComment("8개");
//
//        BoardDTO dto2 = new BoardDTO();
//        dto.setTitle("글제목2임");
//        dto.setId("현열");
//        dto.setDate("54654654");
//        dto.setComment("99개");
//
//        ArrayList<BoardDTO> bList = new ArrayList<>();
//        bList.add(dto);
//        bList.add(dto2);


        //글등록 플로팅액션버튼 → 글쓰기 Activity로 이동
        FloatingActionButton fab = rootView.findViewById(R.id.board_write);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(),BoardWrite.class);
                startActivity(intent);
            }
        });
        return rootView;

    }
}