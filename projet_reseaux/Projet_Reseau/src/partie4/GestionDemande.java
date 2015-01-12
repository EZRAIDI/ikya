package partie4;

import java.net.*;
import java.io.*;

public class GestionDemande implements Runnable 
{
    Socket socket;
    Chef chef;
    Pair pair;

    public GestionDemande(Socket socket, Chef chef, Pair pair) throws Exception 
    {
        this.socket = socket;
        this.chef = chef;
        this.pair = pair;
    }

    @Override
    public void run() 
    {
        try 
        {
            processRequest();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }

    }   

    private void processRequest() throws Exception 
    {
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        String nomFichier = br.readLine();
		Pair resp = chef.trouvePair(nomFichier);
		if (resp != null){
			System.out.println("Le Pair avec l'adresse :" + resp.getHote() +":"+resp.getPort()+" a le fichier.");
			System.out.println("Demande du fichier en cours ...");
			pair.sendRequest(nomFichier, resp.getHote(), resp.getPort());
		}else{
			System.out.println("Aucun des pairs connectés n'a le Morceau recherché ! ");
		}
        
        os.close();
        br.close();
        socket.close();

    }

}
