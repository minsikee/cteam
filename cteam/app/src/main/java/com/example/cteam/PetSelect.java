package com.example.cteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class PetSelect extends AppCompatActivity {
    Calendar calendar;
    WalkBoard walkBoard;
    FindStore findStore;
    PetPhoto petPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_select);
        checkDangerousPermissions(); //위험권한
        calendar=new Calendar();
        walkBoard = new WalkBoard();
        petPhoto = new PetPhoto();
        findStore = new FindStore();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, calendar).commit(); //첫화면에 프래그먼트 1이 나오게

        BottomNavigationView bottomNavigationView =
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.tab1 :
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container,petPhoto).commit();
                        break;
                    case R.id.tab2 :
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container,findStore).commit();
                        break;
                    case R.id.tab3 :
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container,calendar).commit();
                        break;
                    case R.id.tab4 :
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container,walkBoard).commit();
                        break;
                }

                return true;
            }
        });//선택됐을때



    }

    private void checkDangerousPermissions() {  //위험권한
        String[] permissions = {
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.VIBRATE

        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}