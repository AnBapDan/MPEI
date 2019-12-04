import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class DatabaseGen {
	
	PrintWriter writer;
	private int nl = 500;
	
	public DatabaseGen (int nl) throws IOException{
		this.nl = nl;
		writer = new PrintWriter("src/db.txt");
		for(int i = 0; i< 10000; i++) {
			int code = (int) ((Math.random()*100)+250); // Generates code numbers from 250-350 (100 products only)
			int user = (int) ((Math.random()*nl)+1);
			writer.println(user+"\t"+code);
			
		}
		writer.close();
	}

	public DatabaseGen (String s, int nl) throws IOException{
		this.nl = nl;
		File f = new File("src/"+s);
//		if(f.exists()) {
			writer = new PrintWriter(f);
			for(int i = 0; i< 10000; i++) {
				int code = (int) ((Math.random()*100)+250); // Generates code numbers from 250-350 (100 products only)
				int user = (int) ((Math.random()*nl)+1);
				writer.println(user+"\t"+code);
			}
			writer.close();
//		}
	}
}
