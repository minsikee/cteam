package com.example.cteam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.cteam.ATask.ListSelect;
import com.example.cteam.Adapter.petAddAdapter;
import com.example.cteam.Dto.PetDTO;
import com.example.cteam.pet_add.petInsert;

import java.util.ArrayList;

public class PetAdd extends AppCompatActivity {
    public static PetDTO selItem = null;

    Button btnAdd;
    Button btnUpdate;
    ListView petListView;

    ArrayList<PetDTO> myItemArrayList;

    RecyclerView recyclerView;
    petAddAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_add);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        petListView = findViewById(R.id.petListView);


        myItemArrayList = new ArrayList();
        adapter = new petAddAdapter(this, myItemArrayList);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        petListView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PetAdd.this, petInsert.class);
                startActivity(intent);
            }
        });

    }





}