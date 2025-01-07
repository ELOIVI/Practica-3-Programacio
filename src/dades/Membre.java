package dades;


public class Membre {
    private String alias;
    private String emailInstitucional;
    private String dataAlta;
    private String dataBaixa;
    private Associacio[] associacions;
    private int numAssociacions;


    public Membre(String alias, String emailInstitucional, String dataAlta) {
        this.alias = alias;
        this.emailInstitucional = emailInstitucional;
        this.dataAlta = dataAlta;
        this.dataBaixa = null;
        this.associacions = new Associacio[10];
        this.numAssociacions = associacions.length;      

    }

    public String getAlias() {
        return alias;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    public String getTipus() {
        return "membre"; 
    }

    public String getDataAlta() {
        return dataAlta;
    }

    public String getDataBaixa() {
        return dataBaixa;
    }

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

    public void setDataBaixa(String dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public boolean isActiu() {
        return dataBaixa == null;
    }
    
    public Membre copia() {
        return new Membre(alias, emailInstitucional, dataAlta);
    }

    public String getNom() {
        return alias;
    }

    @Override
    public String toString() {
        return alias + ";" + emailInstitucional + ";" + dataAlta + ";" + (dataBaixa != null ? dataBaixa : "null");
    }
}