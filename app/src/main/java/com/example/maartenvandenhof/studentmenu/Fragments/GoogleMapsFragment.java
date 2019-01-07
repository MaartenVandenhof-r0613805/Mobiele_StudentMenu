package com.example.maartenvandenhof.studentmenu.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.maartenvandenhof.studentmenu.Activities.MainActivity;
import com.example.maartenvandenhof.studentmenu.PlaceAutocompleteAdapter;
import com.example.maartenvandenhof.studentmenu.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleMapsFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    private FragmentActivity myContext;
    private static final String TAG = "GoogleMap Fragment";
    public GoogleMap mMap;
    public LatLngBounds mMapBoundary;
    public AutoCompleteTextView mSearchText;
    public PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    public GoogleApiClient mGoogleApiClient;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(new LatLng(-40, -168), new LatLng(71,136));

    //private FusedLocationProviderClient mFusedLocationClient;

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_google_maps, container, false);
        mSearchText = (AutoCompleteTextView) v.findViewById(R.id.input_search);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Local Stores");
        mSearchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geoLocate();
            }
        });
        //mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double bottomBoundary = ((MainActivity)getActivity()).lat -.01;
        double leftBoundary = ((MainActivity)getActivity()).lon - .01;
        double topBoundary = ((MainActivity)getActivity()).lat + .01;
        double rightBoundary = ((MainActivity)getActivity()).lon + .01;

        mMapBoundary = new LatLngBounds(new LatLng(bottomBoundary, leftBoundary), new LatLng(topBoundary, rightBoundary));
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(mMapBoundary, 0));
        // Add a marker in Sydney and move the camera
        LatLng yLocation = new LatLng(((MainActivity)getActivity()).lat, ((MainActivity)getActivity()).lon);
        mMap.addMarker(new MarkerOptions().position(yLocation).title("Your location"));
        init();


    }

    public void geoLocate(){
        Log.d(TAG, "geoLocate: geolocating");

        String searchString = mSearchText.getText().toString();
        Geocoder geocoder = new Geocoder((MainActivity)getContext());
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.d(TAG, "geoLocate: IOException: " + e.getMessage());
        }

        if(list.size() > 0){
            Address address = list.get(0);
            Log.d(TAG, "geoLocate found location" + address.toString());

            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), 15, address.getAddressLine(0));
        }
    }

    public void init(){
        Log.d(TAG, "init: initializing");

        //mGoogleApiClient = new GoogleApiClient.Builder((MainActivity)getContext()).addApi(Places.GEO_DATA_API).addApi(Places.PLACE_DETECTION_API).enableAutoManage((MainActivity)getContext(),this).build();

        //mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter((MainActivity)getContext(), mGoogleApiClient, LAT_LNG_BOUNDS, null);

        mGoogleApiClient = new GoogleApiClient
                .Builder((MainActivity)getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage((MainActivity)getContext(), this)
                .build();

        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter((MainActivity)getContext(), Places.getGeoDataClient(getContext().getApplicationContext()),
                LAT_LNG_BOUNDS, null);

        //mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(getContext(), Places.getGeoDataClient(getContext(), null), LAT_LNG_BOUNDS, null);

        mSearchText.setAdapter(mPlaceAutocompleteAdapter);

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN || event.getAction() == KeyEvent.KEYCODE_ENTER){
                    //execute our method for searching
                    geoLocate();
                }
                return false;
            }
        });
    }

    public void moveCamera(LatLng latLng, float zoom, String title){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        MarkerOptions options = new MarkerOptions().position(latLng).title(title);
        mMap.addMarker(options);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPause() {
        super.onPause();
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }
}