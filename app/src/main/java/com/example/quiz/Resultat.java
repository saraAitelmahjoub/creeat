package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Resultat extends AppCompatActivity {
    DonutProgress  Progressebr;
TextView text_Prg;
int Score=0,i=0;
String email="";
Button Bout,Bres,BLstUser,BLstScore,Bmap;
    FirebaseFirestore DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);


       //Button
        Bout=findViewById(R.id.buttonOut);
        Bres=findViewById(R.id.buttonRes);
        BLstScore=findViewById(R.id.buttonLiteScore);
        BLstUser=findViewById(R.id.buttonListe_user);
        Bmap=findViewById(R.id.buttonMap);

        Intent intnt=getIntent();
        Score=intnt.getIntExtra("Score",Score);
        email=intnt.getStringExtra("Email");

        DB=FirebaseFirestore.getInstance();


        Progressebr=findViewById(R.id.donut_progress);
        Progressebr.setDonut_progress(String.valueOf(Score));

        //afficher la liste des Scores
        BLstScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intnt=new Intent(Resultat.this,Liste_DesScores.class);
                intnt.putExtra("Score",Score);
                startActivity(intnt);
            }
        });

        //Affichier la liste des utilisateurs
        BLstUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intnt=new Intent(Resultat.this,Liste_Reultat.class);
                intnt.putExtra("Score",Score);
                startActivity(intnt);
            }
        });

        //Affiche Map
        Bmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intnt=new Intent(Resultat.this,MapsActivity.class);
                intnt.putExtra("Score",Score);
                startActivity(intnt);
            }
        });

        //Restart Quiz1
        Bres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intnt=new Intent(Resultat.this,Quiz1.class);
                intnt.putExtra("Score",0);
                startActivity(intnt);
            }
        });

        //Log out
        Bout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intnt=new Intent(Resultat.this,MainActivity.class);
                intnt.putExtra("Score",0);
                startActivity(intnt);
            }
        });


        //ajt le score dans la liste des Scores
        Map<String, Object> Liste_Score  = new HashMap<>();;
        Liste_Score .put("Email",email);
        Liste_Score .put("Score",String.valueOf(Score));
                    DB.collection("Liste_Test")
                            .add(Liste_Score )
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(Resultat.this, "Success !!!", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Resultat.this, "Erreur !!", Toast.LENGTH_SHORT).show();}

                            });


    }



}