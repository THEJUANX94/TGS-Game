package main;

import java.awt.*;

import javax.swing.JPanel;

import Tile.TileManager;
import entity.Entity;
import entity.Player;
import object.SuperObject;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings

    private final int originalTileSize = 16;
    private final int scale = 3;
    public final int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int FPS = 60;

    // World Settings

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;

    BufferedImage tempScreen;
    Graphics2D g2;

    public TileManager tileManager = new TileManager(this);
    public KeyHandler keys = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollicionChecker cChecker = new CollicionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // Entity and object
    public Player player = new Player(this, keys);
    public SuperObject obj[] = new SuperObject[15];
    public Entity npc[] = new Entity[10];

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public int dialogueState = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keys);
        this.setFocusable(true);
    }

    public void setFullScreen() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Principal.window);

        screenWidth2 = Principal.window.getWidth();
        screenHeight2 = Principal.window.getHeight();
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        setFullScreen();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {

        }
    }

    public void drawToTempScreen() {
        long drawStart = 0;
        if (keys.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            tileManager.draw(g2);
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }

            }
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }
            player.draw(g2);
            ui.draw(g2);
        }

        if (keys.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
    }

    public void drawToScreed() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                drawToTempScreen();
                drawToScreed();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
}
