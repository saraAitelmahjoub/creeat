package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz3 extends AppCompatActivity {
Button btnNext;
RadioGroup Rg;
RadioButton rp;
String rep="Oui",email="";
    int Score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);
        Intent intent=getIntent();
        Score=intent.getIntExtra("Score",Score);
        email=intent.getStringExtra("Email");
        btnNext=findViewById(R.id.buttonNext3);
        Rg=findViewById(R.id.Rep);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(Quiz3.this,Quiz4.class);
                if(Rg.getCheckedRadioButtonId()!=-1){
                    rp=findViewById(Rg.getCheckedRadioButtonId());
                    if (rp.getText().toString().equals(rep)) {
                     Score+=20;

                    }
                    intent.putExtra("Score",Score);
                    intent.putExtra("Email",email);
                    startActivity(intent);

                }else{
                    Toast.makeText(Quiz3.this, "Vous devez choisir une rep", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}