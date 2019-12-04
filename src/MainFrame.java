import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
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
	private MinHash mh;
	private List<String> lines;
	private	JMenuItem readFile;
	private int n;

	public MainFrame() throws IOException {
		super("Habitos de Compras");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		content = new JPanel();
		content.setLayout(new GridLayout(1,3));
		content.setBackground(Color.GRAY);
		bf = new BloomFilter(10000);			 	//Por omissao serao 10000 compras
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
				} else if(item == readFile) {
					try {
						readFile("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		};
		createMenuBar();
		readFile("db.txt");
		createContent();
		setContentPane(content);
		setJMenuBar(menubar);
		setVisible(true);
	}

	private void createMenuBar() {
		menubar = new JMenuBar();
		menu = new JMenu("Menu");
		menuitem1 = new JMenuItem("Gerar Base de Dados Aleatorios");
		menuitem1.addActionListener(a1);
		menuitem2 = new JMenuItem("Adicionar Compra");
		menuitem2.addActionListener(a1);
		quit = new JMenuItem("Sair");
		quit.addActionListener(a1);
		readFile = new JMenuItem("Ler novo ficheiro");
		readFile.addActionListener(a1);
		menu.add(menuitem1);
		menu.add(menuitem2);
		menu.add(readFile);
		menu.add(quit);
		menubar.add(menu);
	}
	
	private void createContent() {
		
		JPanel leftcol = new JPanel(new GridLayout(2,1));
		JLabel leftcolName = new JLabel("MinHash");
		DefaultListModel<Object> lm = new DefaultListModel<>();
		for(int i = 0; i < lines.size(); i++) {
			String [] tmp = lines.get(i).split("\t");
			lm.add(i, tmp[0]);
		}
		JList<Object> area1 = new JList<Object>(lm);
		JScrollPane scroller = new JScrollPane(area1);
		scroller.setPreferredSize(new Dimension(200,80));
		
		leftcol.add(leftcolName);
		leftcol.add(scroller);
		
		
		
		JPanel midcol = new JPanel(new GridLayout(2,1));
		JLabel midcolName = new JLabel("MinHash");
		JTextArea area2 = new JTextArea();
		area2.setText("Lista de Utilizadores");
		midcol.add(midcolName);
		midcol.add(area2);
		
		JPanel rightcol = new JPanel(new GridLayout(2,1));
		JLabel rightcolName = new JLabel("Similaridade");
		JTextArea area3 = new JTextArea();
		area3.setText("Similaridade: ");
		rightcol.add(rightcolName);
		rightcol.add(area3);
		
		content.add(leftcol);
		content.add(midcol);
		content.add(rightcol);
	}

	private void generateData() throws IOException {
		String s = JOptionPane.showInputDialog(null, "Insira o ficheiro onde pretende escrever");
		DatabaseGen db = new DatabaseGen(s);
		readFile(s);
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

	public void readFile(String ficheiro) throws IOException {
		if(ficheiro.equals("")) {
			String f = JOptionPane.showInputDialog(null,"Insira o ficheiro que quer ler (tem de estar na pasta src) ");
			Path file = Paths.get("src/"+f);
			List<String> lines = Files.readAllLines(file);
			this.lines = lines;
			n = lines.size();
			BloomFilter b = new BloomFilter(n);
			for(int i = 0; i < lines.size(); i++) {
				String [] split = lines.get(i).split("\t");
				int user = Integer.parseInt(split[0]);
				int prod = Integer.parseInt(split[1]);
				b.add(user,prod);
			}
			System.out.println(lines.size()-b.getCont()+" compras adicionadas ao Bloom Filter");
			mh = new MinHash(lines);
		} else {
			Path fich = Paths.get("src/"+ficheiro);
			List<String> lines = Files.readAllLines(fich);
			this.lines = lines;
			n = lines.size();
			BloomFilter b = new BloomFilter(n);
			for(int i = 0; i < lines.size(); i++) {
				String [] split = lines.get(i).split("\t");
				int user = Integer.parseInt(split[0]);
				int prod = Integer.parseInt(split[1]);
				b.add(user,prod);
			}
			System.out.println(lines.size()-b.getCont()+" compras adicionadas ao Bloom Filter");
			mh = new MinHash(lines);

		}
	}
}
