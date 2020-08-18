package com.example.cteam;

import android.os.Bundle;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static com.example.cteam.R.array.board_SpinnerArray;

public class WalkBoard extends Fragment {

    Spinner spinner, spinnerCity, spinnerSigungu;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //spinner 객체 생성

 /*
        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(this, board_SpinnerArray,android.R.layout.simple_spinner_dropdown_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        board_spinner.setAdapter(yearAdapter);
*/



        //values에 있는 array.xml의 item을 String 배열로 가져오기
        //String[] str = getResources().getStringArray(R.array.board_SpinnerArray);

        //spinner_item.xml과 str을 인자로 어댑터 생성.
        /*final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, str);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        board_spinner.setAdapter(adapter);*/



        //spinner 이벤트 리스너
     /*   board_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

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

        });*/
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_walk_board,container, false);

        spinner = rootView.findViewById(R.id.board_spinner);
        listView = rootView.findViewById(R.id.board_list);
        ArrayList<String> list = new ArrayList<>();

        //게시판 선택창
        ArrayAdapter spinnerAdapter =
                ArrayAdapter.createFromResource(this.getActivity(), board_SpinnerArray, android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


/*        //게시판 목록
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        list.add("목록1");
        list.add("목록2");
        list.add("목록3");
        list.add("목록4");
        list.add("목록5");
        list.add("목록6");
        list.add("목록7");*/

        return rootView;
    }





     /*   Spinner board_spinner;
        board_spinner = rootView.findViewById(R.id.board_spinner);
*/

}