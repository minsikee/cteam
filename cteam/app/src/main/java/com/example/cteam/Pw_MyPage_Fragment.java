package com.example.cteam;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class Pw_MyPage_Fragment extends Fragment {
    PetSelect activity;
    Button pw_btn;
    Button pw_cancleBtn;
    EditText editPW;
    MemberDTO dto;
    Bundle bundle = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.pw_mypage,container,false);

        activity = (PetSelect) getActivity();

        if(activity.mBundle != null){
            bundle = activity.mBundle;
            dto = (MemberDTO) bundle.getSerializable("memberdto");
            activity.mBundle = null;
        }

        editPW = rootView.findViewById(R.id.edPW);
        pw_btn = rootView.findViewById(R.id.pw_btn);
        pw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editPW.getText().toString().equals(dto.getPw())){
                    activity.onFragmentChange(1, bundle);
                }else {
                   // Toast.makeText(activity, "비밀번호를 다시 입력하세요!", Toast.LENGTH_LONG).show();
                    showMessage();
                }
            }
        });
        pw_cancleBtn = rootView.findViewById(R.id.pw_cancleBtn);
        pw_cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(2, null);
            }
        });
        return rootView;

    }
    private void showMessage() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(activity);
        builder.setTitle("안내");
        builder.setMessage("비밀번호를 다시 입력하세요!");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setNeutralButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
