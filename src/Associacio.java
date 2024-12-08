/*package (Nombre de la carpeta con los demás archivos)*/

public class Associacio{

    private String nom;             //Nom de l'associació
    private String correu;          //Correu electrònic de contacte
    private String titulacio;       //Sigles de la titulació (GEB,GEI,GESST...)
    private int n_membres;          //Nombre de membres de l'associació
    /*private Membres[] membres;      //Llista de membres de l'associació
    private Alumne president;       //President de l'associació    
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
        n_membres = n;
        this.nom = nom;
        this.correu = correu;
        this.titulacio = titulacio;
        //membres = new Membres[n];
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

    public String ToStringAssociacio(){
        return " [Nom: "+nom+", Correu electrònic: "+correu+", Titulació: "+titulacio+", Nombre de membres: "+n_membres+"]";
    }

    public Associacio copia() {
		Associacio aux= new Associacio(nom, correu, titulacio, n_membres);
		return aux;
	}
}