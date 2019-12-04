import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class testBloom {

	public static void main(String[] args) throws IOException {

		Path f = Paths.get("src/testbloom.txt");
		Scanner scf = new Scanner(f);
		List<String> lines = Files.readAllLines(f);
		BloomFilter b = new BloomFilter(lines.size());
		
		for(int i = 0; i < lines.size(); i++) {
			String [] split = lines.get(i).split("\t");
			int user = Integer.parseInt(split[0]);
			int prod = Integer.parseInt(split[1]);
			b.add(user,prod);
		}
		System.out.println(lines.size()-b.getCont()+" compras adicionadas ao Bloom Filter");
		scf.close();
	}

}
