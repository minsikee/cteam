package com.example.cteam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PasswordModify extends AppCompatActivity {
//extends Activity implements OnClickListener
    Button PasswordModify_cancel, PasswordModify_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_modify);

        //비밀번호 변경취소 버튼 > 로그인 화면으로 이동
        PasswordModify_cancel = (Button)findViewById(R.id.PasswordModify_cancel);
        PasswordModify_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(getApplicationContext(), Login.class);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentLogin);
                finish();
            }
        });

        //비밀번호 변경하기 버튼 > 로그인 화면으로 이동
        PasswordModify_ok = (Button)findViewById(R.id.PasswordModify_ok);
        PasswordModify_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(getApplicationContext(), Login.class);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentLogin);
                finish();
            }
        });

    } //onCreate
}