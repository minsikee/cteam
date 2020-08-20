package com.example.cteam.board;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import com.example.cteam.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static com.example.cteam.R.array.board_SpinnerArray;

public class WalkBoard extends Fragment {

    Spinner spinner_board, spinner_City, spinner_Sigungu;
    ListView listView;
    ArrayAdapter<CharSequence> City_spinner, Sigungu_spinner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_walk_board,container, false);

        spinner_board = rootView.findViewById(R.id.board_spinner_board);
        listView = rootView.findViewById(R.id.board_list);
        ArrayList<String> list = new ArrayList<>();

        //게시판 선택창
        ArrayAdapter spinnerAdapter =
                ArrayAdapter.createFromResource(rootView.getContext(), board_SpinnerArray, android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_board.setAdapter(spinnerAdapter);

        spinner_board.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        //도시 선택
        spinner_City = rootView.findViewById(R.id.board_spinnercity);
        spinner_Sigungu = rootView.findViewById(R.id.board_spinnersigungu);
        Button board_search = rootView.findViewById(R.id.board_search);
        City_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region, android.R.layout.simple_spinner_dropdown_item);
        spinner_City.setAdapter(City_spinner);

        spinner_City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(City_spinner.getItem(i).equals("서울특별시")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_seoul,android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        }

                        public void onNothingSelected(AdapterView<?> adapterView) {//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                        }
                    });
                }else if(City_spinner.getItem(i).equals("부산광역시")) {
                    Sigungu_spinner = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_region_incheon,android.R.layout.simple_spinner_dropdown_item);
                    Sigungu_spinner.setDropDownViewResource(android.R.layout.simple_spinner_item);
                    spinner_Sigungu.setAdapter(Sigungu_spinner);
                    spinner_Sigungu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        }

                        public void onNothingSelected(AdapterView<?> adapterView) {//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                        }
                    });
                }
            }
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

        //글등록 플로팅액션버튼 → 글쓰기 Activity로 이동
        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(),BoardWrite.class);
                startActivity(intent);
            }
        });
        return rootView;
    }





     /*   Spinner board_spinner;
        board_spinner = rootView.findViewById(R.id.board_spinner);
*/

}