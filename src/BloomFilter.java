import java.util.ArrayList;
import java.util.HashMap;

public class BloomFilter {

	private static ArrayList<Produto> allProds;
	private static ArrayList<Utilizador> allUsers;
	private static HashMap<Utilizador,Produto> listagem;
	
	public BloomFilter() {
		allProds = new ArrayList<Produto>();
		listagem = new HashMap<Utilizador,Produto>();
	}
	
	private static int hash1() {
		return 0;
	}
	
	private static int hash2() {
		return 0;
	}
	
	private static int hash3() {
		return 0;
	}
	
	private static int hash4() {
		return 0;
	}
	
	private static int hash5() {
		return 0;
	}
	
	public static void add(Utilizador u, Produto p) {
		boolean b = exists(u,p);
		if(!b) {
			listagem.put(u, p);
		}
	}
	
	public static boolean userExists(Utilizador u) {
		return false;
	}
	
	public static boolean productExists(Produto p) {
		return false;
	}
	
	public static boolean exists(Utilizador u, Produto p) {
		if(hash1() == 0 && hash2() == 0 && hash3() == 0 && hash4() == 0 && hash5() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static HashMap<Utilizador,Produto> getListagem() {
		return listagem;
	}

	public static ArrayList<Produto> getAllProds() {
		return allProds;
	}
	
	public static ArrayList<Utilizador> getAllUsers() {
		return allUsers;
	}
}
