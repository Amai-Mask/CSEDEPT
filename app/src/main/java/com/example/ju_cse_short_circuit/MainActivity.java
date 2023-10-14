package com.example.ju_cse_short_circuit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private CardView CV1,CV2,CV3,CV4,CV5,CV6;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        CV1=(CardView) findViewById(R.id.cv1);
        CV2=(CardView) findViewById(R.id.cv2);
        CV3=(CardView) findViewById(R.id.cv3);
        CV4=(CardView) findViewById(R.id.cv4);
        CV5=(CardView) findViewById(R.id.cv5);
        CV6=(CardView) findViewById(R.id.cv6);



        FirebaseMessaging.getInstance().subscribeToTopic("juweblet")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }

                    }
                });

        String serverKey = "AAAAIzeDjs8:APA91bG4x_jrdITBQpcN6kYIBGsSaezAhblrpJyMcO6ctk7KUvFVUBzW1KhKBpfzfcweAIKqVwkC8K9tpuPxwnVha1EGZFr5XNTd3tSVm0-aCefRL_A7PJhPr0JtTl07UDxE2vzpjVpi";



        CV1.setOnClickListener(this);
        CV2.setOnClickListener(this);
        CV3.setOnClickListener(this);
        CV4.setOnClickListener(this);
        CV5.setOnClickListener(this);
        CV6.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
    if(view.getId()==R.id.cv1)
    {
     Intent intent= new Intent(MainActivity.this, officials.class);
     startActivity(intent);

    }


    else if(view.getId()==R.id.cv2)
    {
            Intent intent= new Intent(MainActivity.this, gallery123.class);
            startActivity(intent);


    }
    else if(view.getId()==R.id.cv3)
    {
        Intent intent= new Intent(MainActivity.this, routine.class);
        startActivity(intent);


    }

    else if(view.getId()==R.id.cv4)
    {
        Intent intent= new Intent(MainActivity.this, task_activity.class);
        startActivity(intent);


    }

    }
}