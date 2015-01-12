import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;


public class RequeteOuReponseHTTP extends Thread{

	
	//public static final RequeteOuReponseHTTP FIN = new RequeteOuReponseHTTP(socket);
	
	public RequeteOuReponseHTTP(Socket socket) throws IOException {
		// TODO Auto-generated constructor stub
		 this.socket = socket;
		 port = socket.getPort();
		  //Flux client
		  clientIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		  clientOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Exemple d'une requete HTTP
		String val ="GET //portquizm.png HTTP//1.1 Host: portquiz.net:666 User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:29.0) Gecko/20100101 Firefox/29.0 Accept: text/html,application/xhtml+xml,application/xml;q=0.9,";
		TestReadQuery(val);
	}
	public static void TestReadQuery(String line){
		
			if (line.trim().toUpperCase().startsWith("GET")) {
	         String url = line.substring(3);
	         String rest = "";
	         System.out.println("url 1 "+url);
			// Obtenir http
			int posit = url.toUpperCase().lastIndexOf("HTTP");
			System.out.println("posit 2 "+ posit);
			        if (posit >= 0) {
						           rest = url.substring(posit).trim();
						           System.out.println("rest 3 "+ rest);
						           url = url.substring(0, posit).trim();
						           System.out.println("url 4 +" + url);
			        } else {
			       	 		   url = url.trim();
			        }

			
			}
			String line2= "Host: portquiz.net:666 User-Agent:";
			if (line2.trim().toUpperCase().startsWith("HOST:")){
		        String  hostt = line2.substring(5).trim();
			
			System.out.println("hostt "+hostt);}
			
	}
	private DataInputStream clientIn, serverIn;
	private DataOutputStream clientOut, serverOut;
	
	  
	private String get, host;
	//liste des requetes !! :(
	private ArrayList Query;
	private Socket socket, socketServer;
	int port;
	
	private void readQuery() throws Exception {
		    BufferedReader bin = new BufferedReader(new InputStreamReader(clientIn));
		    String line;
		    //Normalement il faut travaillé par les méthodes qu'on a deja crée dans la classe utilitaires (on fixera ça apres :) )
		    while ((line = bin.readLine()) != null) {
		      if (line.length() == 0) break;
		        /* recuperer la requete du client ----> reconstruction de la ligne GET --> Envoi au serveur 
		         * Transformation de la requête HTTP :p  */
		     if (line.trim().toUpperCase().startsWith("GET")) {
								         String url = line.substring(3);
								         String rest = "";
				         // Obtenir http
				         int posit = url.toUpperCase().lastIndexOf("HTTP");
							         if (posit >= 0) {
										           rest = url.substring(posit).trim();
										           url = url.substring(0, posit).trim();
							         } else {
							        	 		   url = url.trim();
							         }
				         get = url;
			
					      // New GET REQUETE   ----> GET Chemin HTTP 1.0
					     URL getURL = new URL(url);
			     Query.add("GET " + getURL.getFile() + " " + rest);
					     System.out.println(socket.getInetAddress().getHostName() + " GET " + url);
		     } else {
		         Query.add(line);
		     }
		     // HOST contient l'adresse du serveur
		     if (line.trim().toUpperCase().startsWith("HOST:"))
		         host = line.substring(5).trim();
		    }
	 }
	  public void run() {
		    try {
		      get = host = "";
		      Query = new ArrayList();
		      /*---------------------------------------------->
		       *---------------------------------------------->
		       *---------------------------------------------->
		      */  
		      // recuperation et transformation de le requete http !
		      readQuery();
		      // Connexion to the server
		        URL url = new URL(get);
		        int port;
		        if(url.getPort() > 0){
		        	port = url.getPort();
		        }else port= 80;
		        socketServer = new Socket(url.getHost(),port);
		     
		      // Création flux serveur
		      serverIn = new DataInputStream(new BufferedInputStream(socketServer.getInputStream()));
		      serverOut = new DataOutputStream(new BufferedOutputStream(socketServer.getOutputStream()));
		      // Envoi de la requete http au serveur
		      new clientTOserver(serverOut, Query).start();
		      
		      byte [] reponse = new byte[4096];
		      int bytesRead;
		      // Lecture des informations du serveur et envoi au client
		      while ((bytesRead = serverIn.read(reponse)) != -1) {
		        clientOut.write(reponse, 0, bytesRead);
		        clientOut.flush();
		      }
		    }catch (Exception e) {
		      System.out.println("Error");
		    }
		    }
		  }
	  
