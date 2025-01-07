package aplicacioconsola;

import java.util.*;
import java.io.*;

import dades.*;
import excepcions.*;


public class MenuConsola {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LlistaAssociacions llistaAssociacions = new LlistaAssociacions(50);
        LlistaMembres llistaMembres = new LlistaMembres();
        LlistaAccions llistaAccions = new LlistaAccions(100);

        // Carregar les dades dels fitxers
        carregarDades(llistaAssociacions, llistaMembres, llistaAccions);

        boolean sortir = false;
        while (!sortir) {
            mostrarMenu();
            int opcio = scanner.nextInt();
            scanner.nextLine(); // Netejar el buffer

            switch (opcio) {
                case 1:
                    mostrarAssociacions(llistaAssociacions);
                    break;
                case 2:
                    mostrarMembresAssociacio(llistaAssociacions, scanner);
                    break;
                case 3:
                    mostrarMembresActius(llistaMembres, scanner);
                    break;
                case 4:
                    mostrarAccions(llistaAccions, scanner);
                    break;
                case 5:
                    accionsAssociacio(llistaAssociacions, llistaAccions, scanner);
                    break;
                case 6:
                    xerradesFranjaDates(llistaAccions, scanner);
                    break;
                case 7:
                    afegirAssociacio(llistaAssociacions, scanner);
                    break;
                case 8:
                    altaMembreAssociacio(llistaAssociacions, llistaMembres, scanner);
                    break;
                case 9:
                    afegirXerrada(llistaAccions, llistaAssociacions, llistaMembres, scanner);
                    break;
                case 10:
                    afegirDemostracio(llistaAccions, llistaAssociacions, llistaMembres, scanner);
                    break;
                case 11:
                    demostracionsNoActives(llistaAccions);
                    break;
                case 12:
                    membreMesActiu(llistaMembres);
                    break;
                case 13:
                    xerradesAmbAssistents(llistaAccions, scanner);
                    break;
                case 14:
                    valorarXerrada(llistaAccions, scanner);
                    break;
                case 15:
                    millorValorada(llistaAccions);
                    break;
                case 16:
                    xerradesPersona(llistaAccions, scanner);
                    break;
                case 17:
                    baixaDemostracions(llistaAccions, scanner);
                    break;
                case 18:
                    sortir = true;
                    guardarDades(llistaAssociacions, llistaMembres, llistaAccions);
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("--- MENÚ ---");
        System.out.println("1. Mostrar les dades de la llista d'associacions.");
        System.out.println("2. Mostrar les dades de la llista de membres d'una associació.");
        System.out.println("3. Mostrar les dades dels membres actius.");
        System.out.println("4. Mostrar les dades de les accions.");
        System.out.println("5. Obtenir i mostrar les accions d'una associació.");
        System.out.println("6. Obtenir i mostrar xerrades en una franja de dates.");
        System.out.println("7. Afegir una nova associació.");
        System.out.println("8. Alta d'un membre a una associació.");
        System.out.println("9. Afegir una nova xerrada.");
        System.out.println("10. Afegir una nova demostració.");
        System.out.println("11. Consultar demostracions no actives i calcular el cost total.");
        System.out.println("12. Calcular la persona més activa.");
        System.out.println("13. Consultar xerrades amb més d'un cert nombre d'assistents.");
        System.out.println("14. Valorar una xerrada.");
        System.out.println("15. Consultar la xerrada millor valorada.");
        System.out.println("16. Mostrar les xerrades d'una persona.");
        System.out.println("17. Donar de baixa demostracions no actives abans d'una certa data.");
        System.out.println("18. Sortir de l'aplicació.");
        System.out.print("Selecciona una opció: ");
    }

    private static void carregarDades(LlistaAssociacions llistaAssociacions, LlistaMembres llistaMembres, LlistaAccions llistaAccions) {
        // Carregar associacions
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("fitxers_associacions.txt"))) {
            Associacio[] associacions = (Associacio[]) in.readObject();
            for (Associacio associacio : associacions) {
                llistaAssociacions.afegirAssociacio(associacio);
            }
            System.out.println("Associacions carregades correctament.");
        } catch (FileNotFoundException e) {
            System.out.println("Fitxer de les associacions no trobat. Es començarà amb una llista buida.");
        } catch (Exception e) {
            System.out.println("Error en carregar les associacions: " + e.getMessage());
        }

