package com.example.cteam;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.cteam.R;
import com.example.cteam.ATask.LoginSelect;
import com.example.cteam.Dto.MemberDTO;

import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {

    // 로그인이 성공하면 static 로그인DTO 변수에 담아서
    // 어느곳에서나 접근할 수 있게 한다
    public static MemberDTO loginDTO = null;

    EditText loginId, loginPw;
    Button loginBtn1, loginBtn2,loginBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkDangerousPermissions();

        loginId = findViewById(R.id.login_id);
        loginPw = findViewById(R.id.login_pw);

        loginBtn1 = findViewById(R.id.login_btn1);
        loginBtn2 = findViewById(R.id.login_btn2);
        loginBtn3 = findViewById(R.id.login_btn3);

        // 로그인 버튼
        loginBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDTO = null;

                if(loginId.getText().toString().length() != 0 && loginPw.getText().toString().length() != 0){
                    String id = loginId.getText().toString();
                    String pw = loginPw.getText().toString();

                    LoginSelect loginSelect = new LoginSelect(id, pw);
                    try {
                        loginSelect.execute().get();
                    } catch (ExecutionException e) {
                        e.getMessage();
                    } catch (InterruptedException e) {
                        e.getMessage();
                    }

                } else {
                    Toast.makeText(Login.this, "아이디와 암호를 모두 입력하세요", Toast.LENGTH_SHORT).show();
                    Log.d("main:login", "아이디와 암호를 모두 입력하세요 !!!");
                    return;
                }

                if(loginDTO != null){
                    Toast.makeText(Login.this, "로그인 되었습니다 !!!", Toast.LENGTH_SHORT).show();
                    Log.d("main:login", loginDTO.getMember_id() + "님 로그인 되었습니다 !!!");

                    // 로그인 정보에 값이 있으면 로그인이 되었으므로 메인화면으로 이동
                    if(loginDTO != null){
                        Intent intent = new Intent(getApplicationContext(), PetAdd.class);
                        startActivity(intent);
                    }

                }else {
                    Toast.makeText(Login.this, "아이디나 비밀번호가 일치안함 !!!", Toast.LENGTH_SHORT).show();
                    Log.d("main:login", "아이디나 비밀번호가 일치안함 !!!");
                    loginId.setText("");
                    loginPw.setText("");
                    loginId.requestFocus();
                }

            }
        });
        //비밀 번호 찾기 버튼
        loginBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Find.class);
                startActivity(intent);
            }
        });

        // 회원 가입 버튼
        loginBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 화면
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.main_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PetSelect.class);
                startActivity(intent);
            }
        });


    }


    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,


        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
