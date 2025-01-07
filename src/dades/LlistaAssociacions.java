package dades;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
    public void afegirAssociacio(Associacio m){
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

    public int consultaIndexAssociacioPerNom(String nom){
        for(int i = 0; i < n_associacions; i++){
            if(nom.compareTo(llista[i].getNom()) == 0){
                return(i);
            }
        }
        return -1;
    }

    public void eliminaAssociacio(String nom){
        for(int i = 0; i<n_associacions; i++){
            if(llista[i].getNom().equals(nom)){
                for(int j = i; j<n_associacions; j++){
                    llista[j] = llista[j+1];
                }
                n_associacions--;
            }
        }
    }

    public void afegeixMembre(String nom, Membre membre){
        for(int i = 0; i < n_associacions; i++){
            if(nom.compareTo(llista[i].getNom()) == 0){
                llista[i].afegeixMembre(membre);
            }
        }
    }

    public void guardarAssociacionsEnFitxer(String nomFitxer) {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomFitxer))) {
        out.writeObject(llista);
        System.out.println("Associacions guardades correctament al fitxer: " + nomFitxer);
    } catch (IOException e) {
        System.out.println("Error en guardar les associacions: " + e.getMessage());
    }
}


}
