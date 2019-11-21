import java.util.ArrayList;

public class Utilizador {

	private static ArrayList<Utilizador> lista;
	private String nome;
	private ArrayList<Produto> produtos;
	private static ArrayList<Produto> existingProds = new ArrayList<Produto>();
	
	public Utilizador(String nome) {
		this.nome = nome;
		produtos = new ArrayList<Produto>();
	}
	
	public Utilizador(String nome, ArrayList<Produto> p) {
		this.nome = nome;
		produtos = p;
	}

	public String getNome() {
		return nome;
	}
	
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	
	public ArrayList<Utilizador> getLista() {
		return lista;
	}
	
	public static ArrayList<Produto> getExistingProds() {
		return existingProds;
	}
	
	public static void addUser(String nome, ArrayList<Produto> p) {
		Utilizador u = new Utilizador(nome,p);
		lista.add(u);
	}
	
	public boolean exists() {
		for(Utilizador u : lista) {
			if(u.equals(this)) {
				return true;
			}
		}
		addUser(this.nome,this.produtos);
		return false;
	}
}
