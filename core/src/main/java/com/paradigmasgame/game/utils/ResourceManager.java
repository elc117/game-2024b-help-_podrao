// Classe: ResourceManager.java
package com.paradigmasgame.game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.util.HashMap;

public class ResourceManager {

    private static HashMap<String, Texture> textures;
    private static HashMap<String, BitmapFont> fonts;

    static {
        textures = new HashMap<>();
        fonts = new HashMap<>();
    }

    // Carrega uma textura
    public static void loadTexture(String key, String path) {
        if (!textures.containsKey(key)) {
            textures.put(key, new Texture(path));
        }
    }

    // Obtém uma textura
    public static Texture getTexture(String key) {
        return textures.get(key);
    }

    // Carrega uma fonte
    public static void loadFont(String key, String path) {
        if (!fonts.containsKey(key)) {
            BitmapFont font = new BitmapFont(); // Fontes personalizadas podem ser carregadas se necessário
            fonts.put(key, font);
        }
    }

    // Obtém uma fonte
    public static BitmapFont getFont(String key) {
        return fonts.get(key);
    }

    // Libera os recursos
    public static void dispose() {
        for (Texture texture : textures.values()) {
            texture.dispose();
        }
        for (BitmapFont font : fonts.values()) {
            font.dispose();
        }
        textures.clear();
        fonts.clear();
    }
}
