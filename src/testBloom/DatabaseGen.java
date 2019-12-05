package testBloom;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class DatabaseGen {

	PrintWriter writer;
	private int nl;
	private int nprods;
	private int npurchases;

	public DatabaseGen (int nl, int nprods, int npurchases) throws IOException{
		this.nl = nl;
		this.nprods = nprods;
		this.npurchases = npurchases;
		writer = new PrintWriter("src/testBloom/flood2.txt");
		for(int i = 0; i< npurchases; i++) {
			int code = (int) ((Math.random()*nprods)+250); // Generates code numbers from 250-350 (100 products only)
			int user = (int) ((Math.random()*this.nl)+1);
			writer.println(user+"\t"+code);

		}
		writer.close();
	}

	public DatabaseGen (String s, int nl, int nprods, int npurchases) throws IOException{
		this.nl = nl;
		this.nprods = nprods;
		this.npurchases = npurchases;
		File f = new File("src/"+s);
		writer = new PrintWriter(f);
		for(int i = 0; i < npurchases; i++) {
			int code = (int) ((Math.random()*nprods)+250); // Generates code numbers from 250-350 (100 products only)
			int user = (int) ((Math.random()*this.nl)+1);
			writer.println(user+"\t"+code);
		}
		writer.close();
	}
}
