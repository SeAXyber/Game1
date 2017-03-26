package Models;

import Main.GameObject;
import Main.Handler;
import Main.ID;

import java.awt.*;

/**
 * Created by bryce on 3/23/2017.
 */
public class Wall extends GameObject {

    Handler handler;
    int WIDTH, HEIGHT;

    public Wall(int x, int y, int width, int height, ID id, Handler handler){
        super(x,y,id);
        this.WIDTH = width;
        this.HEIGHT = height;
        this.handler = handler;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(getX(),getY(),WIDTH,HEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }
}
