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

public class Quiz2 extends AppCompatActivity {
Button btnNext;
RadioGroup Rg;
RadioButton rb;
String rep="À droit",email="";
int Score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
        Rg=findViewById(R.id.Rep);

        Intent intent=getIntent();
       Score= intent.getIntExtra("Score",0);
        email=intent.getStringExtra("Email") ;

        btnNext=findViewById(R.id.buttonNext2);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Quiz2.this,Quiz3.class);
                if(Rg.getCheckedRadioButtonId()!=-1){
                    rb=findViewById(Rg.getCheckedRadioButtonId());
                    if(rb.getText().toString().equals(rep)){
                        Score+=20;
                    }
                    intent.putExtra("Email",email);
                    intent.putExtra("Score",Score);
                    startActivity(intent);
                }else{
                    Toast.makeText(Quiz2.this,"verifier",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}