package dades;

import java.io.*;

import excepcions.ErrorAccioException;
import excepcions.ErrorFitxerException;

/**
 * Classe que representa una llista d'accions.
 * Aquesta classe permet emmagatzemar i gestionar una sèrie d'accions.
 * 
 * @author Eloi Viciana Gómez
 */
public class LlistaAccions {

    private int n_accions;  // Número d'accions en la llista
    private Accio[] llista; // Llista d'accions

    /**
     * Constructor de la llista d'accions.
     * @param n El número màxim d'accions que pot contenir la llista
     */
    public LlistaAccions(int n){
        n_accions = 0;
        llista = new Accio[n];
    }

    /**
    * Retorna una còpia de les accions actuals en la llista.
    * @return Un array amb les accions actuals.
    */
    public Accio[] getAccions() {
        Accio[] accionsCopia = new Accio[n_accions];
        System.arraycopy(llista, 0, accionsCopia, 0, n_accions);
        return accionsCopia;
}

    /**
     * Getter per obtenir el número d'accions.
     * @return El número d'accions actuals en la llista
     */
    public int getN_accions(){
        return n_accions;
    }

    /**
     * Mètode per afegir una acció a la primera posició buida de la llista.
     * @param acció L'acció a afegir
     */
    public void afegirAccio(Accio accio){
        if(n_accions < llista.length){
            llista[n_accions] = accio;  // No necessitem fer una còpia aquí, ja que Accio és un objecte mutable
            n_accions++;
        }
    }

    /**
     * Consulta una acció per codi.
     * @param codi El codi de l'acció a buscar
     * @return L'acció amb el codi especificat, o null si no es troba
     */
    public Accio consultaPerCodi(String codi){
        for(int i = 0; i < n_accions; i++){
            if(codi.equals(llista[i].getCodi())){
                return llista[i];
            }
        }
        return null;
    }

    /**
     * Mostra totes les accions.
     * @return Una còpia de la llista d'accions
     */
    public Accio[] mostraAccions(){
        Accio[] taula = new Accio[n_accions];
        System.arraycopy(llista, 0, taula, 0, n_accions);
        return taula;
    }

    /**
     * Consulta l'índex d'una acció per codi.
     * @param codi El codi de l'acció a buscar
     * @return L'índex de l'acció, o -1 si no es troba
     */
    public int consultaIndexAccioPerCodi(String codi){
        for(int i = 0; i < n_accions; i++){
            if(codi.equals(llista[i].getCodi())){
                return i;
            }
        }
        return -1;
    }

    /**
     * Elimina una acció per codi.
     * @param codi El codi de l'acció a eliminar
     */
    public void eliminaAccio(String codi){
        for(int i = 0; i < n_accions; i++){
            if(codi.equals(llista[i].getCodi())){
                for(int j = i; j < n_accions - 1; j++){
                    llista[j] = llista[j + 1];  // Desplaça els elements per omplir el buit
                }
                llista[n_accions - 1] = null;  // Elimina la referència de l'últim element
                n_accions--;
                break;
            }
        }
    }

    /**
     * Modifica el responsable d'una acció.
     * @param codi El codi de l'acció
     * @param responsable El nou responsable de l'acció
     */
    public void modificaResponsable(String codi, Membre responsable){
        for(int i = 0; i < n_accions; i++){
            if(codi.equals(llista[i].getCodi())){
                llista[i].setResponsable(responsable);
                break;
            }
        }
    }

    /**
     * Guarda la llista d'accions en un fitxer de text.
     * @param filename El nom del fitxer on es guardaran les accions
     * @throws ErrorFitxerException Si hi ha un error al escriure en el fitxer
     */
    public void guardarAccionsEnFitxer(String nomFitxer) throws ErrorFitxerException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFitxer))) {
            for (int i = 0; i < n_accions; i++) {
                writer.write(llista[i].toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ErrorFitxerException("Error en guardar les accions al fitxer", e);
        }
    }
   

    /**
     * Carrega les accions des d'un fitxer de text.
     * @param filename El nom del fitxer des d'on es carregaran les accions
     * @throws ErrorFitxerException Si hi ha un error al llegir el fitxer
     * @throws ErrorAccioException Si el fitxer conté informació no vàlida
     */
    public void carregarAccionsDesFitxer(String nomFitxer) throws ErrorFitxerException, ErrorAccioException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFitxer))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                //format fitxer?
                if (linia.trim().isEmpty()) {
                    throw new ErrorAccioException("Línia buida trobada al fitxer");
                }
                System.out.println(linia);
            }
        } catch (IOException e) {
            throw new ErrorFitxerException("Error en carregar les accions des del fitxer", e);
        }
    }
}