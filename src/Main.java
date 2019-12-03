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
		int n = lines.size(); //number of elements
		int m = n * 8; 		  //number of bits of the array
		int k = (int) ((m*Math.log(2))/n);
		System.out.println(n);
		System.out.println(m);
		System.out.print(k);
	//	MinHash mh = new MinHash(lines);
	}
}
