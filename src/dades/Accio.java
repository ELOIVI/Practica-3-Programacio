package dades;

import excepcions.NomAssociacioCurtoException;
import excepcions.ResponsableNulException;
import excepcions.TitolInvalidException;
import excepcions.AssociacionsInvalidesException;


/**
 * Classe abstracta que representa una acció
 * Aquesta classe conté informació comuna a totes les accions.
 * 
 * @author Eloi Viciana Gómez
 */


public abstract class Accio {
    private static int numAccio = 100; //Volem que totes les instàncies utilitzin el mateix valor

    private String codi;
    private String titol;
    private Associacio[] associacions;
    private Membre responsable;
    private int numAssociacions;

    /**
     * Constructor per inicialitzar una acció amb un títol, un conjunt d'associacions i un responsable.
     * 
     * @param titol Títol de l'acció
     * @param associacions Llista d'associacions vinculades a l'acció
     * @param responsable Membre responsable de l'acció
     */    
    public Accio (String titol, Associacio [] associacions, Membre responsable) {
        if (titol == null || titol.trim().isEmpty()) {
            throw new TitolInvalidException("El títol de l'acció no pot ser nul o buit");
        }
        if (associacions == null || associacions.length == 0) {
            throw new AssociacionsInvalidesException("L'acció ha de tenir almenys una associació");
        }

        if (responsable == null) {
            throw new ResponsableNulException("L'acció ha de tenir un responsable");
        }

        this.codi = generarCodi(associacions[0].getNom());
        this.titol = titol;
        this.responsable = responsable;
        this.associacions = new Associacio [associacions.length];
        System.arraycopy(associacions, 0, this.associacions, 0, associacions.length);
        this.numAssociacions = associacions.length;      
    }

    /**
     * Genera un codi únic per a l'acció a partir del nom de la primera associació.
     * El codi té la forma dels tres primers caràcters del nom de l'associació + un número que es va incrementant
     * amb cada nova instància d'acció.
     * 
     * @param nomAssociacio Nom de la primera associació per generar el codi
     * @return El codi generat per a l'acció
     */
    private String generarCodi(String nomAssociacio) {
        if (nomAssociacio == null || nomAssociacio.length() < 3) {
            throw new NomAssociacioCurtoException("El nom de l'associació ha de tenir almenys 3 caràcters");
        }
        return nomAssociacio.substring (0,3).toUpperCase() + numAccio++;
    }

    /**
     * Retorna el codi de l'acció.
     * 
     * @return Codi de l'acció
     */
    public String getCodi() {
        return codi;
    }


    /**
     * Retorna el títol de l'acció.
     * 
     * @return Títol de l'acció
     */
    public String getTitol() {
        return titol;
    }

    /**
     * Retorna el responsable de l'acció.
     * 
     * @return Membre responsable de l'acció
     */
    public Membre getResponsable() {
        return responsable;
    }

     /**
     * Retorna una còpia de la llista d'associacions vinculades a l'acció.
     * 
     * @return Array d'associacions associades a l'acció
     */
    public Associacio[] getAssociacions() { 
        Associacio[] result;
        if (associacions == null) {
            result = new Associacio[0];
        } else {
            result = new Associacio[numAssociacions];
            System.arraycopy(associacions, 0, result, 0, numAssociacions);
        }
        return result;
    }
    
    /**
     * Setter del responsable de l'acció.
     * 
     * @param responsable Membre responsable de l'acció
     */
    public void setResponsable(Membre responsable) {
        if (responsable == null) {
            throw new IllegalArgumentException("El responsable no pot ser nul");
        }
        this.responsable = responsable;
    }
    
    /**
     * Mètode que retorna els seus atributs més rellevants
     * d'Acciox.
     * 
     * @return Cadena de text amb la representació de l'Acció
     */
    @Override
    public String toString() {
        return "Accio {" +
               "Codi='" + codi + '\'' +
               ", Títol='" + titol + '\'' +
               ", Responsable=" + (responsable != null ? responsable.getNom() : "No assignat") +
               ", Num Associacions=" + numAssociacions +
               '}';
    }
    }

