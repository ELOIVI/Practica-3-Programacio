package dades;

import excepcions.CostNegatiuException;
import excepcions.DataInvalidaException;
import excepcions.VegadesOferidaNegativaException;

/**
 * Representa una demostració com a tipus específic d'acció. 
 * Inclou informació sobre la data de disseny, validesa, 
 * vegades que s'ha ofert i el cost dels materials.
 * @author Eloi Viciana
 */

public class Demostracio extends Accio {
    private String dataDisseny;
    private boolean esValid;
    private int vegadesOferida;
    private double costMaterials;

    /**
     * Constructor de la classe Demostracio.
     * 
     * @param titol         Títol de la demostració.
     * @param associacions  Associacions vinculades.
     * @param responsable   Membre responsable.
     * @param dataDisseny   Data de disseny en format "dd/MM/yyyy".
     * @param esValid       Indicador de si la demostració és vàlida.
     * @param vegadesOferida Nombre de vegades que s'ha ofert (≥ 0).
     * @param costMaterials Cost dels materials (≥ 0).
     * @throws DataInvalidaException Si la data de disseny no és vàlida.
     * @throws VegadesOferidaNegativaException Si el nombre de vegades oferides és negatiu.
     * @throws CostNegatiuException Si el cost dels materials és negatiu.     
     */
    public Demostracio(String titol, Associacio[] associacions, Membre responsable, String dataDisseny, boolean esValid, int vegadesOferida, double costMaterials) throws DataInvalidaException, VegadesOferidaNegativaException, CostNegatiuException {
        super(titol, associacions, responsable);
        if (!esDataValida(dataDisseny)) {
            throw new DataInvalidaException("La data de disseny no és vàlida");
        }
        if (vegadesOferida < 0) {
            throw new VegadesOferidaNegativaException("El nombre de vegades oferida no pot ser negatiu");
        }
        if (costMaterials < 0) {
            throw new CostNegatiuException("El cost dels materials no pot ser negatiu");
        }

        this.dataDisseny = dataDisseny;
        this.esValid = esValid;
        this.vegadesOferida = vegadesOferida;
        this.costMaterials = costMaterials;
    }

    /**
     * Constructor alternatiu que inicialitza vegadesOferida a 0 per defecte.
     * 
     * @param titol         Títol de la demostració.
     * @param associacions  Associacions vinculades.
     * @param responsable   Membre responsable.
     * @param dataDisseny   Data de disseny en format "dd/MM/yyyy".
     * @param esValid       Indicador de si la demostració és vàlida.
     * @param costMaterials Cost dels materials (≥ 0).
     * @throws DataInvalidaException Si la data de disseny és invàlida.
     * @throws CostNegatiuException Si el cost dels materials és negatiu.     
     */
    public Demostracio(String titol, Associacio[] associacions, Membre responsable, String dataDisseny, boolean esValid, double costMaterials) throws DataInvalidaException, CostNegatiuException, VegadesOferidaNegativaException {
        this(titol, associacions, responsable, dataDisseny, esValid, 0, costMaterials);
    }

     /**s
     * Retorna la data de disseny de la demostració.
     * 
     * @return Data de disseny en format "dd/MM/yyyy".
     */
    public String getDataDisseny() {
        return dataDisseny;
    }

     /**
     * Indica si la demostració és vàlida.
     * 
     * @return Cert si és vàlida, fals en cas contrari.
     */
    public boolean isEsValid() {
        return esValid;
    }

      /**
     * Retorna el nombre de vegades que s'ha ofert aquesta demostració.
     * 
     * @return Nombre de vegades oferida.
     */
    public int getVegadesOferida() {
        return vegadesOferida;
    }

    /**
     * Retorna el cost dels materials per a aquesta demostració.
     * 
     * @return Cost dels materials.
     */
    public double getCostMaterials() {
        return costMaterials;
    }

    /**
     * Incrementa en 1 el nombre de vegades que s'ha ofert aquesta demostració.
     */
    public void incrementarVegadesOferida() {
        vegadesOferida++;
    }

     /**
     * Estableix si la demostració és vàlida.
     * 
     * @param esValid Cert si és vàlida, fals en cas contrari.
     */
    public void setEsValid(boolean esValid) {
        this.esValid = esValid;
    }

    /**
     * Estableix el cost dels materials per a aquesta demostració.
     * 
     * @param costMaterials Nou cost dels materials (≥ 0).
     * @throws CostNegatiuException Si el cost és negatiu.
     */
    public void setCostMaterials(double costMaterials) throws CostNegatiuException {
        if (costMaterials < 0) {
            throw new CostNegatiuException("El cost dels materials no pot ser negatiu");
        }
        this.costMaterials = costMaterials;
    }  

    /**
     * Retorna una representació en format cadena de la demostració.
     * 
     * @return Representació de la demostració.
     */
    @Override
    public String toString() {
        return "Demostracio {" +
        "Codi='" + getCodi() + '\'' +
        ", Titol='" + getTitol() + '\'' +
        ", Responsable=" + (getResponsable() != null ? getResponsable().getNom() : "No assignat") +
        ", Data Disseny='" + dataDisseny + '\'' +
        ", Es Valida=" + esValid +
        ", Vegades Oferida=" + vegadesOferida +
        ", Cost Materials=" + costMaterials +
        '}';
    }

    /**
     * Comprova si una data està en el format correcte (DD/MM/YYYY) i si és real.
     * Valida els anys, mesos, dies i també té en compte els anys de traspàs.
     * 
     * @param data Data a validar.
     * @return Cert si la data és real, fals en cas contrari.
     */
    private boolean esDataValida(String data) {
        // Comprovar el format bàsic
        if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }

        // Dividir la data en components
        String[] parts = data.split("/");
        int dia = Integer.parseInt(parts[0]);
        int mes = Integer.parseInt(parts[1]);
        int any = Integer.parseInt(parts[2]);

        // Validar l'any (ha de ser positiu o 0)
        if (any < 0) {
            return false;
        }

        // Validar el mes (ha d'estar entre 1 i 12)
        if (mes < 1 || mes > 12) {
            return false;
        }

        // Definir el nombre de dies per cada mes
        int[] diesPerMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Comprovar anys de traspàs
        if (esAnyDeTraspas(any)) {
            diesPerMes[1] = 29; // Febrer té 29 dies si és any de traspàs
        }

        // Validar el dia (ha d'estar dins el rang de dies del mes)
        return dia >= 1 && dia <= diesPerMes[mes - 1];
    }

    /**
     * Comprova si un any és de traspàs.
     * 
     * @param any L'any a comprovar.
     * @return Cert si és any de traspàs, fals en cas contrari.
     */
    private boolean esAnyDeTraspas(int any) {
        // Un any és de traspàs si:
        // - És divisible per 4
        // - No és divisible per 100 (excepte si també és divisible per 400)
        return (any % 4 == 0 && any % 100 != 0) || (any % 400 == 0);
    }



    
}