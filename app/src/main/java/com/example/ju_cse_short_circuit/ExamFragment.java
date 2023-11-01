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

public class ExamFragment extends Fragment {

    private RecyclerView noticeRecyclerView;
    private ExamAdapter examAdapter;
    private List<Exam> examlist;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;

    public ExamFragment() {
        // Required empty public constructor
    }

    public static ExamFragment newInstance() {
        return new ExamFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exam, container, false);

        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        examlist = new ArrayList<>();
        examAdapter = new ExamAdapter(requireContext(), examlist);
        noticeRecyclerView = view.findViewById(R.id.noticeRecyclerView1);
        noticeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        noticeRecyclerView.setAdapter(examAdapter);

        // Fetch data from the database and update the RecyclerView
        displayAllNotices();

        return view; // Return the inflated view
    }

    private void sendNotificationToAllUsers(Exam notice) {
        // Create a data payload for the notice
        String title = "New Exam";
        String body = "A new exam has scheduled. Check it out!";
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

        CollectionReference noticesRef = db.collection("Exam");

        noticesRef.orderBy("Date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        examlist.clear();
                        if (queryDocumentSnapshots != null) { for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Exam notice = document.toObject(Exam.class);
                            examlist.add(notice);
                        }
                        examAdapter.notifyDataSetChanged();}
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle failure to fetch notices
                    // Log the error or show a message to the user
                });
    }
}
