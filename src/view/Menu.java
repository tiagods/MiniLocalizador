package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6391264823149272670L;
	private JPanel contentPane;
	public static JTextField txSeparator, txExtensao;
	public static JLabel txDestino, txLocalizacao, txStatus;
	public static JButton btDestino, btIniciar, btLocalizacao;
	public static JTextArea textArea;
	
	Controller controller = Controller.getInstance();
	public static JScrollPane scrollPane_1;
	public static JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		initComponents();
		controller.iniciar();
	}
	@SuppressWarnings("serial")
	public void initComponents(){
		setTitle("Localizador de Arquivos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		txSeparator = new JTextField();
		txSeparator.setHorizontalAlignment(SwingConstants.CENTER);
		txSeparator.setColumns(10);
		
		txExtensao = new JTextField();
		txExtensao.setColumns(10);
		
		JLabel lblSeparador = new JLabel("Separador");
		
		txDestino = new JLabel("");
		txDestino.setBorder(UIManager.getBorder("TextField.border"));
		
		btDestino = new JButton("Destino");
		btDestino.setActionCommand("Destino");
		btDestino.addActionListener(controller);
		
		btIniciar = new JButton("Iniciar");
		btIniciar.setActionCommand("Iniciar");
		btIniciar.addActionListener(controller);
		
		txStatus = new JLabel("");
		txStatus.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblExtensao = new JLabel("Extensao");
		
		btLocalizacao = new JButton("Localiza\u00E7\u00E3o");
		btLocalizacao.setActionCommand("Localizacao");
		btLocalizacao.addActionListener(controller);
		
		txLocalizacao = new JLabel("");
		txLocalizacao.setBorder(UIManager.getBorder("TextField.border"));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Status", "Copiado", "Origem", "Destino"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, String.class, String.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(3).setPreferredWidth(162);
		table.getColumnModel().getColumn(4).setPreferredWidth(162);
		scrollPane_1.setViewportView(table);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(txLocalizacao, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
							.addGap(129)
							.addComponent(btLocalizacao, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(txDestino, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
							.addGap(129)
							.addComponent(btDestino, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSeparador, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addComponent(txSeparator, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblExtensao, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addComponent(txExtensao, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(scrollPane, Alignment.LEADING)
							.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
								.addComponent(txStatus, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
								.addGap(129)
								.addComponent(btIniciar, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))))
					.addGap(14))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(txLocalizacao, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btLocalizacao))
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(txDestino, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btDestino))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(txStatus, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(btIniciar))
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblSeparador))
						.addComponent(txSeparator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblExtensao))
						.addComponent(txExtensao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
}
