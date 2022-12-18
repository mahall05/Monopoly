package Core;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

import Menus.Button;

public class MyMouseListener extends JComponent implements MouseInputListener{
    private Main game;
    private Window window;
    public MyMouseListener(Main game, Window window){
        this.game = game;
        this.window = window;
    }
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        Point mousePos = window.frame.getMousePosition();

        if(game.gameState == Main.STATE.Start){
            Button[] buttons = game.startMenu.buttons;
            // Start, Load, Settings

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    game.prevState = Main.STATE.Start;
                    switch(i){
                        case(0):
                            game.gameState = Main.STATE.Board;
                            break;
                        case(1):
                            //game.loadMenu.load();
                            //game.gameState = Main.STATE.Load;
                            break;
                        case(2):
                            //game.settingsMenu.prevState = Main.STATE.Start;
                            //game.gameState = Main.STATE.Settings;
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        Point mousePos = window.frame.getMousePosition();
        Button[] buttons = new Button[0];
        boolean hasButtons = true;

        switch(game.gameState){
            case Start:
                buttons = game.startMenu.buttons;
                break;
            default:
                hasButtons = false;
        }

        if(hasButtons){
            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    buttons[i].setButtonColor(Color.GRAY);
                }
            }
        }
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        Point mousePos = window.frame.getMousePosition();
        Button[] buttons = new Button[0];
        boolean hasButtons = true;

        switch(game.gameState){
            case Start:
                buttons = game.startMenu.buttons;
                break;
            default:
                hasButtons = false;
        }

        if(hasButtons){
            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    buttons[i].setButtonColor(Color.WHITE);
                }
            }
        }
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent arg0) {
        
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent arg0) {
        
    }

}