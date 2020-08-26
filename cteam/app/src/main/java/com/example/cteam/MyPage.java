package com.example.cteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cteam.MemberDTO;
import com.example.cteam.PetSelect;
import com.example.cteam.R;

public class MyPage extends Fragment {
    PetSelect activity;
    Bundle bundle = null;
    MemberDTO dto = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_my_page,container,false);

        activity = (PetSelect) getActivity();
        if(activity.mBundle != null){
            bundle = activity.mBundle;
            dto = (MemberDTO) bundle.getSerializable("memberdto");
            activity.mBundle = null;
        }

        TextView myPage_name;
        TextView myPage_id;
        TextView myPage_qs;
        TextView myPage_qs_as;
        TextView myPage_phonenum;

        myPage_name = rootView.findViewById(R.id.MyPage_name);
        myPage_name.setText(dto.getName());
        myPage_id = rootView.findViewById(R.id.MyPage_id);
        myPage_id.setText(dto.getId());
        myPage_qs = rootView.findViewById(R.id.MyPage_qs);
        myPage_qs.setText(dto.getFindPwAsk());
        myPage_qs_as = rootView.findViewById(R.id.MyPage_qs_as);
        myPage_qs_as.setText(dto.getFindAnswer());
        myPage_phonenum = rootView.findViewById(R.id.MyPage_phonenum);
        myPage_phonenum.setText(dto.getPhoneNum());


        return rootView;
    }

}
