package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import object.OBJ_Heart;
import object.SuperObject;

public class UI {

    public GamePanel gp;
    public Graphics2D g2;
    public Font maruMonicaFont;
    public BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    int substate = 0;
    public int correctAnswers = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            File is = new File("./src/Images/Fonts/x12y16pxMaruMonica.ttf");
            maruMonicaFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String message) {
        this.message = message;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(maruMonicaFont);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }

        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }

        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }

        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }

        if (gp.gameState == gp.questionsState) {
            questions_UI();
        }
    }

    private void drawOptionsScreen() {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        int framex = gp.tileSize * 6;
        int framey = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(framex, framey, frameWidth, frameHeight);

        switch (substate) {
            case 0:
                options_top(framex, framey);
                break;
            case 1:
                options_fullScreenNotification(framex, framey);
                break;
            case 2:
                options_Control(framex, framey);
                break;
            case 3:
                options_EndGame(framex, framey);
                break;
            default:

                break;
        }

        gp.keys.enterPressed = false;
    }

    private void options_EndGame(int framex, int framey) {
        int textX = framex + gp.tileSize;
        int textY = framey + gp.tileSize * 3;

        currentDialogue = "¿Estas seguro de salir? \nno se guardara el progreso";

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        String text = "Si";
        textX = geXForCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                substate = 0;
                gp.gameState = gp.titleState;
                gp.stopMusic();
            }
        }

        text = "No";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                substate = 0;
                commandNum = 4;
            }
        }
    }

    public void options_top(int framex, int framey) {
        int textX;
        int textY;

        String text = "Options";
        textX = geXForCenteredText(text);
        textY = framey + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = framex + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed == true) {
                if (gp.fullScreenOn == false) {
                    gp.fullScreenOn = true;
                } else if (gp.fullScreenOn == true) {
                    gp.fullScreenOn = false;
                }
                substate = 1;
            }
        }

        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }

        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }

        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed == true) {
                substate = 2;
                commandNum = 0;
            }
        }

        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                substate = 3;
                commandNum = 0;
            }
        }

        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        textX = framex + (int) (gp.tileSize * 4.5);
        textY = framey + gp.tileSize * 2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if (gp.fullScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }

        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveCofig();
    }

    public void questions_UI() {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        int framex = gp.tileSize * 5;
        int framey = gp.tileSize;
        int frameWidth = gp.tileSize * 10;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(framex, framey, frameWidth, frameHeight);

        switch (substate) {
            case 0:
                initial_Question(framex, framey);
                break;
            case 1:
                cuestionary1(framex, framey);
                break;
            case 2:
                cuestionary2(framex, framey);
                break;
            case 3:
                cuestionary3(framex, framey);
                break;
            case 4:
                cuestionary4(framex, framey);
                break;
            case 5:
                cuestionary5(framex, framey);
                break;
            case 6:
                cuestionary6(framex, framey);
                break;
            case 7:
                cuestionary7(framex, framey);
                break;
            case 8:
                cuestionary8(framex, framey);
                break;
            case 9:
                finalResults(framex, framey);
                break;
        }

        gp.keys.enterPressed = false;
    }

    private void finalResults(int framex, int framey) {
        int textX;
        int textY;

        String text = "Cuestionario";
        textX = geXForCenteredText(text);
        textY = framey + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = framex + (gp.tileSize + 20);
        textY += gp.tileSize * 2;
        currentDialogue = "Tus resultados finales fueron:";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        text = String.valueOf(correctAnswers) + "/8";
        textX = geXForCenteredText(text);
        textY += gp.tileSize * 2;
        g2.drawString(text, textX, textY);

        text = "¿Reiniciar?";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);

        text = "Si";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                substate = 0;
            }
        }

        text = "No";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                substate = 0;
                gp.gameState = gp.playState;
            }
        }
    }

    private void cuestionary1(int framex, int framey) {
        int textX;
        int textY;

        String text = "Cuestionario";
        textX = geXForCenteredText(text);
        textY = framey + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = framex + (gp.tileSize + 20);
        textY += gp.tileSize * 2;
        currentDialogue = "¿Que caracteristica de los \nsistemas tiende al orden?";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        text = "Neguentropia";
        textX = geXForCenteredText(text);
        textY += gp.tileSize * 2;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("Nice", textX + 175, textY);
                correctAnswers++;
                if (gp.keys.enterPressed) {
                    substate = 2;
                }
            }
        }

        text = "Equidad";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("X", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 2;
                }
            }
        }

        text = "Equifinalidad";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("X", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 2;
                }
            }
        }
    }

    private void cuestionary2(int framex, int framey) {
        int textX;
        int textY;

        String text = "Cuestionario";
        textX = geXForCenteredText(text);
        textY = framey + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = framex + (gp.tileSize + 20);
        textY += gp.tileSize * 2;
        currentDialogue = "\"El todo suma mas que \nsus partes\"";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        text = "Peter Senge";
        textX = geXForCenteredText(text);
        textY += gp.tileSize * 2;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("X", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 3;
                }
            }
        }

        text = "Aristoteles";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("Nice", textX + 175, textY);
                correctAnswers++;
                if (gp.keys.enterPressed) {
                    substate = 3;
                }
            }
        }

        text = "Bertalanffy";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("X", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 3;
                }
            }
        }
    }

    private void cuestionary3(int framex, int framey) {
        int textX;
        int textY;

        String text = "Cuestionario";
        textX = geXForCenteredText(text);
        textY = framey + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = framex + (gp.tileSize + 20);
        textY += gp.tileSize * 2;
        currentDialogue = "Tipo de sistema que presenta \nintercambio de energia con \nel ambiente";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        text = "sistema cerrado";
        textX = geXForCenteredText(text);
        textY += gp.tileSize * 2;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("X", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 4;
                }
            }
        }

        text = "Sistema abierto";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("Nice", textX + 175, textY);
                correctAnswers++;
                if (gp.keys.enterPressed) {
                    substate = 4;
                }
            }
        }

        text = "Sistema complejo";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("X", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 4;
                }
            }
        }
    }

    private void cuestionary4(int framex, int framey) {
        int textX;
        int textY;

        String text = "Cuestionario";
        textX = geXForCenteredText(text);
        textY = framey + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = framex + (gp.tileSize + 20);
        textY += gp.tileSize * 2;
        currentDialogue = "Caracteristica de los sistemas que \nsignifica desorden o descontrol";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        text = "Entropia";
        textX = geXForCenteredText(text);
        textY += gp.tileSize * 2;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("Nice", textX + 175, textY);
                correctAnswers++;
                if (gp.keys.enterPressed) {
                    substate = 5;
                }
            }
        }

        text = "Homeostasis";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("X", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 5;
                }
            }
        }

        text = "Complejidad";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("X", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 5;
                }
            }
        }
    }

    private void cuestionary5(int framex, int framey) {
        int textX;
        int textY;

        String text = "Cuestionario";
        textX = geXForCenteredText(text);
        textY = framey + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = framex + (gp.tileSize + 20);
        textY += gp.tileSize * 2;
        currentDialogue = "Padre de la teoria de sistemas";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        text = "Aristoteles";
        textX = geXForCenteredText(text);
        textY += gp.tileSize * 2;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("X", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 6;
                }
            }
        }

        text = "Peter Senge";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("X", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 6;
                }
            }
        }

        text = "Bertalanffy";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("Nice", textX + 175, textY);
                correctAnswers++;
                if (gp.keys.enterPressed) {
                    substate = 6;
                }
            }
        }
    }

    private void cuestionary6(int framex, int framey) {
        int textX;
        int textY;

        String text = "Cuestionario";
        textX = geXForCenteredText(text);
        textY = framey + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = framex + (gp.tileSize + 20);
        textY += gp.tileSize * 2;
        currentDialogue = "Autor de las 11 leyes de creacion \nde sistemas";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        text = "Peter Senge";
        textX = geXForCenteredText(text);
        textY += gp.tileSize * 2;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("Nice", textX + 175, textY);
                correctAnswers++;
                if (gp.keys.enterPressed) {
                    substate = 7;
                }
            }
        }

        text = "Aristoteles";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("X", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 7;
                }
            }
        }

        text = "Jenking";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("X", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 7;
                }
            }
        }
    }

    private void cuestionary7(int framex, int framey) {
        int textX;
        int textY;

        String text = "Cuestionario";
        textX = geXForCenteredText(text);
        textY = framey + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = framex + (gp.tileSize + 20);
        textY += gp.tileSize * 2;
        currentDialogue = "Autor que habla de la \nautoorganizacion de los sistemas";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        text = "Jordan";
        textX = geXForCenteredText(text);
        textY += gp.tileSize * 2;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("Nice", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 8;
                }
            }
        }

        text = "Beer";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("Nice", textX + 175, textY);
                correctAnswers++;
                if (gp.keys.enterPressed) {
                    substate = 8;
                }
            }
        }

        text = "Jenking";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("X", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 8;
                }
            }
        }
    }

    private void cuestionary8(int framex, int framey) {
        int textX;
        int textY;

        String text = "Cuestionario";
        textX = geXForCenteredText(text);
        textY = framey + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = framex + (gp.tileSize + 20);
        textY += gp.tileSize * 2;
        currentDialogue = "Autor que habla de los niveles\n de clasificacion de los sistemas";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        text = "Jordan";
        textX = geXForCenteredText(text);
        textY += gp.tileSize * 2;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("Nice", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 9;
                }
            }
        }

        text = "Beer";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("Nice", textX + 175, textY);
                if (gp.keys.enterPressed) {
                    substate = 9;
                }
            }
        }

        text = "Boulding";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                g2.drawString("Nice", textX + 175, textY);
                correctAnswers++;
                if (gp.keys.enterPressed) {
                    substate = 9;
                }
            }
        }
    }

    private void initial_Question(int framex, int framey) {
        int textX;
        int textY;

        correctAnswers = 0;
        String text = "Cuestionario";
        textX = geXForCenteredText(text);
        textY = framey + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = framex + (gp.tileSize + 20);
        textY += gp.tileSize * 2;
        g2.drawString("¿Quieres iniciar el cuestionario?", textX, textY);

        text = "Si";
        textX = geXForCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                commandNum = 0;
                substate = 1;
            }
        }

        text = "No";
        textX = geXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed) {
                gp.gameState = gp.playState;
            }
        }
    }

    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen() {
        if (titleScreenState == 0) {
            g2.setColor(new Color(80, 70, 182));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Blue boy Adventure";
            int x = geXForCenteredText(text);
            int y = gp.tileSize * 3;

            g2.setColor(Color.BLACK);
            g2.drawString(text, x + 5, y + 5);

            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);

            x = (gp.screenWidth / 2) - (gp.tileSize * 2) / 2;
            y += gp.tileSize * 2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            text = "NEW GAME";
            x = geXForCenteredText(text);
            y += gp.tileSize * 3.5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "QUIT";
            x = geXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        } else if (titleScreenState == 1) {
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your class!";
            int x = geXForCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = geXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Mage";
            x = geXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Assasin";
            x = geXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Back";
            x = geXForCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }

    }

    private void drawDialogueScreen() {
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void options_fullScreenNotification(int framex, int framey) {
        int textX = framex + gp.tileSize;
        int textY = framey + gp.tileSize * 3;

        currentDialogue = "El cambio tendra \nefecto al reiniciar el \njuego";

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        textY = framey + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed == true) {
                substate = 0;
            }
        }
    }

    public void options_Control(int framex, int framey) {
        int textX;
        int textY;

        String text = "Control";
        textX = geXForCenteredText(text);
        textY = framey + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = framex + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Confirm", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Pause", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Options", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Cuestionario", textX, textY);

        textX = framex + gp.tileSize * 6;
        textY = framey + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY);
        textY += gp.tileSize;
        g2.drawString("P", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);
        textY += gp.tileSize;
        g2.drawString("X", textX, textY);

        textX = framex + gp.tileSize;
        textY = framey + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keys.enterPressed == true) {
                substate = 0;
                commandNum = 3;
            }
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setStroke(new BasicStroke(5));
        g2.setColor(c);
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSA";
        int x = geXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int geXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
