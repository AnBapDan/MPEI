import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private JMenuItem quit;
	private JTabbedPane tp;
	private ActionListener a1;
	private BloomFilter bf;
	private MinHash mh;
	private	JMenuItem readFile;
	private int n;
	private int nl;
	private int user1;
	private int user2;
	private Path p;
	private JPanel bloomPanel;
	private JPanel minhashPanel;
	private JPanel terminal;
	private JTextArea console;
	private JLabel label1 = new JLabel("Utilizador 1: Esperando valor");
	private JLabel label2 = new JLabel("Utilizador 2: Esperando valor");
	private JLabel label3 = new JLabel("Resultado: ");

	public MainFrame() throws IOException {
		super("Habitos de Compras");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		tp = new JTabbedPane();
		bloomPanel = new JPanel();
		minhashPanel = new JPanel();
		terminal = new JPanel();
		console = new JTextArea();
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
		setJMenuBar(menubar);
		setVisible(true);
	}

	private void createMenuBar() {
		menubar = new JMenuBar();
		menu = new JMenu("Menu");
		menuitem1 = new JMenuItem("Gerar Base de Dados Aleatorios");
		menuitem1.addActionListener(a1);
		quit = new JMenuItem("Sair");
		quit.addActionListener(a1);
		readFile = new JMenuItem("Ler novo ficheiro");
		readFile.addActionListener(a1);
		menu.add(menuitem1);
		menu.add(readFile);
		menu.add(quit);
		menubar.add(menu);
	}

	private void createBloomPanel() {

		bloomPanel = new JPanel(new GridLayout(2,1));

		JPanel top = new JPanel(new BorderLayout());
		JPanel bottom = new JPanel(new BorderLayout());
		GridBagConstraints gc1 = new GridBagConstraints();
		JPanel topEmbed = new JPanel(new GridBagLayout());
		
		
		gc1.gridx = 0;
		gc1.gridy = 0;
		JLabel utilizador = new JLabel("Utilizador");
		topEmbed.add(utilizador,gc1);
		gc1.gridx = 1;
		gc1.weighty = 0.1;
		JTextField utilizadorID = new JTextField();
		utilizadorID.setPreferredSize(new Dimension(80,20));
		topEmbed.add(utilizadorID,gc1);
		gc1.gridx = 0;
		gc1.gridy = 1;
		JLabel prod = new JLabel("Produto (ID comeca em 250)");
		topEmbed.add(prod,gc1);
		gc1.gridx = 1;
		JTextField prodID = new JTextField();
		prodID.setPreferredSize(new Dimension(80,20));
		topEmbed.add(prodID,gc1);
		gc1.gridy = 2;
		gc1.gridx = 0;
		gc1.weighty = 0.1;
		JButton button = new JButton("Verificar se existe");
		button.setPreferredSize(new Dimension(150,25));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int user = Integer.parseInt(utilizadorID.getText());
				int prod = Integer.parseInt(prodID.getText());

				if(user < 1 || user > mh.getNl()) {
					JOptionPane.showMessageDialog(null,"ID de utilizador invalido");
				} else if(prod < 250) {
					JOptionPane.showMessageDialog(null,"ID de produto invalido");
				} else {
					if(bf.exists(user, prod)) {
						JOptionPane.showMessageDialog(null, "O utilizador "+user+" ja comprou o produto "+prod);
					} else {
						JOptionPane.showMessageDialog(null, "O utilizador "+user+" ainda nao comprou o produto "+prod);
					}
				}
			}

		});
		topEmbed.add(button,gc1);
		gc1.gridx = 1;
		gc1.weightx = 0;
		JButton button2 = new JButton("Adicionar");
		button2.setPreferredSize(new Dimension(150,25));
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int user = Integer.parseInt(utilizadorID.getText());
				int prod = Integer.parseInt(prodID.getText());
				if(user < 1 || user > mh.getNl()) {
					JOptionPane.showMessageDialog(null,"ID de utilizador invalido");
				} else if(!(bf.exists(user, prod))) {
					bf.add(user, prod);
					File file = new File(p.getFileName().toString());
					PrintWriter pw = null;
					try {
						pw = new PrintWriter(file);
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					pw.println(user+"\t"+prod);
					pw.close();
					List<String> lin = new ArrayList<>();
					try {
						lin = Files.readAllLines(p);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					mh = new MinHash(lin,mh.getNl());
					JOptionPane.showMessageDialog(null, "Adicionada compra do produto "+prod+" pelo utilizador "+user);
				} else {
					JOptionPane.showMessageDialog(null, "O utilizador "+user+" ja comprou o produto "+prod);
				}

			}
		});
		topEmbed.add(button2,gc1);
		top.add(topEmbed,BorderLayout.CENTER);
		
		terminal = new JPanel();
		console = new JTextArea();
		console.setPreferredSize(new Dimension(700,500));
		console.setBackground(Color.BLACK);
		console.setEnabled(false);
		console.setText("Consola do Bloom Filter");
		terminal.add(console);
		bottom.add(terminal,BorderLayout.CENTER);

		bloomPanel.add(top);
		bloomPanel.add(bottom);
	}

	private void createContent() {

		minhashPanel = new JPanel();

		//MinHashPanel
		//Left list column
		JPanel leftcol = new JPanel(new BorderLayout());
		JPanel lefttop = new JPanel();
		lefttop.setPreferredSize(new Dimension(50,20));
		JLabel leftcolName = new JLabel("Lista 1");
		DefaultListModel<Object> lm = new DefaultListModel<>();

		int size = mh.getSizeofListSets();
		for(int i = 0; i < size ; i++) {
			lm.add(i, i+1);
		}
		JList<Object> area1 = new JList<Object>(lm);
		JScrollPane scroller = new JScrollPane(area1);
		scroller.setPreferredSize(new Dimension(100,500));
		lefttop.add(leftcolName);

		leftcol.add(lefttop,BorderLayout.NORTH);
		leftcol.add(scroller,BorderLayout.CENTER);

		//Right List column
		JPanel midcol = new JPanel(new BorderLayout());
		JPanel midtop = new JPanel();
		midtop.setPreferredSize(new Dimension(50,20));
		JLabel midcolName = new JLabel("Lista 2");
		DefaultListModel<Object> lm2 = new DefaultListModel<>();

		int size2 = mh.getSizeofListSets();
		for(int i = 0; i < size2 ; i++) {
			lm2.add(i, i+1);
		}
		JList<Object> area2 = new JList<Object>(lm);
		JScrollPane scroller2 = new JScrollPane(area2);
		scroller.setPreferredSize(new Dimension(100,500));
		midtop.add(midcolName);

		midcol.add(midtop,BorderLayout.NORTH);
		midcol.add(scroller2,BorderLayout.CENTER);

		//Similarity column
		JPanel right = new JPanel(new GridLayout(2,1));

		JPanel rightcol = new JPanel(new GridBagLayout());
		GridBagConstraints rgc = new GridBagConstraints();

		JPanel righttop = new JPanel();
		JPanel rightbottom = new JPanel();
		JPanel rightresult = new JPanel();

		JPanel embed = new JPanel(new GridBagLayout());
		GridBagConstraints egc = new GridBagConstraints();

		righttop.add(label1);
		rightbottom.add(label2);
		rightresult.add(label3);
		egc.gridx = 0;
		egc.gridy = 0;
		embed.add(righttop,egc);
		egc.gridy = 1;
		embed.add(rightbottom,egc);

		rgc.gridx = 0;
		rgc.gridy = 0;
		rightcol.add(embed,rgc);
		rgc.gridy = 1;
		rightcol.add(rightresult,rgc);

		area1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JList<Object> tmp = (JList<Object>) e.getSource();
				label1 = new JLabel("Utilizador 1: "+tmp.getSelectedValue().toString());
				user1 = Integer.parseInt(tmp.getSelectedValue().toString());		
				embed.remove(righttop);
				righttop.removeAll();
				embed.revalidate();
				embed.repaint();
				righttop.add(label1);
				egc.gridx = 0;
				egc.gridy = 0;
				embed.add(righttop,egc);
				if(user1 != 0 && user2 != 0) {
					if(user1 > user2) {				
						label3 = new JLabel("Similaridade: "+	String.valueOf(mh.getValueOfMatrix(user2, user1)));
						rightcol.remove(rightresult);
						rightresult.removeAll();
						rightcol.revalidate();
						rightcol.repaint();
						rightresult.add(label3);
						rgc.gridx = 0;
						rgc.gridy = 1;
						rightcol.add(rightresult,rgc);
					}
					else if(user1 == user2) {
						label3 = new JLabel("Similaridade: 1.0");
						rightcol.remove(rightresult);
						rightresult.removeAll();
						rightcol.revalidate();
						rightcol.repaint();
						rightresult.add(label3);
						rgc.gridx = 0;
						rgc.gridy = 1;
						rightcol.add(rightresult,rgc);
					}
					else {
						label3 = new JLabel("Similaridade: "+	String.valueOf(mh.getValueOfMatrix(user1, user2)));
						rightcol.remove(rightresult);
						rightresult.removeAll();
						rightcol.revalidate();
						rightcol.repaint();
						rightresult.add(label3);
						rgc.gridx = 0;
						rgc.gridy = 1;
						rightcol.add(rightresult,rgc);
					}
				}
			}
		});

		area2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JList<Object> tmp = (JList<Object>) e.getSource();
				label2 = new JLabel("Utilizador 2: "+tmp.getSelectedValue().toString());
				user2 = Integer.parseInt(tmp.getSelectedValue().toString());
				embed.remove(rightbottom);
				rightbottom.removeAll();
				embed.revalidate();
				embed.repaint();
				rightbottom.add(label2);
				egc.gridx = 0;
				egc.gridy = 1;
				embed.add(rightbottom,egc);
				if(user1 != 0 && user2 != 0) {
					if(user1 > user2) {				
						label3 = new JLabel("Similaridade: "+	String.valueOf(mh.getValueOfMatrix(user2, user1)));
						rightcol.remove(rightresult);
						rightresult.removeAll();
						rightcol.revalidate();
						rightcol.repaint();
						rightresult.add(label3);
						rgc.gridx = 0;
						rgc.gridy = 1;
						rightcol.add(rightresult,rgc);
					}
					else if(user1 == user2) {
						label3 = new JLabel("Similaridade: 1.0");
						rightcol.remove(rightresult);
						rightresult.removeAll();
						rightcol.revalidate();
						rightcol.repaint();
						rightresult.add(label3);
						rgc.gridx = 0;
						rgc.gridy = 1;
						rightcol.add(rightresult,rgc);
					}
					else {
						label3 = new JLabel("Similaridade: "+	String.valueOf(mh.getValueOfMatrix(user1, user2)));
						rightcol.remove(rightresult);
						rightresult.removeAll();
						rightcol.revalidate();
						rightcol.repaint();
						rightresult.add(label3);
						rgc.gridx = 0;
						rgc.gridy = 1;
						rightcol.add(rightresult,rgc);
					}
				}
			}
		});

		JPanel bottomright = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		JLabel user = new JLabel("Utilizador");
		bottomright.add(user,gc);
		gc.gridx = 1;
		JTextField userID = new JTextField();
		userID.setPreferredSize(new Dimension(80,20));
		gc.weighty = 0.1;
		bottomright.add(userID,gc);
		gc.gridx = 0;
		gc.gridy = 1;
		JLabel thresh = new JLabel("Threshold");
		bottomright.add(thresh,gc);
		gc.gridx = 1;
		JTextField text = new JTextField();
		text.setPreferredSize(new Dimension(80,20));
		gc.weighty = 0.1;
		bottomright.add(text,gc);
		gc.gridx = 1;
		gc.gridy = 2;
		JButton search = new JButton("Procurar");
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Double.parseDouble(text.getText()) < 0 || Double.parseDouble(text.getText()) > 1) {
					JOptionPane.showMessageDialog(null, "O valor deve estar entre 0 e 1");
				} else if(Integer.parseInt(userID.getText()) < 0 || Integer.parseInt(userID.getText()) > mh.getNl()){
					JOptionPane.showMessageDialog(null, "O ID do utilizador nao e valido");
				} else {
					ArrayList<Integer> tmp = mh.thresholdVal(Integer.parseInt(userID.getText()),Double.parseDouble(text.getText()));
					Collections.sort(tmp);
					boolean first = true;
					if(tmp.size() > 20 ) {
						JOptionPane.showMessageDialog(null, "Foram encontrados "+tmp.size()+" valores com a similaridade igual ou superior a "+
								Double.parseDouble(text.getText()));
					} else if (tmp.size() == 0) {
						JOptionPane.showMessageDialog(null, "Nao existem quaisquer utilizadores a apresentar.");
					} else {
						String qp = "";
						for(int q = 0 ; q< tmp.size(); q++) {
							if(first) {
								qp += tmp.get(q);
								first = false;
							}
							else {
								qp += ", "+tmp.get(q);
							}
							if((q+1)%3 == 0) {
								qp += "\n";
								first = true;
							}
						}
						JOptionPane.showMessageDialog(null, qp);						
					}	
				}
			}
		});				
		bottomright.add(search,gc);

		gc.gridx = 1;
		gc.gridy = 3;
		JTextArea textarea = new JTextArea();
		bottomright.add(textarea,gc);

		JPanel left = new JPanel(new GridLayout(1,2));
		left.add(leftcol);
		left.add(midcol);
		right.add(rightcol);
		right.add(bottomright);
		minhashPanel.setLayout(new BorderLayout());
		minhashPanel.add(left,BorderLayout.WEST);
		minhashPanel.add(right,BorderLayout.CENTER);
		tp.setBounds(0, 0, 200, 200);
	}

	private void generateData() throws IOException {
		JPanel p = new JPanel(new GridLayout(4,4));
		JLabel l1 = new JLabel("Insira o ficheiro onde pretende escrever");
		JLabel l2 = new JLabel("Insira o numero de utilizadores");
		JLabel l3 = new JLabel("Insira o numero de produtos diferentes");
		JLabel l4 = new JLabel("Insira o numero de compras a fazer");
		JTextField f1 = new JTextField();
		JTextField f2 = new JTextField();
		JTextField f3 = new JTextField();
		JTextField f4 = new JTextField();

		p.add(l1); p.add(f1); p.add(l2); p.add(f2); p.add(l3); p.add(f3); p.add(l4); p.add(f4);

		JOptionPane.showMessageDialog(null, p);

		DatabaseGen db = new DatabaseGen(f1.getText(),Integer.parseInt(f2.getText()),Integer.parseInt(f3.getText()),Integer.parseInt(f4.getText()));
		this.nl = Integer.parseInt(f2.getText());
		mh.setNl(nl);
		readFile(f1.getText());
	}

	public void refreshBloom(int all, int added) {
		console.setText("\n\n\n\n\n\n\n                            "
				+ "	    De "+all+" compras, apenas "+(all-added)+" foram adicionadas ao Bloom Filter");
	}

	public void readFile(String ficheiro) throws IOException {

		if(ficheiro.equals("")) {
			String f = JOptionPane.showInputDialog(null,"Insira o ficheiro que quer ler (tem de estar na pasta src) ");
			p = Paths.get("src/"+f);
		} else {
			p = Paths.get("src/"+ficheiro);
		}
		List<String> lines = Files.readAllLines(p);
		n = lines.size();
		bf = new BloomFilter(n);

		bloomPanel.removeAll();
		minhashPanel.removeAll();
		tp.removeAll();

		int maxID = 0;
		for(int i = 0; i < lines.size(); i++) {
			String [] split = lines.get(i).split("\t");
			int user = Integer.parseInt(split[0]);
			int prod = Integer.parseInt(split[1]);
			bf.add(user,prod);

			if(user > maxID) {
				maxID = user;
			}
		}
		this.nl = maxID;

		System.out.println("De "+lines.size()+" compras, apenas "+(lines.size()-bf.getCont())+" foram adicionadas ao Bloom Filter");

		createBloomPanel();
		refreshBloom(lines.size(),bf.getCont());
		mh = new MinHash(lines,this.nl);
		createContent();
		bloomPanel.revalidate();
		bloomPanel.repaint();
		minhashPanel.revalidate();
		minhashPanel.repaint();
		tp.add("Bloom Filter",bloomPanel);
		tp.add("MinHash",minhashPanel);
		this.add(tp);
	}
}