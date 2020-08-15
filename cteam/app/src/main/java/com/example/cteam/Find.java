package com.example.cteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Find extends AppCompatActivity {

    Button Find_btn_id, Find_btn_pw;
    Button Find_btn_signup, Find_btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        //아이디 찾기 버튼 > 토스트 말고 칸 하나 만들기로 함
        Find_btn_id = findViewById((R.id.Find_btn_id));
        Find_btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Find.this, "아이디는 XX 입니다", Toast.LENGTH_LONG).show();
            }
        });

        //비밀번호 찾기 버튼 > 비밀번호 변경 페이지로 이동
        Find_btn_pw = findViewById(R.id.Find_btn_pw);
        Find_btn_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PasswordModify.class);
                startActivity(intent);
            }
        });

        //회원가입 버튼 > 가입 화면으로 이동
        Find_btn_signup = findViewById(R.id.Find_btn_signup);
        Find_btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });


        //로그인하기 버튼 > 로그인 화면으로 이동
        Find_btn_login = findViewById(R.id.Find_btn_login);
        Find_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    } //onCreate
}