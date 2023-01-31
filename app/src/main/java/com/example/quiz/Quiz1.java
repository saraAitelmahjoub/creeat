package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Quiz1 extends AppCompatActivity {
Button next;
RadioGroup Rg;
RadioButton Rp;
String rep="Oui";
int Score=0;
    String email="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        Rg=findViewById(R.id.Rep);
        Intent intente =getIntent();
        email=intente.getStringExtra("Email") ;

        next=findViewById(R.id.buttonNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Quiz1.this,Quiz2.class);

                if(Rg.getCheckedRadioButtonId()==-1){
                    Toast.makeText(Quiz1.this,"choisire une reponse",Toast.LENGTH_SHORT);
                }
                else{
                    Rp=findViewById(Rg.getCheckedRadioButtonId());
                    if(Rp.getText().toString().equals(rep)){
                        Score+=20;
                    }
                    intent.putExtra("Email",email);
                    intent.putExtra("Score",Score);
                    startActivity(intent);


                }
            }
        });

    }
}