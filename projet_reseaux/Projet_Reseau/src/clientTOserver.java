import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


public class clientTOserver extends Thread{ 

	public clientTOserver() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	  //**********************************************************
	  //-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-___-_-
	  //-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-___-_-
	  //-_-_-_-_-_-_-_-_-ClientToServer---->Thread_-_-_-_-_-_-_-_-
	  //-_-__-PI : _-_--_-_Execution de la thread lors de l'instanciation
	  //-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-___-_-
	  
	  

		  private DataOutputStream serverOut;
		  private ArrayList requete;

		  public clientTOserver(DataOutputStream serverOut, ArrayList requete) throws Exception {
		    this.serverOut = serverOut;
		    this.requete = requete;
		  }

		  public void run() {
		    try {
		      PrintWriter out = new PrintWriter(new OutputStreamWriter(serverOut));
		      for (int i = 0; i < requete.size(); ++i) {
		        out.println(requete.get(i));
		      }
		      out.println(); // Envoyer une ligne vierge -> fin de la requête
		      out.flush();// flush
		    }
		    catch (Exception e) {
		      System.out.println("Erreur : " + e);
		    }
		  }
	  

}
