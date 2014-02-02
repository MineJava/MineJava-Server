package MineJava.MineJavaServer.MineServer;

import java.net.UnknownHostException;

import MineJava.Utils.android.os.*;

public class MineJavaRunner extends Thread{

	//Bundle key constants
	public final static String ARGS_IP="MineJava.MineJavaServer.networking.ip";
	public final static String ARGS_PORT="MineJava.MineJavaServer.networking.port";
	public final static String ARGS_NAME="MineJava.MineJavaServer.server.name";
	public MineServer server;
	public MineJavaRunner(Bundle args) throws UnknownHostException {
		server = new MineServer(args);
	}
	@Override public synchronized void start () {
		server.run();
	}

}
