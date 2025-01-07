package aplicacioIG;

import dades.*;
import javax.swing.*;
import java.awt.*;

public class PanellDetall extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextArea areaDetalls;

    public PanellDetall() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Detalls de la Demostració"));

        areaDetalls = new JTextArea();
        areaDetalls.setEditable(false);
        areaDetalls.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaDetalls.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollDetalls = new JScrollPane(areaDetalls);
        this.add(scrollDetalls);
        this.setPreferredSize(new Dimension(350, 0));
    }

    public void mostrarDetalls(Demostracio demo) {
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
}
