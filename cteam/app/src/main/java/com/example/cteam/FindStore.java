package com.example.cteam;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class FindStore extends AppCompatActivity implements OnMapReadyCallback,
        NavigationView.OnNavigationItemSelectedListener {

    public GoogleMap mMap;

    //지도 띄우기

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_find_store);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //핀 꼽기 실행
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    //클릭시 지도 변경
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        //선택된 아이디를 얻는다
        int id = menuItem.getItemId();

        if (id == R.id.map_hospital){
            onMap_hospital();
        }else if (id == R.id.map_hair){
            onMap_hair();
        }else if (id == R.id.map_park){
            onMap_park();
        }

        DrawerLayout drawer = findViewById(R.id.container);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //공원 핀
    private void onMap_park() {
        LatLng h1 = new LatLng(223, 225);
        mMap.addMarker(new MarkerOptions().position(h1).title("42517452"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(h1));

        LatLng h2 = new LatLng(27, 25);
        mMap.addMarker(new MarkerOptions().position(h2).title("ㄴㅇㅀㄴㄹ"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(h2));
    }

    //미용실 핀
    private void onMap_hair() {
        LatLng h1 = new LatLng(50, 37);
        mMap.addMarker(new MarkerOptions().position(h1).title("fghjdcyr"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(h1));

        LatLng h2 = new LatLng(86, 15);
        mMap.addMarker(new MarkerOptions().position(h2).title("Ssgrs"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(h2));
    }

    //병원 핀
    private void onMap_hospital() {
        LatLng h1 = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(h1).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(h1));

        LatLng h2 = new LatLng(100, 151);
        mMap.addMarker(new MarkerOptions().position(h2).title("Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(h2));
    }
}