package com.example.notemefirebase;

import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemoryStorage {

    static Calendar c;
    static String todaysDate;
    static String currentTime;
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String notesFirebase = "notes";

    public static List<Note> notes = new ArrayList<>();

    private static void getDateAndTime(){
        // get current date and time
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(c.get(Calendar.HOUR)) + ":" + pad(c.get(Calendar.MINUTE));
        Log.d("calendar", "Date and Time:" + todaysDate + " and " + currentTime );
    }

    private static String pad(int i){
        if (i<10)
            return "0"+i;
        return String.valueOf(i);
    }

    public static void editNote(Note editingNote){
        DocumentReference docRef = db.collection(notesFirebase).document(editingNote.getID());
        getDateAndTime();
        Map<String,String> map = new HashMap<>();
        map.put("title", editingNote.getTitle());
        map.put("content", editingNote.getContent());
        map.put("date", todaysDate);
        map.put("time", currentTime);
        docRef.set(map);
    }

    public static boolean deleteNote(Note editingNote){
        DocumentReference docRef = db.collection(notesFirebase).document(editingNote.getID());
        docRef.delete();
        return false;
    }

    public static void addNote(String noteTitle, String noteDetails){
        DocumentReference docRef = db.collection(notesFirebase).document();
        Map<String,String> map = new HashMap<>();
        getDateAndTime();
        map.put("title", noteTitle);
        map.put("content", noteDetails);
        map.put("date", todaysDate);
        map.put("time", currentTime);
        docRef.set(map);
    }
}
