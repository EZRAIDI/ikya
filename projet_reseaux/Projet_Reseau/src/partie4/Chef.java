package partie4;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Iterator;


public class Chef {
	
	private int port = 0;
	private ArrayList<Pair> contacts = new ArrayList<Pair>();
	ServerSocket srvSocket;
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public ArrayList<Pair> getContacts() {
		return contacts;
	}
	public void setContacts(ArrayList<Pair> contacts) {
		this.contacts = contacts;
	}
	public ServerSocket getServer_socket() {
		return srvSocket;
	}
	public void setServer_socket(ServerSocket server_socket) {
		this.srvSocket = server_socket;
	}
	/**
	 * @param port
	 */
	public Chef(int port) {
		super();
		this.port = port;		
		try {
		    
		    srvSocket = new ServerSocket(getPort());
		    System.out.println("Le serveur ecoute sur le port : " + srvSocket.getLocalPort());

		    while(true) {
			Socket socket = srvSocket.accept();
			System.out.println("Connection acceptee pour " + socket.getInetAddress() + ":" + socket.getPort());
			Pair newP = new Pair(socket.getPort());
			this.contacts.add(newP);
			
			
			try {
			    GestionDemande demande = new GestionDemande(socket,this,newP);
			    Thread thread = new Thread(demande);
			    thread.start();
			}
			catch(Exception e) {
			    System.out.println(e);
			}
		    }
		}
		
		catch (IOException e) {
		    System.out.println(e);
		}
	}
	
	public Pair trouvePair (String nomMorceau){
		Iterator<Pair> it = this.contacts.iterator();
		Pair p=null;
		while(it.hasNext())
		{
		    p = it.next();
		    if(p.trouveMorceau(nomMorceau)){
		    	break;
		    }
		
		}
		return p;
		
	}
	
	public static void main(String[] args) {
		System.out.println("OK ca commence ...");
		new Chef(4444);
	}

}
