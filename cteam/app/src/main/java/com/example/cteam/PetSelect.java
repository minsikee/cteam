package com.example.cteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cteam.board.WalkBoard;
import com.example.lasttest.Help;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class PetSelect extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static MemberDTO dto = null;
    Calendar calendar;
    WalkBoard walkBoard;
    FindStore findStore;
    PetPhoto petPhoto;
    Toolbar toolbar;
    MyPage myPage;
    Help help;
    Logout logout;
    PetChoose petChoose;
    Pw_MyPage_Fragment pw_myPage_fragment;
    // MemberDTO dto;
    Bundle mBundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_select);
        checkDangerousPermissions(); //위험권한
        calendar=new Calendar();
        walkBoard = new WalkBoard();
        petPhoto = new PetPhoto();
        findStore = new FindStore();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                0, 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        petChoose = new PetChoose();
        pw_myPage_fragment = new Pw_MyPage_Fragment();
        myPage = new MyPage();
        help = new Help();
        logout = new Logout();

        //MemberDTO
        dto = new MemberDTO("jamong", "자몽", "0000", "보물 1호?", "나","010-0000-0000");

        //frameLayout 초기화
        getSupportFragmentManager().beginTransaction().replace(R.id.container, petChoose).commit();

        //드로워에 로그인 정보 표시하기
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String loginID = "자몽";
        View headerView = navigationView.getHeaderView(0);
        ImageView imageView = headerView.findViewById(R.id.profile);
        TextView tvloginID = headerView.findViewById(R.id.user_id);
        TextView tvloginName = headerView.findViewById(R.id.user_name);
        tvloginID.setText(dto.getId());
        tvloginName.setText(dto.getName());
        imageView.setImageResource(R.drawable.dog);

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
                        Intent intent = new Intent(getApplicationContext(),FindStore.class);
                        startActivity(intent);
                        break;
                    case R.id.tab3 :
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container,calendar).commit();
                        break;
                    case R.id.tab4 :
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, walkBoard).commit();
                        break;
                }

                return true;
            }
        });//선택됐을때

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        //선택된 아이디를 얻는다
        int id = menuItem.getItemId();

        if(id == R.id.nav_myPage){
            Bundle bundle = new Bundle();
            bundle.putSerializable("memberdto", dto);

            onFragmentSelected(0, bundle);
        }else if(id == R.id.nav_help){
            onFragmentSelected(1, null);
        }else if(id == R.id.nav_logout){
            onFragmentSelected(2, null);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);        //메뉴 누른뒤에 사라지는 것(스타트상태로)
        return true;
    }

    //프레그먼트가 선택 되었을 때 화면전환
    //번들은 보낼 데이터가 있을때 사용
    public void onFragmentSelected(int position, Bundle bundle){
        Fragment curFragment = null;

        if (position == 0){
            mBundle = bundle;
            curFragment = pw_myPage_fragment;
            toolbar.setTitle("마이페이지");
        }else if (position == 1){
            curFragment = help;
            toolbar.setTitle("도움말");
        }else if (position == 2){
            curFragment = logout;
            toolbar.setTitle("로그아웃");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container,curFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    public void onFragmentChange(int state, Bundle bundle){
        if(state == 1){
            mBundle = bundle;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, myPage).commit();
        }else if(state == 2){
            mBundle = bundle;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, petChoose).commit();
        }
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