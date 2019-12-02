import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BloomFilter bf = new BloomFilter();
		
		Path f = Paths.get("src/db.txt");
		Scanner scf = new Scanner(f);
		List<String> lines = Files.readAllLines(f);
		int n =lines.size();										//number of elements
		double p = 0.01;											// % of false positives
		int m = (int)((-n*Math.log(p))/(Math.log(2)*Math.log(2)));	//number of bits needed
		int k = (int) (m/(n*Math.log(2)));
		System.out.println(n);
		System.out.println(m);
		System.out.print(k);
	//	MinHash mh = new MinHash(lines);
	}
}
