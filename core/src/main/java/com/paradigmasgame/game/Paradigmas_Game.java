// Arquivo: Paradigmas_Game.java
package com.paradigmasgame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import com.paradigmasgame.game.entities.Enemy;
import com.paradigmasgame.game.entities.Missile;
import com.paradigmasgame.game.entities.Nave;
import com.paradigmasgame.game.entities.Question;

import java.util.Iterator;

public class Paradigmas_Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture background;
    private Nave nave;
    private Missile missile;
    private Array<Enemy> enemies;
    private float enemySpawnTimer;
    private BitmapFont font;
    private boolean isGameOver;
    private int score;

    private Array<Question> questions; // Lista de perguntas
    private Question currentQuestion;  // Pergunta atual

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("planetario.png");
        nave = new Nave("podrao.png", 100, 100);
        missile = null;
        enemies = new Array<>();
        enemySpawnTimer = 0;
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        isGameOver = false;
        score = 0;
        loadQuestions();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1);
        float deltaTime = Gdx.graphics.getDeltaTime();

        if (!isGameOver) {
            updateGame(deltaTime);
        }
        handleInput();

        batch.begin();
        batch.draw(background, 0, 0);
        nave.draw(batch);

        if (currentQuestion != null) {
            font.draw(batch, currentQuestion.getQuestionText(), 20, Gdx.graphics.getHeight() - 50);
        }

        if (missile != null) {
            missile.draw(batch);
        }

        for (Enemy enemy : enemies) {
            enemy.draw(batch);
        }

        font.draw(batch, "Score: " + score, 20, Gdx.graphics.getHeight() - 20);
        // font.draw(batch, "Pergunta: " + currentQuestion.getQuestionText(), 20, Gdx.graphics.getHeight() - 50);

        if (isGameOver) {
            font.draw(batch, "GAME OVER", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2);
        }

        batch.end();
    }

    private void loadQuestions() {
        questions = new Array<>();

        Array<String> answers1 = new Array<>();
        answers1.add("2"); answers1.add("3"); answers1.add("4");
        questions.add(new Question("Quanto é 1 + 1?", answers1, "2"));

        Array<String> answers2 = new Array<>();
        answers2.add("Azul"); answers2.add("Vermelho"); answers2.add("Amarelo");
        questions.add(new Question("Qual é a cor do céu?", answers2, "Azul"));

        currentQuestion = questions.random(); // Escolhe uma pergunta aleatória
    }

    private void handleInput() {
        if (isGameOver) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                restartGame();
            }
            return;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            nave.moveLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            nave.moveRight();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            nave.moveUp();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            nave.moveDown();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (missile == null || !missile.isActive()) {
                missile = new Missile("missile.png", nave.getX(), nave.getY());
            }
        }
    }

    private void restartGame() {
        // Limpa os inimigos
        enemies.clear();
    
        // Reseta as variáveis do jogo
        nave.setPosition(100, 100); // Reinicia a posição da nave
        missile = null;
        enemySpawnTimer = 0;
        score = 0;
        isGameOver = false;
    }

    private void updateGame(float deltaTime) {
        enemySpawnTimer += deltaTime;
        if (enemySpawnTimer > 1) {
            spawnEnemy();
            enemySpawnTimer = 0;
        }

        if (missile != null) {
            missile.update(deltaTime);
            if (missile.getY() > Gdx.graphics.getHeight()) {
                missile.deactivate();
            }
        }

        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.update(deltaTime);

            if (missile != null && missile.isActive() && enemy.getBounds().overlaps(missile.getBounds())) {
                if (enemy.getAnswer().equals(currentQuestion.getCorrectAnswer())) {
                    score++;
                    changeQuestion();
                } else {
                    isGameOver = true; // Perde o jogo se errar
                }
                iterator.remove();
                missile.deactivate();
            }

            if (enemy.getBounds().overlaps(nave.getBounds())) {
                isGameOver = true;
            }
        }
    }

    private void changeQuestion() {
        if (questions.size > 0) {
            currentQuestion = questions.random(); // Escolhe uma nova pergunta aleatória
            enemies.clear(); // Remove todos os inimigos existentes
            spawnEnemiesForQuestion(); // Gera novos inimigos para a nova pergunta
        } else {
            // Se todas as perguntas foram usadas, reinicia o jogo ou mostra uma mensagem de vitória
            isGameOver = true; // Ou implemente lógica de vitória aqui
        }
    }

    private void spawnEnemy() {
        float y = (float) Math.random() * (Gdx.graphics.getHeight() - 64);
        String answer = currentQuestion.getAnswers().random();
        Enemy enemy = new Enemy("haskell.png", (float)Gdx.graphics.getWidth(), y, answer);
        enemies.add(enemy);
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        nave.dispose();
        if (missile != null) missile.dispose();
        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
        font.dispose();
    }
}