package tests;
import java.util.Scanner;

import dades.Associacio;
import dades.LlistaAssociacions;
import dades.Membre;

public class TestAssociacions {
    static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args){

        LlistaAssociacions llista = new LlistaAssociacions(20); //Exemple per afegir fins a 16 associacions...

        int menu;

        boolean continuar = true;

        Associacio associacio1, associacio2, associacio3, associacio4;

        associacio1 = new Associacio("CodeMasters", "codemasters@universidadficticia.edu", "GEI", 0);
        associacio2 = new Associacio("BioHacking Club", "biohacking@universidadficticia.edu", "BioGEI", 0);
        associacio3 = new Associacio("Green Innovators", "greeninnovators@universidadficticia.edu", "GEB", 0);
        associacio4 = new Associacio("Data Warriors", "datawarriors@universidadficticia.edu", "GESST", 0);

        llista.afegirAssociacio(associacio1);
        llista.afegirAssociacio(associacio2);
        llista.afegirAssociacio(associacio3);
        llista.afegirAssociacio(associacio4);

        while(continuar == true){
            System.out.println("\nSelecciona una opció (Test per la classe Associacions):");
            System.out.println("1.Consulta una associació pel nom");
            System.out.println("2.Mostra totes les associacions");
            System.out.println("3.Afegeix una associació");
            System.out.println("4.Afegeix un membre a una associació");
            System.out.println("5.Elimina una associació");
            System.out.println("6.Mostra el llistat de membres d'una associació");
            System.out.println("7.Surt del programa");

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
                    System.out.println("Inserta el nom de la nova associació:");
                    String nom = teclat.nextLine();
                    System.out.println("Inserta el correu de la nova associació:");
                    String correu = teclat.nextLine();
                    System.out.println("Inserta la titulació de la nova associació:");
                    String tit = teclat.nextLine();
                    System.out.println("Inserta el nombre de membres de la nova associació");
                    int num_membres = Integer.parseInt(teclat.nextLine());
                    Associacio nova = new Associacio(nom, correu, tit, num_membres);
                    llista.afegirAssociacio(nova);
                    break;
                case 4:
                    System.out.println("Escriu el nom de la associació a la qual afegir un membre:");
                    String n_associacio = teclat.nextLine();
                    Associacio aux = llista.ConsultaPerNom(n_associacio);
                    if(aux == null){
                        System.out.println("No s'ha trobat cap associació amb aquest nom");
                        break;
                    }
                    int nMembresSegur = aux.getN_membres();
                    {
                        System.out.println("Inserta la informació del nou membre:");
                        System.out.println("Nom:");
                        String nom_membre = teclat.nextLine();
                        System.out.println("Data de alta");
                        String data_alta = teclat.nextLine();
                        System.out.println("Correu electrònic:");
                        String correu_membre = teclat.nextLine();
                        Membre membre_afegir = new Membre(nom_membre, data_alta, correu_membre);
                        llista.afegeixMembre(n_associacio,membre_afegir);
                        int nmem = llista.ConsultaPerNom(n_associacio).getN_membres();
                        if(nMembresSegur == nmem){
                            System.out.println("\nNo s'ha pogut afegir correctament...");
                        } else {
                            System.out.println("\nMembre afegit amb éxit!");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Nom de la associació a eliminar:");
                    String nom_nom = teclat.nextLine();
                    llista.eliminaAssociacio(nom_nom);
                    break;
                case 6:
                    System.out.println("Inserta el nom de la associació per veure els membres:");
                    String nom_case6 = teclat.nextLine();
                    Associacio aux_6 = llista.ConsultaPerNom(nom_case6);
                    int num_membres6 = aux_6.getN_membres();
                    Membre[] taulaMembres = aux_6.ConsultaMembre();
                    for(int i = 0; i < num_membres6; i++){
                        System.out.println("Membre "+(i+1)+": "+taulaMembres[i].toString());
                    }
                    break;
                case 7:
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
