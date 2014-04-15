package MineJava.MineJavaServer.networking;

import java.net.DatagramSocket;
import java.net.DatagramPacket;

import MineJava.MineJavaServer.MineServer.MineServer;

public class Networker extends Thread{
	public DatagramSocket socket;
	public MineServer server;
	public Networker(MineServer server){
		this.server=server;
	}
	public synchronized void start(){
		socket=new DatagramSocket(server.ip, server.port);
	}
}
