import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import dades.*;
import excepcions.ExcepcioMembreJaExisteix;
import excepcions.ExcepcioMembreNoTrobat;

public class LlistaMembres {
    private Membre[] membres;
    private int count;

    public LlistaMembres() {
        this.membres = new Membre[100]; // Limitem la mida a 100 membres.
        this.count = 0;
    }

    public void afegirMembre(Membre membre) throws ExcepcioMembreJaExisteix {
        for (int i = 0; i < count; i++) {
            if (membres[i].getAlias().equals(membre.getAlias())) {
                throw new ExcepcioMembreJaExisteix("El membre ja existeix: " + membre.getAlias());
            }
        }
        membres[count++] = membre;
    }

    public void eliminarMembre(String alias) throws ExcepcioMembreNoTrobat {
        for (int i = 0; i < count; i++) {
            if (membres[i].getAlias().equals(alias)) {
                membres[i] = membres[count - 1];
                membres[count - 1] = null;
                count--;
                return;
            }
        }
        throw new ExcepcioMembreNoTrobat("Membre no trobat: " + alias);
    }

    public Membre[] membresActius() {
        Membre[] actius = new Membre[count];
        int index = 0;
        for (int i = 0; i < count; i++) {
            if (membres[i].isActiu()) {
                actius[index++] = membres[i];
            }
        }
        return actius;
    }

    public void guardarMembres(String nomFitxer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFitxer))) {
            for (int i = 0; i < count; i++) {
                writer.write(membres[i].toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error en guardar els membres: " + e.getMessage());
        }
    }

    public void carregarMembres(String nomFitxer) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFitxer))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] dades = linia.split(";");
                if (dades.length < 4) {
                    System.err.println("Línia mal formada: " + linia);
                    continue;
                }
    
                String alias = dades[0];
                String email = dades[1];
                String dataAlta = dades[2];
                String dataBaixa = "null".equals(dades[3]) ? null : dades[3];
    
                if (dades.length > 4) { // És un Alumne
                    String ensenyament = dades[4];
                    int anysETSE = Integer.parseInt(dades[5]);
                    afegirMembre(new Alumne(alias, email, dataAlta, ensenyament, anysETSE));
                } else { // És un Membre
                    afegirMembre(new Membre(alias, email, dataAlta));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fitxer no trobat: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error en carregar els membres: " + e.getMessage());
        } catch (ExcepcioMembreJaExisteix e) {
            System.err.println("Error al carregar un membre duplicat: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error en convertir un nombre: " + e.getMessage());
        }
    }

    public void mostrarMembresAssociacio(String nomAssociacio, String filtre) {
        System.out.println("Membres de l'associació: " + nomAssociacio);
    
        for (int i = 0; i < count; i++) {
            if (membres[i] != null) {
                if (membres[i].getAssociacions().contains(nomAssociacio)) {
                    String tipus = membres[i].getTipus(); 
                    if (filtre.equalsIgnoreCase("ambdós") || filtre.equalsIgnoreCase(tipus)) {
                        System.out.println(membres[i]);
                    }
                }
            }
        }
    }



}