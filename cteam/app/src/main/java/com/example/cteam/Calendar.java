package com.example.cteam;

import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.cteam.ATask.CalListSelect;
import com.example.cteam.ATask.CalcalSelect;
import com.example.cteam.Dto.CalendarDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.cteam.Common.CommonMethod.isNetworkConnected;

public class Calendar extends Fragment {
    //로그 찍어볼 때
    //private static final String TAG = "main:Calendar";

    PetSelect activity;

    CalendarDTO dto;
    Bundle bundle = null;

    com.applandeo.materialcalendarview.CalendarView CalendarView;
    List<EventDay> events;
    ImageView Calendar_event;

    ArrayList<CalendarDTO> icons;
    CalcalSelect calcalSelect;

    String date;

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
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String select_date = format.format(eventDay.getCalendar().getTime());
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

        //스케줄 데이터 불러옴
        if(isNetworkConnected(getActivity()) == true) {
            calcalSelect = new CalcalSelect();
            try {
                calcalSelect.execute().get();
                Log.d("main:Calendar", "onCreateView: " + icons.get(0).calendar_date);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

        /*
        //날짜 차이 구하기
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String strDate = format.format(date);

        String date1;
        String date2;
        Date firstDate;
        Date secondDate;
        long calDate;
        long calDates;

        try {
            for (int i = 0; i < icons.size(); i++) {
                java.util.Calendar calendar3 = java.util.Calendar.getInstance();

                date1 = icons.get(i).calendar_date;
                date2 = format.format(calendar3.getTime());

                firstDate = format.parse(date1);
                secondDate = format.parse(date2);

                calDate = firstDate.getTime() - secondDate.getTime();
                calDates = calDate / (24 * 60 * 60 * 1000);

                Log.d("main:Calendar", "차이: " + calDates);

                    LayerDrawable cal_icon3 = (LayerDrawable) getResources().getDrawable(R.drawable.cal_3icons);
                    if (icons.get(i).calendar_icon.equals("11")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon11));
                    } else if (icons.get(i).calendar_icon.equals("12")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon12));
                    } else if (icons.get(i).calendar_icon.equals("13")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon13));
                    } else if (icons.get(i).calendar_icon.equals("14")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon14));
                    } else if (icons.get(i).calendar_icon.equals("15")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon15));
                    } else if (icons.get(i).calendar_icon.equals("16")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon16));
                    } else if (icons.get(i).calendar_icon.equals("17")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon17));
                    } else if (icons.get(i).calendar_icon.equals("18")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon18));
                    }
                    calendar3.add(java.util.Calendar.DAY_OF_MONTH, (int) calDates);
                    events.add(new EventDay(calendar3, R.drawable.cal_3icons));

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
*/

                    /*
                    //아이콘 붙이기
                    LayerDrawable cal_icon3 = (LayerDrawable) getResources().getDrawable(R.drawable.cal_3icons);

                    if (icons.get(0).calendar_icon.equals("11")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon11));
                    } else if (icons.get(0).calendar_icon.equals("12")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon12));
                    } else if (icons.get(0).calendar_icon.equals("13")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon13));
                    } else if (icons.get(0).calendar_icon.equals("14")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon14));
                    } else if (icons.get(0).calendar_icon.equals("15")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon15));
                    } else if (icons.get(0).calendar_icon.equals("16")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon16));
                    } else if (icons.get(0).calendar_icon.equals("17")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon17));
                    } else if (icons.get(0).calendar_icon.equals("18")) {
                        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon18));
                    }


                    calendar3.add(java.util.Calendar.DAY_OF_MONTH, 10);
                    events.add(new EventDay(calendar3, R.drawable.cal_3icons));
                    */




        /*
        //아이콘 붙이기
        LayerDrawable cal_icon3 = (LayerDrawable) getResources().getDrawable(R.drawable.cal_3icons);

        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon11));
        cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon33));
        cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon44));

        java.util.Calendar calendar3 = java.util.Calendar.getInstance();
        calendar3.add(java.util.Calendar.DAY_OF_MONTH, (int) rhkdus);
        events.add(new EventDay(calendar3, R.drawable.cal_3icons));
        */

        /*
        //날짜에 아이콘 붙이기 (3개짜리)
        LayerDrawable cal_icon3 = (LayerDrawable) getResources().getDrawable(R.drawable.cal_3icons);

        cal_icon3.setDrawableByLayerId(R.id.cal_3icons1, getResources().getDrawable(R.drawable.icon11));
        cal_icon3.setDrawableByLayerId(R.id.cal_3icons2, getResources().getDrawable(R.drawable.icon33));
        cal_icon3.setDrawableByLayerId(R.id.cal_3icons3, getResources().getDrawable(R.drawable.icon44));

        java.util.Calendar calendar3 = java.util.Calendar.getInstance();
        calendar3.add(java.util.Calendar.DAY_OF_MONTH, 0);
        events.add(new EventDay(calendar3, R.drawable.cal_3icons));

        //날짜에 아이콘 붙이기 (4개짜리)
        LayerDrawable cal_icon4 = (LayerDrawable) getResources().getDrawable(R.drawable.cal_4icons);

        cal_icon4.setDrawableByLayerId(R.id.cal_4icons1, getResources().getDrawable(R.drawable.icon11));
        cal_icon4.setDrawableByLayerId(R.id.cal_4icons2, getResources().getDrawable(R.drawable.icon22));
        cal_icon4.setDrawableByLayerId(R.id.cal_4icons3, getResources().getDrawable(R.drawable.icon33));
        cal_icon4.setDrawableByLayerId(R.id.cal_4icons4, getResources().getDrawable(R.drawable.icon44));

        java.util.Calendar calendar4 = java.util.Calendar.getInstance();
        calendar4.add(java.util.Calendar.DAY_OF_MONTH, 0);
        events.add(new EventDay(calendar4, R.drawable.cal_4icons));
        */

        return rootView;

    } //onCreateView()

}