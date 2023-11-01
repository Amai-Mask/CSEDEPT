package com.example.ju_cse_short_circuit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ju_cse_short_circuit.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.messaging.FirebaseMessaging;

public class examroutine extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nothome);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // as soon as the application opens the first
        // fragment should be shown to the user
        // in this case it is algorithm fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new nohome()).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);

        if (fragment instanceof nohome) {
            // Handle back button press logic specific to your fragment here
            nohome nohomeFragment = (nohome) fragment;
            if (!nohomeFragment.canGoBack) {
                // Prevent going back without logging out
                Toast.makeText(this, "Sign Out!", Toast.LENGTH_SHORT).show();
            } else {
                super.onBackPressed(); // Call the superclass method to handle the back button press
            }
        } else {
            super.onBackPressed(); // Call the superclass method to handle the back button press for other fragments
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        // By using switch we can easily get
        // the selected fragment
        // by using there id.
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.h1) {
            selectedFragment = new nohome();
        } else if (itemId == R.id.e1) {
            selectedFragment = new ExamFragment();
        } else if (itemId == R.id.a1) {
            selectedFragment = new AssFragment();
        }
        // It will help to replace the
        // one fragment to other.
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
        }
        return true;
    };
}

