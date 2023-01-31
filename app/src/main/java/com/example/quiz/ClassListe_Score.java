package com.example.quiz;

public class ClassListe_Score {
    String email,score;
    public ClassListe_Score() {
    }



    public ClassListe_Score(String email, String sc) {
        this.email = email;
        score=sc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
