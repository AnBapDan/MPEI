
public class Produto {

	private int preco;
	private Tipo p;
	private String nome;
	
	public Produto(String nome, Tipo p, int preco) {
		this.nome = nome;
		this.p = p;
		this.preco = preco;
	}
	
	
	
	public int getPreco() {
		return preco;
	}
}
