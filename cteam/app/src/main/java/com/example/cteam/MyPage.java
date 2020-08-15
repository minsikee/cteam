package com.example.cteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyPage extends AppCompatActivity {

    Button MyPage_cancel, MyPage_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        //회원정보 수정 취소 버튼
        MyPage_cancel = findViewById(R.id.MyPage_cancel);

        //회원정보 수정 확인 버튼
        MyPage_ok = findViewById(R.id.MyPage_ok);

    } //onCreate
}
