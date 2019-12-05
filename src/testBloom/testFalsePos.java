package testBloom;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class testFalsePos {
	public static void main(String [] args) throws IOException {
	//	DatabaseGen x = new DatabaseGen(100,50,20);					(nl*prods)/2
		Path p = Paths.get("src/testBloom/filter.txt");
		List<String> lines = Files.readAllLines(p);
		BloomFilter a = new BloomFilter(lines.size());
		for( String i : lines) {
			String[] b= i.split("\t");
			a.add(Integer.parseInt(b[0]), Integer.parseInt(b[1]));
			
		}
		

		Set<String> ax = new HashSet<>();
		DatabaseGen t = new DatabaseGen(100,50,1000000);
		Path x = Paths.get("src/testBloom/flood2.txt");
		List<String> lines2 = Files.readAllLines(x);
		int cont =0;
		int dup=0;
		int f =0;
		boolean found = false;
		for( String i : lines2) {
			String[] b= i.split("\t");
			ax.add(i);
			if(a.exists(Integer.parseInt(b[0]), Integer.parseInt(b[1]))) {
				System.out.println("-------------------------------");
				for(String k : lines) {
					if(k.equals(i)) {
						System.out.println("True");
						found = true;
					}
				}
				if(!found) {
					for(String l : lines2) {
						if(i.equals(l)) {
							dup++;
						}
					}
					System.out.println("False Positive");
					if(dup >1) {
						f++;
					}

				}
			}
		}
		
//		for(String i : lines) {
//			String[] b= i.split("\t");
//			
//			
//		}
		double pp = (double) f/lines.size();
		System.out.println(pp);
		pp = (double) f/ax.size();
		System.out.println(pp);
		System.out.println(f);
		System.out.println(lines.size());
		System.out.println(ax.size());
		
		
		
	}
	
}
