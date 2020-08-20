package com.example.cteam.pet_add;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cteam.R;

public class petInsert extends AppCompatActivity {

    EditText petName, petAge,petWeight,petGender;
    Button btn_add,btnCancle;

    String name = "", age = "",date = "",weight = "",gender ="";
    Button btn_add, btnCancle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_add_insert);



    }
}
