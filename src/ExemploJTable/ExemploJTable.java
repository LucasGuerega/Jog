package ExemploJTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class ExemploJTable extends JFrame {
    public ExemploJTable() {
        // Criar os dados para a tabela
        Object[][] data = {
                {getImageIcon("https://raw.githubusercontent.com/WalkTourDJKL/Sistema-da-WalkTour/main/src/main/java/imgs/Blumenau.png"), "", getImageIcon("https://raw.githubusercontent.com/WalkTourDJKL/Sistema-da-WalkTour/main/src/main/java/imgs/Canela.png"), ""},
                {getImageIcon("https://raw.githubusercontent.com/WalkTourDJKL/Sistema-da-WalkTour/main/src/main/java/imgs/Gramado.png"), "", getImageIcon("https://via.placeholder.com/50x50"), ""},
                {getImageIcon("https://raw.githubusercontent.com/WalkTourDJKL/Sistema-da-WalkTour/main/src/main/java/imgs/São%20Francisco%20do%20sul.png"), "", getImageIcon("https://via.placeholder.com/50x50"), ""},
                {getImageIcon("https://raw.githubusercontent.com/WalkTourDJKL/Sistema-da-WalkTour/main/src/main/java/imgs/Bento%20Gonçalves.png"), "", getImageIcon("https://via.placeholder.com/50x50"), ""},
                {getImageIcon("https://via.placeholder.com/50x50"), "", getImageIcon("https://via.placeholder.com/50x50"), ""}
        };

        // Criar os nomes das colunas
        String[] columnNames = {"", "", "", ""};

        // Criar o modelo da tabela
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Criar a JTable com o modelo
        JTable table = new JTable(model);
        table.setRowHeight(100); // Altura

        // Configurar o renderizador para todas as colunas
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new CustomPanelRenderer());
        }

        // Configurar o evento de clique para toda a célula
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                // Aqui você pode colocar o código para redirecionar para outro lugar
                JOptionPane.showMessageDialog(null, "Redirecionando para algum lugar: " + table.getValueAt(row, column));
            }
        });

        // Adicionar a JTable a um JScrollPane para suportar rolagem
        JScrollPane scrollPane = new JScrollPane(table);

        // Configurar o JFrame
        setTitle("JTable With Custom Content Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 300);
        setLocationRelativeTo(null);

        // Adicionar a JScrollPane (que contém a JTable) ao JFrame
        getContentPane().add(scrollPane);

        // Exibir o JFrame
        setVisible(true);
    }

    // Método para carregar as imagens a partir de URLs
    private ImageIcon getImageIcon(String url) {
        try {
            return new ImageIcon(new URL(url));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Classe para renderizar os painéis personalizados na tabela
    static class CustomPanelRenderer extends JPanel implements TableCellRenderer {
        public CustomPanelRenderer() {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(300, 100));

            JLabel imageLabel = new JLabel();
            add(imageLabel, BorderLayout.CENTER);

            JPanel textPanel = new JPanel(new GridLayout(2, 1));
            JLabel text1Label = new JLabel("Texto 1");
            JLabel text2Label = new JLabel("Texto 2");
            textPanel.add(text1Label);
            textPanel.add(text2Label);
            add(textPanel, BorderLayout.SOUTH);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof ImageIcon) {
                ((JLabel) getComponent(0)).setIcon((ImageIcon) value);
            }
            return this;
        }
    }

    public static void main(String[] args) {
        // Criar uma instância do JFrame
        new ExemploJTable();
    }
}
