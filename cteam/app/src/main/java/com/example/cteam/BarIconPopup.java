package com.example.cteam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class BarIconPopup extends Activity {

    Button iconPopup_agree,iconPopup_cancel,iconBtn1;
    int resId;
    String drawable;
    String packageName;
    int btnImg;


    Button button[] = new Button[15];

    Integer[] Rid_button = {

            R.id.iconBtn0, R.id.iconBtn1, R.id.iconBtn2, R.id.iconBtn3,

            R.id.iconBtn4, R.id.iconBtn5, R.id.iconBtn6, R.id.iconBtn7

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_bar_icon_popup);
        //타이틀바 없애기

        packageName= getPackageName();

        iconPopup_agree=findViewById(R.id.iconPopup_agree);
        iconPopup_cancel=findViewById(R.id.iconPopup_cancel);
        iconBtn1=findViewById(R.id.iconBtn1);

        //UI 객체생성
        //동의

        for(int i=0;i<=7; i++){

            button[i] = (Button) findViewById(Rid_button[i]);

//            button[i].setTag(i);

        }//버튼찾기


        for(int i=0;i<=7;i++) {
            final int INDEX;
            final int INDEX2;

            INDEX = i;
            INDEX2=i+1;
            button[INDEX].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resId=getResources().getIdentifier("sample_icon_"+INDEX2,"drawable",packageName);
                    drawable="R.drawable.sample_icon_"+INDEX+1;
                    btnImg=INDEX2;

                }
            });
        }

        //선택버튼 클릭시
        iconPopup_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //    Toast.makeText(BarIconPopup.this, resId, Toast.LENGTH_SHORT).show();

                //정보전달
                Intent intent = new Intent(getApplicationContext(), Petbar_Add.class);
                intent.putExtra("data",resId);
                intent.putExtra("btnImg",btnImg);
                setResult(RESULT_OK, intent) ;
                finish();
            }
        });

        //취소
        iconPopup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }



}