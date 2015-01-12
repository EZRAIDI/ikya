package partie4;

import java.io.*;
import java.net.*;
import java.util.*;


public class Pair {
	
	private int port;
	private String hote = "127.0.0.1";
    private ArrayList<Pair> contacts = new ArrayList<Pair>();
    private ArrayList<Morceau> ListeMorceau = new ArrayList<Morceau>();
    private String Folder;
    
    public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHote() {
		return hote;
	}

	public void setHote(String hote) {
		this.hote = hote;
	}

	public ArrayList<Pair> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Pair> contacts) {
		this.contacts = contacts;
	}

	public ArrayList<Morceau> getListeMorceau() {
		return ListeMorceau;
	}

	public void setListeMorceau(ArrayList<Morceau> listeMorceau) {
		ListeMorceau = listeMorceau;
	}
	
	public String getFolder() {
		return Folder;
	}

	public void setFolder(String folder) {
		Folder = folder;
	}

	Pair(final int port)
    {
        this.setPort(port);
        new Thread(new Runnable() {
        	   public void run() {
        	       startClientServer( port );
        	   }
        	}).start();
    }

	public boolean trouveMorceau (String nomMorceau){
		Iterator<Morceau> it = this.ListeMorceau.iterator();
		Morceau M = null;
		boolean resultat = false;
		while(it.hasNext())
		{
			M = it.next();
		    if (M.getNom().equalsIgnoreCase(nomMorceau)){
		    	resultat = true;
		     }else {
		    	resultat = false;
			}
		}
		return resultat;
		
	}
	
    public void sendRequest(String fileName, String host, int port) throws UnknownHostException, IOException
    {
        Socket socket = new Socket(host, port);
        PrintWriter out = new PrintWriter( socket.getOutputStream(), true );
        out.println(fileName);

        out.close();
        socket.close();

    }

    private void startClientServer( int numPort )
    {
        try 
        {
            ServerSocket server = new ServerSocket( 0 );
            System.out.println("listening on port " + server.getLocalPort());

            while( true )
            {
                Socket connection = server.accept();
                GestionRequete requete = new GestionRequete( connection );
                Thread thread = new Thread(requete);
                thread.start();
                
            }
            
        } 
        catch (Exception e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public void lireFichierCascade(String nomFichier) throws IOException{
    	
    	FileReader fileReader = new FileReader(nomFichier);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lignes = new ArrayList<String>();
        String ligne = null;
        while ((ligne = bufferedReader.readLine()) != null) {
            lignes.add(ligne);
        }
        bufferedReader.close();
        String[] reslignes = lignes.toArray(new String[lignes.size()]);
        
        //for (String resligne : reslignes) {
        //    System.out.println(resligne);
        //}
        String nomFichierOrig= reslignes[0].substring(reslignes[0].lastIndexOf(":")+1);
        String urlChef= reslignes[1].substring(reslignes[1].lastIndexOf(":")+1);
        int tailleFichier= Integer.parseInt(reslignes[2].substring(reslignes[2].lastIndexOf(":")+1,reslignes[2].lastIndexOf(" ")));
        int nbrMorceau= Integer.parseInt(reslignes[3].substring(reslignes[3].lastIndexOf(":")+1));
        //System.out.println(urlChef +" "+ nomFichierOrig +" "+ tailleFichier +" "+ nbrMorceau);
        ArrayList<Morceau> Morceaux = new ArrayList<Morceau>();
        for (int i = 4; i < reslignes.length-3; i=i+3) {
			String nom = reslignes[i].substring(reslignes[i].lastIndexOf(":")+1);
			int taille = Integer.parseInt(reslignes[i+2].substring(reslignes[i+2].lastIndexOf(":")+1,reslignes[i+2].lastIndexOf(" ")));
			String sha1= reslignes[i+1].substring(reslignes[i+1].lastIndexOf(":")+1);
			Morceau M = new Morceau(nom, taille, sha1);
			Morceaux.add(M);
		}
        
        //System.out.println(Morceaux);
        
        Cascade c = new Cascade(urlChef, nomFichierOrig, tailleFichier, nbrMorceau, Morceaux);
        
        
        for (int i = 0; i < c.nbrMorceau; i++) {
			sendRequest(c.listeMorceau.get(i).getNom(), c.getUrlChef(), 4444);
		}
        
	}
    
    public static void main(String[] args) {
    	
    		Pair Pair1 = new Pair(0);
    		Pair1.setFolder("./files/RepPair1");
    		Morceau P1M1 = new Morceau("test.jpg.001", 16384, "686e2edad5af5f6e40beffea8dec4c4418c8b616");
    		Morceau P1M2 = new Morceau("test.jpg.002", 16384, "3a5cc752312c2e101883035616354a16c49ceb00");
    		Morceau P1M3 = new Morceau("test.jpg.003", 16384, "8dafceca4862a0ab09ac18370e592315161a03f0");
    		Morceau P1M4 = new Morceau("test.jpg.004", 16384, "0594c9f84d3021143ec2e573eeb67e09a990778c");
    		Morceau P1M5 = new Morceau("test.jpg.005", 11554, "1c0e27ea5745321d4d1ad1e2bc5f9d8037fec7fc");
    		Pair1.ListeMorceau.add(P1M1);
    		Pair1.ListeMorceau.add(P1M2);
    		Pair1.ListeMorceau.add(P1M3);
    		Pair1.ListeMorceau.add(P1M4);
    		Pair1.ListeMorceau.add(P1M5);
    		
    		Pair Pair2 = new Pair(0);
    		Pair2.setFolder("./files/RepPair2");
    		Morceau P2M1 = new Morceau("test.jpg.003", 16384, "686e2edad5af5f6e40beffea8dec4c4418c8b616");
    		Morceau P2M2 = new Morceau("test.jpg.005", 11554, "3a5cc752312c2e101883035616354a16c49ceb00");
    		Pair2.ListeMorceau.add(P2M1);
    		Pair2.ListeMorceau.add(P2M2);
    		
    		Pair Pair3 = new Pair(0);
    		Pair3.setFolder("./files/RepPair3");
    		
    		System.out.println("Pair1 demmaré sur le port : " + Pair1.getPort());
    		System.out.println("Pair2 demmaré sur le port : " + Pair2.getPort());
    		System.out.println("Pair3 demmaré sur le port : " + Pair3.getPort());
    		try {
				Pair3.lireFichierCascade("./files/GMI2N8.Cascasde");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	

}
