package com.paradigmasgame.game;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Paradigmas_Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image, tNave, tMissile, tEnemy;
    private Sprite nave, missile;
    private float posX, posY, xMissile, yMissile;
    private final int velocity = 5;
    private boolean atack, gameOver;
    private long lastEnemyTime;
    private Array<Rectangle> enemies;
    private int score;
    private int power;
    private int numEnemies;
    private BitmapFont bitmap;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("bg.png");
        tNave = new Texture("spaceship.png");
        nave = new Sprite(tNave);
        posX = 0;
        posY = 0;
        xMissile = posX;
        yMissile = posY;
        lastEnemyTime = 0;
        numEnemies = 999999999;

        atack = false;
        gameOver = false;
        tMissile = new Texture("missile.png");
        missile = new Sprite(tMissile);

        tEnemy = new Texture("enemy.png");
        enemies = new Array<>();

        score = 0;
        power = 3;

        // Usando BitmapFont simples, sem a necessidade de FreeTypeFontGenerator
        bitmap = new BitmapFont(); // Fonte padrão do LibGDX (ou pode carregar um arquivo .fnt)
        bitmap.setColor(Color.WHITE); // Cor da fonte
    }

    @Override
    public void render() {
        this.moveNave();
        this.moveMissile();
        this.moveEnemies();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image,0,0);
        if(!gameOver){
            if(atack){
                batch.draw(missile, xMissile, yMissile);
            }
            batch.draw(nave,posX,posY);
    
            for(Rectangle enemy: enemies){
                batch.draw(tEnemy, enemy.x, enemy.y);
            }
            bitmap.draw(batch, "Score: "+score, 20, Gdx.graphics.getHeight()-20);
            bitmap.draw(batch, "Power: "+power, Gdx.graphics.getWidth()-150, Gdx.graphics.getHeight()-20);
        }else{
            bitmap.draw(batch, "Score: "+score, 20, Gdx.graphics.getHeight()-20);
            bitmap.draw(batch, "GAME OVER", Gdx.graphics.getWidth()-150, Gdx.graphics.getHeight()-20);
            bitmap.draw(batch, "PRESS ENTER", Gdx.graphics.getWidth() / 2-150, Gdx.graphics.getHeight() /2);
            if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
                numEnemies = 999999999;
                enemies = new Array<>();
                score = 0;
                power = 3;
                posX=0;
                posY=0;
                gameOver = false;
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        tNave.dispose();
        bitmap.dispose(); // Não se esqueça de liberar recursos de BitmapFont
    }

    private void moveNave() {
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(posX < Gdx.graphics.getWidth()-nave.getWidth()){
                posX+= velocity;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(posX > 0){
                posX-= velocity;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            if(posY < Gdx.graphics.getHeight()-nave.getHeight()){
                posY+= velocity;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            if(posY > 0){
                posY-= velocity;
            }
        }
    }

    private void moveMissile() {
        
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && !atack){
            atack = true;
            yMissile = posY  + nave.getHeight() / 2 - 12;
        }

        if(atack){
            if(xMissile < Gdx.graphics.getWidth()){
                xMissile+=20;
            }else{
                xMissile = posX + nave.getWidth() / 2;
                atack = false;
            }
        }else{
            xMissile = posX + nave.getWidth() / 2;
            yMissile = posY  + nave.getHeight() / 2 - 12;
        }
    }

    private void spawnEnemys(){
        Rectangle enemy = new Rectangle(Gdx.graphics.getWidth(), MathUtils.random(0, Gdx.graphics.getHeight() - tEnemy.getHeight()), tEnemy.getWidth(), tEnemy.getHeight());
        enemies.add(enemy);
        lastEnemyTime = TimeUtils.nanoTime();
    }

    private void moveEnemies(){
        if(TimeUtils.nanoTime() - lastEnemyTime > numEnemies){
            this.spawnEnemys();
        }

        for( Iterator<Rectangle> iter = enemies.iterator(); iter.hasNext(); ){
            Rectangle enemy = iter.next();
            enemy.x-= 400 * Gdx.graphics.getDeltaTime();

            if(collide(enemy.x, enemy.y, enemy.width, enemy.height, xMissile, yMissile, missile.getWidth(), missile.getHeight()) && atack ){
               ++score;
               if(score % 5 == 0 && numEnemies > 150000000){
                numEnemies -= 70000000;
               }
                atack = false;
                iter.remove();
            }else if(collide(enemy.x, enemy.y, enemy.width, enemy.height, posX, posY, nave.getWidth(), nave.getHeight())&& !gameOver){
                --power;
                if(power <=0){
                    gameOver = true;
                }
                iter.remove();
            }

            if(enemy.x +tEnemy.getWidth() < 0){
                iter.remove();
            }
        }
    }

    private boolean collide(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2){
        return x1 + w1 > x2 && x1 < x2 + w2 && y1 + h1 > y2 && y1 < y2 + h2;
    }
}
