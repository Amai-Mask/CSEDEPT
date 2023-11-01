package com.example.ju_cse_short_circuit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssFragment extends Fragment {

    private RecyclerView noticeRecyclerView;
    private AssAdapter assAdapter;
    private List<Ass> asslist;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;

    public AssFragment() {
        // Required empty public constructor
    }

    public static AssFragment newInstance() {
        return new AssFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ass, container, false);

        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        asslist = new ArrayList<>();
        assAdapter = new AssAdapter(requireContext(), asslist);
        noticeRecyclerView = view.findViewById(R.id.noticeRecyclerView2);
        noticeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        noticeRecyclerView.setAdapter(assAdapter);

        // Fetch data from the database and update the RecyclerView
        displayAllNotices();

        return view; // Return the inflated view
    }

    private void sendNotificationToAllUsers(Exam notice) {
        // Create a data payload for the notice
        String title = "New Assignment";
        String body = "A new assignment has uploaded. Check it out!";
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
        if (db == null) {
            // Handle null db object, log an error, or show a message to the user
            return;
        }

        CollectionReference noticesRef = db.collection("Ass");

        noticesRef.orderBy("Due", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        asslist.clear();
                        if (queryDocumentSnapshots != null) { for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Ass notice = document.toObject(Ass.class);
                            asslist.add(notice);
                        }
                            assAdapter.notifyDataSetChanged();}
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle failure to fetch notices
                    // Log the error or show a message to the user
                });
    }
}
