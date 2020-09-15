package com.example.cteam;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cteam.ATask.PetbarListInsert;

import java.lang.reflect.Field;
import java.util.List;

public class Petbar_Add extends AppCompatActivity {
    Button baradd_ok, baradd_cancel, baradd_icon,petadd_Alarm;
    TextView baraddTime,baradd_memo;
    int selectedTime, hour;
    String minute,drawable;
    String memo="";
    int resId,btnImg;
    AlarmManager alarmManager;


    private int TIME_PICKER_INTERVAL = 15;
    NumberPicker minutePicker;
    List<String> displayedValues;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petbar_add);

        baradd_cancel=findViewById(R.id.baradd_cancel);
        baradd_ok=findViewById(R.id.baradd_ok);
        baraddTime=findViewById(R.id.baraddTime);
        baradd_icon=findViewById(R.id.baradd_icon);
        petadd_Alarm=findViewById(R.id.petadd_Alarm);
        baradd_memo=findViewById(R.id.baradd_memo);



        final NumberPicker np = findViewById(R.id.petbarPicker);


        baradd_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        baradd_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        hour=intent.getIntExtra("time",0);

        alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //알람
        petadd_Alarm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                    //반복 알람 : 10초 후에 처음 알람, 20초마다 반복 알람

                    //20초마다 발동할 Broadcast Receiver를
                    //PendingIntent로 생성
                    Intent intent = new Intent(getApplicationContext(), AlarmReciever.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 20, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    //첫 알람 설정(10초 후에)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, pendingIntent);
                    } else {
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, pendingIntent);
                    }
                 }
        });




        //아이콘 기본설정

        baradd_icon.setBackgroundResource(R.drawable.dogface);
        baradd_icon.setTag(R.drawable.dogface);

        //아이콘 클릭하면 변경 팝업창
        baradd_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), BarIconPopup.class);
                startActivityForResult(intent, 1);

            }
        });



        baraddTime.setText(hour+"시");



     /*   TimePicker timePicker = (TimePicker) findViewById(R.id.simple_timepicker);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            timePicker.setHour(time);
        } else {
            timePicker.setCurrentHour(time);
        }*/

        np.setMinValue(0);
        np.setMaxValue(59);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setDividerColor(np, android.R.color.white );
        np.setWrapSelectorWheel(false);
        np.setValue(0);
        
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                minute=String.valueOf(newVal);

            }
        });

        selectedTime=np.getValue();


        //등록버튼 클릭시
        baradd_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(Petbar_Add.this, hour+"시"+minute+"분", Toast.LENGTH_SHORT).show();

                //메모내용 가져오기
                memo= baradd_memo.getText().toString();
                String icon = String.valueOf(btnImg);
                String hour2= String.valueOf(hour);
                String date="20200801";

                PetbarListInsert petbarListInsert= new PetbarListInsert(date,memo,icon,hour2,minute);
                petbarListInsert.execute();

                //Intent intent = new Intent(getApplicationContext(), PetBar.class);
                //intent.putExtra("ok",1);
                //setResult(RESULT_OK, intent) ;
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK){

             resId=data.getIntExtra("data",0);
             btnImg=data.getIntExtra("btnImg",0);

            if(resId!=0){
                baradd_icon.setBackgroundResource(resId);
            }else{
                Toast.makeText(this, "비었음", Toast.LENGTH_SHORT).show();
            }


        }



    }

    private void setDividerColor(NumberPicker picker, int color) {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


}


