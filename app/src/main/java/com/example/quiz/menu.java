package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {
    Button Quiz,User,Score,Map,NFC,writeNfc;

    String Email="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intente =getIntent();
        Email=intente.getStringExtra("Email") ;
        Quiz=findViewById(R.id.buttonQuiz);
        User=findViewById(R.id.buttonUtilisteur);
        Score=findViewById(R.id.buttonScore);
        Map=findViewById(R.id.buttonMap);
        NFC=findViewById(R.id.buttonNFC);
        writeNfc=findViewById(R.id.buttonWrite);

        NFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(menu.this,ReadNFC.class);
                 startActivity(intent);
            }
        });

        writeNfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(menu.this,WriteNfc.class);
                startActivity(intent);
            }
        });

        Quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(menu.this,Quiz1.class);
                intent.putExtra("Email",Email);
                startActivity(intent);
            }
        });
        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(menu.this,Liste_Reultat.class);
                intent.putExtra("Email",Email);
                startActivity(intent);
            }
        });
        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(menu.this,MapsActivity.class);
                intent.putExtra("Email",Email);
                startActivity(intent);
            }
        });

                Score.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(menu.this,Liste_DesScores.class);
                        intent.putExtra("Email",Email);
                        startActivity(intent);
                    }
                });
    }
}