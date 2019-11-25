import java.util.ArrayList;
import java.util.HashMap;

public class MinHash {
	private HashMap<Utilizador,ArrayList<Produto>> intern;
	private String n;
	
	public MinHash() {
		intern = BloomFilter.getListagem();

		
		
	}
	
	public double similarity() {
		double value =0;
		for(int i=0 ; i<intern.size() & intern.containsKey(i); i++) {
			
		}
		
		
		return value;
	}
	
	
	
}
