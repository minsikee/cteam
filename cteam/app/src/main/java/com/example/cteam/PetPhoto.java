package com.example.cteam;

import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cteam.ATask.CalListSelect;
import com.example.cteam.ATask.PetPhotoListSelect;
import com.example.cteam.Adapter.PetPhotoAdapter;
import com.example.cteam.Dto.CalendarDTO;
import com.example.cteam.Dto.PetPhotoDTO;
import com.example.cteam.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.cteam.Common.CommonMethod.isNetworkConnected;

public class PetPhoto extends Fragment {

    public static PetPhotoDTO selectPetPhoto = null;


    ImageButton photoPlus, photoMinus;


    RecyclerView petPhoto_View;
    ArrayList<PetPhotoDTO> petPhotos;
    PetPhotoAdapter petPhotoAdapter;

    PetPhotoListSelect petPhotoListSelect;

    //java & xml 연동
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pet_photo, container, false);

        //리사이클러 뷰 셋팅
        petPhotos = new ArrayList<>();
        petPhotoAdapter = new PetPhotoAdapter(getActivity(), petPhotos);
        petPhoto_View = (RecyclerView) root.findViewById(R.id.petPhotoRecyclerView);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //CalendarAdd_view.setLayoutManager(layoutManager);
        petPhoto_View.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(petPhoto_View.getContext(),new LinearLayoutManager(getContext()).getOrientation());
//          // 리싸이클러뷰 구분선

        petPhoto_View.addItemDecoration(dividerItemDecoration);
        //구분선 추가

        RecyclerDecoration spaceDecoration = new RecyclerDecoration();
        petPhoto_View.addItemDecoration(spaceDecoration);


        //간격
        petPhoto_View.addItemDecoration(new RecyclerDecoration());


        petPhoto_View.setAdapter(petPhotoAdapter);





        //데이터 불러옴

        String pet_name="자몽";

        if(isNetworkConnected(getActivity()) == true) {
            petPhotoListSelect = new PetPhotoListSelect(petPhotos, petPhotoAdapter, pet_name);
            try {
                petPhotoListSelect.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }




        return root;
    }


}