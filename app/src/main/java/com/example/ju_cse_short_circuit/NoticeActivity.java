package com.example.ju_cse_short_circuit;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
//import androidx.recyclerview.widget.RecyclerView;


public class NoticeActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    private EditText noticeEditText;
    private Button uploadButton;
    private RecyclerView noticeRecyclerView;
    private NoticeAdapter noticeAdapter;
    private List<Notice> noticeList;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        categorySpinner = findViewById(R.id.categorySpinner);
        noticeEditText = findViewById(R.id.noticeEditText);
        uploadButton = findViewById(R.id.uploadButton);
        noticeRecyclerView = findViewById(R.id.noticeRecyclerView);
        noticeList = new ArrayList<>();
        noticeAdapter = new NoticeAdapter(this, noticeList);
        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        noticeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        noticeRecyclerView.setAdapter(noticeAdapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.notice_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Subscribe the user to a topic, e.g., their user ID
        if (currentUser != null) {
            String userId = currentUser.getUid();
            FirebaseMessaging.getInstance().subscribeToTopic(userId);
        }


        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadNotice();
            }
        });

        displayAllNotices();
    }

    private void uploadNotice() {
        String category = categorySpinner.getSelectedItem().toString();
        String details = noticeEditText.getText().toString().trim();

        if (category.isEmpty() || details.isEmpty()) {
            // Handle validation error
            return;
        }

        // Create a new Notice object
        Notice notice = new Notice(category, details, Objects.requireNonNull(currentUser).getEmail(), new Date().toString());

        // Add the notice to Firestore
        CollectionReference noticesRef = db.collection("notices");
        noticesRef.add(notice)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Successfully added the notice to Firestore
                        noticeList.add(notice);
                        noticeAdapter.notifyDataSetChanged();
                        noticeEditText.setText(""); // Clear the input field

                        // Send a notification to all users (subscribers to the topic)
                        sendNotificationToAllUsers(notice);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure to add the notice
                    }
                });
    }





    private void sendNotificationToAllUsers(Notice notice) {
        // Create a data payload for the notice
        String title = "New Notice Uploaded";
        String body = "A new notice has been uploaded. Check it out!";
        String userId = currentUser.getUid(); // Sender's user ID

        // Send the notification to the topic (all users)
        Map<String, String> data = new HashMap<>();
        data.put("title", title);
        data.put("body", body);
       // FirebaseMessaging.getInstance().subscribeToTopic("juweblet");
        RemoteMessage message = new RemoteMessage.Builder("juweblet")
                .setData(data)
                .build();

        // Log messages to help with debugging
        Log.d("NotificationDebug", "Sending notification: " + message.getData());

        FirebaseMessaging.getInstance().send(message);
    }




    private void displayAllNotices() {
        CollectionReference noticesRef = db.collection("notices");

        noticesRef.orderBy("uploadTime", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        noticeList.clear();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Notice notice = document.toObject(Notice.class);
                            noticeList.add(notice);
                        }
                        noticeAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure to fetch notices
                    }
                });
    }
}
