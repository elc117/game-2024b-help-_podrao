// Arquivo: Enemy.java
package com.paradigmasgame.game.entities;


public class Question {
    public String question;
    public String[] answers;
    public int correctIndex;
  
    public Question(String question, String[] answers, int correctIndex) {
      this.question = question;
      this.answers = answers;
      this.correctIndex = correctIndex;
    }
  }