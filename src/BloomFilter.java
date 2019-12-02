
public class BloomFilter {

	private static int[] bloom;
	private static int[] prime = {233,1019,24889,38327,51949,60617,80363,87277,100019,102013,104729};
	private static long a, b = 0;
	private static int p = 0;
	private static final int M = 38327;
	
	public BloomFilter() {
		bloom = new int[95850];
	}
	
	public void add(int u, int p) {
		int hashCode = hash(p);
		boolean b = exists(u,p,hashCode);
		if(b) {
			System.out.println("Already exists");
		} else {
//			System.out.println("Added");
			bloom[hashCode] = 1;
		}
		
	}
	
	private boolean exists(int u, int p, int hashCode) {  //h1(1) = mod(sum(str(1,:).*h(1:),M);  M = 1009; h = randi(100,M,3); str = ('ola','ela','ele','ggg');
		for(int i = 0; i < bloom.length; i++) {
			if(bloom[hashCode] == 1)	
				return true;
		}
		return false;
	}

	private int hash(long hashprod) {																// Carter Wegman
		int tmp = 0;	
		if(a==0) {																					//Needs to be reseted after each row
			try {
				p = (int)(Math.random()*3)+1;	
				p = prime[3+p-1];																	//tries to copy the prime number of the array
			}
			catch(Exception e) {p=prime[prime.length-1];}											//if tmp+p-1 is bigger than the array prime,it chooses the last index
			a = (long) (Math.random()*p)+1;															//generates a number between 1 and the prime P
			b = (long) (Math.random()*p)+1;															//generates a number between 1 and the prime P
			tmp = (int) (Math.random()*11)+1;														//generates a number between 1 11
			//M = prime[tmp-1];
		}
		if(a!=0 & b!= 0 & p!=0 & M!=0) {
			int temporaria = (int) (((a*hashprod+b) % p )% 95850);
			System.out.println(temporaria);
			return temporaria;
		}
		return -1;																					//error code										
	}
}
