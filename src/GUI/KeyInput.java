package GUI;

import Main.Game;
import Main.GameObject;
import Main.Handler;
import Main.ID;
import Models.Missile;
import Models.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by bryce on 3/19/2017.
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private int MissileSpeed = 10;
    private int Player1Speed = 2;
    private int Player2Speed = 2;
    private boolean[] keyDown1 = new boolean[4];
    private boolean[] keyDown2 = new boolean[4];

    public KeyInput(Handler handler){
        this.handler = handler;
        keyDown1[0] = false;
        keyDown1[1] = false;
        keyDown1[2] = false;
        keyDown1[3] = false;

        keyDown2[0] = false;
        keyDown2[1] = false;
        keyDown2[2] = false;
        keyDown2[3] = false;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        // Player 1 controls
        for (int i=0; i<handler.objects.size();i++){
            GameObject object = handler.objects.get(i);
            if (object.getId()== ID.Player1){
                //Key Events for Player1

                if (key == KeyEvent.VK_A) {keyDown1[0] = true; object.setDx(-Player1Speed);}
                if (key == KeyEvent.VK_D) {keyDown1[1] = true; object.setDx(Player1Speed);}
                if (key == KeyEvent.VK_W) {keyDown1[2] = true; object.setDy(-Player1Speed);}
                if (key == KeyEvent.VK_S) {keyDown1[3] = true; object.setDy(Player1Speed);}
                //if(object.getMissileCount()!=0) {
                    if (key == KeyEvent.VK_F) {
                        handler.addObject(new Missile(object.getX(), object.getY() + Player.Player_HEIGHT / 2
                                , -MissileSpeed,0,7,1, ID.P1_Missile, handler));
                        object.setMissileCount(object.getMissileCount() - 1);
                    }
                    if (key == KeyEvent.VK_G) {
                        handler.addObject(new Missile(object.getX() + Player.Player_WIDTH/2, object.getY() + Player.Player_HEIGHT
                                ,0,MissileSpeed,1,7, ID.P1_Missile, handler));
                        object.setMissileCount(object.getMissileCount() - 1);
                    }
                    if (key == KeyEvent.VK_H) {
                        handler.addObject(new Missile(object.getX() + Player.Player_WIDTH, object.getY() + Player.Player_HEIGHT / 2
                                ,MissileSpeed,0,7,1, ID.P1_Missile, handler));
                        object.setMissileCount(object.getMissileCount() - 1);
                    }
                    if (key == KeyEvent.VK_T) {
                        handler.addObject(new Missile(object.getX() + Player.Player_WIDTH/2, object.getY(),0
                                , -MissileSpeed,1,7, ID.P1_Missile, handler));
                        object.setMissileCount(object.getMissileCount() - 1);
                    }
                }
           // }
        }

        // Player 2 controls
        for (int i=0; i<handler.objects.size();i++) {
            GameObject object = handler.objects.get(i);
            if (object.getId() == ID.Player2) {
                //Key Events for Player2

                if (key == KeyEvent.VK_LEFT) {keyDown2[0] = true; object.setDx(-Player2Speed);}
                if (key == KeyEvent.VK_RIGHT) {keyDown2[1] = true;  object.setDx(Player2Speed);}
                if (key == KeyEvent.VK_UP) {keyDown2[2] = true; object.setDy(-Player2Speed);}
                if (key == KeyEvent.VK_DOWN) {keyDown2[3] = true; object.setDy(Player2Speed);}
                //if (key == KeyEvent.VK_SPACE && object.getMissileCount() != 0) {
                    if (key == KeyEvent.VK_NUMPAD4) {
                        handler.addObject(new Missile(object.getX(), object.getY() + Player.Player_HEIGHT / 2
                                , -MissileSpeed, 0,7,1, ID.P2_Missile, handler));
                        object.setMissileCount(object.getMissileCount() - 1);
                    }
                    if (key == KeyEvent.VK_NUMPAD5) {
                        handler.addObject(new Missile(object.getX() + Player.Player_WIDTH / 2, object.getY() + Player.Player_HEIGHT
                                , 0, MissileSpeed,1,7, ID.P2_Missile, handler));
                        object.setMissileCount(object.getMissileCount() - 1);
                    }
                    if (key == KeyEvent.VK_NUMPAD6) {
                        handler.addObject(new Missile(object.getX() + Player.Player_WIDTH, object.getY() + Player.Player_HEIGHT / 2
                                , MissileSpeed, 0,7,1, ID.P2_Missile, handler));
                        object.setMissileCount(object.getMissileCount() - 1);
                    }
                    if (key == KeyEvent.VK_NUMPAD8) {
                        handler.addObject(new Missile(object.getX() + Player.Player_WIDTH / 2, object.getY(), 0
                                , -MissileSpeed,1,7, ID.P2_Missile, handler));
                        object.setMissileCount(object.getMissileCount() - 1);
                    }
                //}
            }
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
        if (key == KeyEvent.VK_SPACE){
            Game game = new Game();
            game.setup();
            game.gameState = Game.STATE.Game;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for (int i=0; i<handler.objects.size();i++){
            GameObject object = handler.objects.get(i);
            if (object.getId()==ID.Player1){
                //Key Events for Player1

                if (key == KeyEvent.VK_A) keyDown1[0] = false;
                if (key == KeyEvent.VK_D) keyDown1[1] = false;
                if (key == KeyEvent.VK_W) keyDown1[2] = false;
                if (key == KeyEvent.VK_S) keyDown1[3] = false;

                //vertical movement
                if(!keyDown1[2] && !keyDown1[3]){object.setDy(0);}

                //horizontal movement
                if(!keyDown1[0] && !keyDown1[1]){object.setDx(0);}
            }
        }

        for (int i=0; i<handler.objects.size();i++){
            GameObject object = handler.objects.get(i);
            if (object.getId()==ID.Player2){
                //Key Events for Player2

                if (key == KeyEvent.VK_LEFT) keyDown2[0] = false;
                if (key == KeyEvent.VK_RIGHT)keyDown2[1] = false;
                if (key == KeyEvent.VK_UP) keyDown2[2] = false;
                if (key == KeyEvent.VK_DOWN) keyDown2[3] = false;

                //vertical movement
                if(!keyDown2[2] && !keyDown2[3]){object.setDy(0);}

                //horizontal movement
                if(!keyDown2[0] && !keyDown2[1]){object.setDx(0);}
            }
        }
    }

}
