// Arquivo: Enemy.java
package com.paradigmasgame.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

// public class Enemy {
//     private Rectangle rectangle;
//     private String text;
//     private boolean isCorrect;
//     private Texture texture;

//     public Enemy(float x, float y, float width, float height, String text, boolean isCorrect, Texture texture) {
//         this.rectangle = new Rectangle(x, y, width, height);
//         this.text = text;
//         this.isCorrect = isCorrect;
//         this.texture = texture;
//     }

//     public void update(float deltaTime, float speed) {
//         rectangle.x -= speed * deltaTime;
//     }

//     public void render(SpriteBatch batch) {
//         batch.draw(texture, rectangle.x, rectangle.y);
//     }

//     public boolean isOutOfScreen() {
//         return rectangle.x + rectangle.width < 0;
//     }

//     public boolean checkCollision(float x, float y, float width, float height) {
//         return rectangle.overlaps(new Rectangle(x, y, width, height));
//     }

//     public String getText() {
//         return text;
//     }

//     public boolean isCorrect() {
//         return isCorrect;
//     }

//     public Rectangle getRectangle() {
//         return rectangle;
//     }
// }


public class Enemy {
    public Rectangle rectangle;
    public String text;
    public boolean isCorrect;
  
    public Enemy(Rectangle rectangle, String text, boolean isCorrect) {
      this.rectangle = rectangle;
      this.text = text;
      this.isCorrect = isCorrect;
    }
  }