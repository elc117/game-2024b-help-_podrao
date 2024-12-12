// Classe: Missile.java
package com.paradigmasgame.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Missile {
    private Texture texture;
    private float x, y;
    private float speed;
    private boolean active;
    private Rectangle bounds;

    public Missile(String texturePath, float x, float y) {
        this.texture = new Texture(texturePath);
        this.x = x;
        this.y = y;
        this.speed = 500;
        this.active = true;
        this.bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void update(float deltaTime) {
        if (active) {
            x += speed * deltaTime;
            bounds.setPosition(x, y);
        }
    }

    public void draw(SpriteBatch batch) {
        if (active) {
            batch.draw(texture, x, y);
        }
    }

    public void deactivate() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}