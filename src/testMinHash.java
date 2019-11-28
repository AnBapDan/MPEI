import java.io.IOException;
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
		MinHash mh = new MinHash(lines);
		
		int[][] tmp = mh.getMins();
		for(int i = 0; i < 1000; i++) {
			for(int j = 0; j < 1000; j++) {
				System.out.println(tmp[i][j]);
			}
		}
	}

}
