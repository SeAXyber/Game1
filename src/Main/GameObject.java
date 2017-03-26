package Main;

import java.awt.*;

/**
 * Created by bryce on 3/19/2017.
 */
public abstract class GameObject {

    protected int x,y;
    protected int dx,dy;
    protected ID id;
    protected int MissileCount = 10;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setId(ID id){
        this.id = id;
    }

    public void setDx(int dx){
        this.dx = dx;
    }

    public void setDy(int dy){
        this.dy = dy;
    }

    public void setMissileCount(int mc) {this.MissileCount = mc;}

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public ID getId(){
        return this.id;
    }

    public int getDx(){
        return this.dx;
    }

    public int getDy(){
        return this.dy;
    }

    public int getMissileCount(){return this.MissileCount;}
}
