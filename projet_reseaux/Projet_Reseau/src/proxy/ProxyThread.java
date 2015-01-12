package proxy;

import java.net.*;
import java.io.*;
import java.util.*;

public class ProxyThread extends Thread {
	private Socket socket = null;
	private static final int BUFFER_SIZE = 16384;

	public ProxyThread(Socket socket) {
		super("ProxyThread");
		this.socket = socket;
	}

	public void run() {
		traitClient();
	}
	
	public void traitClient() {
		
		try {
			DataOutputStream out = new DataOutputStream(
					socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			String input;
			int cpt = 0;
			String myURL = "";

			while ((input = in.readLine()) != null) {
				try {
					StringTokenizer st = new StringTokenizer(input);
					st.nextToken();
				} catch (Exception e) {
					break;
				}

				if (cpt == 0) {
					String[] tokens = input.split(" ");
					myURL = tokens[1];
					System.out.println("Request for : " + myURL);
				}

				cpt++;
			}

			BufferedReader br = null;
			try {
				URL url = new URL(myURL);
				URLConnection ucon = url.openConnection();
				ucon.setDoInput(true);
				ucon.setDoOutput(false);
				InputStream is = null;
				//HttpURLConnection huc = (HttpURLConnection) ucon;
				if (ucon.getContentLength() > 0) {
					try {
						is = ucon.getInputStream();
						br = new BufferedReader(new InputStreamReader(is));
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}

				byte by[] = new byte[BUFFER_SIZE];
				int index = is.read(by, 0, BUFFER_SIZE);
				while (index != -1) {
					out.write(by, 0, index);
					index = is.read(by, 0, BUFFER_SIZE);
				}
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
				out.writeBytes("");
			}
			if (br != null) {
				br.close();
			}
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
			if (socket != null) {
				socket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}