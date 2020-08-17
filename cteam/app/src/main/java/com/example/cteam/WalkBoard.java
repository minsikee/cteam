package com.example.cteam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class WalkBoard extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //spinner 객체 생성
        final Spinner board_spinner = (Spinner) getView().findViewById(R.id.board_spinner);

        //values에 있는 array.xml의 item을 String 배열로 가져오기
        String[] str = getResources().getStringArray(R.array.board_SpinnerArray);

        //spinner_item.xml과 str을 인자로 어댑터 생성.
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, str);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        board_spinner.setAdapter(adapter);

        //spinner 이벤트 리스너
        board_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (board_spinner.getSelectedItemPosition() > 0) {
                    //선택된 항목
                    Log.v("알림", board_spinner.getSelectedItem().toString() + "is selected");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
    }
}