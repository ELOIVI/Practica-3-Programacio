package aplicacioIG;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import dades.*;

public class PanellDemostracions extends JPanel {
    private static final long serialVersionUID = 1L;

    private JButton[] bDemostracions;

    public PanellDemostracions() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void actualitzarDemostracions(Associacio[] associacions, Demostracio[] demostracionsActives, JCheckBox[] checkAssociacions) {
        this.removeAll();

        // Obtener las asociaciones seleccionadas
        Associacio[] associacionsSeleccionades = new Associacio[checkAssociacions.length];
        int count = 0;
        for (int i = 0; i < checkAssociacions.length; i++) {
            if (checkAssociacions[i].isSelected()) {
                associacionsSeleccionades[count++] = associacions[i];
            }
        }
        
        // Filtrar las demostraciones por las asociaciones seleccionadas
        Demostracio[] demostracionsFiltrades = Arrays.stream(demostracionsActives)
            .filter(demo -> Arrays.stream(demo.getAssociacions())
                .anyMatch(assoc -> Arrays.asList(associacionsSeleccionades).contains(assoc)))
            .toArray(Demostracio[]::new);
        
        // Crear botones para las demostraciones filtradas
        bDemostracions = new JButton[demostracionsFiltrades.length];
        for (int i = 0; i < demostracionsFiltrades.length; i++) {
            Demostracio demo = demostracionsFiltrades[i];
            bDemostracions[i] = createDemostracioButton(demo);
            this.add(bDemostracions[i]);
        }

        if (demostracionsFiltrades.length == 0) {
            JLabel noResults = new JLabel("No hi ha demostracions actives per mostrar");
            noResults.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(noResults);
        }

        this.revalidate();
        this.repaint();
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
        // Actualiza el panel de detalles en la clase principal
        // Este método podría interactuar con el `PanellDetall` directamente si fuera necesario
    }
}
