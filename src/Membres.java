public class Membres{

    private String alias;
    private String data_alta;
    private String correu;

    public Membres(String nom, String data_alta, String correu){
        this.alias = nom;
        this.data_alta = data_alta;
        this.correu = correu;
    }

    public Membres copia() {
		Membres aux = new Membres(alias, data_alta, correu);
		return aux;
	}

    public String ToStringMembres(){
        return ("[Nom: " +alias+ "] [Correu: " +correu+ "] [Data Alta: " +data_alta+ "]");
    }
}
