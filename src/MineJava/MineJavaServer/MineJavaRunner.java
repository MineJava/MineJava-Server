package MineJava.MineJavaServer.MineServer;



public class MineJavaRunner extends Thread{

	public MineServer server;
	public MineJavaServer(Bundle args) {
		sever = new MineServer(args);
	}
	@Override public synchronized void start () {
		server.start();
	}

}
