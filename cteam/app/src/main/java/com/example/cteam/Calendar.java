package com.example.cteam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.cteam.Dto.CalendarDTO;


import java.util.ArrayList;
import java.util.List;

public class Calendar extends Fragment {
    //로그 찍어볼 때
    //private static final String TAG = "main:Calendar";

    PetSelect activity;

    CalendarDTO dto;
    Bundle bundle = null;

    com.applandeo.materialcalendarview.CalendarView CalendarView;
    List<EventDay> events;
    ImageView Calendar_event;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_calendar,
                container, false);

        activity = (PetSelect) getActivity();

        //캘린더뷰 찾기
        CalendarView = (CalendarView) rootView.findViewById(R.id.CalendarView);

        //달력 범위 설정 : -12개월 ~ +12개월
        java.util.Calendar min = java.util.Calendar.getInstance();
        min.add(java.util.Calendar.MONTH, -12);
        java.util.Calendar max = java.util.Calendar.getInstance();
        max.add(java.util.Calendar.MONTH, 12);
        CalendarView.setMinimumDate(min);
        CalendarView.setMaximumDate(max);

        //이벤트 추가할 배열 생성
        events = new ArrayList<>();

        //캘린더에 이벤트 등록
        CalendarView.setEvents(events);

        //날짜 클릭 > 이벤트 추가 버튼 등장
        CalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                //날짜 데이터 보내는 부분
                String select_date = eventDay.getCalendar().getTime().toString();
                //Log.d(TAG, "onDayClick: " + select_date);
                Bundle bundle = new Bundle();
                bundle.putString("select_date", select_date);

                //이벤트 추가 버튼 등장
                Calendar_event = rootView.findViewById(R.id.Calendar_event);
                Calendar_event.setVisibility(View.VISIBLE);
                //이벤트 추가 버튼 클릭 > 날짜 데이터 갖고 CalendarAdd로 이동
                Calendar_event.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activity.onFragmentChange(4, bundle);
                    }
                });

            }
        });

        return rootView;

    } //onCreateView()

}