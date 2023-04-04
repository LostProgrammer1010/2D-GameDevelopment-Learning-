package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gp;
    //DEBUG
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //KEY PRESSED HANDLER
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }

    }


    //KEY RELEASE HANDLER
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_P) {
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if (gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }
        if (code == KeyEvent.VK_Q) {
            if (gp.player.playerHasWand){
                gp.player.attack("Wand");
            }
        }
        // DEBUG
        if (code == KeyEvent.VK_T){
            if (!checkDrawTime)
            checkDrawTime = true;
        }
        else if(checkDrawTime){
            checkDrawTime = false;
        }
    }

}
