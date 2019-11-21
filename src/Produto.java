
public class Produto {

	private double preco;
	private Tipo tipo;
	private int id;
	
	public Produto(int id, Tipo p, double preco) {
		this.id = id;
		this.tipo = p;
		this.preco = preco;
	}
	
	public int getID() {
		return id;
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
