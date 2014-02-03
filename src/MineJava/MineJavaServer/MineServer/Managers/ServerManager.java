package MineJava.MineJavaServer.MineServer.Managers;

public class ServerManager implements Manager<MineServer>{
	public static ServerManager getInstance(){
		return new ServerManager();
	}
	private int type;
	private ServerManager(){
		type=0;
	}
	
}