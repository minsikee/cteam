package com.example.cteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button login_btn1, login_btn2, login_btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //로그인 버튼 > 펫 선택 화면으로 이동
        login_btn1 = findViewById(R.id.login_btn1);

        //비밀번호찾기 버튼 > 찾기 화면으로 이동
        login_btn2 = findViewById(R.id.login_btn2);
        login_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Find.class);
                startActivity(intent);
            }
        });

        //회원가입 버튼 > 가입 화면으로 이동
        login_btn3 = findViewById(R.id.login_btn3);
        login_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

    } //onCreate
}