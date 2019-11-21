
public class Convert {
	private Utilizador a;
	private Produto p;
	private String prod;
	private int t;
	private double preco;
	
	public Convert(Utilizador a) {
		this.a =a ;
		
	}
	
	private void toNumber(Produto p) {
		this.p = p;
		prod = p.getNome();
		t = p.getTipo().getVal();
		preco = p.getPreco();
		
	}
}
