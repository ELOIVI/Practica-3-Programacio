package tests;

import java.util.Scanner;
import dades.*;
import excepcions.*;

public class TestDemostracio {
    static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args) {
        LlistaAccions llista = new LlistaAccions(20); // Ejemplo para almacenar hasta 20 acciones

        boolean continuar = true;
        int menu;

        // Crear ejemplos de prueba
        Associacio associacio1 = new Associacio("CodeURV", "codeurv@estudiants.urv", "GEI", 0);
        Associacio associacio2 = new Associacio("URVoltage Racing", "urvoltageracing@estudiants.urv", "BioGEI", 0);
        Membre responsable = new Membre("Eloi Viciana", "eloi.viciana@estudiants.urv", "08/10/2025");

        // Crear algunas demostraciones y añadirlas a la lista
        try {
            Demostracio demostracio1 = new Demostracio("Disseny de robots", new Associacio[]{associacio1}, responsable, "15/02/2024", true, 5, 100.50);
            Demostracio demostracio2 = new Demostracio("Carrera solar", new Associacio[]{associacio2}, responsable, "10/06/2024", true, 3, 50.75);

            llista.afegirAccio(demostracio1);
            llista.afegirAccio(demostracio2);
        } catch (DataInvalidaException | VegadesOferidaNegativaException | CostNegatiuException e) {
            System.out.println("Error al crear les demostracions: " + e.getMessage());
        }

        while (continuar) {
            System.out.println("\nSelecciona una opció (Test per la classe Demostracio):");
            System.out.println("1. Consulta una demostració pel codi");
            System.out.println("2. Mostra totes les demostracions");
            System.out.println("3. Crea una nova demostració");
            System.out.println("4. Assigna un responsable a una demostració");
            System.out.println("5. Incrementa el nombre de vegades ofertes");
            System.out.println("6. Surt del programa");

            menu = Integer.parseInt(teclat.nextLine());

            switch (menu) {
                case 1: // Consulta per codi
                    System.out.println("Escriu el codi de la demostració:");
                    String codi = teclat.nextLine();
                    Accio accioConsultada = llista.consultaPerCodi(codi);
                    if (accioConsultada == null) {
                        System.out.println("No s'ha trobat cap demostració amb aquest codi...");
                    } else {
                        System.out.println(accioConsultada);
                    }
                    break;

                case 2: // Mostra totes les demostracions
                    for (Accio accio : llista.getAccions()) {
                        if (accio != null && accio instanceof Demostracio) {
                            System.out.println(accio);
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

                    System.out.println("Inserta la data de disseny (dd/MM/yyyy):");
                    String dataDisseny = teclat.nextLine();
                    System.out.println("És vàlida la demostració (true/false):");
                    boolean esValid = Boolean.parseBoolean(teclat.nextLine());
                    System.out.println("Inserta el cost dels materials:");
                    double costMaterials = Double.parseDouble(teclat.nextLine());

                    try {
                        Demostracio novaDemostracio = new Demostracio(titol, associacions, nouResponsable, dataDisseny, esValid, costMaterials);
                        llista.afegirAccio(novaDemostracio);
                        System.out.println("Demostració creada amb èxit!");
                    } catch (DataInvalidaException | VegadesOferidaNegativaException | CostNegatiuException e) {
                        System.out.println("Error al crear la demostració: " + e.getMessage());
                    }
                    break;

                case 4: // Assigna un responsable
                    System.out.println("Escriu el codi de la demostració:");
                    String codiAssignacio = teclat.nextLine();
                    Accio accioPerAssignar = llista.consultaPerCodi(codiAssignacio);
                    if (accioPerAssignar == null || !(accioPerAssignar instanceof Demostracio)) {
                        System.out.println("No s'ha trobat cap demostració amb aquest codi...");
                        break;
                    }
                    System.out.println("Escriu el nom del nou responsable:");
                    String nomNouResponsable = teclat.nextLine();
                    Membre nouResp = new Membre(nomNouResponsable, "correu_nou@test.com", "01/01/2025");
                    accioPerAssignar.setResponsable(nouResp);
                    System.out.println("Responsable assignat amb èxit!");
                    break;

                case 5: // Incrementa el nombre de vegades ofertes
                    System.out.println("Escriu el codi de la demostració:");
                    String codiIncrement = teclat.nextLine();
                    Accio accioPerIncrementar = llista.consultaPerCodi(codiIncrement);
                    if (accioPerIncrementar == null || !(accioPerIncrementar instanceof Demostracio)) {
                        System.out.println("No s'ha trobat cap demostració amb aquest codi...");
                        break;
                    }
                    ((Demostracio) accioPerIncrementar).incrementarVegadesOferida();
                    System.out.println("Nombre de vegades ofertes incrementat!");
                    break;

                case 6: // Surt del programa
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
