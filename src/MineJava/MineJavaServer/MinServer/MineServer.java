package MineJava.MineJavaServer.MinServer;
import java.util.*;
import java.net.*;

import MineJava.Utils.*;
import MineJava.Utils.android.*;

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
	long lastTick,startMilli;
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
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 1/20 second - one tick
			if (System.currentTimeMillis() - lastTick > 100)
				//what to do when server is too overloaded
			lastTick=System.currentTimeMillis();
		}
	}
	public MineServer(Bundle args){
		ip=InetAddress.getByName(args.getString("networking.ip", "0.0.0.0"));
		port=args.getInt("networking.port", 19132);
		name=args.getString("server.name", "MineJava server");
		
	}
	public boolean isOnline(){
		return isStarted && ! isStopped;
	}
	protected void tickEvent(){
		
	}
}
