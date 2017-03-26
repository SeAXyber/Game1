package Main;

import GUI.*;
import Models.Player;
import Models.Wall;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by bryce on 3/19/2017.
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 700, HEIGHT = 700;
    private Thread thread;
    private Boolean running = false;

    private Handler handler;
    private HUD hud;
    private EndScreen endscreen;
    private GUI.Menu menu;

    public enum STATE{
        Menu,
        Settings,
        Game;
    }

    public STATE gameState = STATE.Menu;

    public Game(){
        handler = new Handler();
        menu = new GUI.Menu(this, handler);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(WIDTH,HEIGHT,"MyGame0",this);

        hud = new HUD(this);
        hud.P1_LIVES = 3;
        hud.P2_LIVES = 3;
        this.addMouseListener(hud);

        endscreen = new EndScreen(hud);



        if(gameState == STATE.Game) {
            setup();
        }
        requestFocus();
    }

    public void setup(){
        handler.addObject(new Player(0,HEIGHT/2,ID.Player1, handler));
        handler.addObject(new Player(WIDTH-30,HEIGHT/2,ID.Player2, handler));

        handler.addObject(new Wall(75,130,3,90,ID.Wall,handler)); //left hand side
        handler.addObject(new Wall(75,280,3,90,ID.Wall,handler));
        handler.addObject(new Wall(75,430,3,90,ID.Wall,handler));

        handler.addObject(new Wall(WIDTH-75,130,3,90,ID.Wall,handler)); //right hand side
        handler.addObject(new Wall(WIDTH-75,280,3,90,ID.Wall,handler));
        handler.addObject(new Wall(WIDTH-75,430,3,90,ID.Wall,handler));

        handler.addObject(new Wall(WIDTH/2,160,3,120,ID.Wall,handler)); //vertical middle
        handler.addObject(new Wall(WIDTH/2,HEIGHT-HUD.ScoreSection-160-120,3,120,ID.Wall,handler));

        handler.addObject(new Wall(170,(HEIGHT-HUD.ScoreSection)/2,100,3,ID.Wall,handler)); //horizontal middle
        handler.addObject(new Wall(430,(HEIGHT-HUD.ScoreSection)/2,100,3,ID.Wall,handler));

        handler.addObject(new Wall(210,80,110,3,ID.Wall,handler)); //upper-left T
        handler.addObject(new Wall(210,80,3,120,ID.Wall,handler));

        handler.addObject(new Wall(380,80,110,3,ID.Wall,handler)); //upper-right T
        handler.addObject(new Wall(490,80,3,120,ID.Wall,handler));

        handler.addObject(new Wall(210,HEIGHT-HUD.ScoreSection-160-120+80+120,110,3,ID.Wall,handler)); //lower-left T
        handler.addObject(new Wall(210,HEIGHT-HUD.ScoreSection-160-120+80,3,120,ID.Wall,handler));

        handler.addObject(new Wall(380,HEIGHT-HUD.ScoreSection-160-120+80+120,110,3,ID.Wall,handler)); //lower-left T
        handler.addObject(new Wall(490,HEIGHT-HUD.ScoreSection-160-120+80,3,120,ID.Wall,handler));
    }

    public synchronized void start(){ //for thread. Gonna use single threaded, ie. only 1 thing can happen at a time
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){ //for thread. Gonna use single threaded, ie. only 1 thing can happen at a time
        try {
            thread.join(); //killing the thread
            running = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() { //this run loop is copy pasted
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                //System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        if(gameState == STATE.Game) {
            //prevent multiple mouse events happening
            menu.MouseEnabled = false;
            hud.MouseEnabled = true;

            handler.tick();
            hud.tick();
        } else if (gameState == STATE.Menu) {
            menu.MouseEnabled = true;
            hud.MouseEnabled = false;
            handler.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        if(gameState == STATE.Game) {
            hud.render(g);
        } else {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    //bounds checker
    public static int clamp(int var, int min, int max){
        if(var>=max)
            return var=max;
        else if (var<=min)
            return var=min;
        else
            return var;
    }

    public Handler getHandler(){
        return this.handler;
    }

    public static void main (String args[]){
        new Game();
    }

}
