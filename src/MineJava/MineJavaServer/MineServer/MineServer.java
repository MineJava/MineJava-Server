package MineJava.MineJavaServer.MineServer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import MineJava.Utils.android.os.Bundle;
import MineJava.MineJavaServer.networking.Networker;

public class MineServer implements Runnable{
	public final static String NAME="MineServer";
	public static MineServer instance=null;

	//////////////
	//properties//
	//////////////
	public InetAddress ip;
	public int port;
	public String name;
	public Networker networker;
	/////////
	//debug//
	/////////
	protected long lastTick,startMilli;
	protected Logger logger;
	////////
	//info//
	////////
	public boolean isStarted=false, isStopped=false;

	public void run(){
		//Main thread startup
		serverInit();
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
		instance=this;
	}
	public boolean isOnline(){
		return isStarted && ! isStopped;
	}
	protected void tickEvent(){
		// TODO every tick listener
	}
	protected void serverInit(){
		networker=new Networker(this);
		networker.start();
	}
}
