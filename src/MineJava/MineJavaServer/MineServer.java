package MineJava.MineJavaServer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import MineJava.Utils.android.os.Bundle;

class MineServer implements Runnable{
	public final static String NAME="MineServer";
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
	Logger logger;
	////////
	//info//
	////////
	public boolean isStarted=false, isStopped=false;
	public void run(){
		//Main thread startup
		//TODO: Get sockets
		logger=Logger.getLogger(NAME);
		startMilli=System.currentTimeMillis();
		isStarted=true;
		lastTick=startMilli;
		while(isOnline()){
			tickEvent();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (System.currentTimeMillis() - lastTick > 100)
				onOverloaded();
			lastTick=System.currentTimeMillis();
		}
	}
	protected void onOverloaded() {
		// TODO Auto-generated method stub
		
	}
	public MineServer(Bundle args) throws UnknownHostException{
		ip=InetAddress.getByName(args.getString(MineJavaRunner.ARGS_IP, "0.0.0.0"));
		port=args.getInt(MineJavaRunner.ARGS_PORT, 19132);
		name=args.getString(MineJavaRunner.ARGS_NAME, "MineJava server");
		
	}
	public boolean isOnline(){
		return isStarted && ! isStopped;
	}
	protected void tickEvent(){
		
	}
}
