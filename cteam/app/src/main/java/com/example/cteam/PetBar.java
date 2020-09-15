package com.example.cteam;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cteam.ATask.PetBarListSelect;



import com.example.cteam.Adapter.PetBarAdapter;
import com.example.cteam.Dto.PetBarItem;

import java.util.ArrayList;
import com.example.cteam.ATask.PetBarListSelect;
import com.example.cteam.Adapter.PetBarAdapter;
import com.example.cteam.Dto.PetBarItem;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.cteam.ATask.PetBarListSelect.petBarItemArrayList;
import static com.example.cteam.Common.CommonMethod.isNetworkConnected;


public class PetBar extends AppCompatActivity {
    private static final String TAG = "main:PetBar";

    public static PetBarItem selItem =null;
    PetBarListSelect petBarListSelect;
    RecyclerView recyclerView;
    PetBarAdapter petBarAdapter;
    ProgressDialog progressDialog;

    ArrayList<PetBarItem> petBarItemsArrayList;

    int ok;
    int hour_button;

    Button button[] = new Button[24];

    Integer[] Rid_button = {

            R.id.barBtn0, R.id.barBtn1, R.id.barBtn2, R.id.barBtn3, R.id.barBtn4,

            R.id.barBtn5, R.id.barBtn6, R.id.barBtn7, R.id.barBtn8, R.id.barBtn9,

            R.id.barBtn10, R.id.barBtn11, R.id.barBtn12, R.id.barBtn13, R.id.barBtn14,

            R.id.barBtn15, R.id.barBtn16, R.id.barBtn17, R.id.barBtn18, R.id.barBtn19,

            R.id.barBtn20, R.id.barBtn21, R.id.barBtn22, R.id.barBtn23

    };

    Button bar_calendar,bar_add,bar_modify,bar_delete;
    /*barBtn0,barBtn1,barBtn2,barBtn3,barBtn4,barBtn5,barBtn6,
            barBtn7,barBtn8,barBtn9,bar_btn10,bar_btn11,bar_btn12,bar_btn13,bar_btn14,bar_btn15,bar_btn16,
            barBtn17,barBtn18,bar_btn19,bar_btn020,bar_btn21,bar_btn22,bar_btn23;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_bar);

        bar_calendar=findViewById(R.id.bar_calender);
        bar_add=findViewById(R.id.bar_add);
        bar_modify=findViewById(R.id.bar_modify);
        bar_delete=findViewById(R.id.bar_delete);

        //리싸이클러 뷰 시작
        petBarItemsArrayList = new ArrayList<>();
        petBarAdapter=new PetBarAdapter(this,petBarItemsArrayList);
        recyclerView=findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(petBarAdapter);





       /* //hour 가져와서 버튼 색칠
        for(int i=0;i<petBarItemArrayList.size();i++) {
           if(petBarItemArrayList.get(i).getHour()!=null) {
               hour_button = Integer.parseInt(petBarItemArrayList.get(i).getHour());
           }
            Toast.makeText(this, hour_button, Toast.LENGTH_SHORT).show();
              if(hour_button==0||hour_button==12){
                    button[hour_button].setBackgroundResource(R.drawable.barbtnleftclicked);
              }else if(hour_button==11||hour_button==23){
                    button[hour_button].setBackgroundResource(R.drawable.barbtnrightclicked);
              }else{
                    button[hour_button].setBackgroundResource(R.drawable.barbtnclicked);
              }


        }*/

        if(isNetworkConnected(this) == true){
            petBarListSelect = new PetBarListSelect(petBarItemsArrayList, petBarAdapter, progressDialog);
            try {
                String result = petBarListSelect.execute().get();
                Log.d(TAG, "onCreate: " + result);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }

        //캘린더로 돌아가기
        bar_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Toast.makeText(this, petBarItemArrayList.size(), Toast.LENGTH_SHORT).show();

        for(int i=0;i<=23; i++){

            button[i] = findViewById(Rid_button[i]);

        }//버튼찾기

        for(int i=0; i<petBarItemsArrayList.size(); i++){


            int hour = Integer.parseInt(petBarItemsArrayList.get(i).getHour());

            if(hour==0||hour==12){
                button[hour].setBackgroundResource(R.drawable.barbtnleftclicked);
            }else if(hour==11||hour==23){
                button[hour].setBackgroundResource(R.drawable.barbtnrightclicked);
            }else{
                button[hour].setBackgroundResource(R.drawable.barbtnclicked);

            }

        }


        //버튼 클릭시
        for(int i=0; i<button.length; i++){

            final int INDEX;

            INDEX = i;

            button[INDEX].setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {


                    /*  if(INDEX==0||INDEX==12){
                    button[INDEX].setBackgroundResource(R.drawable.barbtnleftclicked);
                }else if(INDEX==11||INDEX==23){
                    button[INDEX].setBackgroundResource(R.drawable.barbtnrightclicked);
                    }else{
                    button[INDEX].setBackgroundResource(R.drawable.barbtnclicked);

                }*/
                    //추가
                    bar_add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(isNetworkConnected(getApplicationContext()) == true){
                                Intent intent = new Intent(getApplicationContext(), Petbar_Add.class);
                                intent.putExtra("time",INDEX);
                                startActivityForResult(intent, 1);

                            }else {
                                Toast.makeText(getApplicationContext(), "인터넷이 연결되어 있지 않습니다.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });




                }

            });

        }//버튼클릭시



        bar_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK){

            ok=data.getIntExtra("ok",0);

        }
    }


}