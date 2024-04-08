package ExemploJTable;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class ExemploJTable extends JFrame {
	private static final long serialVersionUID = 1L;

	public ExemploJTable() {
		// Criar os dados para a tabela
		Object[][] data = { { getImageIcon("Blumenau"), "", getImageIcon("Blumenau"), "" },
				{ getImageIcon("Blumenau"), "", getImageIcon("Blumenau"), "" },
				{ getImageIcon("Blumenau"), "", getImageIcon("Blumenau"), "" },
				{ getImageIcon("Blumenau"), "", getImageIcon("Blumenau"), "" },
				{ getImageIcon("Blumenau"), "", getImageIcon("Blumenau"), "" } };

		// Criar os nomes das colunas
		String[] columnNames = { "", "", "", "" };

		// Criar o modelo da tabela
		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Criar a JTable com o modelo
		JTable table = new JTable(model);
		table.setShowGrid(false);
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
				JOptionPane.showMessageDialog(null,
						"Redirecionando para algum lugar: " + table.getValueAt(row, column));
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
		File directory = new File(".");
		Image img = null;
		String path = "";
		try {
			path = directory.getCanonicalPath() + "/src/imgs/" + url + ".png";
			System.out.println(path);
			img = ImageIO.read(new File(path));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ImageIcon(img);

	}

	// Classe para renderizar os painéis personalizados na tabela
	class CustomPanelRenderer extends JPanel implements TableCellRenderer {
		private static final long serialVersionUID = 1L;

		public CustomPanelRenderer() {
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(300, 100));

			JLabel imageLabel = new JLabel();
			add(imageLabel, BorderLayout.CENTER);
			JPanel textPanel = new JPanel(new GridLayout(2, 1));
			JLabel txtCidade = new JLabel("Cidade");
			JLabel txtEstado = new JLabel("Estado");
			textPanel.add(txtCidade);
			textPanel.add(txtEstado);
			add(textPanel, BorderLayout.SOUTH);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
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
