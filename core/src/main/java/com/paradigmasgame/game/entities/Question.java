package com.paradigmasgame.game.entities;
import com.badlogic.gdx.utils.Array;

public class Question {
  private String questionText;
  private Array<String> answers; // Alternativas
  private String correctAnswer; // Resposta correta

  public Question(String questionText, Array<String> answers, String correctAnswer) {
      this.questionText = questionText;
      this.answers = answers;
      this.correctAnswer = correctAnswer;
  }

  public String getQuestionText() {
      return questionText;
  }

  public Array<String> getAnswers() {
      return answers;
  }

  public String getCorrectAnswer() {
      return correctAnswer;
  }
}
