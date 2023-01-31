package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText Email,Password;
    Button singIn;
    TextView insc;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email=findViewById(R.id.EmailAddress);
        Password=findViewById(R.id.Password_txt);
        auth = FirebaseAuth.getInstance();
        insc=findViewById(R.id.redirecLogIn);
        singIn=findViewById(R.id.SingIn);

        insc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Inscription.class);
                startActivity(intent);
            }
        });

        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Email.getText().toString().isEmpty() || Password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"les champs vide",Toast.LENGTH_LONG).show();
                }else{
                    auth.signInWithEmailAndPassword(Email.getText().toString(),Password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                       // Intent intent=new Intent(MainActivity.this,Quiz1.class);
                                        Intent intent=new Intent(MainActivity.this,menu.class);
                                        intent.putExtra("Email",Email.getText().toString());
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }



            }
        });

    }
}