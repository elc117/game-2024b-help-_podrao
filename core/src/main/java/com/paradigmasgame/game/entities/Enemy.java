// Classe: Enemy.java
package com.paradigmasgame.game.entities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    private Texture texture;
    private float x, y;
    private float speed;
    private Rectangle bounds;
    private String answer;

    public Enemy(String texturePath, float x, float y, String answer) {
        this.texture = new Texture(texturePath);
        this.x = x;
        this.y = y;
        this.speed = 200;
        this.bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        this.answer = answer;
    }

    public String getAnswer() {
      return answer;
    }

    public void update(float deltaTime) {
        x -= speed * deltaTime;
        bounds.setPosition(x, y);
    }
    

    public void draw(SpriteBatch batch) {
      batch.draw(texture, x, y); // Desenha o inimigo
  
      // Exibe a resposta acima do inimigo
      BitmapFont font = new BitmapFont();
      font.setColor(Color.WHITE);
      font.getData().setScale(1.5f);
      font.draw(batch, answer, x, y + texture.getHeight() + 10);
    }

    // public void draw(SpriteBatch batch) {
    //     batch.draw(texture, x, y);
    // }

    public Rectangle getBounds() {
        return bounds;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return bounds.getWidth();
    }

    public float getHeight() {
      return bounds.getHeight();
    }

    public void dispose() {
        texture.dispose();
    }
}
