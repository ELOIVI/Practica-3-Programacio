package dades;

public class Membre {
    private String alias;
    private String emailInstitucional;
    private String dataAlta;
    private String dataBaixa;

    public Membre(String alias, String emailInstitucional, String dataAlta) {
        this.alias = alias;
        this.emailInstitucional = emailInstitucional;
        this.dataAlta = dataAlta;
        this.dataBaixa = null;
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
