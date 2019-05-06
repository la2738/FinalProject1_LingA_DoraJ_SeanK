package com.example.android.finalproject_linga_doraj_seank;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class NearbyFragment extends Fragment implements OnMapReadyCallback , View.OnClickListener {
    private static final String RETAIL_STORE = "Retail Store";
    private static final String HANDMAN = "HandyMan";
    private static final String DIY_WORDSHOP = "DIY Workshop";
    private static int locationRequestCode = 1000;

    private double currentLatitude = 0.0, currentLongitude = 0.0;
    private int currentZoom = 10;

    private GoogleMap map;
    private MapFragment mapFragment;

    private View rootView ;
    private FusedLocationProviderClient mFusedLocationClient;
    private Context context;
    private AutoCompleteTextView autoCompleteTextView;
    private List<PlaceItem> placeList ;
    private List<Marker> markers ;

    private void InitMap() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_nearby, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.rootView = view ;

        autoCompleteTextView = (AutoCompleteTextView)view.findViewById(R.id.autocompleteTextView);
        fillPlaces();
        setupSearchBox();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        getCurrentLocation();

        // assign button click handler
        view.findViewById(R.id.btnRetailStore).setOnClickListener(this);
        view.findViewById(R.id.btnHandyMan).setOnClickListener(this);
        view.findViewById(R.id.btnDIY).setOnClickListener(this);
        view.findViewById(R.id.btnMore).setOnClickListener(this);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context ;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null ;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        markers = new ArrayList<>();    //initialize
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnRetailStore:
                showLabels(RETAIL_STORE);
                break;
            case R.id.btnHandyMan:
                showLabels(HANDMAN);
                break;
            case R.id.btnDIY:
                showLabels(DIY_WORDSHOP);
                break;
            case R.id.btnMore:
                showLabels("");
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                currentLatitude = location.getLatitude();
                                currentLongitude = location.getLongitude();
                                showCurrentLocation();
                            }
                        }
                    });

                } else {
                    Snackbar.make(rootView, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void getCurrentLocation() {
        // check permission
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // reuqest for permission
            ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    locationRequestCode);

        } else {
            // already permission granted
            mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        currentLatitude = location.getLatitude();
                        currentLongitude = location.getLongitude();
                        showCurrentLocation();
                    }
                }
            });

        }
    }

    private void showCurrentLocation() {

        LatLng currentLoc = new LatLng(this.currentLatitude, this.currentLongitude);
//        LatLng currentLoc = new LatLng(24.80361, 120.96861);    //hsinchu
        Marker marker = map.addMarker(new MarkerOptions().position(currentLoc).title("Hello, I am here."));
        map.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
    }



    private void setupSearchBox() {
        List<String> names = new ArrayList<>();
        for (PlaceItem p : this.placeList) {
            names.add(p.getName());
        }

        String[] targetNames = names.toArray(new String[0]);

        // Create the object of ArrayAdapter with String
        // which hold the data as the list item.
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this.context, android.R.layout.select_dialog_item, targetNames);

        // Give the suggestion after 1 words.
        autoCompleteTextView.setThreshold(1);

        // Set the adapter for data as a list
        autoCompleteTextView.setAdapter(adapter);



        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<PlaceItem> places = new ArrayList<>();
                String targetName = parent.getItemAtPosition(position).toString();
                for(PlaceItem p : placeList) {
                    if (p.getName().equals(targetName)) {
                        places.add(p);
                        break;
                    }
                }
                showMarkers(places);
            }
        });
    }


    private void showLabels(String label) {
        List<PlaceItem> result = new ArrayList<>();
        for(PlaceItem pi : placeList) {
            if (pi.getLabel().equals(label)) {
                result.add(pi);
            }
        }
        showMarkers(result);
    }


    private void showMarkers(List<PlaceItem> places) {
        this.clearAllMarkers();

        if (places.size() == 0) {
            return ;
        } else if (places.size() == 1) {
            PlaceItem pi = places.get(0);
            Marker marker = map.addMarker(new MarkerOptions().position(pi.getLatLng()).title(pi.getName()));
            markers.add(marker);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 16));
        }
        else {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            for(PlaceItem pi : places) {
                Marker marker = map.addMarker(new MarkerOptions().position(pi.getLatLng()).title(pi.getName()));
                markers.add(marker);
                builder.include(marker.getPosition());
            }

            LatLngBounds bounds = builder.build();
            int padding = 0; // offset from edges of the map in pixels
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            map.moveCamera(cu);
        }

        //hide soft keyboard
        hideKeyboardFrom(context, autoCompleteTextView);

    }

    private void clearAllMarkers() {
        for(Marker marker : markers) {
            marker.remove();
        }
        markers.clear();
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void fillPlaces() {
        this.placeList = new ArrayList<>();
        placeList.add(new PlaceItem("The Home Depot-Manhattan 59th Street", 40.742289, -73.991408 , RETAIL_STORE ));
        placeList.add(new PlaceItem("The Home Depot-Manhattan West 23rd Street", 40.761837, -73.966938 , RETAIL_STORE ));
        placeList.add(new PlaceItem("The Home Depot-Bronx Terminal", 40.825766, -73.930170 , RETAIL_STORE ));
        placeList.add(new PlaceItem("The Home Depot-Secaucus", 40.785353, -74.049820 , RETAIL_STORE ));
        placeList.add(new PlaceItem("Lowe's HomeImprovement-North Bergen",40.812131, -74.019559, RETAIL_STORE));
        placeList.add(new PlaceItem("Lowe's HomeImprovement-Gowanus",40.677233, -73.993579, RETAIL_STORE));
        placeList.add(new PlaceItem("Lowe's HomeImprovement-Carlstadt",40.834585, -74.090554, RETAIL_STORE));
        placeList.add(new PlaceItem("Costco Wholesale-East Harlem",40.800007, -73.931344, RETAIL_STORE));

        placeList.add(new PlaceItem("NYC 24/7 HandyMan", 40.806941, -73.957456 , HANDMAN ));
        placeList.add(new PlaceItem("Accurate Handyman & Construction", 40.804820, -73.967218 , HANDMAN ));
        placeList.add(new PlaceItem("Handyman Same Day Upper West Side", 40.791228, -73.978348 , HANDMAN ));

        placeList.add(new PlaceItem("City Tech College Continuing Education- Classes include basic home repair and basic plumbing", 40.699517, -73.985974 , DIY_WORDSHOP ));
        placeList.add(new PlaceItem("Bronx Community College- Classes include basic boiler care and lock repair", 40.858366, -73.912740 , DIY_WORDSHOP ));
    }
}
