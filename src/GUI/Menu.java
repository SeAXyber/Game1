package GUI;

import Main.Game;
import Main.Handler;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by bryce on 3/24/2017.
 */
public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    public boolean MouseEnabled;

    public Menu(Game game, Handler handler){
        this.game = game;
        this.handler = handler;
    }

    int PlayX = 225;
    int PlayY = 300;
    int PlayWidth = 250;
    int PlayHeight = 100;

    int SettingX = 225;
    int SettingY = 450;
    int SettingWidth = 250;
    int SettingHeight = 100;

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        //Play Button
        if(MouseEnabled){
            if(game.gameState == Game.STATE.Menu){
                if(mouseOver(mx,my,PlayX,PlayY,PlayWidth,PlayHeight)){
                    game.gameState = Game.STATE.Game;
                    game.setup();
                }
                //Settings Button
                if(mouseOver(mx,my,SettingX,SettingY,SettingWidth,SettingHeight)){
                    game.gameState = Game.STATE.Settings;
                }
            }
            if(game.gameState == Game.STATE.Settings){
                if(mx>game.getWidth()-52 && mx<game.getWidth()) {
                    if (my > 0 && my <= 35) {
                        game.getHandler().objects.clear();
                        game.gameState = Game.STATE.Menu;
                    }
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e){

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < (x+width)){
            if(my > y && my < (y+height)){
                return true;
            } else return false;
        } else return false;
    }

    public void tick(){

    }

    public void render(Graphics g){
        if(game.gameState == Game.STATE.Menu){
            g.setColor(Color.white);
            g.drawRect(PlayX,PlayY,PlayWidth,PlayHeight); //play
            g.drawRect(SettingX,SettingY,SettingWidth,SettingHeight); //setting

            Font font0 = new Font("Arial",1,60);
            g.setFont(font0);
            g.drawString("Game 0: Box Nemesis",40,200);

            Font font = new Font("Arial",1,40);
            g.setFont(font);
            g.drawString("Play",305,370);
            g.drawString("Settings",270,515);
        } else if(game.gameState == Game.STATE.Settings){
            Font font0 = new Font("Arial",1,60); //title setting
            g.setColor(Color.WHITE);
            g.setFont(font0);
            g.drawString("Settings",220,200);

            //escape button
            g.setColor(Color.WHITE);
            g.drawRect(game.getWidth() - 52,0,50,50);
            Font escfont = new Font("Arial",1,20);
            g.setFont(escfont);
            g.drawString("esc",game.getWidth()-40,35);
        }
    }
}
