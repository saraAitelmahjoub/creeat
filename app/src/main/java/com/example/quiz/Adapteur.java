package com.example.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapteur extends RecyclerView.Adapter<Adapteur.Proprietaire> {

    Context context;
    ArrayList<User> Utilisateur;

    public Adapteur(ArrayList<User> utilisateur, Context context) {
        this.context = context;
       this.Utilisateur = utilisateur;
    }



    @NonNull
    @Override
    public Adapteur.Proprietaire onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View v= LayoutInflater.from(context).inflate(R.layout.element_liste_user,parent,false);
        return new Proprietaire(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapteur.Proprietaire holder, int position) {
        User Nvuser=Utilisateur.get(position);

        holder.Name_view.setText(Nvuser.Name);
        holder.Email_view.setText(Nvuser.Email);
        holder.Pwd_view.setText(Nvuser.Pswd);

    }

    @Override
    public int getItemCount() {
        return Utilisateur.size();
    }

    public  static  class Proprietaire extends  RecyclerView.ViewHolder{

        TextView Name_view,Email_view,Pwd_view;

        public Proprietaire(@NonNull View itemView) {
            super(itemView);
            Name_view=itemView.findViewById(R.id.Name_user);
            Email_view=itemView.findViewById(R.id.Email_user);
            Pwd_view=itemView.findViewById(R.id.Password_user);
        }

    }
}
