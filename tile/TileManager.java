package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import main.GamePanel;
//import entity.EntityNpc;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    // PATHS
/* 
    String grass_0_Path = "../res/Tiles/grass_0.png";
    String water_0_Path = "../res/Tiles/water_0.png";
    String wall_0_Path = "../res/Tiles/wall_0.png";

    String map_0_Path = "../res/Maps/map0.txt";
*/
    String floor_dome_0_Decorado = "../res/Tiles/newTiles/Floor_Dome_0_Decorado.png";
    String floor_dome_0 = "../res/Tiles/newTiles/Floor_Dome_0.png";
    String ground_0 = "../res/Tiles/newTiles/ground_0.png"; 


    String map_1_Path = "../res/Maps/map1.txt";
    

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];// Agrandar el valor para mas Tiles
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap(map_1_Path);
    }

    public void getTileImage() {
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream(ground_0));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream(floor_dome_0));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream(floor_dome_0_Decorado));
            tile[2].collision = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br/* bufferedReader */ = new BufferedReader(new InputStreamReader((is/* inputStream */)));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();// lee una linea y lo pone en la variable "line"

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }

            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {
        // tiles 64x64
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //este if es para que solo se dibujen las tiles que esten dentro de la pantalla y que no carge el mapa entero inecesariamente
            if( worldX + gp.tileSize > gp.player.worldX - gp.player.screenX  && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX  && 
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY  && 
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }


    }

}