// package com.paradigmasgame.game.entities;

// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.Sprite;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.math.Rectangle;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.Input;

// public class Nave {

//     private Sprite sprite;
//     private Rectangle bounds;
//     private float speed;

//     public Nave(Texture texture, float x, float y, float speed) {
//         this.sprite = new Sprite(texture);
//         this.sprite.setPosition(x, y);
//         this.bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
//         this.speed = speed;
//     }

//     public void update(float deltaTime) {
//         if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//             move(-speed * deltaTime, 0);
//         }
//         if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//             move(speed * deltaTime, 0);
//         }
//         if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//             move(0, speed * deltaTime);
//         }
//         if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//             move(0, -speed * deltaTime);
//         }

//         // Limitar a nave à tela
//         float screenWidth = Gdx.graphics.getWidth();
//         float screenHeight = Gdx.graphics.getHeight();

//         if (bounds.x < 0) bounds.x = 0;
//         if (bounds.y < 0) bounds.y = 0;
//         if (bounds.x + bounds.width > screenWidth) bounds.x = screenWidth - bounds.width;
//         if (bounds.y + bounds.height > screenHeight) bounds.y = screenHeight - bounds.height;

//         sprite.setPosition(bounds.x, bounds.y);
//     }

//     private void move(float dx, float dy) {
//         bounds.x += dx;
//         bounds.y += dy;
//     }

//     public void render(SpriteBatch batch) {
//         sprite.draw(batch);
//     }

//     public Rectangle getBounds() {
//         return bounds;
//     }
// }


// Arquivo: Nave.java
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
