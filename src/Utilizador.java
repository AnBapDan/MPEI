import java.util.ArrayList;

public class Utilizador {

	private String nome;
	private ArrayList<Produto> produtos;
	
	public Utilizador(int id, String nome) {
		this.nome = nome;
		produtos = new ArrayList<Produto>();
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
}
