package Models;
import Main.Game;
import Main.GameObject;
import Main.Handler;
import Main.ID;

import java.awt.*;

/**
 * Created by bryce on 3/21/2017.
 */
public class Missile extends GameObject {

    Handler handler;
    int Missile_WIDTH, Missile_HEIGHT;

    public Missile (int x, int y, int dx, int dy, int width, int height, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;
        this.dx = dx;
        this.dy = dy;
        this.Missile_WIDTH = width;
        this.Missile_HEIGHT = height;
    }
    @Override
    public void tick() {
        x+=dx;
        y+=dy;
        collision();
    }

    public void collision(){
            for(int i = 0; i<handler.objects.size();i++){
                GameObject object = handler.objects.get(i);
                if(object.getId() == ID.Wall){
                    if (getBounds().intersects(object.getBounds())){
                        handler.objects.remove(this);
                    }
                }
            }
        //}
    }

    @Override
    public void render(Graphics g) {
        if (getId() == ID.P1_Missile){ g.setColor(Color.YELLOW);}
        if (getId() == ID.P2_Missile){ g.setColor(Color.red);}
        g.fillRect(x,y,Missile_WIDTH,Missile_HEIGHT);
    }

    @Override //COMPENSATING FOR MISSILES GOING THROUGH WALL
    public Rectangle getBounds() {
        return new Rectangle(x-8,y,Missile_WIDTH+9,Missile_HEIGHT);
    }
}
