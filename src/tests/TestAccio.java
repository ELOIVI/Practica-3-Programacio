package tests;
import java.util.Scanner;

import dades.*;

public class TestAccio {
    static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args) {
        LlistaAccions llista = new LlistaAccions(20); // Ejemplo para almacenar hasta 20 acciones

        boolean continuar = true;
        int menu;

        // Crear ejemplos de prueba
        Associacio associacio1 = new Associacio("CodeURV", "codeurv@estudiants.urv", "GEI", 0);
        Associacio associacio2 = new Associacio("URVoltage Racing", "urvoltageracing@estudiants.urv", "BioGEI", 0);
        Membre responsable = new Membre("Eloi Viciana", "eloi.viciana@estudiants.urv", "08/10/2025");

        // Crear algunas acciones y añadirlas a la lista
        AccioConcreta accio1 = new AccioConcreta("Hackathon", new Associacio[]{associacio1}, responsable);
        AccioConcreta accio2 = new AccioConcreta("Carrera elèctrica", new Associacio[]{associacio2}, responsable);

        llista.afegirAccio(accio1);
        llista.afegirAccio(accio2);

        while (continuar) {
            System.out.println("\nSelecciona una opció (Test per la classe Accio):");
            System.out.println("1. Consulta una acció pel codi");
            System.out.println("2. Mostra totes les accions");
            System.out.println("3. Crea una nova acció");
            System.out.println("4. Assigna un responsable a una acció");
            System.out.println("5. Surt del programa");

            menu = Integer.parseInt(teclat.nextLine());

            switch (menu) {
                case 1: // Consulta per codi
                    
                    System.out.println("\nTingui en compte que ha d'estar format per tres lletres i tres números.");
                    System.out.println("Escriu el codi de l'acció:");
                    String codi = teclat.nextLine();

                    //Verifiquem amb el regex
                    if (!codi.matches("(?i)^[A-Z]{3}[0-9]{3}$")) {
                        if (codi.length() != 6) {
                            System.out.println("El codi ha de tenir exactament 6 caràcters.");
                        }
                        //introduïm procediment per detectar que és exactament el que falta, si lletres, o números, i el num que falten.
                        else {

                        }
                        
                        
                        Accio accioConsultada = llista.consultaPerCodi(codi);
                    if (accioConsultada == null) {
                        System.out.println("No s'ha trobat cap acció amb aquest codi...");
                    } else {
                        System.out.println(accioConsultada);
                        }
                    }
                    break;

                case 2: // Mostra totes les accions
                    for (Accio accio : llista.getAccions()) {
                        if (accio != null) {
                            System.out.println(accio);
                        }
                    }
                    break;

                case 3: // Crea una nova acció
                    System.out.println("Inserta el títol de la nova acció:");
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
                        Accio novaAccio = new AccioConcreta(titol, associacions, nouResponsable);
                        llista.afegirAccio(novaAccio);
                        System.out.println("Acció creada amb èxit!");
                    } catch (Exception e) {
                        System.out.println("Error al crear l'acció: " + e.getMessage());
                    }
                    break;

                case 4: // Assigna un responsable
                    System.out.println("Escriu el codi de l'acció:");
                    String codiAssignacio = teclat.nextLine();
                    Accio accioPerAssignar = llista.consultaPerCodi(codiAssignacio);
                    if (accioPerAssignar == null) {
                        System.out.println("No s'ha trobat cap acció amb aquest codi...");
                        break;
                    }
                    System.out.println("Escriu el nom del nou responsable:");
                    String nomNouResponsable = teclat.nextLine();
                    Membre nouResp = new Membre(nomNouResponsable, "correu_nou@test.com", "01/01/2025");
                    accioPerAssignar.setResponsable(nouResp);
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

