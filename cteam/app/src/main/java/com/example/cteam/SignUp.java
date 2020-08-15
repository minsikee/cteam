package com.example.cteam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {

    Button SignUp_cancel, SignUp_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //가입취소 버튼 > 로그인 화면으로 이동
        SignUp_cancel = findViewById(R.id.SignUp_cancel);
        SignUp_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //가입하기 버튼 > 로그인 화면으로 이동
        SignUp_ok = findViewById(R.id.SignUp_ok);
        SignUp_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    } //onCreate
}