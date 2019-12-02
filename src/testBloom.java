import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class testBloom {

	public static void main(String[] args) throws IOException {

		BloomFilter b = new BloomFilter();
		Path f = Paths.get("src/db.txt");
		Scanner scf = new Scanner(f);
		List<String> lines = Files.readAllLines(f);
		
		for(int i = 0; i < lines.size(); i++) {
			String [] split = lines.get(i).split("\t");
			b.add(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
		}
		
	}

}
