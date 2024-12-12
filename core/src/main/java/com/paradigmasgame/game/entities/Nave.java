package com.paradigmasgame.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Nave {
    private Sprite sprite;

    public Nave(String texturePath, float x, float y) {
        Texture texture = new Texture(texturePath);
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x, y);
    }

    public void moveLeft() {
        sprite.translateX(-5);
    }

    public void moveRight() {
        sprite.translateX(5);
    }

    public void moveUp() {
        sprite.translateY(5);
    }

    public void moveDown() {
        sprite.translateY(-5);
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public Rectangle getBounds() {
        return sprite.getBoundingRectangle();
    }

    public void draw(com.badlogic.gdx.graphics.g2d.SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }
}
