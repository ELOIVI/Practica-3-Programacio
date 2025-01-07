package dades;

import java.io.Serializable;
import excepcions.AssociacioPlenaException;
import excepcions.ExcepcioMembreJaExisteix;

public class Associacio implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nom;             // Nom de l'associació
    private String correu;          // Correu electrònic de contacte
    private String titulacio;       // Sigles de la titulació (GEB, GEI, GESST...)
    private int n_membres;          // Nombre de membres de l'associació
    
    private Membre[] membres;       // Llista de membres de l'associació

    /**
     * Setter d'una associació
     * @param nom
     * @param correu
     * @param titulacio
     * @param n
     */
    public Associacio(String nom, String correu, String titulacio, int n) {
        this.n_membres = n;
        this.nom = nom;
        this.correu = correu;
        this.titulacio = titulacio;
        membres = new Membre[50];
    }

    /**
     * Getter nom de l'associació
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter correu electrònic de contacte
     * @return
     */
    public String getCorreu() {
        return correu;
    }

    /**
     * Getter titulació de l'associació
     * @return
     */
    public String getTitulacio() {
        return titulacio;
    }

    /**
     * Getter nombre de membres de l'associació
     * @return
     */
    public int getN_membres() {
        return n_membres;
    }

    public int getMembrelength() {
        return membres.length;
    }

    public String ToStringAssociacio() {
        return " [Nom: " + nom + ", Correu electrònic: " + correu + ", Titulació: " + titulacio + ", Nombre de membres: " + n_membres + "]";
    }

    public Associacio copia() {
        Associacio aux = new Associacio(nom, correu, titulacio, n_membres);
        return aux;
    }

    public void afegeixMembre(Membre membre_af) {
        if (n_membres < membres.length) {
            membres[n_membres] = membre_af.copia();
            n_membres++;
        }
    }

    public Membre[] ConsultaMembre() {
        Membre[] auxMembre = new Membre[n_membres];
        for (int i = 0; i < n_membres; i++) {
            auxMembre[i] = membres[i].copia();
        }
        return auxMembre;
    }

    public void afegirMembre(Membre membre) throws ExcepcioMembreJaExisteix, AssociacioPlenaException {
        // Comprova si el membre ja existeix
        for (int i = 0; i < n_membres; i++) {
            if (membres[i].getAlias().equals(membre.getAlias())) {
                throw new ExcepcioMembreJaExisteix("El membre ja forma part de l'associació.");
            }
        }

        // Comprova si hi ha espai per afegir un nou membre
        if (n_membres >= membres.length) {
            throw new AssociacioPlenaException("No es poden afegir més membres a l'associació."); 
        }

        // Afegeix el membre
        membres[n_membres] = membre;
        n_membres++;
    }

    public Membre[] getMembres() {
        Membre[] membresActuals = new Membre[n_membres];
        System.arraycopy(membres, 0, membresActuals, 0, n_membres);
        return membresActuals;
    }
}