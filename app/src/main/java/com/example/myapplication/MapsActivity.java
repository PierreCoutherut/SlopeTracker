package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DatabaseHelper db = new DatabaseHelper(this);
    List<Point> points = new ArrayList<>();
    //getPointWhereIdSession

    //Ajouter la liste de getPointWhereIdSession dans la liste points  et extraire la latitude & la longitude pour la mettre dans "new LatLng(,),"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //test
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        //Ajouter la liste de getPointWhereIdSession dans la liste points  et extraire la latitude & la longitude pour la mettre dans "new LatLng(,),"

                              //getPointWhereIdSession
                        new LatLng(47.767221, 6.773356),
                        new LatLng(47.767842, 6.773949),
                        new LatLng(47.768556, 6.774343),
                        new LatLng(47.769544, 6.774761),
                        new LatLng(47.770193, 6.775018),
                        new LatLng(47.771605, 6.774958),
                        new LatLng(47.772045, 6.774988)
                )
        );
        // Add a marker in Sydney and move the camera  47.63499316398847,
        LatLng first = new LatLng(47.767221, 6.773356);
        //mMap.addMarker(new MarkerOptions().position(Belfort).title("Belfort"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(first));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(first, 15.0f));
    }
}