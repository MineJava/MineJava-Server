package MineJava.MineJavaServer;

import MineJava.Utils.android.*;

public class MineJavaRunner extends Thread{

	public MineServer server;
	public MineJavaRunner(Bundle args) {
		server = new MineServer(args);
	}
	@Override public synchronized void start () {
		server.run();
	}

}
