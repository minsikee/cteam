package com.example.cteam;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cteam.ATask.CalListInsert;
import com.example.cteam.Dto.CalendarDTO;

import static com.example.cteam.Common.CommonMethod.isNetworkConnected;

public class CalendarAddInsert extends Fragment {

    PetSelect activity;

    CalendarDTO dto;
    Bundle bundle = null;

    String calendar_date;
    String calendar_icon;
    String calendar_memo;

    ImageView CalendarAddInsert_color1, CalendarAddInsert_color2, CalendarAddInsert_color3, CalendarAddInsert_color4,
            CalendarAddInsert_color5, CalendarAddInsert_color6, CalendarAddInsert_color7, CalendarAddInsert_color8;
    int checked1 = 0;
    ImageView CalendarAddInsert_icon1, CalendarAddInsert_icon2, CalendarAddInsert_icon3, CalendarAddInsert_icon4,
            CalendarAddInsert_icon5, CalendarAddInsert_icon6, CalendarAddInsert_icon7, CalendarAddInsert_icon8;
    int checked2 = 0;
    TextView CalendarAddInsert_memo;
    Button CalendarAddInsert_cancel, CalendarAddInsert_ok;

    String select_date = "";
    java.text.SimpleDateFormat tmpDateFormat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_calendar_add_insert, container, false);

        activity = (PetSelect) getActivity();

        //날짜 데이터 받는 부분 : bundle로 받은 후 bundle 비우기
        if(activity.cBundle != null){
            bundle = activity.cBundle;
            select_date = bundle.getString("select_date");
            activity.cBundle = null;
        }

        //날짜 포맷 설정
        tmpDateFormat = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss");

        //찾기
        CalendarAddInsert_memo = (TextView) rootView.findViewById(R.id.CalendarAddInsert_memo);

        //색상 선택 > (코드 간단하게 수정 가능?)
        CalendarAddInsert_color1 = rootView.findViewById(R.id.CalendarAddInsert_color1);
        CalendarAddInsert_color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 1;
            }
        });
        CalendarAddInsert_color2 = rootView.findViewById(R.id.CalendarAddInsert_color2);
        CalendarAddInsert_color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 2;
            }
        });
        CalendarAddInsert_color3 = rootView.findViewById(R.id.CalendarAddInsert_color3);
        CalendarAddInsert_color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 3;
            }
        });
        CalendarAddInsert_color4 = rootView.findViewById(R.id.CalendarAddInsert_color4);
        CalendarAddInsert_color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 4;
            }
        });
        CalendarAddInsert_color5 = rootView.findViewById(R.id.CalendarAddInsert_color5);
        CalendarAddInsert_color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 5;
            }
        });
        CalendarAddInsert_color6 = rootView.findViewById(R.id.CalendarAddInsert_color6);
        CalendarAddInsert_color6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 6;
            }
        });
        CalendarAddInsert_color7 = rootView.findViewById(R.id.CalendarAddInsert_color7);
        CalendarAddInsert_color7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.BLACK);
                CalendarAddInsert_color8.setBackgroundColor(Color.TRANSPARENT);

                checked1 = 7;
            }
        });
        CalendarAddInsert_color8 = rootView.findViewById(R.id.CalendarAddInsert_color8);
        CalendarAddInsert_color8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarAddInsert_color1.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color2.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color3.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color4.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color5.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color6.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color7.setBackgroundColor(Color.TRANSPARENT);
                CalendarAddInsert_color8.setBackgroundColor(Color.BLACK);

                checked1 = 8;
            }
        });

        //아이콘1 선택 > (코드 간단하게 수정 가능?)
        CalendarAddInsert_icon1 = rootView.findViewById(R.id.CalendarAddInsert_icon1);
        CalendarAddInsert_icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddInsert_icon1.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);

                checked2 = 1;
            }
        });
        //아이콘2 선택
        CalendarAddInsert_icon2 = rootView.findViewById(R.id.CalendarAddInsert_icon2);
        CalendarAddInsert_icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checked1 == 1) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#FF0000"));
                } else if (checked1 == 2) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#FF9800"));
                } else if (checked1 == 3) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#FFC107"));
                } else if (checked1 == 4) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#41AF39"));
                } else if (checked1 == 5) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#3F51B5"));
                } else if (checked1 == 6) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#A566FF"));
                } else if (checked1 == 7) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if (checked1 == 8) {
                    CalendarAddInsert_icon2.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);

                checked2 = 2;
            }
        });
        //아이콘3 선택
        CalendarAddInsert_icon3 = rootView.findViewById(R.id.CalendarAddInsert_icon3);
        CalendarAddInsert_icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checked1 == 1) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#FF0000"));
                } else if (checked1 == 2) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#FF9800"));
                } else if (checked1 == 3) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#FFC107"));
                } else if (checked1 == 4) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#41AF39"));
                } else if (checked1 == 5) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#3F51B5"));
                } else if (checked1 == 6) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#A566FF"));
                } else if (checked1 == 7) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if (checked1 == 8) {
                    CalendarAddInsert_icon3.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);

                checked2 = 3;
            }
        });
        //아이콘4 선택
        CalendarAddInsert_icon4 = rootView.findViewById(R.id.CalendarAddInsert_icon4);
        CalendarAddInsert_icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddInsert_icon4.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);

                checked2 = 4;
            }
        });
        //아이콘5 선택
        CalendarAddInsert_icon5 = rootView.findViewById(R.id.CalendarAddInsert_icon5);
        CalendarAddInsert_icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddInsert_icon5.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);

                checked2 = 5;
            }
        });
        //아이콘6 선택
        CalendarAddInsert_icon6 = rootView.findViewById(R.id.CalendarAddInsert_icon6);
        CalendarAddInsert_icon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddInsert_icon6.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);

                checked2 = 6;
            }
        });
        //아이콘4 선택
        CalendarAddInsert_icon7 = rootView.findViewById(R.id.CalendarAddInsert_icon7);
        CalendarAddInsert_icon7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#FFC107"));
                } else if(checked1 == 4) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#41AF39"));
                } else if(checked1 == 5) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddInsert_icon7.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon8.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);

                checked2 = 7;
            }
        });
        //아이콘8 선택
        CalendarAddInsert_icon8 = rootView.findViewById(R.id.CalendarAddInsert_icon8);
        CalendarAddInsert_icon8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked1 == 1) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#FF0000"));
                } else if(checked1 == 2) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#FF9800"));
                } else if(checked1 == 3) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#FFC107"));
                }  else if(checked1 == 4) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#4374D9"));
                } else if(checked1 == 5) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#3F51B5"));
                } else if(checked1 == 6) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#A566FF"));
                } else if(checked1 == 7) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#FFB2F5"));
                } else if(checked1 == 8) {
                    CalendarAddInsert_icon8.setColorFilter(Color.parseColor("#8C8C8C"));
                }
                CalendarAddInsert_icon1.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon2.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon3.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon4.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon5.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon6.setColorFilter(Color.BLACK);
                CalendarAddInsert_icon7.setColorFilter(Color.BLACK);

                checked2 = 8;
            }
        });

        //취소 > 저장하지 않고 CalendarAdd로 이동
        CalendarAddInsert_cancel = rootView.findViewById(R.id.CalendarAddInsert_cancel);
        CalendarAddInsert_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(4, null);
            }
        });

        //확인 > 저장하고 CalendarAdd로 이동
        CalendarAddInsert_ok = rootView.findViewById(R.id.CalendarAddInsert_ok);
        CalendarAddInsert_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkConnected(getContext()) == true) {
                    calendar_date = select_date;
                    if(checked2 == 1) {
                        calendar_icon = "/res/drawable/sample_icon_1";
                        //calendar_icon = String.valueOf(getResources().getIdentifier("sample_icon_1", null, null));
                        //calendar_icon = String.valueOf((R.drawable.sample_icon_1));
                        //calendar_icon = String.valueOf(getResources().getIdentifier("sample_icon_1","drawable", getActivity().getPackageName()));
                    } else if(checked2 == 2) {
                        calendar_icon = String.valueOf(getResources().getIdentifier("sample_icon_2","drawable", getActivity().getPackageName()));
                    } else if(checked2 == 3) {
                        calendar_icon = String.valueOf(getResources().getIdentifier("sample_icon_3","drawable", getActivity().getPackageName()));
                    } else if(checked2 == 4) {
                        calendar_icon = String.valueOf(getResources().getIdentifier("sample_icon_4","drawable", getActivity().getPackageName()));
                    } else if(checked2 == 5) {
                        calendar_icon = String.valueOf(getResources().getIdentifier("sample_icon_5","drawable", getActivity().getPackageName()));
                    } else if(checked2 == 6) {
                        calendar_icon = String.valueOf(getResources().getIdentifier("sample_icon_6","drawable", getActivity().getPackageName()));
                    } else if(checked2 == 7) {
                        calendar_icon = String.valueOf(getResources().getIdentifier("sample_icon_7","drawable", getActivity().getPackageName()));
                    } else if(checked2 == 8) {
                        calendar_icon = String.valueOf(getResources().getIdentifier("sample_icon_8","drawable", getActivity().getPackageName()));
                    }
                    calendar_memo = CalendarAddInsert_memo.getText().toString();

                    CalListInsert calListInsert = new CalListInsert(calendar_date, calendar_icon, calendar_memo);
                    calListInsert.execute();

                    activity.onFragmentChange(4, null);
                }
                //Toast.makeText(getContext(), calendar_icon, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;

    } //onCreateView()

}