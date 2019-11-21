import java.util.ArrayList;

public class Produto {

	private double preco;
	private Tipo tipo;
	private int id;
	private static ArrayList<Produto> existingProds = new ArrayList<Produto>();
	
	public Produto(int id, Tipo p, double preco) {
		this.id = id;
		this.tipo = p;
		this.preco = preco;
		existingProds = new ArrayList<Produto>();
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
		for(Produto tmp : Produto.getExistingProds()) {
			if(this.equals(tmp)) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<Produto> getExistingProds() {
		return existingProds;
	}
}
