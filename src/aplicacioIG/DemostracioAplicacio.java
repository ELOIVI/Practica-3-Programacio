package aplicacioIG;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import dades.*;
import excepcions.ErrorAccioException;
import excepcions.ErrorFitxerException;

public class DemostracioAplicacio extends JFrame {
    private static final long serialVersionUID = 1L;
    
    // Panells Principals
    private JPanel panellAssociacions;
    private JPanel panellDemostracions;
    private JPanel panellDetalls;
    
    // Components del panell associacions
    private JCheckBox[] checkAssociacions;
    private Associacio[] associacions;
    
    // Components del panell demostracions
    private JButton[] bDemostracions;  // Changed from single button to array
    private Demostracio[] demostracions;  // Changed from List to array
    private Demostracio[] demostracionsActives;  // Changed from List to array

    // Components del panell detalls
    private JTextArea areaDetalls;

    public DemostracioAplicacio(String titol, Demostracio[] demostracions, Associacio[] associacions) {
        super(titol);
        this.demostracions = demostracions;
        this.associacions = associacions;
        this.demostracionsActives = filtrarDemostracionsActives();

        // Configuració de la finestra
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
        panellDemostracions.removeAll();

        // Obtenim les associacions seleccionades
        Associacio[] associacionsSeleccionades = new Associacio[checkAssociacions.length];
        int count = 0;  // Initialize count
        for (int i = 0; i < checkAssociacions.length; i++) {
            if (checkAssociacions[i].isSelected()) {
                associacionsSeleccionades[count++] = associacions[i];
            }
        }
        
        // Crear array del tamany correcte
        final Associacio [] associacionsFinals = Arrays.copyOf(associacionsSeleccionades, count);
                
        // Filtrar demostracions per associacions seleccionades
        Demostracio[] demostracionsFiltrades = Arrays.stream(demostracionsActives)
            .filter(demo -> Arrays.stream(demo.getAssociacions())
                .anyMatch(assoc -> Arrays.asList(associacionsFinals).contains(assoc)))
            .toArray(Demostracio[]::new);
            
        // Crear botones para las demostraciones filtradas
        bDemostracions = new JButton[demostracionsFiltrades.length];

        for (int i = 0; i < demostracionsFiltrades.length; i++) {
            Demostracio demo = demostracionsFiltrades[i];
            bDemostracions[i] = createDemostracioButton(demo);
            panellDemostracions.add(bDemostracions[i]);
        }

        if (demostracionsFiltrades.length == 0) {
            JLabel noResults = new JLabel("No hi ha demostracions actives per mostrar");
            noResults.setAlignmentX(Component.CENTER_ALIGNMENT);
            panellDemostracions.add(noResults);
        }

        panellDemostracions.revalidate();
        panellDemostracions.repaint();
    }

    private void iniContingutFinestra() {
        this.setLayout(new BorderLayout(10, 10));

        // Títol
        JLabel titol = new JLabel("Demostracions Actives ETSE", SwingConstants.CENTER);
        titol.setFont(new Font("Calibri", Font.BOLD, 24));
        titol.setForeground(Color.BLUE);
        this.add(titol, BorderLayout.NORTH);

        // Panel esquerra - Filtres per associació
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
        panellDemostracions = new JPanel();
        panellDemostracions.setLayout(new BoxLayout(panellDemostracions, BoxLayout.Y_AXIS));
        JScrollPane scrollDemostracions = new JScrollPane(panellDemostracions);
        this.add(scrollDemostracions, BorderLayout.CENTER);

        // Panel dret - Detalls
        panellDetalls = new JPanel(new BorderLayout());
        panellDetalls.setBorder(BorderFactory.createTitledBorder("Detalls de la Demostració"));
        
        areaDetalls = new JTextArea();
        areaDetalls.setEditable(false);
        areaDetalls.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaDetalls.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollDetalls = new JScrollPane(areaDetalls);
        panellDetalls.add(scrollDetalls);
        panellDetalls.setPreferredSize(new Dimension(350, 0));
        
        this.add(panellDetalls, BorderLayout.EAST);
    }

    private JButton createDemostracioButton(Demostracio demo) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.setPreferredSize(new Dimension(500, 60));
        button.setMaximumSize(new Dimension(2000, 60));
        
        JLabel titol = new JLabel(demo.getTitol());
        titol.setFont(new Font("Dialog", Font.BOLD, 14));
        button.add(titol, BorderLayout.CENTER);
        
        JLabel codi = new JLabel("Codi: " + demo.getCodi());
        button.add(codi, BorderLayout.WEST);
        
        button.addActionListener(e -> mostrarDetalls(demo));
        
        return button;
    }

    private void mostrarDetalls(Demostracio demo) {
        StringBuilder sb = new StringBuilder();
        sb.append("DETALLS DE LA DEMOSTRACIÓ\n");
        sb.append("=========================\n\n");
        sb.append("Codi: ").append(demo.getCodi()).append("\n");
        sb.append("Títol: ").append(demo.getTitol()).append("\n");
        sb.append("Data disseny: ").append(demo.getDataDisseny()).append("\n");
        sb.append("Vegades oferida: ").append(demo.getVegadesOferida()).append("\n");
        sb.append("Cost materials: ").append(String.format("%.2f€", demo.getCostMaterials())).append("\n\n");
        
        sb.append("Responsable:\n");
        sb.append("- ").append(demo.getResponsable().getNom()).append("\n\n");
        
        sb.append("Associacions:\n");
        for (Associacio assoc : demo.getAssociacions()) {
            sb.append("- ").append(assoc.getNom()).append("\n");
        }
        
        areaDetalls.setText(sb.toString());
        areaDetalls.setCaretPosition(0);
    }

    public static void main (String args []) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Cargar datos
                LlistaAccions llistaAccions = carregarDades();
                
                // Filtrar solo las demostraciones
                Demostracio[] demostracions = filtrarDemostracions(llistaAccions);
                Associacio[] associacions = carregarAssociacions();

                // Crear y mostrar la ventana
                new DemostracioAplicacio("Gestió de Demostracions ETSE", 
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
        // Aquí deberías especificar la ruta correcta de tu archivo
        String rutaFitxer = "data/accions.txt";  // Ajusta esta ruta según tu estructura
        LlistaAccions llista = new LlistaAccions(100);  // Ajusta el tamaño según necesites
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

    private static Associacio[] carregarAssociacions() {
        // Aquí deberías implementar la carga de asociaciones desde tu fichero
        // Por ahora, retornamos algunas asociaciones de prueba
        return new Associacio[] {
            new Associacio("IEEE"),
            new Associacio("ACM"),
            new Associacio("AESS")
        };
    }
}
