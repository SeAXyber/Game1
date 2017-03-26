package Models;

import GUI.HUD;
import Main.*;

import java.awt.*;

/**
 * Created by bryce on 3/21/2017.
 */
public class Player extends GameObject {
    public final static int Player_WIDTH = 20, Player_HEIGHT = 20;
    Handler handler;
    private int P1death_timer;
    private int P2death_timer;
    private boolean P1dead;
    private boolean P2dead;
    public boolean collidedR, collidedL, collidedU, collidedD;

    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        P1death_timer  = 0;
        P2death_timer  = 0;
        P1dead = false;
        P2dead = false;
        collidedR = false; collidedL = false; collidedU = false; collidedD = false;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,Player_WIDTH,Player_HEIGHT);
    }

    @Override
    public void tick() {
        if(P1death_timer>0){ //Player 1 death timer
            P1dead = true;
            P1death_timer--;
            x += dx;
            y += dy;
            x = Game.clamp(x, 0, Game.WIDTH - Player_WIDTH-8);
            y = Game.clamp(y, HUD.ScoreSection, Game.HEIGHT - 50);
        }
        else {
            if(HUD.P1_LIVES>0) {
                if(collidedU) {
                }
                P1dead = false;
                x += dx;
                y += dy;
                x = Game.clamp(x, 0, Game.WIDTH - Player_WIDTH-8);
                y = Game.clamp(y, HUD.ScoreSection, Game.HEIGHT - 50);
                collision();
            }
        }
        if(P2death_timer>0) { //Player 2 death timer
            P2dead = true;
            P2death_timer--;
            x += dx;
            y += dy;
            x = Game.clamp(x, 0, Game.WIDTH - Player_WIDTH-8);
            y = Game.clamp(y, HUD.ScoreSection, Game.HEIGHT - 50);
        }
        else {
            if(HUD.P2_LIVES>0) {
                P2dead = false;
                x += dx;
                y += dy;
                x = Game.clamp(x, 0, Game.WIDTH - Player_WIDTH-8);
                y = Game.clamp(y, HUD.ScoreSection, Game.HEIGHT - 50);
                collision();
            }
        }
    }

    private void collision(){
        if(getId()==ID.Player1) { // Player 1 collision
            if (!P1dead) {
                for (int i = 0; i < handler.objects.size(); i++) {
                    GameObject object = handler.objects.get(i);
                    if (object.getId() == ID.P2_Missile) { // if it collides with p2's missile
                        if (getBounds().intersects(object.getBounds())) {
                            HUD.P1_LIVES--;
                            if(HUD.P1_LIVES!=0) { // if not out of lives
                                P1death_timer = 200;
                                handler.objects.remove(object);
                            }
                            else if(HUD.P1_LIVES==0){ // if out of lives
                                P1dead = true;
                                handler.objects.remove(this);
                                handler.objects.remove(object);
                            }
                        }
                    }
                    if (object.getId() == ID.Wall || object.getId() == ID.Player2){ // if it collides with the wall or player1
                        if(getBounds().intersects((object.getBounds()))) {
                           /* if (getDx() > 0) {
                                this.x -= 1 + getDx();
                            } else if(getDx() < 0) {
                                this.x += 1 - getDx();
                            }*/
                            this.x -= getDx();
                            this.y -= getDy();
                            setDx(0);
                            setDy(0);
                        }
                    }
                }
            }
        }
        if(getId()==ID.Player2) { // Player 2 collision
            if (!P2dead) {
                for (int i = 0; i < handler.objects.size(); i++) {
                    GameObject object = handler.objects.get(i);
                    if (object.getId() == ID.P1_Missile) { // if it collides with p1's missile
                        if (getBounds().intersects(object.getBounds())) {
                            //collision code
                            HUD.P2_LIVES--;
                            if(HUD.P2_LIVES!=0) {
                                P2death_timer = 200;
                                handler.objects.remove(object);
                            }
                            else if(HUD.P2_LIVES==0) { //if out of lives
                                P2dead = true;
                                handler.objects.remove(this);
                                handler.objects.remove(object);
                            }
                        }
                    }
                    if (object.getId() == ID.Wall || object.getId() == ID.Player1){ // if it collides with the wall or player1
                        if(getBounds().intersects(object.getBounds())) {
                            this.x -= getDx();
                            this.y -= getDy();
                            setDx(0);
                            setDy(0);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(getId() == ID.Player1) g.setColor(Color.BLUE);
        if(getId() == ID.Player2) g.setColor(Color.RED);
        g.fillRect(x,y,Player_WIDTH,Player_HEIGHT);

        if((P1dead && HUD.P1_LIVES!=0) || (P2dead && HUD.P2_LIVES!=0)){ //invincibility after death
            g.setColor(Color.GREEN);
            g.drawRect(getX(),getY(),Player_WIDTH,Player_HEIGHT);
            g.drawRect(getX()-1,getY()-1,Player_WIDTH+2, Player_HEIGHT+2);
            g.drawRect(getX()-2,getY()-2,Player_WIDTH+4, Player_HEIGHT+4);
        }
    }
}
