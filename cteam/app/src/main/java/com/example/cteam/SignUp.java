package com.example.cteam;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    Button SignUp_cancel, SignUp_ok;
    TextView SignUp_agree_text,txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //txtResult = (TextView)findViewById(R.id.txtResult);



        //가입하기 버튼 > 로그인 화면으로 이동
        SignUp_ok = findViewById(R.id.SignUp_ok);
        SignUp_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(getApplicationContext(), Login.class);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentLogin);
                finish();
            }
        });

        //가입취소 버튼 > 로그인 화면으로 이동
        SignUp_cancel = findViewById(R.id.SignUp_cancel);
        SignUp_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(getApplicationContext(), Login.class);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentLogin);
                finish();
            }
        });

        //약관보기
        SignUp_agree_text = (TextView) findViewById(R.id.SignUp_agree_text);

        SignUp_agree_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                intent.putExtra("data", "Test Popup");
                startActivityForResult(intent, 1);

            }
        });

    } //onCreate
}