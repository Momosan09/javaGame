package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_0 extends SuperObject{

    public OBJ_0(){
        name = "Objeto 0";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("../res/objects/object_0.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = false; //el objeto tiene colision;
    }
}