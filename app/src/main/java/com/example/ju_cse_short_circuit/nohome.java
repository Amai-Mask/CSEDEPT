package com.example.ju_cse_short_circuit;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class nohome extends Fragment implements View.OnClickListener{

    private Button logout;
    public boolean canGoBack = false;
    private FirebaseAuth firebaseAuth;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public nohome() {
        // Required empty public constructor

    }

    // TODO: Rename and change types and number of parameters
    public static nohome newInstance(String param1, String param2) {
        nohome fragment = new nohome();

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
        View view = inflater.inflate(R.layout.fragment_nohome, container, false);
        logout = view.findViewById(R.id.logoutid);
        firebaseAuth = FirebaseAuth.getInstance();
        logout.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }



    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.logoutid)
            logOut();
    }
//    public void onBackPressed() {
//        if (!canGoBack) {
//            // Prevent going back without logging out
//            Toast.makeText(getActivity(), "Sign Out!", Toast.LENGTH_SHORT).show();
//        } else {
//            super.onBackPressed(); // Call the superclass method to handle the back button press
//        }
//    }

    private void logOut() {
        firebaseAuth.signOut();
        Toast.makeText(getActivity(), "Sign Out!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}