        // Carregar membres
        try (BufferedReader reader = new BufferedReader(new FileReader("membres.txt"))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] dades = linia.split(";");
                if (dades.length == 5) { // Alumne
                    llistaMembres.afegirMembre(new Alumne(dades[0], dades[1], dades[2], dades[3], Integer.parseInt(dades[4])));
                } else if (dades.length == 6) { // Professor
                    llistaMembres.afegirMembre(new Professor(dades[0], dades[1], dades[2], 0, dades[3], Integer.parseInt(dades[4])));
                }
            }
            System.out.println("Membres carregats correctament.");
        } catch (FileNotFoundException e) {
            System.out.println("Fitxer de membres no trobat. Es començarà amb una llista buida.");
        } catch (Exception e) {
            System.out.println("Error en carregar els membres: " + e.getMessage());
        }

        // Carregar accions
        try (BufferedReader reader = new BufferedReader(new FileReader("accions.txt"))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] dades = linia.split(";");
                if (dades.length > 0 && dades[0].startsWith("D")) { // Demostració
                    llistaAccions.afegirAccio(new Demostracio(dades[0], null, null, dades[1], Boolean.parseBoolean(dades[2]), Double.parseDouble(dades[3])));
                } else if (dades.length > 0 && dades[0].startsWith("X")) { // Xerrada
                    llistaAccions.afegirAccio(new Xerrada(dades[1], Integer.parseInt(dades[2]), Integer.parseInt(dades[3]), dades[0], null, null));
                }
            }
            System.out.println("Accions carregades correctament.");
        } catch (FileNotFoundException e) {
            System.out.println("Fitxer d'accions no trobat. Es començarà amb una llista buida.");
        } catch (Exception e) {
            System.out.println("Error en carregar les accions: " + e.getMessage());
        }
    }

    private static void guardarDades(LlistaAssociacions llistaAssociacions, LlistaMembres llistaMembres, LlistaAccions llistaAccions) {
        // Guardar associacions
        try {
            llistaAssociacions.guardarAssociacionsEnFitxer("fitxer_associacions.txt");
        } catch (Exception e) {
            System.out.println("Error en guardar les associacions: " + e.getMessage());
        }

        // Guardar membres
        try {
            llistaMembres.guardarMembres("membres.txt");
        } catch (Exception e) {
            System.out.println("Error en guardar els membres: " + e.getMessage());
        }

        // Guardar accions
        try {
            llistaAccions.guardarAccionsEnFitxer("accions.txt");
        } catch (Exception e) {
            System.out.println("Error en guardar les accions: " + e.getMessage());
        }
    }

    private static void mostrarAssociacions(LlistaAssociacions llistaAssociacions) {
        Associacio[] associacions = llistaAssociacions.mostraAssociacions();
        if (associacions.length == 0) {
            System.out.println("No hi ha associacions registrades.");
        } else {
            System.out.println("Llista d'associacions:");
            for (Associacio associacio : associacions) {
                System.out.println(associacio.ToStringAssociacio());
            }
        }
    }

    private static void mostrarMembresAssociacio(LlistaAssociacions llistaAssociacions, Scanner scanner) {
        System.out.print("Introdueix el nom de l'associació: ");
        String nomAssociacio = scanner.nextLine();

        Associacio associacio = llistaAssociacions.ConsultaPerNom(nomAssociacio);
        if (associacio == null) {
            System.out.println("No s'ha trobat cap associació amb aquest nom.");
            return;
        }

        System.out.print("Filtrar per (professors/alumnes/ambdós): ");
        String filtre = scanner.nextLine().toLowerCase();

        Membre[] membres = associacio.ConsultaMembre();
        if (membres.length == 0) {
            System.out.println("Aquesta associació no té membres.");
        } else {
            System.out.println("Membres de l'associació " + nomAssociacio + ":");
            for (Membre membre : membres) {
                if (filtre.equals("ambdós") || membre.getTipus().equalsIgnoreCase(filtre)) {
                    System.out.println(membre.toString());
                }
            }
        }
    }

    private static void mostrarMembresActius(LlistaMembres llistaMembres, Scanner scanner) {
        System.out.print("Filtrar per (professors/alumnes/ambdós): ");
        String filtre = scanner.nextLine().toLowerCase();
    
        Membre[] membresActius = llistaMembres.membresActius();
        if (membresActius.length == 0) {
            System.out.println("No hi ha membres actius.");
        } else {
            System.out.println("Membres actius:");
            for (Membre membre : membresActius) {
                if (filtre.equals("ambdós") || membre.getTipus().equalsIgnoreCase(filtre)) {
                    System.out.println(membre.toString());
                }
            }
        }
    }
    
    private static void mostrarAccions(LlistaAccions llistaAccions, Scanner scanner) {
        System.out.print("Filtrar per tipus d'acció (demostracions/xerrades/totes): ");
        String filtre = scanner.nextLine().toLowerCase();

        Accio[] accions = llistaAccions.mostraAccions();
        if (accions.length == 0) {
            System.out.println("No hi ha accions registrades.");
        } else {
            System.out.println("Llista d'accions:");
            for (Accio accio : accions) {
                if (filtre.equals("totes") ||
                    (filtre.equals("demostracions") && accio instanceof Demostracio) ||
                    (filtre.equals("xerrades") && accio instanceof Xerrada)) {
                    System.out.println(accio.toString());
                }
            }
        }
    }   

    private static void accionsAssociacio(LlistaAssociacions llistaAssociacions, LlistaAccions llistaAccions, Scanner scanner) {
        System.out.print("Introdueix el nom de l'associació: ");
        String nomAssociacio = scanner.nextLine();
    
        Associacio associacio = llistaAssociacions.ConsultaPerNom(nomAssociacio);
        if (associacio == null) {
            System.out.println("No s'ha trobat cap associació amb aquest nom.");
            return;
        }
    
        Accio[] accions = llistaAccions.mostraAccions();
        boolean accioTrobada = false;
    
        System.out.println("Accions associades a l'associació \"" + nomAssociacio + "\":");
        for (Accio accio : accions) {
            for (Associacio assoc : accio.getAssociacions()) {
                if (assoc.getNom().equalsIgnoreCase(nomAssociacio)) {
                    System.out.println(accio.toString());
                    accioTrobada = true;
                    break;
                }
            }
        }
    
        if (!accioTrobada) {
            System.out.println("No hi ha accions associades a aquesta associació.");
        }
    }

    private static void xerradesFranjaDates(LlistaAccions llistaAccions, Scanner scanner) {
        System.out.print("Introdueix la data inicial (dd/MM/yyyy): ");
        String dataInicialStr = scanner.nextLine();
        System.out.print("Introdueix la data final (dd/MM/yyyy): ");
        String dataFinalStr = scanner.nextLine();
    
        try {
            java.text.SimpleDateFormat formatData = new java.text.SimpleDateFormat("dd/MM/yyyy");
            Date dataInicial = formatData.parse(dataInicialStr);
            Date dataFinal = formatData.parse(dataFinalStr);
    
            Accio[] accions = llistaAccions.mostraAccions();
            boolean xerradaTrobada = false;
    
            System.out.println("Xerrades entre " + dataInicialStr + " i " + dataFinalStr + ":");
            for (Accio accio : accions) {
                if (accio instanceof Xerrada) {
                    Xerrada xerrada = (Xerrada) accio;
                    Date dataXerrada = formatData.parse(xerrada.getData());
                    if (!dataXerrada.before(dataInicial) && !dataXerrada.after(dataFinal)) {
                        System.out.println(xerrada.toString());
                        xerradaTrobada = true;
                    }
                }
            }
    
            if (!xerradaTrobada) {
                System.out.println("No s'han trobat xerrades en aquesta franja de dates.");
            }
        } catch (java.text.ParseException e) {
            System.out.println("Error: El format de les dates és incorrecte.");
        }
    }
    
    private static void afegirAssociacio(LlistaAssociacions llistaAssociacions, Scanner scanner) {
        System.out.print("Introdueix el nom de l'associació: ");
        String nom = scanner.nextLine();
    
        System.out.print("Introdueix el correu electrònic de l'associació: ");
        String correu = scanner.nextLine();
    
        System.out.print("Introdueix la titulació (GEB, GEI, etc.): ");
        String titulacio = scanner.nextLine();
    
        System.out.print("Introdueix el nombre inicial de membres: ");
        int numMembres;
        try {
            numMembres = Integer.parseInt(scanner.nextLine());
            if (numMembres < 0) {
                System.out.println("El nombre de membres no pot ser negatiu.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Has d'introduir un número vàlid.");
            return;
        }
    
        // Crear una nova associació i afegir-la a la llista
        Associacio novaAssociacio = new Associacio(nom, correu, titulacio, numMembres);
        llistaAssociacions.afegirAssociacio(novaAssociacio);

        System.out.println("Associació afegida correctament:");
        System.out.println(novaAssociacio.ToStringAssociacio());
    }    

    private static void altaMembreAssociacio(LlistaAssociacions llistaAssociacions, LlistaMembres llistaMembres, Scanner scanner) {
        System.out.print("Introdueix el nom de l'associació: ");
        String nomAssociacio = scanner.nextLine();

        Associacio associacio = llistaAssociacions.ConsultaPerNom(nomAssociacio);
        if (associacio == null) {
            System.out.println("No s'ha trobat cap associació amb aquest nom.");
            return;
        }

        System.out.print("Introdueix l'àlies del membre: ");
        String alias = scanner.nextLine();

        Membre membre = null;
        for (Membre m : llistaMembres.membresActius()) {
            if (m.getAlias().equalsIgnoreCase(alias)) {
                membre = m;
                break;
            }
        }

        if (membre == null) {
            System.out.println("El membre no existeix. Crearem un nou membre.");
            System.out.print("És un alumne o un professor (alumne/professor)? ");
            String tipus = scanner.nextLine().toLowerCase();

            System.out.print("Introdueix l'email institucional: ");
            String email = scanner.nextLine();

            System.out.print("Introdueix la data d'alta (dd/MM/yyyy): ");
            String dataAlta = scanner.nextLine();

            if (tipus.equals("alumne")) {
                System.out.print("Introdueix l'ensenyament: ");
                String ensenyament = scanner.nextLine();

                System.out.print("Introdueix els anys a l'ETSE: ");
                int anysETSE;
                try {
                    anysETSE = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Error: Has d'introduir un número vàlid.");
                    return;
                }

                membre = new Alumne(alias, email, dataAlta, ensenyament, anysETSE);
            } else if (tipus.equals("professor")) {
                System.out.print("Introdueix el departament: ");
                String departament = scanner.nextLine();

                System.out.print("Introdueix el número del despatx: ");
                int numDespatx;
                try {
                    numDespatx = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Error: Has d'introduir un número vàlid.");
                    return;
                }

                membre = new Professor(alias, email, dataAlta, 0, departament, numDespatx);
            } else {
                System.out.println("Tipus de membre no vàlid.");
                return;
            }

            try {
                llistaMembres.afegirMembre(membre);
            } catch (ExcepcioMembreJaExisteix e) {
                System.out.println("El membre ja existeix: " + e.getMessage());
                return;
            }
        }

        try {
            associacio.afegeixMembre(membre);
            System.out.println("Membre afegit correctament a l'associació.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void afegirXerrada(LlistaAccions llistaAccions, LlistaAssociacions llistaAssociacions, LlistaMembres llistaMembres, Scanner scanner) {
        System.out.print("Introdueix la data de la xerrada (dd/MM/yyyy): ");
        String data = scanner.nextLine();
    
        System.out.print("Introdueix el nombre d'assistents: ");
        int numAssistents;
        try {
            numAssistents = Integer.parseInt(scanner.nextLine());
            if (numAssistents < 0) {
                System.out.println("El nombre d'assistents no pot ser negatiu.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Has d'introduir un número vàlid.");
            return;
        }
    
        System.out.print("Introdueix la valoració inicial (0-10): ");
        int valoracio;
        try {
            valoracio = Integer.parseInt(scanner.nextLine());
            if (valoracio < 0 || valoracio > 10) {
                System.out.println("La valoració ha d'estar entre 0 i 10.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Has d'introduir un número vàlid.");
            return;
        }
    
        System.out.print("Introdueix el títol de la xerrada: ");
        String titol = scanner.nextLine();
    
        System.out.print("Introdueix l'àlies del responsable: ");
        String aliasResponsable = scanner.nextLine();
        Membre responsable = null;
        for (Membre membre : llistaMembres.membresActius()) {
            if (membre.getAlias().equalsIgnoreCase(aliasResponsable)) {
                responsable = membre;
                break;
            }
        }
        if (responsable == null) {
            System.out.println("No s'ha trobat cap membre actiu amb aquest àlies.");
            return;
        }
    
        System.out.print("Quantes associacions participen en aquesta xerrada? ");
        int numAssociacions;
        try {
            numAssociacions = Integer.parseInt(scanner.nextLine());
            if (numAssociacions <= 0) {
                System.out.println("El nombre d'associacions ha de ser almenys 1.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Has d'introduir un número vàlid.");
            return;
        }
    
        Associacio[] associacions = new Associacio[numAssociacions];
        for (int i = 0; i < numAssociacions; i++) {
            System.out.print("Introdueix el nom de l'associació " + (i + 1) + ": ");
            String nomAssociacio = scanner.nextLine();
            Associacio associacio = llistaAssociacions.ConsultaPerNom(nomAssociacio);
            if (associacio == null) {
                System.out.println("No s'ha trobat cap associació amb aquest nom.");
                return;
            }
            associacions[i] = associacio;
        }
    
        // Crear i afegir la xerrada
        Xerrada novaXerrada = new Xerrada(data, numAssistents, valoracio, titol, responsable, associacions);
        llistaAccions.afegirAccio(novaXerrada);
    
        System.out.println("Xerrada afegida correctament:");
        System.out.println(novaXerrada.toString());
    }
    
    private static void afegirDemostracio(LlistaAccions llistaAccions, LlistaAssociacions llistaAssociacions, LlistaMembres llistaMembres, Scanner scanner) {
        System.out.print("Introdueix la data de disseny (dd/MM/yyyy): ");
        String dataDisseny = scanner.nextLine();
    
        System.out.print("Introdueix el títol de la demostració: ");
        String titol = scanner.nextLine();
    
        System.out.print("És vàlida la demostració (sí/no)? ");
        String esValidaStr = scanner.nextLine().toLowerCase();
        boolean esValida = esValidaStr.equals("sí");
    
        System.out.print("Introdueix el cost dels materials: ");
        double costMaterials;
        try {
            costMaterials = Double.parseDouble(scanner.nextLine());
            if (costMaterials < 0) {
                System.out.println("El cost dels materials no pot ser negatiu.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Has d'introduir un número vàlid.");
            return;
        }
    
        System.out.print("Introdueix l'àlies del responsable: ");
        String aliasResponsable = scanner.nextLine();
        Membre responsable = null;
        for (Membre membre : llistaMembres.membresActius()) {
            if (membre.getAlias().equalsIgnoreCase(aliasResponsable)) {
                responsable = membre;
                break;
            }
        }
        if (responsable == null) {
            System.out.println("No s'ha trobat cap membre actiu amb aquest àlies.");
            return;
        }
    
        System.out.print("Quantes associacions participen en aquesta demostració? ");
        int numAssociacions;
        try {
            numAssociacions = Integer.parseInt(scanner.nextLine());
            if (numAssociacions <= 0) {
                System.out.println("El nombre d'associacions ha de ser almenys 1.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Has d'introduir un número vàlid.");
            return;
        }
    
        Associacio[] associacions = new Associacio[numAssociacions];
        for (int i = 0; i < numAssociacions; i++) {
            System.out.print("Introdueix el nom de l'associació " + (i + 1) + ": ");
            String nomAssociacio = scanner.nextLine();
            Associacio associacio = llistaAssociacions.ConsultaPerNom(nomAssociacio);
            if (associacio == null) {
                System.out.println("No s'ha trobat cap associació amb aquest nom.");
                return;
            }
            associacions[i] = associacio;
        }
    
        // Crear i afegir la demostració
        try {
            Demostracio novaDemostracio = new Demostracio(titol, associacions, responsable, dataDisseny, esValida, costMaterials);
            llistaAccions.afegirAccio(novaDemostracio);
    
            System.out.println("Demostració afegida correctament:");
            System.out.println(novaDemostracio.toString());
        } catch (Exception e) {
            System.out.println("Error en crear la demostració: " + e.getMessage());
        }
    }    

    private static void demostracionsNoActives(LlistaAccions llistaAccions) {
        Accio[] accions = llistaAccions.mostraAccions();
        boolean demostracioTrobada = false;
        double costTotal = 0;
    
        System.out.println("Demostracions no actives:");
        for (Accio accio : accions) {
            if (accio instanceof Demostracio) {
                Demostracio demostracio = (Demostracio) accio;
                if (!demostracio.isEsValid()) {
                    System.out.println(demostracio.toString());
                    costTotal += demostracio.getCostMaterials();
                    demostracioTrobada = true;
                }
            }
        }
    
        if (!demostracioTrobada) {
            System.out.println("No s'han trobat demostracions no actives.");
        } else {
            System.out.println("Cost total de les demostracions no actives: " + costTotal + " €");
        }
    }
    
    private static void membreMesActiu(LlistaMembres llistaMembres) {
        Membre[] membresActius = llistaMembres.membresActius();
        if (membresActius.length == 0) {
            System.out.println("No hi ha membres actius.");
            return;
        }
    
        Membre membreMesActiu = null;
        int maxAssociacions = 0;
        String dataAltaMesAntiga = null;
    
        for (Membre membre : membresActius) {
            Associacio[] associacions = membre.getAssociacions();
            int numAssociacions = associacions.length;
    
            if (numAssociacions > maxAssociacions || 
                (numAssociacions == maxAssociacions && (dataAltaMesAntiga == null || membre.getDataAlta().compareTo(dataAltaMesAntiga) < 0))) {
                membreMesActiu = membre;
                maxAssociacions = numAssociacions;
                dataAltaMesAntiga = membre.getDataAlta();
            }
        }
    
        if (membreMesActiu != null) {
            System.out.println("La persona més activa és:");
            System.out.println(membreMesActiu.toString());
            System.out.println("Participa en " + maxAssociacions + " associacions.");
        }
    }
    
    private static void xerradesAmbAssistents(LlistaAccions llistaAccions, Scanner scanner) {
        System.out.print("Introdueix el nombre mínim d'assistents: ");
        int numAssistentsMin;
        try {
            numAssistentsMin = Integer.parseInt(scanner.nextLine());
            if (numAssistentsMin < 0) {
                System.out.println("El nombre mínim d'assistents no pot ser negatiu.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Has d'introduir un número vàlid.");
            return;
        }
    
        Accio[] accions = llistaAccions.mostraAccions();
        boolean xerradaTrobada = false;
    
        System.out.println("Xerrades amb més de " + numAssistentsMin + " assistents:");
        for (Accio accio : accions) {
            if (accio instanceof Xerrada) {
                Xerrada xerrada = (Xerrada) accio;
                if (xerrada.getNumAssistents() > numAssistentsMin) {
                    System.out.println(xerrada.toString());
                    xerradaTrobada = true;
                }
            }
        }
    
        if (!xerradaTrobada) {
            System.out.println("No s'han trobat xerrades amb més de " + numAssistentsMin + " assistents.");
        }
    }    

    private static void valorarXerrada(LlistaAccions llistaAccions, Scanner scanner) {
        System.out.print("Introdueix el codi de la xerrada que vols valorar: ");
        String codiXerrada = scanner.nextLine();
    
        Accio accio = llistaAccions.consultaPerCodi(codiXerrada);
        if (accio == null || !(accio instanceof Xerrada)) {
            System.out.println("No s'ha trobat cap xerrada amb aquest codi.");
            return;
        }
    
        Xerrada xerrada = (Xerrada) accio;
    
        System.out.print("Introdueix la valoració (0-10): ");
        int novaValoracio;
        try {
            novaValoracio = Integer.parseInt(scanner.nextLine());
            if (novaValoracio < 0 || novaValoracio > 10) {
                System.out.println("La valoració ha d'estar entre 0 i 10.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Has d'introduir un número vàlid.");
            return;
        }
    
        // Actualitzar la valoració
        xerrada.setValoracio(novaValoracio);
        System.out.println("La valoració de la xerrada s'ha actualitzat correctament:");
        System.out.println(xerrada.toString());
    }
    
    private static void millorValorada(LlistaAccions llistaAccions) {
        Accio[] accions = llistaAccions.mostraAccions();
        Xerrada millorXerrada = null;
        double millorValoracio = -1;
        int valoracionsEmpat = 0;
    
        for (Accio accio : accions) {
            if (accio instanceof Xerrada) {
                Xerrada xerrada = (Xerrada) accio;
                double valoracioActual = xerrada.getValoracio();
    
                if (valoracioActual > millorValoracio || 
                    (valoracioActual == millorValoracio && xerrada.getNumAssistents() > valoracionsEmpat)) {
                    millorXerrada = xerrada;
                    millorValoracio = valoracioActual;
                    valoracionsEmpat = xerrada.getNumAssistents();
                }
            }
        }
    
        if (millorXerrada != null) {
            System.out.println("La xerrada millor valorada és:");
            System.out.println(millorXerrada.toString());
            System.out.println("Valoració mitjana: " + millorValoracio);
        } else {
            System.out.println("No s'ha trobat cap xerrada.");
        }
    }    

    private static void xerradesPersona(LlistaAccions llistaAccions, Scanner scanner) {
        System.out.print("Introdueix l'àlies de la persona: ");
        String aliasPersona = scanner.nextLine();
    
        Accio[] accions = llistaAccions.mostraAccions();
        boolean xerradaTrobada = false;
    
        System.out.println("Xerrades on participa com a responsable la persona amb àlies \"" + aliasPersona + "\":");
        for (Accio accio : accions) {
            if (accio instanceof Xerrada) {
                Xerrada xerrada = (Xerrada) accio;
                Membre responsable = xerrada.getResponsable();
                if (responsable != null && responsable.getAlias().equalsIgnoreCase(aliasPersona)) {
                    System.out.println(xerrada.toString());
                    xerradaTrobada = true;
                }
            }
        }
    
        if (!xerradaTrobada) {
            System.out.println("No s'han trobat xerrades on aquesta persona sigui responsable.");
        }
    }    

    private static void baixaDemostracions(LlistaAccions llistaAccions, Scanner scanner) {
        System.out.print("Introdueix la data límit (dd/MM/yyyy): ");
        String dataLimitStr = scanner.nextLine();
    
        try {
            java.text.SimpleDateFormat formatData = new java.text.SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dataLimit = formatData.parse(dataLimitStr);
    
            Accio[] accions = llistaAccions.mostraAccions();
            boolean demostracioEliminada = false;
    
            for (int i = 0; i < accions.length; i++) {
                Accio accio = accions[i];
                if (accio instanceof Demostracio) {
                    Demostracio demostracio = (Demostracio) accio;
                    java.util.Date dataDisseny = formatData.parse(demostracio.getDataDisseny());
    
                    if (!demostracio.isEsValid() && dataDisseny.before(dataLimit)) {
                        llistaAccions.eliminaAccio(demostracio.getCodi());
                        demostracioEliminada = true;
                        System.out.println("S'ha eliminat la demostració: " + demostracio.toString());
                    }
                }
            }
    
            if (!demostracioEliminada) {
                System.out.println("No s'han trobat demostracions no actives abans de la data especificada.");
            }
        } catch (java.text.ParseException e) {
            System.out.println("Error: El format de la data és incorrecte.");
        }
    }
}
