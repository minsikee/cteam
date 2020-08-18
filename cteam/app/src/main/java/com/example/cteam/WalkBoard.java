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