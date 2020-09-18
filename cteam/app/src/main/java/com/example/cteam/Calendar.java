package com.example.cteam;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        //아이콘 추가1 >> 한개만
        java.util.Calendar calendar1 = java.util.Calendar.getInstance();
        calendar1.add(java.util.Calendar.DAY_OF_MONTH, 1);
        events.add(new EventDay(calendar1, R.drawable.icon11));

        //아이콘 추가2 >> 한개만
        java.util.Calendar calendar2 = java.util.Calendar.getInstance();
        calendar2.add(java.util.Calendar.DAY_OF_MONTH, 2);
        events.add(new EventDay(calendar2, R.drawable.icon21));
        events.add(new EventDay(calendar2, R.drawable.icon22));

        //아이콘 추가3 >> 한개만
        java.util.Calendar calendar3 = java.util.Calendar.getInstance();
        calendar3.add(java.util.Calendar.DAY_OF_MONTH, 3);
        events.add(new EventDay(calendar3, R.drawable.icon41, R.drawable.icon42));

        //아이콘 추가4 >> 4+4일뒤로 설정됨, 한개만됨
        //java.util.Calendar calendar4 = java.util.Calendar.getInstance();
        //calendar4.add(java.util.Calendar.DAY_OF_MONTH, 4);
        //events.add(new EventDay(calendar4, R.drawable.icon51));
        //calendar4.add(java.util.Calendar.DAY_OF_MONTH, 4);
        //events.add(new EventDay(calendar4, R.drawable.icon52));

        //아이콘 추가5 >> 위치지정은 되나 한개만...
        java.util.Calendar calendar5 = java.util.Calendar.getInstance();
        calendar5.add(java.util.Calendar.DAY_OF_MONTH, 5);
        events.add(new EventDay(calendar5, (R.drawable.cal_icon1 & R.drawable.cal_icon2)));

        //아이콘 추가6 >> 하나만됨
        java.util.Calendar calendar6 = java.util.Calendar.getInstance();
        calendar6.add(java.util.Calendar.DAY_OF_MONTH, 6);
        events.add(new EventDay(calendar6, R.drawable.cal_icon1));
        events.add(new EventDay(calendar6, R.drawable.cal_icon2));

        //아이콘 추가7 >> 오류
        //java.util.Calendar calendar7 = java.util.Calendar.getInstance();
        //calendar7.add(java.util.Calendar.DAY_OF_MONTH, 7);
        //events.add(new EventDay(calendar7, R.drawable.cal_icon1 + R.drawable.cal_icon2));

        //이렇게 추가해보기8 >> 추가는 된당ㅋㅋ
        //int imgv1 = R.drawable.cal_icon1 + R.drawable.cal_icon2;
        int imgv1 = R.drawable.cal_icon1;
        java.util.Calendar calendar8 = java.util.Calendar.getInstance();
        calendar8.add(java.util.Calendar.DAY_OF_MONTH, 8);
        events.add(new EventDay(calendar8, imgv1));

        //이렇게 추가해보기9 >> 오류
        //int imgv2 = Integer.parseInt(String.valueOf(R.drawable.cal_icon1) + String.valueOf(R.drawable.cal_icon2));
        //String imgv2 = (String.valueOf(R.drawable.cal_icon1)) + (String.valueOf(R.drawable.cal_icon2));
        //java.util.Calendar calendar9 = java.util.Calendar.getInstance();
        //calendar9.add(java.util.Calendar.DAY_OF_MONTH, 9);
        //events.add(new EventDay(calendar9, Integer.parseInt(imgv2)));

        //이렇게 추가해보기10 >> 안됨
        int imgv3 = R.drawable.cal_icon1;
        int imgv4 = R.drawable.cal_icon2;
        int imgv5 = imgv3 & imgv4;
        java.util.Calendar calendar10 = java.util.Calendar.getInstance();
        calendar10.add(java.util.Calendar.DAY_OF_MONTH, 10);
        events.add(new EventDay(calendar10, imgv5));

        //이렇게 추가해보기
        //java.util.Calendar calendar11 = java.util.Calendar.getInstance();
        //calendar11.add(java.util.Calendar.DAY_OF_MONTH, 11);
        //events.add(new EventDay(calendar11, R.drawable.cal_icon1));
        //events.add(R.drawable.cal_icon2);
        //events.add(new EventDay(calendar11, R.drawable.cal_icon2));

        //이렇게 추가해보기
        String imgv10 = String.valueOf(R.layout.fragment_calendar_view);
        java.util.Calendar calendar12 = java.util.Calendar.getInstance();
        calendar12.add(java.util.Calendar.DAY_OF_MONTH, 12);
        events.add(new EventDay(calendar12, Integer.parseInt(imgv10)));

        return rootView;

    } //onCreateView()

}