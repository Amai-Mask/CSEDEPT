package com.example.ju_cse_short_circuit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class teacherprofile extends AppCompatActivity implements AdapterView.OnItemClickListener

{
    private ListView lv;
    public Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherprofile);
        String[] name = {
                "Dr. Md. Golam Moazzam","Dr. Jugal Krishna Das" , "Dr. Mohammad Shorif Uddin" , "Dr. Mohammad Zahidur Rahman ", "Dr. Md. Imdadul Islam ", "Dr. Liton Jude Rozario ","Dr. Md. Humayun Kabir","Dr. Md Abul Kalam Azad","Dr. Abu Sayed Md. Mostafizur Rahaman","Dr. Md. Ezharul Islam","Dr. Md. Musfique Anwar","Anup Majumder","Bulbul Ahammad","Mohammad Ashraful Islam"
        };

        lv = (ListView) findViewById(R.id.lv1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,name);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (i == 0) {

            Fragment fragment;
            fragment = new techaer1("teacher", 0);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1,fragment);
            fragmenttransaction.commit();


        }
       else if (i == 1) {

            Fragment fragment;
            fragment = new techaer1("teacher", 1);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1, fragment);
            fragmenttransaction.commit();


        }

       else if (i == 2) {

            Fragment fragment;
            fragment = new techaer1("teacher", 2);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1,fragment);
            fragmenttransaction.commit();


        }
       else if (i == 3) {

            Fragment fragment;
            fragment = new techaer1("teacher",3);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1,fragment);
            fragmenttransaction.commit();


        }
        else if (i == 4) {

            Fragment fragment;
            fragment = new techaer1("teacher",4);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1,fragment);
            fragmenttransaction.commit();


        }
        else if (i == 5) {

            Fragment fragment;
            fragment = new techaer1("teacher",5);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1,fragment);
            fragmenttransaction.commit();


        }
        else if (i == 6) {

            Fragment fragment;
            fragment = new techaer1("teacher",6);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1,fragment);
            fragmenttransaction.commit();


        }
        else if (i == 7) {

            Fragment fragment;
            fragment = new techaer1("teacher",7);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1,fragment);
            fragmenttransaction.commit();


        }
        else if (i == 8) {

            Fragment fragment;
            fragment = new techaer1("teacher",8);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1,fragment);
            fragmenttransaction.commit();


        }
        else if (i == 9) {

            Fragment fragment;
            fragment = new techaer1("teacher",9);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1,fragment);
            fragmenttransaction.commit();


        }
        else if (i == 10) {

            Fragment fragment;
            fragment = new techaer1("teacher",10);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1,fragment);
            fragmenttransaction.commit();


        }
        else if (i == 11) {

            Fragment fragment;
            fragment = new techaer1("teacher",11);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1,fragment);
            fragmenttransaction.commit();


        }
        else if (i == 12) {

            Fragment fragment;
            fragment = new techaer1("teacher",12);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1,fragment);
            fragmenttransaction.commit();


        }
        else {
            Fragment fragment;
            fragment = new techaer1("teacher",13);
            FragmentManager fragmentmanager = getSupportFragmentManager();

            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.replace(R.id.fid1,fragment);
            fragmenttransaction.commit();

        }



    }


}
