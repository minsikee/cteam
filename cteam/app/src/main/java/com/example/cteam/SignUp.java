package com.example.cteam;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cteam.ATask.JoinInsert;

import java.nio.file.Files;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    String state;

    EditText signupId, signupPw, signupName, signupPwConfirm, signupQuestion, signupAnswer, signupPhoneNum;
    Button btnJoin, btnCancel;
    TextView SignUp_agree_text,txtResult;
    CheckBox SignUp_agree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupId = findViewById(R.id.SignUp_id);
        signupPw = findViewById(R.id.SignUp_pw);
        signupName = findViewById(R.id.SignUp_name);
        signupQuestion = findViewById(R.id.SignUp_qs);
        signupAnswer = findViewById(R.id.SignUp_qs_as);
        signupPhoneNum = findViewById(R.id.SignUp_phonenum);
        btnJoin = findViewById(R.id.SignUp_join);
        btnCancel = findViewById(R.id.SignUp_cancel);
        signupPwConfirm =findViewById(R.id.SignUp_pw_confirm);
        SignUp_agree= findViewById(R.id.SignUp_agree);


        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String member_id = signupId.getText().toString();
                String member_pw = signupPw.getText().toString();
                String member_name = signupName.getText().toString();
                String member_qeustion =  signupQuestion.getText().toString();
                String member_answer = signupAnswer.getText().toString();
                String member_phonenum = signupPhoneNum.getText().toString();
                String member_signupPwConfirm =signupPwConfirm.getText().toString();


                JoinInsert joinInsert = new JoinInsert(member_id,member_pw, member_name, member_qeustion, member_answer, member_phonenum);
                try {
                    state = joinInsert.execute().get().trim();
                    Log.d("main:joinact0 : ", state);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(state.equals("1")){
                    Toast.makeText(SignUp.this, "회원가입 되었습니다", Toast.LENGTH_SHORT).show();
                    Log.d("main:joinact", "삽입성공 !!!");
                    finish();
                }else{

                    //아이디 유효성
                    if(!Pattern.matches("^[가-힣]{2,8}$", member_name))
                    {
                        Toast.makeText(SignUp.this,"이름을 한글 2-8자로 입력하세요.",Toast.LENGTH_SHORT).show();
                        signupName.setText("");
                        signupName.requestFocus();
                        return;
                    }


                    //아이디 유효성
                    if(!Pattern.matches("^[a-zA-Z0-9]{5,10}$", member_id))
                    {
                        Toast.makeText(SignUp.this,"아이디를 영문,숫자 5-10자로 입력하세요.",Toast.LENGTH_LONG).show();
                        signupId.setText("");
                        signupId.requestFocus();
                        return;
                    }

                    //패스워드
                    if(!Pattern.matches("^[a-zA-Z0-9]{8,12}$", member_pw))
                    {
                        Toast.makeText(SignUp.this,"패스워드는 영문,숫자 8-12자로 입력하세요.",Toast.LENGTH_LONG).show();
                        signupPw.setText("");
                        signupPw.requestFocus();
                        return;
                    }

                    if(TextUtils.isEmpty(signupPwConfirm.getText())){
                        Toast.makeText(SignUp.this,"비밀번호확인을 입력하세요",Toast.LENGTH_LONG).show();
                        signupPwConfirm.requestFocus();
                        return;
                    }

                    //패스워드와 패스워드확인 일치
                    if(!member_signupPwConfirm.equals(member_pw)){
                        Toast.makeText(SignUp.this,"비밀번호가 다릅니다",Toast.LENGTH_LONG).show();
                        signupPwConfirm.setText("");
                        signupPwConfirm.requestFocus();
                        return;
                    }

                    //비밀번호 답 확인

                    if(TextUtils.isEmpty(signupAnswer.getText())){
                        Toast.makeText(SignUp.this,"비밀번호 찾기답을 입력하세요",Toast.LENGTH_LONG).show();
                        signupAnswer.requestFocus();
                        return;
                    }

                    //핸드폰 유효성
                    if(!Pattern.matches("^01(?:0|1|[6-9])(\\d{3}|\\d{4})(\\d{4})$", member_phonenum))
                    {
                        Toast.makeText(SignUp.this,"올바른 핸드폰 번호가 아닙니다.",Toast.LENGTH_LONG).show();
                        signupPhoneNum.setText("");
                        signupPhoneNum.requestFocus();
                        return;
                    }



                   /*
                    Toast.makeText(SignUp.this, "정보를 입력해주세요", Toast.LENGTH_SHORT).show();*/
                    Log.d("main:joinact", "삽입실패 !!!");


                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    }

}
