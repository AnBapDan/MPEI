
public class BloomFilter {

	private static int[] bloom;
	
	public BloomFilter() {
		bloom = new int[1009];
	}
	
	public static void add(int u, int p) {
		int [] added = exists(u,p);
		
	}
	
	private static int[] exists(int u, int p) {  //h1(1) = mod(sum(str(1,:).*h(1:),M);  M = 1009; h = randi(100,M,3); str = ('ola','ela','ele','ggg');
		
		for(int i = 0; i < bloom.length; i++) {
			int hashCode = (int)(Math.random()*p)) % 1009;
			bloom[hashCode] = 1;
		}
		return bloom;
	}
}
