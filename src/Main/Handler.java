package Main;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by bryce on 3/19/2017.
 */
public class Handler {
    //handles and processes each object we have
    //loops through all objects in our game, individually updates and renders to screen

    public LinkedList<GameObject> objects = new LinkedList<GameObject>();

    public void tick(){
        for (int i=0; i<objects.size(); i++){
            GameObject object = objects.get(i);

            object.tick();
        }
    }

    public void render(Graphics g){
        for (int i=0; i<objects.size(); i++){
            GameObject object = objects.get(i);

            object.render(g);
        }
    }

    public void addObject(GameObject object){
        objects.add(object);
    }

    public void removeObject(GameObject object){
        objects.remove(object);
    }
}
