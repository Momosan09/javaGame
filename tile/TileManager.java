package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    // PATHS
    String grass_0_Path = "../res/Tiles/grass_0.png";
    String water_0_Path = "../res/Tiles/water_0.png";
    String wall_0_Path = "../res/Tiles/wall_0.png";

    String map_0_Path = "../res/Maps/map0.txt";

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];//Agrandar el valor para mas Tiles
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap(map_0_Path);
    }

    public void getTileImage() {
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream(grass_0_Path));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream(water_0_Path));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream(wall_0_Path));

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

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();// lee una linea y lo pone en la variable "line"

                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
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
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }

    }

}