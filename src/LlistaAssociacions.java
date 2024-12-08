

public class LlistaAssociacions {

    private int n_associacions;
    private Associacio[] llista;

    /**
     * Setter llista d'associacions
     * @param n
     */
    public LlistaAssociacions(int n){
        n_associacions = 0;
        llista = new Associacio[n];
    }

    /**
     * Getter número d'associacions
     */
    public int getN_associacions(){
        return n_associacions;
    }

    /**
     * Métode per afegir una associació a la primera posició buida
     * @param m
     */
    public void afegirAssociacions(Associacio m){
        if(n_associacions < llista.length){
            llista[n_associacions] = m.copia();
            n_associacions++;
        }

    }

    public Associacio ConsultaPerNom(String nom){

        for(int i = 0; i < n_associacions; i++){
            if(nom.compareTo(llista[i].getNom()) == 0){
                return(llista[i]);
            }
        }
        return null;
    }

    public Associacio[] mostraAssociacions(){
        Associacio[] taula = new Associacio[n_associacions];
        
        for(int i = 0; i < n_associacions; i++){
            taula[i] = llista[i].copia();
        }
        return taula;
    }
}
