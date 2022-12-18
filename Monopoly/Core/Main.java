package Core;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import Menus.*;

public class Main extends Canvas implements Runnable{

    private Thread thread; // The game runs on this thread
    private boolean running = false;

    public static Window window;

    public enum STATE{
        Start,
        Board,
    };

    /* MENUS */
    StartMenu startMenu;
    BoardScreen boardScreen;


    public STATE gameState;
    public STATE prevState = STATE.Start;

    public Main() throws IOException{
        /* MENUS */
        startMenu = new StartMenu();
        boardScreen = new BoardScreen();

        ImageLoader.loadImages();
        window = new Window(Constants.WIDTH, Constants.HEIGHT, "Monopoly", this);
    }

    private void tick(){
        switch(gameState){
            case Start:
                startMenu.tick();
                break;
            case Board:
                boardScreen.tick();
                break;
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        /*
        switch(gameState){
            case Start:
                startMenu.render(g);
                break;
            case Board:
                boardScreen.render(g);
                break;
        }
        */

        if(gameState == STATE.Start){
            startMenu.render(g);
        }else if(gameState == STATE.Board){
            boardScreen.render(g);
        }

        g.dispose();
        bs.show();
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;

        gameState = STATE.Start;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        new Main();
    }

    @Override
    public void run() { // The game loop
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
            }
        }
    }

    public static int randomInt(int minInclusive, int maxInclusive){
        int range = maxInclusive - minInclusive + 1;
        int rng = (int)(Math.random() * range) + minInclusive;
        return rng;
    }

    public static double randomDouble(double minInclusive, double maxInclusive){
        double range = maxInclusive - minInclusive + 1;
        double rng = (Math.random() * range) + minInclusive;
        return rng;
    }
}
