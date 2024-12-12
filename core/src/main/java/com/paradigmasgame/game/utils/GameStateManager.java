// // Classe: GameStateManager.java
// package com.paradigmasgame.game.utils;

// import com.paradigmasgame.game.states.GameState;
// import java.util.Stack;

// public class GameStateManager {

//     private Stack<GameState> states;

//     public GameStateManager() {
//         states = new Stack<>();
//     }

//     // Adiciona um novo estado no topo da pilha
//     public void push(GameState state) {
//         states.push(state);
//     }

//     // Remove o estado atual e adiciona um novo
//     public void set(GameState state) {
//         if (!states.isEmpty()) {
//             states.pop().dispose();
//         }
//         states.push(state);
//     }

//     // Atualiza o estado atual
//     public void update(float deltaTime) {
//         if (!states.isEmpty()) {
//             states.peek().update(deltaTime);
//         }
//     }

//     // Renderiza o estado atual
//     public void render() {
//         if (!states.isEmpty()) {
//             states.peek().render();
//         }
//     }

//     // Remove e libera memória de todos os estados
//     public void dispose() {
//         while (!states.isEmpty()) {
//             states.pop().dispose();
//         }
//     }
// }
