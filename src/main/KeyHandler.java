package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean wPressed, sPressed, aPressed, dPressed, enterPressed;
    public boolean checkDrawTime = false;
    private GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState) {
            if (gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                }

                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                }

                if (code == KeyEvent.VK_ENTER) {
                    switch (gp.ui.commandNum) {
                        case 0:
                            gp.ui.titleScreenState = 1;
                            break;
                        case 1:

                            break;
                        case 2:
                            System.exit(0);
                            break;

                        default:
                            break;
                    }
                }
            }

            else if (gp.ui.titleScreenState == 1) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }

                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }

                if (code == KeyEvent.VK_ENTER) {
                    switch (gp.ui.commandNum) {
                        case 0:
                            System.out.println("Do something as a Fighter");
                            gp.gameState = gp.playState;
                            gp.playMusic(0);
                            break;
                        case 1:
                            System.out.println("Do something as a Mage");
                            gp.gameState = gp.playState;
                            gp.playMusic(0);
                            break;

                        case 2:
                            System.out.println("Do something as a Assasin");
                            gp.gameState = gp.playState;
                            gp.playMusic(0);
                            break;
                        case 3:
                            gp.ui.titleScreenState = 0;
                            break;
                    }
                }
            }

        }

        if (gp.gameState == gp.playState) {

            if (code == KeyEvent.VK_W) {
                wPressed = true;
            }

            if (code == KeyEvent.VK_A) {
                aPressed = true;
            }

            if (code == KeyEvent.VK_S) {
                sPressed = true;
            }

            if (code == KeyEvent.VK_D) {
                dPressed = true;
            }

            if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }

            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

            if (code == KeyEvent.VK_T) {
                if (!checkDrawTime) {
                    checkDrawTime = true;
                } else if (checkDrawTime) {
                    checkDrawTime = false;
                }
            }
        }

        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }

        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            wPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            aPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            sPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            dPressed = false;
        }
    }

}
