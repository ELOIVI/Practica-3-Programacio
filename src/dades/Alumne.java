package dades;

public class Alumne extends Membre {
    private String ensenyament;
    private int anysETSE;

    public Alumne(String alias, String emailInstitucional, String dataAlta, String ensenyament, int anysETSE) {
        super(alias, emailInstitucional, dataAlta); // Crida explícita al constructor de la classe Membre
        this.ensenyament = ensenyament;
        this.anysETSE = anysETSE;
    }

    public String getEnsenyament() {
        return ensenyament;
    }

    public int getAnysETSE() {
        return anysETSE;
    }

    public void setGraduat() {
        this.anysETSE = -1; // Indica que l'alumne està graduat.
    }

    @Override
    public String toString() {
        return super.toString() + ";" + ensenyament + ";" + anysETSE;
    }
}

