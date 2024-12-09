import java.io.FileNotFoundException;
import java.util.Scanner;
import dades.*; 


public class UsaAplicacion {
    static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		
		LlistaAssociacions dadesAssociacions = new LlistaAssociacions();
		LlistaMembres dadesMembres = new LlistaMembres();
		LlistaAccions dadesAccions = new LlistaAccions();

		menu();
		int opcio = Integer.parseInt(teclat.nextLine());
		while (opcio != 17) {
			switch (opcio) {
				case 1:
					// 1. Mostrar el conjunt de mesures de la llista
					opcio1(dadesAssociacions);
					break;
				case 2:
					opcio2(dadesMembres);
					break;
				case 3:
					opcio9();
					break;
				case 4:
					opcio13(dadesAccions);
					break;
				case 5:
					opcio18();
					break;
			}
			menu();
			opcio = Integer.parseInt(teclat.nextLine());
		}

	}

    
    public static void menu(){
            System.out.println("1. Mostrar les dades de la llista associacions");
            System.out.println("2.Mostrar les dades de la llista de membres que formen part d’una associació, afegint filtre per" +
                                " a professors, alumnes o ambdós. ");
            /*System.out.println("3. Mostrar les dades de la llista de membres actius, que formen part de qualsevol associació,"+
                               "afegint filtre per a professors, alumnes o ambdós.");
            System.out.println("4. Mostrar les dades de la llista d’accions, afegint filtre o no per tipus d’acció.");
            System.out.println("5. Obtenir i mostrar la llista d’accions que ofereix una associació concreta.");
            System.out.println("6. Obtenir i mostrar la llista de les xerrades que es duen a terme en una franja de dates indicada" +
                              " per teclat.");
            System.out.println("7. Afegir una nova associació.");
            System.out.println("8.Alta d’un membre a una associació. Es pot donar el cas que el membre sigui nou, i s’haurà" +
                                "d’introduir tota la informació del membre, o que el membre ja participi en una altra associació" +
                                "i en aquest cas serà afegir la relació corresponent.");/*/ 
            System.out.println("9. Afegir una nova xerrada");
            /*System.out.println("10. Afegir una nova demostració.");
            System.out.println("11. Consultar i mostrar les dades de les demostracions que es consideren no actives. Calcular el"+
                                "cost econòmic total que va suposar preparar totes aquestes demostracions.");
            System.out.println("12. Calcular la persona més activa, és a dir, la que participa en més associacions. En cas d’empat" +
                                "es considera la que e té més antiguitat (en qualsevol associació). Si encara hi ha empat, s’escull" +
                                "qualsevol de les persones que compleixen els requisits.");/*/
            System.out.println("13. Consultar i mostrar les dades de les xerrades que ha tingut més d’un cert nombre indicat d’assistents.");
            /*System.out.println("14. . Valorar una xerrada per part d’un assistent. ");
            System.out.println("15. Consultar i mostrar la xerrada que està millor valorada (que serà la que té la mitjana de" +
                                "valoracions més alta), en cas d’empat en la nota, considerar la que ha tingut més valoracions i"+
                                "en cas d’empat agafar qualsevol.");
            System.out.println("16. Mostrar les dades de les xerrades que farà una persona concreta.");
            System.out.println("17. . Donar de baixa les demostracions que no estiguin actives i que es van dissenyar abans d’una certa data.");/*/
            System.out.println("18. Sortir de l’aplicació.");
    }
 

	public static void opcio1 (LlistaAssociacions dades) {
		System.out.println("Les dades de la llista: " + dades);
	}

	public static void opcio2 (LlistaMembres dadesMembres) {
		System.out.println("Introdueix el tipus de membre que vols filtrar (professor, alumne o tots): ");
		String tipus = teclat.nextLine();
		String associacio = teclat.nextLine();
		dadesMembres.mostrarMembresAssociacio(associacio, tipus);
	}

	public static void opcio9 () {
		String data = teclat.nextLine();
		int numAssistent = Integer.parseInt(teclat.nextLine());
		int valoracio = Integer.parseInt(teclat.nextLine());
		int codiIden = Integer.parseInt(teclat.nextLine());
		String titol = teclat.nextLine();
		String responsableAccio = teclat.nextLine();
		Xerrada xerrada1 = new Xerrada(data, numAssistent, valoracio, codiIden, titol, responsableAccio);
		System.out.println("Xerrada creada: " + xerrada1);
	} 
	
	public static void opcio13 (LlistaAccions dadesAccions) {
		System.out.println("Introdueix el nombre d'assistents: ");
		int assistents = Integer.parseInt(teclat.nextLine());
		System.out.println("Les xerrades son " + dadesAccions. mostrarXerradaAmbUnCertNombreAssistents(assistents));
	}

	public static void opcio18 () {
		System.out.println("Sortint de l'aplicació...");
	}
}

