import java.util.ArrayList;
import java.util.HashMap;

public class BloomFilter {

//	private static ArrayList<Produto> allProds;
//	private static ArrayList<Utilizador> allUsers;
//	private static HashMap<Utilizador,ArrayList<Produto>> listagem;
	private static int [][] matrix;
	
	public BloomFilter() {
//		allProds = new ArrayList<Produto>();
//		listagem = new HashMap<Utilizador,ArrayList<Produto>>();  //Hashmap com todas as compras (Utilizador - Produto)
//		allUsers = new ArrayList<Utilizador>();
		matrix = new int[1000][1000];
	}
	
	public static void add(int u, int p) {
		int [] added = hashFunc(p,u);
		
	}
	
	private static int[] hashFunc(int p, int u) {    //h1(1) = mod(sum(str(1,:).*h(1:),M);  M = 1009; h = randi(100,M,3); str = ('ola','ela','ele','ggg');
		int [] marked = new int[1009];
		
		for(int i = 0; i < matrix.length; i++) {
			int hashCode = (matrix[u][i] * (int)(Math.random()*p)) % 1009;
			marked[i] = hashCode;
		}
		return marked;
	}
}
