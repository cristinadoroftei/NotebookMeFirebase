package com.example.firebasehello;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final static String notes = "notes";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //addNewNote

        startNoteListener();
        //delete

        DocumentReference docRef = db.collection(notes).document("nyb60TA4WcmULXjjRGmg");
        Map<String, String> map = new HashMap<>();
        map.put("head", "changed");
        map.put("body", "changed body");
        docRef.set(map);
    }

    private void deleteNote() {
        DocumentReference docRef = db.collection(notes).document("A2vIhqr5NFfo1tZZR6wy");
        docRef.delete();
    }

    private void startNoteListener() {
        db.collection(notes).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot values, @Nullable FirebaseFirestoreException e) {
                for (DocumentSnapshot snap : values.getDocuments()){
                    Log.i("all", "read from FB" + snap.get("head").toString());
                }
            }
        });
    }

    private void addNewNote() {
        DocumentReference docRef = db.collection(notes).document();
        Map<String, String> map = new HashMap<>();
        map.put("head", "new headline 2");
        map.put("body", "new body 2");
        docRef.set(map);
    }
}
