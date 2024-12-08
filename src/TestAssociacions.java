import java.util.Scanner;

public class TestAssociacions {
    static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args){

        LlistaAssociacions llista = new LlistaAssociacions(4);

        int menu;

        boolean continuar = true;

        Associacio associacio1, associacio2, associacio3, associacio4;

        associacio1 = new Associacio("CodeMasters", "codemasters@universidadficticia.edu", "GEI", 35);
        associacio2 = new Associacio("BioHacking Club", "biohacking@universidadficticia.edu", "BioGEI", 22);
        associacio3 = new Associacio("Green Innovators", "greeninnovators@universidadficticia.edu", "GEB", 28);
        associacio4 = new Associacio("Data Warriors", "datawarriors@universidadficticia.edu", "GESST", 40);

        llista.afegirAssociacions(associacio1);
        llista.afegirAssociacions(associacio2);
        llista.afegirAssociacions(associacio3);
        llista.afegirAssociacions(associacio4);

        while(continuar == true){
            System.out.println("\nSelecciona una opció (Test per la classe Associacions):");
            System.out.println("1.Consulta una associació pel nom");
            System.out.println("2.Mostra totes les associacions");
            System.out.println("3.Surt del programa");

            System.out.println("\n");
            menu = Integer.parseInt(teclat.nextLine());
            System.out.println("\n");

            switch(menu){
                case 1:
                    System.out.println("Escriu el nom de l'associació: ");
                    String nom_associacio = teclat.nextLine();
                    if(llista.ConsultaPerNom(nom_associacio) == null){
                        System.out.println("No s'ha trobat cap associació amb aquest nom...");
                    } else {
                        System.out.println(llista.ConsultaPerNom(nom_associacio).ToStringAssociacio());
                    }
                    break;
                case 2:
                    int num = llista.getN_associacions();
                    Associacio[] taula = new Associacio[num];
                    taula = llista.mostraAssociacions();
                    for(int i = 0; i < num; i++){
                        System.out.println("Associació "+(i+1)+ taula[i].ToStringAssociacio());
                    }
                    break;
                case 3:
                    System.out.println("Surtint del programa...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opció no vàlida...");
                    break;
            }
        }
    }
}
