package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Quiz4 extends AppCompatActivity {
Button btnNext;
    RadioButton rp;
    RadioGroup Rg;
    String rep="Le tramway est arrêté",email="";
    int Score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz4);
        Intent intnt=getIntent();
        Score=intnt.getIntExtra("Score",Score);
        email=intnt.getStringExtra("Email");
        btnNext=findViewById(R.id.buttonNext4);
        Rg=findViewById(R.id.Rep);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Quiz4.this,Quiz5.class);
                if(Rg.getCheckedRadioButtonId()!=-1){
                    rp=findViewById(Rg.getCheckedRadioButtonId());
                    if(rp.getText().toString().equals(rep)){
                        Score+=20;

                    }
                    intent.putExtra("Email",email);
                    intent.putExtra("Score",Score);
                    startActivity(intent);
                }else{
                    Toast.makeText(Quiz4.this, "vous devez choisir une rep", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}