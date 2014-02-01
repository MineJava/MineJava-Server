package MineJava.MineJavaServer.MineServer;
import java.util.*;
import java.net.*;
import MineJava.MineJavaServer.Utils.*;

class MineServer implements Runnable{
	//////////////
	//properties//
	//////////////
	InetAddress ip;
	int port;
	String name;
	/////////
	//debug//
	/////////
	long lastTick;
	////////
	//info//
	////////
	public boolean isStarted=false, isStopped=false;
	public void run(){
		//Main thread startup
		//TODO: Get sockets, Get logger init
		startMilli=System.currentTimeMillis();
		isStarted=true;
		lastTick=startMilli;
		while(isOnline()){
			tickEvent();
			Thread.sleep(50); // 1/20 second - one tick
			if (System.currentTimeMillis() - lastTick > 100)
				//what to do when server is too overloaded
			lastTick=System.currentTimeMillis();
		}
	}
	public MineServer(Bundle args){
		ip=InetAddress.getAddress(args.getString("networking.ip", "0.0.0.0"));
		port=args.getInt("networking.port", 19132);
		name=args.getString("server.name", "MineJava server");
		
	}
	public boolean isOnline(){
		return isStarted && ! isStopped;
	}
	protected void tickEvent(){
		
	}
}
