//Main server file
//Need main class MineJavaServer

import java.util.logging.Logger;//let me know if this is right also, sorry if it's wrong -ZacHack

class MineJavaServer implements Runnable{
	//Main server thread
	
	public static void run(){
		//Main method
		//This function is called when the thread is started
		ip="0.0.0.0"
		port="19132"
		this.startServer(ip, port);
                logger.info(Starting server on ".ip.":".port.");// I do not know how to do that in java yet -ZacHack
	}
	
	
}

class Server {
	
	public static void main(String [] args){
		//Run on startup
	}

}
