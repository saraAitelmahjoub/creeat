package com.example.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Score_elemet extends RecyclerView.Adapter<Score_elemet.Element> {

    Context context;
    ArrayList<ClassListe_Score> Score;

    public Score_elemet(ArrayList<ClassListe_Score> score,Context context) {
        Score = score;
        this.context=context;
    }

    @NonNull
    @Override
    public Score_elemet.Element onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.element_score,parent,false);
       return new Element(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Score_elemet.Element holder, int position) {

                ClassListe_Score ScoreElmt=Score.get(position);
                holder.Email_view.setText(ScoreElmt.email);
                holder.Score_view.setText(ScoreElmt.score);
    }

    @Override
    public int getItemCount() { return Score.size();  }


    public class Element extends RecyclerView.ViewHolder{
        TextView  Email_view,Score_view;
        public Element(@NonNull View itemView) {
            super(itemView);
            Score_view=itemView.findViewById(R.id.Score_User);
            Email_view=itemView.findViewById(R.id.Email_User_score);

        }
    }
}
