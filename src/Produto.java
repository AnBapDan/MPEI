
public class Produto {

	private double preco;
	private Tipo tipo;
	private String nome;
	
	public Produto(String nome, Tipo p, double preco) {
		this.nome = nome;
		this.tipo = p;
		this.preco = preco;
	}
	
	public String getNome() {
		return nome;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public boolean exists() {
		for(Produto tmp : Utilizador.getExistingProds()) {
			if(this.equals(tmp)) {
				return true;
			}
		}
		return false;
	}
}
