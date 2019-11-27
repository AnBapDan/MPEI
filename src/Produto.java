import java.util.ArrayList;

public class Produto {

	private int id;
	private static ArrayList<Produto> existingProds = new ArrayList<Produto>();
	
	public Produto(int id) {
		this.id = id;
		existingProds = new ArrayList<Produto>();
	}
	
	public int getID() {
		return id;
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
