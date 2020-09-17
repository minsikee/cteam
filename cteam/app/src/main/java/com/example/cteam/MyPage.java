package com.example.cteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cteam.ATask.MypageUpdate;

import java.io.IOException;


public class MyPage extends Fragment {
    PetSelect activity;
    Button MyPage_ok;
    String member_id;
    String member_pw;
    String member_name;
    String member_phonenum;
    String member_question;
    String member_answer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_my_page,container,false);

        TextView myPage_name;
        TextView myPage_pw;
        TextView myPage_id;
        TextView myPage_qs;
        TextView myPage_qs_as;
        TextView myPage_phonenum;

        myPage_name = rootView.findViewById(R.id.MyPage_name);
        myPage_name.setText(Login.loginDTO.getMember_name());
        myPage_id = rootView.findViewById(R.id.MyPage_id);
        myPage_id.setText(Login.loginDTO.getMember_id());
        myPage_pw = rootView.findViewById(R.id.MyPage_pw);
        myPage_pw.setText(Login.loginDTO.getMember_pw());
        myPage_qs = rootView.findViewById(R.id.MyPage_qs);
        myPage_qs.setText(Login.loginDTO.getMember_qeustion());
        myPage_qs_as = rootView.findViewById(R.id.MyPage_qs_as);
        myPage_qs_as.setText(Login.loginDTO.getMember_answer());
        myPage_phonenum = rootView.findViewById(R.id.MyPage_phonenum);
        myPage_phonenum.setText(Login.loginDTO.getMember_phonenum());

        MyPage_ok = rootView.findViewById(R.id.MyPage_ok);
        MyPage_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                member_id = myPage_id.getText().toString();
                member_pw = myPage_pw.getText().toString();
                member_name = myPage_name.getText().toString();
                member_question = myPage_qs.getText().toString();
                member_answer = myPage_qs_as.getText().toString();
                member_phonenum = myPage_phonenum.getText().toString();

                MypageUpdate mypageUpdate = null;
                try {
                    mypageUpdate = new MypageUpdate(member_id,member_pw,member_name, member_question,member_answer,member_phonenum);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mypageUpdate.execute();
            }
        });

        return rootView;

    }

}
