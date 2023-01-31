package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class Inscription extends AppCompatActivity {
EditText Name,Pwd,ConfPwd,Email;
Button enrgButn;
FirebaseAuth auth;
FirebaseFirestore DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        DB=FirebaseFirestore.getInstance();
        Name=findViewById(R.id.Name);
        Pwd=findViewById(R.id.Password);
        ConfPwd=findViewById(R.id.ConfPassword);
        Email=findViewById(R.id.EmailAddress);
        enrgButn=findViewById(R.id.Register);

        auth=FirebaseAuth.getInstance();
        enrgButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Email.getText().toString().isEmpty()){
                    Email.setError("verifier l'Email");
                    return;
                }else if(Pwd.getText().toString().isEmpty()){
                    Pwd.setError("Verifier le Password est vide");
                }
                else if(ConfPwd.getText().toString().isEmpty()){
                    ConfPwd.setError("Verifier le Password est vide");
                }
                else if(!(Pwd.getText().toString().equals(ConfPwd.getText().toString()))){
                    ConfPwd.setError("le Password n est pas compatible");
                }

                auth.createUserWithEmailAndPassword(Email.getText().toString(),Pwd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){

                       Map<String, Object> NewUser = new HashMap<>();
                       NewUser.put("Name",Name.getText().toString());
                       NewUser.put("Email",Email.getText().toString());
                       NewUser.put("Password ",Pwd.getText().toString() );
                       DB.collection("Users")
                               .add(NewUser)
                               .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                   @Override
                                   public void onSuccess(DocumentReference documentReference) {
                                       Toast.makeText(Inscription.this, "Success  ", Toast.LENGTH_SHORT).show();
                                   Intent intnt=new Intent(Inscription.this,MainActivity.class);
                                   startActivity(intnt);
                                   }
                               })
                               .addOnFailureListener(new OnFailureListener() {
                                   @Override
                                   public void onFailure(@NonNull Exception e) {
                                       Toast.makeText(Inscription.this, "Failed", Toast.LENGTH_SHORT).show();}

                               });

                   }else{
                       Toast.makeText(Inscription.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                   }
                    }
                });


                //ajouter sur fireStore

            }
        });

    }
}