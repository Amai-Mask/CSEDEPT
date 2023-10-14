package com.example.ju_cse_short_circuit;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class stuff1 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private int mParam2;

    // Change the constructor signature as suggested by Android Studio
    public stuff1(String param1, int param2) {
        mParam1 = param1;
        mParam2 = param2;
    }

    public static stuff1 newInstance(String param1, int param2) {
        stuff1 fragment = new stuff1(param1, param2); // Use the constructor with parameters
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    Button btn1,btn2;
    private static final int PERMISSION_CODE = 100;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove the parameter retrieval from the getArguments() method
        // as we are now using the constructor with parameters
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stuff1, container, false);
        // You can access the parameters directly using mParam1 and mParam2
        // For example, set the text of a TextView in the layout to display the values:

        btn1 = rootView.findViewById(R.id.callbtn11);
        btn2 = rootView.findViewById(R.id.emailbtn11);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the CALL_PHONE permission is granted
                if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    // If permission is granted, make the phone call
                    String phone;
                        phone = getString(R.string.officephone);


                    makePhoneCall(String.valueOf(phone));

                } else {
                    // If permission is not granted, request the permission
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CODE);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email;
                email = getString(R.string.officeemail);
                String[] str = {email};
                sendEmail(str);
            }
        });




        TextView textview = rootView.findViewById(R.id.fid1_tv11);
        ImageView imageview = rootView.findViewById(R.id.fid1_iv11);

        btn1.setText(R.string.officephone);
        btn2.setText(R.string.officeemail);
        if(mParam2 == 0)
        {

            imageview.setImageResource(R.drawable.contact);
            textview.setText(R.string.stuff1);

           // btn1.setText(R.string.phone1);
           // btn2.setText(R.string.email1);



        }
        else if(mParam2 == 1)
        {

            imageview.setImageResource(R.drawable.contact);
            textview.setText(R.string.stuff2);




        }
        else if(mParam2 ==2)
        {

            imageview.setImageResource(R.drawable.contact);
            textview.setText(R.string.stuff3);




        }
        else if(mParam2 ==3)
        {

            imageview.setImageResource(R.drawable.contact);
            textview.setText(R.string.stuff4);


        }
        else {

            imageview.setImageResource(R.drawable.contact);
            textview.setText("stuff5");






        }

        return rootView;
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            // If the permission is granted, make the phone call
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                String phone;
                if(mParam2==0)
                    phone = getString(R.string.phone1);
                else if(mParam2==1)
                    phone = getString(R.string.phone2);
                else if(mParam2==2)
                    phone = getString(R.string.phone3);
                else if(mParam2==3)
                    phone = getString(R.string.phone4);

                else phone = getString(R.string.phone5);

                makePhoneCall(String.valueOf(phone));

            } else {
                // If the permission is denied, you may show a message or handle it accordingly
            }
        }
    }

    private void makePhoneCall(String phoneNumber){

        // String phoneNumber = "1234567890"; // Replace this with the actual phone number you want to call
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private void sendEmail( String[] recipientEmail) {
        String subject = "Subject";
        String body = "Body";



        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, recipientEmail);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);

        if (intent.resolveActivity(requireContext().getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Choose an Email client:"));
        } else {
            Toast.makeText(requireContext(), "No app found to handle the email.", Toast.LENGTH_SHORT).show();
        }

        startActivity(Intent.createChooser(intent, "Choose an Email client:"));
    }



}
