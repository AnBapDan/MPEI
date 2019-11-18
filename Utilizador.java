
public class Utilizador {

	private int id;
	private String nome;
	private ArrayList<Produto> produtos;
	
	public Utilizador(int id, String nome) {
		this.id = id;
		this.nome = nome;
		produtos = new ArrayList<Produto>();
	}
	
	public int getID() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
}
