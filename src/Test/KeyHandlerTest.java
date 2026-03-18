package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.GamePanel;
import main.KeyHandler;

public class KeyHandlerTest {

    private GamePanel gp;
    private KeyHandler keyHandler;
    private JPanel dummyPanel; // Lo necesitamos para simular de dónde viene el "teclazo"

    @BeforeEach
    public void setUp() {
        gp = new GamePanel();
        keyHandler = new KeyHandler(gp);
        dummyPanel = new JPanel();
    }

    // --- MÉTODO AYUDANTE ---
    // Este método crea una tecla "falsa" para que podamos probarla
    private KeyEvent simularTecla(int keyCode, int tipoEvento) {
        return new KeyEvent(dummyPanel, tipoEvento, System.currentTimeMillis(), 0, keyCode, ' ');
    }

    // --- PRUEBAS ---

    @Test
    public void testTeclasDeMovimientoEnPlayState() {
        // 1. Preparamos el estado
        gp.gameState = gp.playState;

        // 2. Simulamos presionar 'W' y 'D'
        KeyEvent presionarW = simularTecla(KeyEvent.VK_W, KeyEvent.KEY_PRESSED);
        KeyEvent presionarD = simularTecla(KeyEvent.VK_D, KeyEvent.KEY_PRESSED);
        
        keyHandler.keyPressed(presionarW);
        keyHandler.keyPressed(presionarD);

        // 3. Verificamos que los booleanos se activaron correctamente
        assertTrue(keyHandler.wPressed, "wPressed debería ser true");
        assertTrue(keyHandler.dPressed, "dPressed debería ser true");
        assertFalse(keyHandler.sPressed, "sPressed debería seguir siendo false");

        // 4. Simulamos soltar la tecla 'W'
        KeyEvent soltarW = simularTecla(KeyEvent.VK_W, KeyEvent.KEY_RELEASED);
        keyHandler.keyReleased(soltarW);

        // 5. Verificamos que se desactivó solo 'W'
        assertFalse(keyHandler.wPressed, "wPressed debería ser false al soltar la tecla");
        assertTrue(keyHandler.dPressed, "dPressed debería seguir true porque no la hemos soltado");
    }

    @Test
    public void testPausarJuego() {
        // 1. Empezamos jugando
        gp.gameState = gp.playState;

        // 2. Presionamos 'P'
        KeyEvent presionarP = simularTecla(KeyEvent.VK_P, KeyEvent.KEY_PRESSED);
        keyHandler.keyPressed(presionarP);

        // 3. Verificamos que el estado cambió a Pausa
        assertEquals(gp.pauseState, gp.gameState, "El juego debería cambiar al estado de pausa");
    }

    @Test
    public void testNavegacionMenuPantallaTitulo() {
        // 1. Configuramos el juego en la pantalla de título
        gp.gameState = gp.titleState;
        gp.ui.titleScreenState = 0;
        gp.ui.commandNum = 0; // Apuntando a la primera opción

        // 2. Presionamos 'S' (Abajo)
        KeyEvent presionarS = simularTecla(KeyEvent.VK_S, KeyEvent.KEY_PRESSED);
        keyHandler.keyPressed(presionarS);

        // 3. Verificamos que bajó al siguiente comando
        assertEquals(1, gp.ui.commandNum, "El cursor debería bajar a la opción 1");
    }
}