package dades;

import java.util.Arrays;

public class Xerrada extends Accio {

    private String data; 
    private Membre [] infoMembres; // taula de tipus Membres on s'emmagatzema la informacio dels membres que imparteixen la xerrada
    private int numAssistents;
    private int valoracio;  
    private Membre membre; // membre que imparteix la xerrada
    private final int limitMembres = 3; // limit de membres que poden impartir una xerrada
    private int numElem; // variable que controla el nombre de membres que imparteixen la xerrada


    public Xerrada(String data, int numAssistents, int valoracio, String titol, Membre responsableAccio, Associacio[] associacions) {
        super(titol, associacions, responsableAccio); // Aquí llamamos al constructor de Accio con los parámetros correctos
        this.data = data;
        this.numAssistents = numAssistents;
        this.valoracio = valoracio;
        numElem = 0;
    }
    

    public void setData(String data) {
        this.data = data;
    }

    public void setNumAssistents(int numAssistents) {
        this.numAssistents = numAssistents;
    }

    public void setValoracio(int valoracio) {
        this.valoracio = valoracio;
    }

    // Metode que rep per parametre una clase membre, i s'afegeix a la xerrada. Retorna un boolea si s'ha afegit o no.
    public boolean afegirMembre (Membre membre){ 
        if (numElem < limitMembres){
            infoMembres[numElem] = membre;  
            numElem++;
            return true;
        }
        else{
            return false;
        }    
    }
 
    public String toString() {
        return "Xerrada [data=" + data + ", infoMembres=" + Arrays.toString(infoMembres) + ", numAssistents="
                + numAssistents + ", valoracio=" + valoracio + ", membre=" + membre + ", limitMembres=" + limitMembres
                + ", numElem=" + numElem + "]";
    }

    public boolean mostrarXerradaAmbUnCertNombreAssistents(int numAssistents){
        if (this.numAssistents > numAssistents){
            return true;
        }
        else{
            return false;
        }
    }

    public String getData() {
        return data;
    }

    public int getNumAssistents() {
        return numAssistents;
    }

    public int getValoracio() {
        return valoracio;
    }
    
}
