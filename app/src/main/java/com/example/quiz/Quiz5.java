package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Quiz5 extends AppCompatActivity {
    Button btnNext;
    RadioGroup Rg;
    RadioButton rp;
    String rep="Oui",email="";
    int Score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz5);

        btnNext=findViewById(R.id.buttonNext5);
        Rg=findViewById(R.id.Rep);
        Intent intent=getIntent();
        Score= intent.getIntExtra("Score",0);
        email=intent.getStringExtra("Email");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Quiz5.this,Resultat.class);
                if(Rg.getCheckedRadioButtonId()!=-1){
                    rp=findViewById(Rg.getCheckedRadioButtonId());
                    if(rp.getText().toString().equals(rep)){
                        Score+=20;

                    }
                    intent.putExtra("Email",email);
                    intent.putExtra("Score",Score);
                    startActivity(intent);


                }else{
                    Toast.makeText(Quiz5.this, "Vous devez choisir une rep", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}