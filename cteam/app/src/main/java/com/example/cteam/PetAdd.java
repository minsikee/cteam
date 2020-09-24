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
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.cteam.Login.loginDTO;

public class PetAdd extends AppCompatActivity {
    public static PetDTO dto = null;

    ListSelect listSelect;
    Button btnAdd;
    Button btnUpdate;

    ArrayList<PetDTO> petList;

    RecyclerView recyclerView;
    petAddAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_add);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);

        petList = new ArrayList<>();
        adapter = new petAddAdapter(this, petList);
        recyclerView = findViewById(R.id.petListView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PetAdd.this, petInsert.class);
                startActivity(intent);
            }
        });

        listSelect = new ListSelect(loginDTO.getMember_id(), petList, adapter );
        try {
            listSelect.execute().get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}