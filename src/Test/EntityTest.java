package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Entity;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.GamePanel;

// Nota: Importa aquí tus clases GamePanel, UI, Player, etc. si están en otros paquetes.

public class EntityTest {

    private GamePanel gp;
    private Entity entity;

    @BeforeEach
    public void configuracionInicial() {
        // Inicializamos el panel y la entidad antes de cada prueba
        gp = new GamePanel();
        
        // ¡ATENCIÓN! Como Entity usa estas clases internamente, 
        // debemos asegurarnos de que no sean nulas en GamePanel.
        // Asumiendo que puedes instanciarlas así:
        // gp.ui = new UI(gp);
        // gp.player = new Player(gp, gp.keyH);
        // gp.cChecker = new CollisionChecker(gp);
        
        entity = new Entity(gp);
        
        // Configuraciones por defecto para las pruebas
        entity.speed = 5;
        entity.worldX = 100;
        entity.worldY = 100;
    }

    // --- PRUEBAS PARA EL MÉTODO speak() ---

    @Test
    public void testSpeakDireccionArriba() {
        // Preparar
        entity.dialogues[0] = "Hola";
        entity.dialogueIndex = 0;
        gp.player.direction = "up"; // Simulamos que el jugador mira arriba

        // Ejecutar
        entity.speak();

        // Comprobar (Assert)
        assertEquals("down", entity.direction, "La entidad debería mirar hacia abajo si el jugador mira hacia arriba.");
        assertEquals(1, entity.dialogueIndex, "El índice de diálogo debió incrementar.");
    }

    @Test
    public void testSpeakDialogoNulo() {
        // Preparar
        entity.dialogues[0] = null; // Forzamos la condición de nulo
        entity.dialogueIndex = 0;
        gp.player.direction = "left";

        // Ejecutar
        entity.speak();

        // Comprobar
        // Al ser null, el código lo reinicia a 0, le asigna el diálogo y luego incrementa a 1
        assertEquals(1, entity.dialogueIndex); 
        assertEquals("right", entity.direction);
    }

    // --- PRUEBAS PARA EL MÉTODO update() ---

    @Test
    public void testUpdateMovimientoSinColisionHaciaAbajo() {
        // Preparar
        entity.direction = "down";
        int posicionYInicial = entity.worldY;

        // Ejecutar
        entity.update();

        // Comprobar
        assertFalse(entity.collisionOn);
        assertEquals(posicionYInicial + entity.speed, entity.worldY, "La entidad debió moverse hacia abajo sumando su velocidad.");
    }

    @Test
    public void testUpdateCambioDeSprite() {
        // Preparar
        entity.direction = "up";
        entity.spriteCounter = 12; // Justo en el límite
        entity.spriteNum = 1;

        // Ejecutar
        entity.update(); // Aquí el counter subirá a 13, activando el if

        // Comprobar
        assertEquals(2, entity.spriteNum, "El spriteNum debió cambiar a 2.");
        assertEquals(0, entity.spriteCounter, "El contador debió reiniciarse a 0.");
    }

    // --- PRUEBAS PARA EL MÉTODO draw() ---

    @Test
    public void testDrawFueraDePantalla() {
        // Preparar: Alejamos la entidad del jugador para que el IF principal sea falso
        gp.player.worldX = 0;
        gp.player.worldY = 0;
        // screenX and screenY are final fields, cannot be assigned directly
        
        entity.worldX = 5000; // Muy lejos a la derecha
        entity.worldY = 5000;

        // Creamos un lienzo falso para que no dé error nulo
        BufferedImage imagenFalsa = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2Falso = imagenFalsa.createGraphics();

        // Ejecutar: No debería hacer nada ni lanzar errores
        assertDoesNotThrow(() -> entity.draw(g2Falso));
    }

    @Test
    public void testDrawDentroDePantallaTodasDirecciones() {
        // Preparar: Ponemos a la entidad exactamente donde está el jugador (visible)
        gp.player.worldX = 100;
        gp.player.worldY = 100;
        
        entity.worldX = 100;
        entity.worldY = 100;

        BufferedImage imagenFalsa = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2Falso = imagenFalsa.createGraphics();

        // Ejecutar y Comprobar: Cambiamos direcciones y sprites para cubrir cada "case" e "if"
        String[] direcciones = {"up", "down", "left", "right"};
        
        for (String dir : direcciones) {
            entity.direction = dir;
            
            entity.spriteNum = 1;
            assertDoesNotThrow(() -> entity.draw(g2Falso), "Falló dibujando " + dir + " sprite 1");
            
            entity.spriteNum = 2;
            assertDoesNotThrow(() -> entity.draw(g2Falso), "Falló dibujando " + dir + " sprite 2");
        }
        // Con este bucle logramos el 100% de cobertura del switch gigante de las imágenes.
    }

    // --- PRUEBAS PARA EL MÉTODO setUp() ---

    @Test
    public void testSetUpArchivoInvalido() {
        // Preparar: Le pasamos un nombre de archivo que sabemos que no existe
        String nombreArchivoFalso = "imagen_que_no_existe_12345";

        // Ejecutar: Esto forzará que falle el ImageIO.read() y entre al bloque catch (IOException)
        BufferedImage resultado = entity.setUp(nombreArchivoFalso);

        // Comprobar: Como falló, el resultado debe ser nulo y cubrimos la línea del e.printStackTrace()
        assertNull(resultado, "El resultado debe ser nulo si la imagen no existe.");
    }

    // Nota: Para probar el éxito de setUp() al 100%, necesitarías una prueba igual a la anterior 
    // pero pasándole el nombre de un archivo .png que SÍ exista en tu carpeta /src/Images/
}