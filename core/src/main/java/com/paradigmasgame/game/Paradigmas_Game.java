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

        // font.draw(batch, "Inimigos: " + enemies.size, 10, Gdx.graphics.getHeight() - 10);  // Debugger com Numero inimigos
        font.draw(batch, "Score: " + score, 20, Gdx.graphics.getHeight() - 20);
        // font.draw(batch, "Pergunta: " + currentQuestion.getQuestionText(), 20, Gdx.graphics.getHeight() - 50);

        if (isGameOver) {
            font.draw(batch, "GAME OVER", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2);
        }

        batch.end();
    }

    private void loadQuestions() {
        questions = new Array<>();
         // Perguntas sobre a UFSM
        Array<String> answers1 = new Array<>();
        answers1.add("1960");
        answers1.add("1970");
        answers1.add("1980");
        questions.add(new Question("Em que ano a UFSM foi fundada?", answers1, "1960"));

        Array<String> answers2 = new Array<>();
        answers2.add("José Mariano da Rocha Filho");
        answers2.add("Oswaldo Aranha");
        answers2.add("Getúlio Vargas");
        questions.add(new Question("Qual é o nome do criador da UFSM?", answers2, "José Mariano da Rocha Filho"));

        Array<String> answers3 = new Array<>();
        answers3.add("Jardim Botânico");
        answers3.add("Teatro São Pedro");
        answers3.add("Catedral Metropolitana");
        questions.add(new Question("Qual desses locais é uma atração dentro da UFSM?", answers3, "Jardim Botânico"));

        // Perguntas sobre o Geoparque Quarta Colônia
        Array<String> answers4 = new Array<>();
        answers4.add("Geológico e cultural");
        answers4.add("Moderno e arquitetônico");
        answers4.add("Esportivo");
        questions.add(new Question("O Geoparque Quarta Colônia é reconhecido por preservar qual tipo de patrimônio?", answers4, "Geológico e cultural"));

        Array<String> answers5 = new Array<>();
        answers5.add("Preservar fósseis e rochas");
        answers5.add("Construir grandes monumentos");
        answers5.add("Promover esportes aquáticos");
        questions.add(new Question("Qual é um dos objetivos principais do Geoparque Quarta Colônia?", answers5, "Preservar fósseis e rochas"));

        // Perguntas sobre o Distrito Criativo Centro-Gare
        Array<String> answers6 = new Array<>();
        answers6.add("Incentivo à economia criativa");
        answers6.add("Zona industrial");
        answers6.add("Polo esportivo");
        questions.add(new Question("O que caracteriza o Distrito Criativo Centro-Gare em Santa Maria?", answers6, "Incentivo à economia criativa"));

        Array<String> answers7 = new Array<>();
        answers7.add("A Gare da antiga Estação Ferroviária");
        answers7.add("O Theatro Treze de Maio");
        answers7.add("A Biblioteca Pública Municipal");
        questions.add(new Question("Qual é um marco histórico preservado no Distrito Criativo Centro-Gare?", answers7, "A Gare da antiga Estação Ferroviária"));

        // Perguntas sobre o Geoparque Caçapava
        Array<String> answers8 = new Array<>();
        answers8.add("Morros Testemunhos");
        answers8.add("Chapadas");
        answers8.add("Cânions");
        questions.add(new Question("O Geoparque Caçapava é conhecido por qual formação geológica?", answers8, "Morros Testemunhos"));

        Array<String> answers9 = new Array<>();
        answers9.add("Santa Maria");
        answers9.add("Caçapava do Sul");
        answers9.add("São Sepé");
        questions.add(new Question("Qual é o principal município envolvido no Geoparque Caçapava?", answers9, "Caçapava do Sul"));

        // Perguntas sobre o Jardim Botânico da UFSM
        Array<String> answers10 = new Array<>();
        answers10.add("Conservação da biodiversidade");
        answers10.add("Educação física e esportes");
        answers10.add("Desenvolvimento industrial");
        questions.add(new Question("O Jardim Botânico da UFSM tem como objetivo principal:", answers10, "Conservação da biodiversidade"));

        Array<String> answers11 = new Array<>();
        answers11.add("Trilhas ecológicas");
        answers11.add("Esculturas históricas");
        answers11.add("Parques aquáticos");
        questions.add(new Question("Qual destes elementos é encontrado no Jardim Botânico da UFSM?", answers11, "Trilhas ecológicas"));

        Array<String> answers12 = new Array<>();
        answers12.add("2"); answers12.add("3"); answers12.add("4");
        questions.add(new Question("Quanto é 1 + 1?", answers12, "2"));

        Array<String> answers13 = new Array<>();
        answers13.add("Azul"); answers13.add("Vermelho"); answers13.add("Amarelo");
        questions.add(new Question("Qual é a cor do céu?", answers13, "Azul"));

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

            if (enemy.getX() + enemy.getWidth() < 0) {
                iterator.remove();
                continue; // Vai para o próximo inimigo
            }

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
        currentQuestion = questions.random(); // Escolhe uma nova pergunta aleatória
        // enemies.clear(); // Remove todos os inimigos existentes 
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