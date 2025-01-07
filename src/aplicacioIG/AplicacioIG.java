package aplicacioIG;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;
import dades.*;
import excepcions.ErrorAccioException;
import excepcions.ErrorFitxerException;

public class AplicacioIG extends JFrame {
    private static final long serialVersionUID = 1L;

    // Paneles principales
    private JPanel panellAssociacions;
    private PanellDemostracions panellDemostracions;
    private PanellDetall panellDetalls;
    
    // Componentes del panel asociaciones
    private JCheckBox[] checkAssociacions;
    private Associacio[] associacions;
    
    // Componentes del panel demostraciones
    private JButton[] bDemostracions;  // Changed from single button to array
    private Demostracio[] demostracions;  // Changed from List to array
    private Demostracio[] demostracionsActives;  // Changed from List to array

    public AplicacioIG(String titol, Demostracio[] demostracions, Associacio[] associacions) {
        super(titol);
        this.demostracions = demostracions;
        this.associacions = associacions;
        this.demostracionsActives = filtrarDemostracionsActives();

        // Configuración de la ventana
        this.setLocation(100, 200);
        this.setSize(800, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        iniContingutFinestra();
        actualitzarDemostracions();

        this.setVisible(true);
    }

    private Demostracio[] filtrarDemostracionsActives() {
        return Arrays.stream(this.demostracions)
            .filter(Demostracio::isEsValid)
            .toArray(Demostracio[]::new);
    }

    private void actualitzarDemostracions() {
        panellDemostracions.actualitzarDemostracions(this.associacions, this.demostracionsActives, this.checkAssociacions);
    }

    private void iniContingutFinestra() {
        this.setLayout(new BorderLayout(10, 10));

        // Título
        JLabel titol = new JLabel("Demostracions Actives ETSE", SwingConstants.CENTER);
        titol.setFont(new Font("Calibri", Font.BOLD, 24));
        titol.setForeground(Color.BLUE);
        this.add(titol, BorderLayout.NORTH);

        // Panel izquierdo - Filtros por asociación
        panellAssociacions = new JPanel();
        panellAssociacions.setBorder(BorderFactory.createTitledBorder("Filtrar per Associacions"));
        panellAssociacions.setLayout(new BoxLayout(panellAssociacions, BoxLayout.Y_AXIS));
        
        checkAssociacions = new JCheckBox[associacions.length];
        for (int i = 0; i < associacions.length; i++) {
            checkAssociacions[i] = new JCheckBox(associacions[i].getNom());
            checkAssociacions[i].setSelected(true);
            checkAssociacions[i].addActionListener(e -> actualitzarDemostracions());
            panellAssociacions.add(checkAssociacions[i]);
        }
        
        JScrollPane scrollAssociacions = new JScrollPane(panellAssociacions);
        scrollAssociacions.setPreferredSize(new Dimension(250, 0));
        this.add(scrollAssociacions, BorderLayout.WEST);

        // Panel central - Llista de demostracions
        panellDemostracions = new PanellDemostracions();
        this.add(panellDemostracions, BorderLayout.CENTER);

        // Panel derecho - Detalles
        panellDetalls = new PanellDetall();
        this.add(panellDetalls, BorderLayout.EAST);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Cargar datos
                LlistaAccions llistaAccions = carregarDades();
                
                // Filtrar solo las demostraciones
                Demostracio[] demostracions = filtrarDemostracions(llistaAccions);
                Associacio[] associacions = carregarAssociacions();

                // Crear y mostrar la ventana
                new AplicacioIG("Gestió de Demostracions ETSE", 
                                demostracions, 
                                associacions);
                                       
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Error al iniciar la aplicació: " + e.getMessage(),
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                System.exit(1);
            }
        });
    }

    private static LlistaAccions carregarDades() throws ErrorFitxerException, ErrorAccioException {
        String rutaFitxer = "src/data/accions.txt";  
        LlistaAccions llista = new LlistaAccions(100);
        llista.carregarAccionsDesFitxer(rutaFitxer);
        return llista;
    }

    private static Demostracio[] filtrarDemostracions(LlistaAccions llista) {
        Accio[] accions = llista.getAccions();
        int numDemostracions = 0;
        
        // Primero contamos cuántas demostraciones hay
        for (Accio accio : accions) {
            if (accio instanceof Demostracio) {
                numDemostracions++;
            }
        }
        
        // Creamos el array del tamaño exacto y lo llenamos
        Demostracio[] demostracions = new Demostracio[numDemostracions];
        int index = 0;
        for (Accio accio : accions) {
            if (accio instanceof Demostracio) {
                demostracions[index++] = (Demostracio) accio;
            }
        }
        
        return demostracions;
    }

    private static Associacio[] carregarAssociacions() throws ErrorFitxerException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/data/fitxer_associacions.txt"))) {
        // Leemos las asociaciones desde el archivo serializado
        Associacio[] associacions = (Associacio[]) ois.readObject();
        return associacions;
    } catch (IOException | ClassNotFoundException e) {
        throw new ErrorFitxerException("Error al llegir l'arxiu d'associacions", e);
    }
}
    
}
