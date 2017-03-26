package GUI;

import java.awt.*;

/**
 * Created by bryce on 3/22/2017.
 */
public class EndScreen {
    private HUD hud;
    private String message;
    private int x,y;

    public EndScreen(HUD hud){
        this.hud = hud;
    }

    public void render(Graphics g){
        g.drawString(message, x, y);
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public String getMessage(){
        return this.message;
    }
}
