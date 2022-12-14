package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 32;
    final int scale = 2;

    public final int tileSize = originalTileSize * scale; // 64x64
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 1024 pixeles
    public final int screenHeight = tileSize * maxScreenRow; // 768 pixeles

    //WORLD SETTINGS
    public final int maxWorldCol = 12;//el tamaño del estas variables y del mapa tienen que ser los iguales
    public final int maxWorldRow = 12;//el tamaño del estas variables y del mapa tienen que ser los iguales
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    // FPS
    public int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,keyH);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter ASetter = new AssetSetter(this);
    public SuperObject obj[] = new SuperObject[10];//esto dice que solo podes mostrar 10 objetos a la vez, no es la cantidad de objetos que se pueden agregar
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        ASetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);// pasa GamePanel como parametro
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {

                // UPDATE: Actualiza informacion, como la informacion de posicion del personaje
                update();

                // DRAW: Dibuja en la pantalla con la informacion actualizada
                repaint();

                delta--;
                drawCount++;
                if (timer >= 1000000000) {
                    System.out.println("FPS:" + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }

        }

    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);// "super" es la clase padre de la clase en la que estoy
        Graphics2D g2 = (Graphics2D) g;

        //Tile
        tileM.draw(g2);
        
        //Object
        for(int i=0;i<obj.length;++i){
            if(obj[i] != null){//checkear que el vector no este vacio
                obj[i].draw(g2, this);
            }
        }

        //Player
        player.draw(g2);
        
        g2.dispose();
    }

}
