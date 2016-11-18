package com.ericwei.mosquitosquadron;

import android.app.Fragment;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;



public class MapDetailsFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private MapView mapView;
    private Geocoder mGeocoder;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LOC_NAME = "param1";

    // TODO: Rename and change types of parameters
    private String mLocationName;

    public MapDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MapDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapDetailsFragment newInstance(String param) {
        MapDetailsFragment fragment = new MapDetailsFragment();
        Bundle args = new Bundle();
        args.putString(LOC_NAME, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGeocoder = new Geocoder(getActivity());

        if (getArguments() != null) {
            mLocationName = getArguments().getString(LOC_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_map_details, container, false);
        View view = inflater.inflate(R.layout.fragment_map_details, container, false);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        List<Address> addressList = null;

        try {
            if (mLocationName.equals("Royal Canadian Legion Branch 66")) {
                addressList = mGeocoder.getFromLocationName("6 Spring Garden Ave M2N 3G2", 1);
            } else {
                addressList = mGeocoder.getFromLocationName(mLocationName, 1);
            }

            if (addressList.size() > 0) {
                double latitude = addressList.get(0).getLatitude();
                double longitude = addressList.get(0).getLongitude();

                // Add a marker in Sydney and move the camera
                LatLng toronto = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(toronto).title(mLocationName));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(toronto));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}