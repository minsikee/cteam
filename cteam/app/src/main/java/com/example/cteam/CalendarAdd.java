package com.example.cteam;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cteam.ATask.CalListDelete;
import com.example.cteam.ATask.CalListSelect;
import com.example.cteam.Adapter.CalendarAdapter;
import com.example.cteam.Dto.CalendarDTO;

import java.util.ArrayList;

import static com.example.cteam.Common.CommonMethod.isNetworkConnected;

public class CalendarAdd extends Fragment {
    public static CalendarDTO selectIcon = null;

    PetSelect activity;

    CalendarDTO dto;
    Bundle bundle = null;

    Button CalendarAdd_insert, CalendarAdd_update, CalendarAdd_delete, CalendarAdd_back;

    RecyclerView CalendarAdd_view;
    ArrayList<CalendarDTO> icons;
    CalendarAdapter adapter;

    CalListSelect calListSelect;

    String select_date = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_calendar_add, container, false);

        activity = (PetSelect) getActivity();

        //날짜 데이터 받는 부분
        if(activity.cBundle != null){
            bundle = activity.cBundle;
            select_date = bundle.getString("select_date");
        }

        //리사이클러 뷰 셋팅
        icons = new ArrayList<>();
        adapter = new CalendarAdapter(getActivity(), icons);
        CalendarAdd_view = (RecyclerView) rootView.findViewById(R.id.CalendarAdd_view);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //CalendarAdd_view.setLayoutManager(layoutManager);
        CalendarAdd_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        CalendarAdd_view.setAdapter(adapter);

        //데이터 선택했을 때
        if(isNetworkConnected(getActivity()) == true) {
            calListSelect = new CalListSelect(icons, adapter);
            calListSelect.execute();
        } else {
            Toast.makeText(getActivity(), "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

        //추가 클릭 > CalendarInsert로 이동
        CalendarAdd_insert = rootView.findViewById(R.id.CalendarAdd_insert);
        CalendarAdd_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkConnected(getContext()) == true){
                    activity.onFragmentChange(5, bundle);
                } else {
                    Toast.makeText(getContext(), "인터넷이 연결되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //수정 > CalendarUpdate로 이동
        CalendarAdd_update = rootView.findViewById(R.id.CalendarAdd_update);
        CalendarAdd_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkConnected(getContext()) == true) {
                    //선택된 아이콘이 있을 때만 이동
                    if (selectIcon != null) {
                        //화면이동
                        activity.onFragmentChange(6, null);
                    } else {
                        Toast.makeText(getContext(), "수정할 스케줄을 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "인터넷이 연결되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //삭제
        CalendarAdd_delete = rootView.findViewById(R.id.CalendarAdd_delete);
        CalendarAdd_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkConnected(getContext()) == true) {
                    //선택된 아이콘이 있을 때만 삭제
                    if(selectIcon != null){
                        CalListDelete calListDelete = new CalListDelete(selectIcon.getCalendar_icon());
                        calListDelete.execute();
                        // 화면갱신
                        refresh();
                        adapter.notifyDataSetChanged();

                        Toast.makeText(getContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "삭제할 스케줄을 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "인터넷이 연결되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //뒤로가기 > CalendarAdd로 이동
        CalendarAdd_back = rootView.findViewById(R.id.CalendarAdd_back);
        CalendarAdd_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(3, null);
            }
        });

        /*
        //뒤로가기 > 화면 이동 X 화면 종료 O
        CalendarAdd_back = rootView.findViewById(R.id.CalendarAdd_back);
        CalendarAdd_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(CalendarAdd.this).commit();
                fragmentManager.popBackStack();
            }
        });
        */

        return rootView;

    } //onCreateView()

    //새로고침
    private void refresh() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    } //refresh()

}