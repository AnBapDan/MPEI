import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BloomFilter {

	private static ArrayList<Produto> allProds;
	private static ArrayList<Utilizador> allUsers;
	private static HashMap<Utilizador,ArrayList<Produto>> listagem;
	private static int [][] matrix;
	
	public BloomFilter() {
		allProds = new ArrayList<Produto>();
		listagem = new HashMap<Utilizador,ArrayList<Produto>>();  //Hashmap com todas as compras (Utilizador - Produto)
		allUsers = new ArrayList<Utilizador>();
		matrix = new int[1000][1000];
	}
	
	public static void add(Utilizador u, Produto[] p) {
		int user = u.hashCode();
		int [] added = hashFunc(user);
		
	}
	
	private static int[] hashFunc(int seed) {    //h1(1) = mod(sum(str(1,:).*h(1:),M);  M = 1009; h = randi(100,M,3); str = ('ola','ela','ele','ggg');
		int [] p = new int[1009];
		
		for(int i = 0; i < allProds.size(); i++) {
			int hashCode = (allProds.get(i).getID() * (int)(Math.random()*seed)) % 1009;
			p[i] = hashCode; 
		}
		return p;
	}

	public static HashMap<Utilizador,ArrayList<Produto>> getListagem() {
		return listagem;
	}
	
	public static ArrayList<Produto> getSingleList(Utilizador u) {
		return u.getProdutos();
	}
}
