
public class BloomFilter {

	private static int [][] matrix;
	private static int[] marked;
	
	public BloomFilter() {
		matrix = new int[1000][1000];
		marked = new int[1009];
	}
	
	public static void add(int u, int p) {
		int [] added = hashFunc(u,p);
		
	}
	
	private static int[] hashFunc(int u, int p) {  //h1(1) = mod(sum(str(1,:).*h(1:),M);  M = 1009; h = randi(100,M,3); str = ('ola','ela','ele','ggg');
		
		for(int i = 0; i < matrix.length; i++) {
			int hashCode = (matrix[u][i] * (int)(Math.random()*p)) % 1009;
			marked[hashCode] = 1;
		}
		return marked;
	}
}
