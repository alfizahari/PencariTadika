package com.example.pencaritadika;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.FirebaseDatabase;


public class recfragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView recview;
    com.example.pencaritadika.myadapter adapter;
    public recfragment() {

    }

    public static com.example.pencaritadika.recfragment newInstance(String param1, String param2) {
        com.example.pencaritadika.recfragment fragment = new com.example.pencaritadika.recfragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recfragment, container, false);

        recview=(RecyclerView)view.findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<com.example.pencaritadika.model> options =
                new FirebaseRecyclerOptions.Builder<com.example.pencaritadika.model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("kindergarten"), com.example.pencaritadika.model.class)
                        .build();

        adapter=new com.example.pencaritadika.myadapter(options, mParam1);
        recview.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}