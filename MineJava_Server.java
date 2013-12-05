/*
 * Me Syriamanal
 * 
 */
package pocketserver;

import java.io.File;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Logger;
import pocketserver.command.ServerCommands;
import pocketserver.config.ServerConfiguration;
import pocketserver.entity.PlayerEntity;
import pocketserver.network.NetworkManager;
import pocketserver.save.Serializer;

/**
 *
 * @author dev
 */
public class PocketServer implements Runnable {

    public static final Logger logger = Logger.getLogger("PocketServer");
    
    public NetworkManager networkManager;
    
    private ServerConfiguration serverConfig;
    
    private static boolean serverRunning;
    public static boolean serverStopped;
    private long startTime;
    private int tps;
    private long lastTpsTime;

    public ArrayList<PlayerEntity> playerEntityList;
    public ArrayList<Integer> playerEntityIDList;
    
    public PocketServer() {
        serverRunning = true;
        serverStopped = false;
        
        playerEntityList = new ArrayList<PlayerEntity>();
        playerEntityIDList = new ArrayList<Integer>();
    }
    
    private boolean startServer() {
        
        
        logger.info("Starting Pocketserver Version: 0.6.8"); //for minecraft 0.6.x; revision 8;
        startTime = System.currentTimeMillis();
        ServerCommands serverCommands = new ServerCommands(this);
        serverCommands.setDaemon(true);
        serverCommands.start();
        
        serverConfig = new ServerConfiguration();

        
        try {
            networkManager = new NetworkManager(this, 19132);
            networkManager.start();
        } catch (SocketException s) {
            s.printStackTrace();
        }


        return true;
    }

    public void run() {
        if(startServer()){
            mainLoop();
            
        }
    }

    private void mainLoop() {
        long lastLoopTime = System.nanoTime();
            final int FARGET_TPS = 2;
            final long OPTIMAL_TIME = 1000000000 / FARGET_TPS;
            int gcount = 0;
            while (serverRunning) {
                long now = System.nanoTime();
                long updateLength = now - lastLoopTime;
                lastLoopTime = now;

                lastTpsTime += updateLength;
                tps++;
                gcount++;
                if (lastTpsTime >= 1000000000) {
                    System.out.println("(TPS: " + tps +")");
                    lastTpsTime = 0;
                    tps = 0;
                }

                tick();

                try { Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000); } 
                catch (Exception e) { }

                if (gcount % 100 == 0) {
                    Runtime.getRuntime().gc();
                    gcount=0;
                }
            }
    }
    
    public void initiateShutDown() {
        serverRunning = false;
        serverConfig.worldConfig.saveConfigFile();
        savePlayers();
        System.exit(0);
    }
    
    public static void main(String[] args) {
        try {
            PocketServer server = new PocketServer();
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    public void savePlayers(){
        for(PlayerEntity player : playerEntityList)
            Serializer.write(player.getName(), "player", player);
    }
    
    public PlayerEntity loadPlayer(String name){
        return (PlayerEntity) Serializer.read(new File(name+".player"));
    }
    
    public boolean doesPlayerSaveExist(String name){
        File file = new File(name+".player");
        return file.exists();
    }
    
    public void addPlayer(InetAddress ip, int port) {
        if(!isPlayerConnected(ip,port)) {
            System.out.println("Adding player: " + ip + " " + port);
            
            PlayerEntity newPlayer = new PlayerEntity(ip,port);
            newPlayer.setEntityID(getNewEntityId());
            
            playerEntityList.add(newPlayer);
        }
    }
    
    public boolean isPlayerConnected(InetAddress ip, int port) {
        for (PlayerEntity p : playerEntityList) {
            if (p.getIP().equals(ip) && p.getPort() == port && p.isConnected()) {
                return true;
            }     
        }
        return false;
    }
    
    public PlayerEntity getCurrentPlayer(InetAddress ip, int port) {
        for (PlayerEntity p : playerEntityList) {
            if (p.getIP().equals(ip) && p.getPort() == port && p.isConnected()) {
                return p;
            }     
        }
        return null;
    }
    
    private int getNewEntityId() {
        int entityID = 1;
        boolean b = false;
        while(!b) {
            if(!playerEntityIDList.contains(entityID)) {
                b = true;
            }
            entityID = 1 + (int)(Math.random() * 50);
            
        }
        return entityID;
    }

    private void tick() {
        networkManager.sendAllQueue();
    }
    
    public long getStartTime() {
        return startTime;
    }

    public ServerConfiguration getServerConfig() {
        return serverConfig;
    }
    
    
}
