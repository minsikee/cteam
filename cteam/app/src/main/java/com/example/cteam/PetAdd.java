package com.example.cteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.cteam.Adapter.petAddAdapter;
import com.example.cteam.Dto.MyItem;
import com.example.cteam.pet_add.petInsert;

import java.util.ArrayList;

public class PetAdd extends AppCompatActivity {
    public static MyItem selItem = null;

    Button btnAdd;
    Button btnUpdate;
    ListView petListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_add);




        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        petListView = findViewById(R.id.petListView);

        // 임의의 리스트뷰 아이템 추가
        ArrayList<MyItem> items = new ArrayList<>();
        //MyItem myItem = new MyItem();
        //items.add(myItem);

        // 어댑터 생성
        petAddAdapter adapter = new petAddAdapter(PetAdd.this, items);

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