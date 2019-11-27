import java.util.ArrayList;
import java.util.HashMap;

public class BloomFilter {

	private static ArrayList<Produto> allProds;
	private static ArrayList<Utilizador> allUsers;
	private static HashMap<Utilizador,ArrayList<Produto>> listagem;
	
	public BloomFilter() {
		allProds = new ArrayList<Produto>();
		listagem = new HashMap<Utilizador,ArrayList<Produto>>();  //Hashmap com todas as compras (Utilizador - Produto)
	}
	
	public static void add(Utilizador u, Produto p) {
		if(!(exists(u,p))) {
			if(listagem.containsKey(u)) {
				ArrayList<Produto> tmp = listagem.get(u);
				if(!(tmp.contains(p))) {
					tmp.add(p);
				}
				listagem.replace(u,tmp);
			} else {
				ArrayList<Produto> values = new ArrayList<Produto>();
				values.add(p);
				listagem.put(u, values);
			}
		}
	}
	
	public static boolean exists(Utilizador u, Produto p) {
		return false;
	}

	public static HashMap<Utilizador,ArrayList<Produto>> getListagem() {
		return listagem;
	}

//	public static ArrayList<Produto> getAllProds() {
//		return allProds;
//	}
//	
//	public static ArrayList<Utilizador> getAllUsers() {
//		return allUsers;
//	}
	
	public static ArrayList<Produto> getSingleList(Utilizador u) {
		return u.getProdutos();
	}
}
