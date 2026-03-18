package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import object.SuperObject;
import entity.Entity;
import main.GamePanel;

public class GamePanelTest {

    private GamePanel gp;

    @BeforeEach
    public void configuracion() {
        // Al instanciar GamePanel, se crean sus variables: tileManager, keys, etc.
        gp = new GamePanel();
        
        // Inicializamos tempScreen y g2 para evitar NullPointerExceptions 
        // cuando llamemos a métodos de dibujo.
        BufferedImage tempScreen = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        // Use reflection or a public setter method to initialize gp.tempScreen and gp.g2
        try {
            java.lang.reflect.Field screenField = GamePanel.class.getDeclaredField("tempScreen");
            screenField.setAccessible(true);
            screenField.set(gp, tempScreen);
            
            java.lang.reflect.Field g2Field = GamePanel.class.getDeclaredField("g2");
            g2Field.setAccessible(true);
            g2Field.set(gp, tempScreen.getGraphics());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // --- PRUEBAS PARA setupGame() ---

    @Test
    public void testSetupGameSinFullScreen() {
        gp.fullScreenOn = false;
        gp.setupGame();

        assertEquals(gp.titleState, gp.gameState, "El estado del juego debe ser titleState.");
        try {
            java.lang.reflect.Field screenField = GamePanel.class.getDeclaredField("tempScreen");
            screenField.setAccessible(true);
            assertNotNull(screenField.get(gp), "tempScreen debió inicializarse.");
            
            java.lang.reflect.Field g2Field = GamePanel.class.getDeclaredField("g2");
            g2Field.setAccessible(true);
            assertNotNull(g2Field.get(gp), "g2 debió inicializarse.");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // Nota: Evito probar setupGameConFullScreen() porque depende de una clase 'Principal' 
    // y de hardware real (GraphicsEnvironment), lo que suele romper pruebas unitarias.

    // --- PRUEBAS PARA update() ---

    @Test
    public void testUpdateEstadoJuego() {
        gp.gameState = gp.playState;
        
        // Simulamos un NPC no nulo para cubrir el if(npc[i] != null)
        gp.npc[0] = new Entity(gp); 

        // Al llamar a update, debería actualizar al jugador y pasar por el if del NPC
        assertDoesNotThrow(() -> gp.update(), "El update en playState no debe lanzar excepciones.");
    }

    @Test
    public void testUpdateEstadoPausa() {
        gp.gameState = gp.pauseState;
        // Solo verificamos que pase por la condición sin errores
        assertDoesNotThrow(() -> gp.update()); 
    }

    // --- PRUEBAS PARA drawToTempScreen() ---

    @Test
    public void testDrawToTempScreenTitleState() {
        gp.gameState = gp.titleState;
        gp.keys.checkDrawTime = true; // Cubre el if del tiempo
        
        // Ejecutamos
        assertDoesNotThrow(() -> gp.drawToTempScreen());
    }

    @Test
    public void testDrawToTempScreenPlayState() {
        gp.gameState = gp.playState;
        gp.keys.checkDrawTime = false;
        
        // Simulamos objetos para cubrir los bucles 'for' y sus 'if'
        gp.obj[0] = new SuperObject(); // Asumiendo que SuperObject tiene un constructor vacío
        gp.npc[0] = new Entity(gp);
        
        assertDoesNotThrow(() -> gp.drawToTempScreen());
    }

    // --- PRUEBAS PARA MANEJO DE SONIDO ---

    @Test
    public void testPlayStopMusic() {
        // Probamos que los métodos se ejecuten sin lanzar excepciones.
        // Si la clase Sound falla al no encontrar el archivo, esto lo detectará.
        assertDoesNotThrow(() -> {
            // Asumiendo que el índice 0 no rompe la clase Sound
            gp.playMusic(0); 
            gp.stopMusic();
        });
    }

    @Test
    public void testPlaySE() {
        assertDoesNotThrow(() -> gp.playSE(0));
    }
}