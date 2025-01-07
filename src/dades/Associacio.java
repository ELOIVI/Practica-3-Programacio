package dades;

public class Associacio{

    private String nom;             //Nom de l'associació
    private String correu;          //Correu electrònic de contacte
    private String titulacio;       //Sigles de la titulació (GEB,GEI,GESST...)
    private int n_membres;          //Nombre de membres de l'associació
    
    
     private Membre[] membres;      //Llista de membres de l'associació
    /*private Alumne president;       //President de l'associació    
    private Alumne secretari;       //Secretari de l'associació
    private Alumne tresorer;        //Tresorer de l'associació*/

    /**
     * Setter d'una associació
     * @param nom
     * @param correu
     * @param titulacio
     * @param n
     */
    public Associacio(String nom, String correu, String titulacio, int n){
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
    public String getNom(){
        return nom;
    }

    /**
     * Getter correu electrònic de contacte
     * @return
     */
    public String getCorreu(){
        return correu;
    }

    /**
     * Getter titulació de l'associació
     * @return
     */
    public String getTitulacio(){
        return titulacio;
    }

    /**
     * Getter nombre de membres de l'associació
     * @return
     */
    public int getN_membres(){
        return n_membres;
    }

    public int getMembrelength(){
        return membres.length;
    }

    public String ToStringAssociacio(){
        return " [Nom: "+nom+", Correu electrònic: "+correu+", Titulació: "+titulacio+", Nombre de membres: "+n_membres+"]";
    }

    public Associacio copia() {
		Associacio aux= new Associacio(nom, correu, titulacio, n_membres);
		return aux;
	}


    public void afegeixMembre(Membre membre_af){
        if(n_membres < membres.length){
            membres[n_membres] = membre_af.copia();
            n_membres++;
        }
    }

    public Membre[] ConsultaMembre(){
        Membre[] auxMembre = new Membre[n_membres];
        for(int i = 0; i < n_membres; i++){
            auxMembre[i] = membres[i].copia();
        }
        return auxMembre;
    }
}

    
