package partie4;

import java.util.ArrayList;

public class Cascade {
	public String urlChef;
	public String nomFichier;
	public int tailleFichier;
	public int nbrMorceau;
	public ArrayList<Morceau> listeMorceau;
	public String getUrlChef() {
		return urlChef;
	}
	public void setUrlChef(String urlChef) {
		this.urlChef = urlChef;
	}
	public String getNomFichier() {
		return nomFichier;
	}
	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}
	public int getTailleFichier() {
		return tailleFichier;
	}
	public void setTailleFichier(int tailleFichier) {
		this.tailleFichier = tailleFichier;
	}
	public int getNbrMorceau() {
		return nbrMorceau;
	}
	public void setNbrMorceau(int nbrMorceau) {
		this.nbrMorceau = nbrMorceau;
	}
	public ArrayList<Morceau> getListeMorceau() {
		return listeMorceau;
	}
	public void setListeMorceau(ArrayList<Morceau> listeMorceau) {
		this.listeMorceau = listeMorceau;
	}
	/**
	 * @param urlChef
	 * @param nomFichier
	 * @param tailleFichier
	 * @param nbrMorceau
	 * @param listeMorceau
	 */
	public Cascade(String urlChef, String nomFichier, int tailleFichier,
			int nbrMorceau, ArrayList<Morceau> listeMorceau) {
		super();
		this.urlChef = urlChef;
		this.nomFichier = nomFichier;
		this.tailleFichier = tailleFichier;
		this.nbrMorceau = nbrMorceau;
		this.listeMorceau = listeMorceau;
	}
	
	

}
