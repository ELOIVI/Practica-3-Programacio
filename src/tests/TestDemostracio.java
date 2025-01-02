package tests;

import java.util.Scanner;
import dades.*;

public class TestDemostracio {
    static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args) {
        LlistaDemostracions llista = new LlistaDemostracions(20); // Exemples per emmagatzemar fins a 20 demostracions

        boolean continuar = true;
        int menu;

        // Crear exemples de prova per a demostracions
        Associacio associacio1 = new Associacio("CodeURV", "codeurv@estudiants.urv", "GEI", 0);
        Membre responsable = new Membre("Eloi Viciana", "eloi.viciana@estudiants.urv", "08/10/2025");

        // Crear algunes demostracions i afegir-les a la llista
        Demostracio demostracio1 = new Demostracio("Hackathon", new Associacio[]{associacio1}, responsable);
        Demostracio demostracio2 = new Demostracio("Carrera elèctrica", new Associacio[]{associacio1}, responsable);

        llista.afegirDemostracio(demostracio1);
        llista.afegirDemostracio(demostracio2);

        while (continuar) {
            System.out.println("\nSelecciona una opció (Test per la classe Demostracio):");
            System.out.println("1. Consulta una demostració pel codi");
            System.out.println("2. Mostra totes les demostracions");
            System.out.println("3. Crea una nova demostració");
            System.out.println("4. Assigna un responsable a una demostració");
            System.out.println("5. Surt del programa");

            menu = Integer.parseInt(teclat.nextLine());

            switch (menu) {
                case 1: // Consulta per codi
                    System.out.println("Escriu el codi de la demostració:");
                    String codi = teclat.nextLine();
                    Demostracio demostracioConsultada = llista.consultaPerCodi(codi);
                    if (demostracioConsultada == null) {
                        System.out.println("No s'ha trobat cap demostració amb aquest codi...");
                    } else {
                        System.out.println(demostracioConsultada);
                    }
                    break;

                case 2: // Mostra totes les demostracions
                    for (Demostracio demostracio : llista.getDemostracions()) {
                        if (demostracio != null) {
                            System.out.println(demostracio);
                        }
                    }
                    break;

                case 3: // Crea una nova demostració
                    System.out.println("Inserta el títol de la nova demostració:");
                    String titol = teclat.nextLine();
                    System.out.println("Inserta el nombre d'associacions vinculades:");
                    int numAssociacions = Integer.parseInt(teclat.nextLine());
                    Associacio[] associacions = new Associacio[numAssociacions];
                    for (int i = 0; i < numAssociacions; i++) {
                        System.out.println("Inserta el nom de l'associació " + (i + 1) + ":");
                        associacions[i] = new Associacio(teclat.nextLine(), "email" + i + "@test.com", "Titulació" + i, 0);
                    }
                    System.out.println("Inserta el nom del responsable:");
                    String nomResponsable = teclat.nextLine();
                    Membre nouResponsable = new Membre(nomResponsable, "correu@test.com", "10/12/2024");

                    try {
                        Demostracio novaDemostracio = new Demostracio(titol, associacions, nouResponsable);
                        llista.afegirDemostracio(novaDemostracio);
                        System.out.println("Demostració creada amb èxit!");
                    } catch (Exception e) {
                        System.out.println("Error al crear la demostració: " + e.getMessage());
                    }
                    break;

                case 4: // Assigna un responsable
                    System.out.println("Escriu el codi de la demostració:");
                    String codiAssignacio = teclat.nextLine();
                    Demostracio demostracioPerAssignar = llista.consultaPerCodi(codiAssignacio);
                    if (demostracioPerAssignar == null) {
                        System.out.println("No s'ha trobat cap demostració amb aquest codi...");
                        break;
                    }
                    System.out.println("Escriu el nom del nou responsable:");
                    String nomNouResponsable = teclat.nextLine();
                    Membre nouResp = new Membre(nomNouResponsable, "correu_nou@test.com", "01/01/2025");
                    demostracioPerAssignar.setResponsable(nouResp);
                    System.out.println("Responsable assignat amb èxit!");
                    break;

                case 5: // Surt del programa
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
