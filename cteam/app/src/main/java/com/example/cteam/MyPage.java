package com.example.cteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MyPage extends Fragment {
    PetSelect activity;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_my_page,container,false);

        TextView myPage_name;
        TextView myPage_id;
        TextView myPage_qs;
        TextView myPage_qs_as;
        TextView myPage_phonenum;

        myPage_name = rootView.findViewById(R.id.MyPage_name);
        myPage_name.setText(Login.loginDTO.getMember_name());
        myPage_id = rootView.findViewById(R.id.MyPage_id);
        myPage_id.setText(Login.loginDTO.getMember_id());
        myPage_qs = rootView.findViewById(R.id.MyPage_qs);
        myPage_qs.setText(Login.loginDTO.getMember_qeustion());
        myPage_qs_as = rootView.findViewById(R.id.MyPage_qs_as);
        myPage_qs_as.setText(Login.loginDTO.getMember_answer());
        myPage_phonenum = rootView.findViewById(R.id.MyPage_phonenum);
        myPage_phonenum.setText(Login.loginDTO.getMember_phonenum());


        return rootView;
    }

}
