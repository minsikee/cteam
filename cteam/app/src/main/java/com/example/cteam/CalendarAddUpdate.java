package com.example.cteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cteam.Dto.CalendarDTO;

public class CalendarAddUpdate extends Fragment {

    PetSelect activity;

    CalendarDTO dto;
    Bundle bundle = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_calendar_add_update, container, false);

        activity = (PetSelect) getActivity();





        return rootView;

    } //onCreateView()

}