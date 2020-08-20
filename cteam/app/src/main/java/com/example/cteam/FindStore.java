package com.example.cteam;

import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

public class FindStore extends AppCompatActivity implements OnMapReadyCallback,
        NavigationView.OnNavigationItemSelectedListener {

    public GoogleMap mMap;
    MarkerOptions myMarker;

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



        //초기위치 농성동
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(
                new LatLng(35.1533, 126.8880)));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
        googleMap.animateCamera(zoom);
        mMap = googleMap;
    }

    //클릭시 지도 변경
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        //선택된 아이디를 얻는다
        int id = menuItem.getItemId();

        if (id == R.id.map_park) {
            onMap_park();
        }/*else if (id == R.id.map_hair){
            onMap_hair(mMap);
        }else if (id == R.id.map_hospital){
            onMap_hospital(mMap);
        }*/

        DrawerLayout drawer = findViewById(R.id.container);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //공원 핀
    private void onMap_park() {
/*        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions
              .position(new LatLng(37.555744, 126.970431))
              .title("서울역")
              .snippet("서울역입니다.")
              .icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
        mMap.addMarker(markerOptions);*/

        Location markerLocation = new Location("");
        markerLocation.setLatitude(35.15073);
        markerLocation.setLongitude(126.8889);
        showMyLocationMarker(markerLocation);

/*      LatLng h1 = new LatLng(35.15073, 126.887398);
        LatLng h2 = new LatLng(35.14548, 126.8837081);*/
    }

    //공원 핀 보여주기
    private void showMyLocationMarker(Location location) {
        if (myMarker == null) {
            myMarker = new MarkerOptions();
            myMarker.position(
                    new LatLng(location.getLatitude(),
                            location.getLongitude()));
            myMarker.title("◎ 내 위치\n");
            myMarker.snippet("여기가 어딘가?");
            myMarker.icon
                    (BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            mMap.addMarker(myMarker);
        }






/*    //미용실 핀
    private void onMap_hair(GoogleMap googleMap) {
        LatLng h1 = new LatLng(35.1535008, 126.8610185);
        LatLng h2 = new LatLng(35.1339805, 126.8537201);
    }

    //병원 핀
    private void onMap_hospital(GoogleMap googleMap) {
        LatLng h1 = new LatLng(35.1401319, 126.9046928);
        LatLng h2 = new LatLng(35.1643017, 126.9073783);
    }*/
    }
}