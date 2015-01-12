package partie4;

public class Morceau {
	public String nom;
	public int taille;
	public String sha1;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public String getSha1() {
		return sha1;
	}
	public void setSha1(String sha1) {
		this.sha1 = sha1;
	}
	/**
	 * @param nom
	 * @param taille
	 * @param sha1
	 */
	public Morceau(String nom, int taille, String sha1) {
		super();
		this.nom = nom;
		this.taille = taille;
		this.sha1 = sha1;
	}
	
	

}
