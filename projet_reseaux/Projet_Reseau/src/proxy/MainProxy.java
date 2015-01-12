package proxy;

import java.net.*;
import java.io.*;

public class MainProxy {
	public static void main(String[] args) {

		ServerSocket socket = null;

		boolean listening = true;

		int port = 0;

		try {
			port = Integer.parseInt(args[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			port = 10000;
		}

		try {
			socket = new ServerSocket(port);
			System.out.println("Le serveur ecoute sous le port : " + port);
		} catch (IOException e) {
			System.err.println("Port: " + args[0] + "non disponible !");
			System.exit(-1);
		}

		while (listening) {
			try {
				new ProxyThread(socket.accept()).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}