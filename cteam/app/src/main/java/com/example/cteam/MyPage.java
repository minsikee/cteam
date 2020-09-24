package com.example.cteam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cteam.ATask.LoginSelect;
import com.example.cteam.ATask.MypageUpdate;
import com.example.cteam.ATask.UpdateLogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;


public class MyPage extends Fragment {
    PetSelect activity;
    Button MyPage_ok;
    Button myPage_cancle;
    String member_id;
    String member_pw;
    String member_name;
    String member_phonenum;
    String member_question;
    String member_answer;

    Spinner Find_qs_spinner;
    SpinnerAdapter spinnerAdapter;
    String myPage_qs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (PetSelect) getActivity();
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_my_page,container,false);

        TextView myPage_name;
        TextView myPage_pw;
        TextView myPage_id;
        TextView myPage_qs_as;
        TextView myPage_phonenum;
        TextView myPage_pw_confirm;
        myPage_pw_confirm = rootView.findViewById(R.id.MyPage_pw_confirm);

        myPage_name = rootView.findViewById(R.id.MyPage_name);
        myPage_name.setText(Login.loginDTO.getMember_name());
        myPage_id = rootView.findViewById(R.id.MyPage_id);
        myPage_id.setText(Login.loginDTO.getMember_id());
        myPage_pw = rootView.findViewById(R.id.MyPage_pw);
        myPage_pw.setText(Login.loginDTO.getMember_pw());
        myPage_qs_as = rootView.findViewById(R.id.MyPage_qs_as);
        myPage_qs_as.setText(Login.loginDTO.getMember_answer());
        myPage_phonenum = rootView.findViewById(R.id.MyPage_phonenum);
        myPage_phonenum.setText(Login.loginDTO.getMember_phonenum());

        //데이터
        List<String> data = new ArrayList<>();
        data.add("롤모델의 이름"); data.add("당신의 꿈"); data.add("사랑하는 펫의 이름"); data.add("펫을 데려온 날짜"); data.add("인상깊게 본 영화의 제목");
        data.add(Login.loginDTO.getMember_qeustion());


        //UI생성
        Find_qs_spinner = rootView.findViewById(R.id.MyPage_qs);

        //Adapter
        spinnerAdapter = new com.example.cteam.Adapter.SpinnerAdapter(getActivity(),data);

        //Adapter 적용
        Find_qs_spinner.setAdapter(spinnerAdapter);

        //힌트 나타나게
        Find_qs_spinner.setSelection(5);


        myPage_qs= (String)Find_qs_spinner.getSelectedItem();

        //스피너가 선택한 값 받게
        Find_qs_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                myPage_qs=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        MyPage_ok = rootView.findViewById(R.id.MyPage_ok);
        MyPage_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String pw = myPage_pw.getText().toString();
               String confirm = myPage_pw_confirm.getText().toString();
               String phoneNum = myPage_phonenum.getText().toString();
                if(!pw.equals(confirm)){
                    myPage_pw.requestFocus();
                    myPage_pw.setCursorVisible(true);
                    Toast.makeText(getActivity(), "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                }else if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[.$@$!%*#?&])[A-Za-z\\d.$@$!%*#?&]{8,20}$", pw))
                    {
                        Toast.makeText(getActivity(),"비밀번호 형식을 지켜주세요.\n영문,숫자,특수문자를 합하여 8-20자리입니다",Toast.LENGTH_LONG).show();
                        myPage_pw.setText("");
                        myPage_pw.requestFocus();
                        myPage_pw.setCursorVisible(true);

                   }else  if(!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phoneNum))
                {
                    Toast.makeText(getActivity(),"올바른 핸드폰 번호가 아닙니다.\n010-0000-0000으로 적어주세요",Toast.LENGTH_SHORT).show();
                    myPage_phonenum.setText("");
                    myPage_phonenum.requestFocus();
                    myPage_phonenum.setCursorVisible(true);
                } else {

                    Login.loginDTO = null;
                    member_id = myPage_id.getText().toString();
                    member_pw = myPage_pw.getText().toString();
                    member_name = myPage_name.getText().toString();
                    member_question = myPage_qs;
                    member_answer = myPage_qs_as.getText().toString();
                    member_phonenum = myPage_phonenum.getText().toString();

                    try {
                        MypageUpdate mypageUpdate = new MypageUpdate(member_id, member_pw, member_name, member_question, member_answer, member_phonenum);
                        mypageUpdate.execute().get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }

                    LoginSelect loginSelect = new LoginSelect(member_id, member_pw);
                    try {
                        loginSelect.execute().get();

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getActivity(), "회원정보 수정이 완료되었습니다", Toast.LENGTH_LONG).show();
                    myPage_pw_confirm.setText(null);
                    activity.onFragmentChange(3, null );
                }
            }


        });

        myPage_cancle = rootView.findViewById(R.id.MyPage_cancel);
        myPage_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "회원정보 수정이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                activity.onFragmentChange(3, null );
            }
        });

        return rootView;
    }
}
