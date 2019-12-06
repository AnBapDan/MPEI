import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class testMinHash {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Path f = Paths.get("src/db.txt");
		Scanner scf = new Scanner(f);
		List<String> lines = Files.readAllLines(f);
		MinHash mh = new MinHash(lines,500);
		
		
		PrintWriter pw = new PrintWriter("src/pw.txt"); //Inicialmente testámos para analisar a matriz de similaridade de todos os utilizadores
														 
		double[][] tmp = mh.getMatrix();
		for(int i = 0; i < mh.getNl(); i++) {
			for(int j = 0; j < mh.getNl(); j++) {
//				System.out.print(tmp[i][j]+" ");
				pw.print(tmp[i][j]+" ");
			}
//			System.out.println();
			pw.println();
		}
	}

}
