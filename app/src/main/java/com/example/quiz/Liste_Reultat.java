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

public class Liste_Reultat extends AppCompatActivity {

        RecyclerView recyclerView;
        ArrayList<User> arrayUser;
        Adapteur adapteur;
        FirebaseFirestore DB;
    ProgressDialog prgDialog;
    int Score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_reultat);

        Intent intent=getIntent();
        Score= intent.getIntExtra("Score",Score);
        intent.putExtra("Score",Score);


         prgDialog=new ProgressDialog(this);
        prgDialog.setCancelable(false);
        prgDialog.setMessage("Recuperation du data ....");
        prgDialog.show();


        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DB=FirebaseFirestore.getInstance();

        arrayUser=new ArrayList<User>();
        adapteur =new Adapteur(arrayUser,Liste_Reultat.this);

        recyclerView.setAdapter(adapteur);
        EventchangeListener();
    }

    private void EventchangeListener() {

        DB.collection("Users").orderBy("Name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null){
                            if(prgDialog.isShowing()){
                                prgDialog.dismiss();
                            }

                            Log.e("Name",error.getMessage());

                            return;
                        }
                        for(DocumentChange dc:value.getDocumentChanges()){
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                arrayUser.add(dc.getDocument().toObject(User.class));
                            }
                            adapteur.notifyDataSetChanged();
                            if(prgDialog.isShowing()){
                                prgDialog.dismiss();
                            }
                        }

                    }
                });

    }
}