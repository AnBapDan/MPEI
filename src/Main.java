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
		MinHash mh = new MinHash();
		
		
		Path f = Paths.get("src/db.txt");
		Scanner scf = new Scanner(f);
		while(scf.hasNextLine())
			mh.newline(scf.nextLine());
		
		
//		List<String> list = Files.readAllLines(f);
//		Iterator<String> it = list.iterator();
//		int user,prod;
//		List<Object> users = new ArrayList<Object>(1000);

//		while(it.hasNext()) {
//			String[] t = it.next().split("\t");
//			user = Integer.parseInt(t[0]);
//			prod = Integer.parseInt(t[1]);
//			try {
//				ArrayList<Integer> tmp = new ArrayList<Integer>(100);	
//				tmp.addAll((Collection<? extends Integer>) users.get(user-1));
//				tmp.add(prod);
//				users.add(user-1,tmp);				
//			}catch(Exception e) {
//				ArrayList<Integer> tmp = new ArrayList<Integer>(100);
//				tmp.add(prod);
//				users.add(user-1,tmp);	
//			}
//
//		}
	}
}
