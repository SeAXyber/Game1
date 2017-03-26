package GUI;

import GUI.EndScreen;
import Main.Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by bryce on 3/19/2017.
 */
public class HUD extends MouseAdapter {
    public static int P1_LIVES = 3;
    public static int P2_LIVES = 3;
    private Game game;
    private Font font = new Font("TimesRoman", Font.BOLD, 30);
    int Life_WIDTH = 60, Life_HEIGHT = 30;
    public static final int ScoreSection = 50;
    public boolean MouseEnabled;

    public HUD(Game g){
        this.game = g;
        g.setFont(font);
    }

    //private int time = 1000;

    public void tick() {
    }

    public void render(Graphics g) {

        //LIVES
        g.setColor(Color.WHITE);
        g.drawLine(0,ScoreSection,Game.WIDTH,ScoreSection);
        g.setColor(Color.RED);
        g.fillRect(15 + Life_WIDTH,15,3*Life_WIDTH,Life_HEIGHT);
        g.fillRect(Game.WIDTH - 15 - Life_WIDTH -3*Life_WIDTH,15,3*Life_WIDTH,Life_HEIGHT);
        for (int i = 0; i < P1_LIVES; i++) { //P1 LIVES
            g.setColor(Color.GREEN);
            g.fillRect(15 + Life_WIDTH * (i + 1), 15, Life_WIDTH, Life_HEIGHT);
        }

        for (int i = 0; i < P2_LIVES; i++) { //P2 LIVES
            g.setColor(Color.GREEN);
            g.fillRect((Game.WIDTH - 15 - Life_WIDTH) - Life_WIDTH * (i + 1), 15, Life_WIDTH, Life_HEIGHT);
        }

        EndScreen es = new EndScreen(this);
        es.setMessage("Game Over");
        es.setX(Game.WIDTH / 2 - 75);
        es.setY(Game.HEIGHT / 2 - 40);

        EndScreen es1 = new EndScreen(this);
        es1.setMessage(printWinner(P2_LIVES));
        es1.setX(Game.WIDTH / 2 - 65);
        es1.setY(Game.HEIGHT / 2);

        EndScreen es2 = new EndScreen(this);
        es2.setMessage("Press [Space] to reset, or [Esc] to quit");
        es2.setX(Game.WIDTH / 2 - 120);
        es2 .setY(Game.HEIGHT / 2 + 40);

        // game-over screen
        if (P1_LIVES==0){
            game.getHandler().objects.clear();
            g.setColor(Color.WHITE);
            es.render(g);
            es1.setMessage(printWinner(P2_LIVES));
            es1.render(g);
            g.setFont(new Font("Times New Romans", Font.ITALIC,15));
            es2.render(g);
        }

            if (P2_LIVES==0){
            game.getHandler().objects.clear();
            g.setColor(Color.WHITE);
            es.render(g);
            es1.setMessage(printWinner(P2_LIVES));
            es1.render(g);
            g.setFont(new Font("Times New Romans", Font.ITALIC,15));
            es2.render(g);
        }

        // escape button
        g.setColor(Color.WHITE);
        g.drawRect(game.getWidth() - 52,0,50,50);
        Font escfont = new Font("Arial",1,20);
        g.setFont(escfont);
        g.drawString("esc",game.getWidth()-40,35);

        // settings button
        g.setColor(Color.WHITE);
        g.drawRect(0,0,50,50);
        g.setFont(escfont);
        g.drawString("set",12,35);
    }

    public String printWinner(int p2_LIVES){
        if (p2_LIVES == 0){
            return "Blue Wins";
        }
        else return "Red Wins";
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        if(MouseEnabled){
            // if esc pressed
            if(mx>game.getWidth()-52 && mx<game.getWidth()) {
                if (my > 0 && my <= 49) {
                    game.getHandler().objects.clear();
                    game.gameState = Game.STATE.Menu;
                }
            }

            //if set pressed
            if(mx>=0 && mx<=50) {
                if (my >= 0 && my <= 50) {
                    game.getHandler().objects.clear();
                    game.gameState = Game.STATE.Settings;
                }
            }
        }
    }
}