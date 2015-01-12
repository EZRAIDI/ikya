import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ProxyServer extends Thread {

	int port;
	public ProxyServer(int port) {
		// TODO Auto-generated constructor stub
		this.port = port;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	 //**********************************************************
	  //-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
	  //-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
	  //-_-_-_-_-_-_-_-_-ProxyServer---->Thread_-_-_-_-_-_-_-_-_-_-_-
	  //-_-__-PI : _-_--_-_Execution du thread lors de l'instanciation
	  //-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
	   //**********************************************************
	public void run() {
		ServerSocket server;
		try {
			server = new ServerSocket(port);
			//Boucle --> un client qui va se connecter 
			while (!interrupted()) {
		        Socket client = server.accept();
		        RequeteOuReponseHTTP ProxySRVCLT= new RequeteOuReponseHTTP(client);
		        ProxySRVCLT.start();
		     }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
