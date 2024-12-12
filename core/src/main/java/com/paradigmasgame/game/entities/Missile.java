// // Arquivo: Missile.java
// package com.paradigmasgame.game.entities;

// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.Sprite;
// import com.badlogic.gdx.math.Rectangle;

// public class Missile {
//     private Sprite sprite;
//     private Rectangle bounds;
//     private float speed;
//     private boolean active;

//     public Missile(Texture texture, float startX, float startY, float speed) {
//         this.sprite = new Sprite(texture);
//         this.sprite.setPosition(startX, startY);
//         this.bounds = new Rectangle(startX, startY, texture.getWidth(), texture.getHeight());
//         this.speed = speed;
//         this.active = false;
//     }

//     public void update(float deltaTime) {
//         if (active) {
//             sprite.translateX(speed * deltaTime);
//             bounds.setPosition(sprite.getX(), sprite.getY());

//             // Desativa o míssil se ele sair da tela
//             if (sprite.getX() > com.badlogic.gdx.Gdx.graphics.getWidth()) {
//                 deactivate();
//             }
//         }
//     }

//     public void activate(float startX, float startY) {
//         sprite.setPosition(startX, startY);
//         bounds.setPosition(startX, startY);
//         active = true;
//     }

//     public void deactivate() {
//         active = false;
//     }

//     public boolean isActive() {
//         return active;
//     }

//     public Sprite getSprite() {
//         return sprite;
//     }

//     public Rectangle getBounds() {
//         return bounds;
//     }
// }
