import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.*;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2269971701250845501L;

	private JPanel content;
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem menuitem1;
	private JMenuItem menuitem2;
	private JMenuItem quit;
	private ActionListener a1;
	private BloomFilter bf;
	
	public MainFrame() {
		super("Habitos de Compras");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		content = new JPanel();
		content.setBackground(Color.GRAY);
		bf = new BloomFilter(10000);
		a1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JMenuItem item = (JMenuItem) e.getSource();
				if(item == menuitem1) {
					try {
						generateData();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if(item == menuitem2) {
					addPurchase();
				} else if(item == quit) {
					int y = JOptionPane.showConfirmDialog(null, "Deseja mesmo sair?");
					if(y == 0) System.exit(0);
				}
			}
		};
		createContent();
		setContentPane(content);
		setJMenuBar(menubar);
		setVisible(true);
	}

	public void createContent() {
		JPanel panel = new JPanel(new BorderLayout());
		content.add(panel);
		menubar = new JMenuBar();
		menu = new JMenu("Menu");
		menuitem1 = new JMenuItem("Gerar Base de Dados Aleatorios");
		menuitem1.addActionListener(a1);
		menuitem2 = new JMenuItem("Adicionar Compra");
		menuitem2.addActionListener(a1);
		quit = new JMenuItem("Sair");
		quit.addActionListener(a1);
		menu.add(menuitem1);
		menu.add(menuitem2);
		menu.add(quit);
		menubar.add(menu);
	}
	
	private void generateData() throws IOException {
		String s = JOptionPane.showInputDialog(null, "Insira o ficheiro onde pretende escrever");
		if(s.equals(null)) {
			DatabaseGen db = new DatabaseGen();
		} else {
			DatabaseGen db = new DatabaseGen(new File(s));
		}
	}
	
	private void addPurchase() {
		JPanel p = new JPanel(new GridLayout(2,2));
		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		JLabel l1 = new JLabel("ID do utilizador (1-1000) ");
		JLabel l2 = new JLabel("ID do produto ");
		p.add(l1); p.add(field1); p.add(l2); p.add(field2);
		JOptionPane.showMessageDialog(null, p);
		int user = Integer.parseInt(field1.getText());
		int prod = Integer.parseInt(field2.getText());
		bf.add(user,prod);
	}
	
	public void readFile() throws IOException {
		String f = JOptionPane.showInputDialog(null,"Insira o ficheiro que quer ler");
		Path p =  Paths.get(f);
		List<String> lines = Files.readAllLines(p);
	}
}
