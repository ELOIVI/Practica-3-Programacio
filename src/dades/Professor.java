package dades;

public class Professor extends Membre {

    private String Departament; 
    private int numDespatx; 

     public Professor (String alies, String correuElectronic, String dataAlta, int dataBaixa, String Departament, int numDespatx) {
        super(alies, dataAlta, correuElectronic);
        this.Departament = Departament;
        this.numDespatx = numDespatx;
     }

    public String getDepartament() {
        return Departament;
    }

    public int getNumDespatx() {
        return numDespatx;
    }

    public void setDepartament(String departament) {
        Departament = departament;
    }

    public void setNumDespatx(int numDespatx) {
        this.numDespatx = numDespatx;
    }

    
    public String toString() {
        return "Professor [Departament=" + Departament + ", numDespatx=" + numDespatx + "]";
    }

    

}
