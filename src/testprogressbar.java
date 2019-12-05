import javax.swing.*;

public class testprogressbar {

	public static void main(String[] args) {
		JFrame bar = new JFrame("Loading...");
		JPanel pb = new JPanel();
		JProgressBar j = new JProgressBar();
		pb.add(j);
	//	bar.setDefaultCloseOperation(bar.EXIT_ON_CLOSE);
		bar.setUndecorated(true);
		bar.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		bar.setLocationRelativeTo(null);
		bar.setSize(150, 30);
		bar.setContentPane(pb);
		bar.setVisible(true);
		int i;
		j.setStringPainted(true);
		for (i = 0; i < 60; i++) {
			j.setValue(i);
			//System.out.println("Antes Update");
			j.updateUI();
			System.out.println(i);
		}


	}

}
