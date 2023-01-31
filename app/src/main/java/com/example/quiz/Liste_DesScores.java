package com.example.quiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Liste_DesScores extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ClassListe_Score> arrayScore;
    Score_elemet elmScore;
    FirebaseFirestore DB;
    ProgressDialog prgDialog;
    int Score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_des_scores);

        Intent intent=getIntent();
       Score= intent.getIntExtra("Score",Score);
       intent.putExtra("Score",Score);

        prgDialog=new ProgressDialog(this);
        prgDialog.setCancelable(false);
        prgDialog.setMessage("Recuperation du data ....");
        prgDialog.show();


        recyclerView=findViewById(R.id.Lsite_TTsScore);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DB=FirebaseFirestore.getInstance();
        arrayScore=new ArrayList<ClassListe_Score>();
        elmScore=new Score_elemet(arrayScore,Liste_DesScores.this);


        recyclerView.setAdapter(elmScore);
        EventchangeListener();
    }

    private void EventchangeListener() {

        DB.collection("Liste_Test")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null){
                            if(prgDialog.isShowing()){
                                prgDialog.dismiss();
                            }

                            Log.e("Email",error.getMessage());

                            return;
                        }
                        for(DocumentChange dc:value.getDocumentChanges()){
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                arrayScore.add(dc.getDocument().toObject(ClassListe_Score.class));
                            }
                            elmScore.notifyDataSetChanged();
                            if(prgDialog.isShowing()){
                                prgDialog.dismiss();
                            }
                        }

                    }
                });

    }

}