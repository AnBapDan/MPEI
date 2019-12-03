 
public class BloomFilter {

	private int[] bloom;
	private static int[] prime = {233,1019,24889,38327,51949,60617,80363,87277,100019,102013,104729};
	private static long a, b;
	private static int p;
	private static final int M = 38327;
	private int cont;
	
	
	public BloomFilter(int n) {
		bloom = new int[n*8];
		a = 0;
		b = 0;
		p = 0;
		cont = 0;
	}
	
	public void add(int u, int p) {
		
		int[] hashes = {hash1(u,p),hash2(u,p)};
		boolean bol = exists(u,p,hashes);
		if(bol) {
			System.out.println("Already exists");
			cont++;
		} else {
			System.out.println("Added");
			bloom[hashes[0]] = 1;
			bloom[hashes[1]] = 1;
		}	
	}
	
	private boolean exists(int u, int p, int[] hashes) {
		System.out.println("Checking if already exists...");
		for(int i : hashes) {
			if(bloom[i] == 0) {
				return false;
			}
		}
		return true;
	}
	
	public int getCont() {
		return cont;
	}

	private int hash1(long hashuser, long hashprod) {										// Carter Wegman	
//		int tmp = 0;
		if(a==0) {																					//Needs to be reseted after each row
			try {
				p = (int)(Math.random()*3)+1;	
				p = prime[3+p-1];																	//tries to copy the prime number of the array
			}
			catch(Exception e) {p=prime[prime.length-1];}											//if tmp+p-1 is bigger than the array prime,it chooses the last index
			a = (long) (Math.random()*p)+1;															//generates a number between 1 and the prime P
			b = (long) (Math.random()*p)+1;															//generates a number between 1 and the prime P
//			tmp = (int) (Math.random()*11)+1;														//generates a number between 1 1
		}
		if(a!=0 & b!= 0 & p!=0 & M!=0) {
			int tmp = (int) (((a*hashprod+b*hashuser) % p )% 80000);
			System.out.print(tmp + " ");
			return tmp;
		}
		return -1;																					//error code										
	}
	
	private int hash2(long hashuser,long hashprod) {
//		int tmp=0;	
		if(a==0) {																					//Needs to be reseted after each row
			try {
				p= (int)(Math.random()*3)+1;	
				p= prime[3+p-1];																	//tries to copy the prime number of the array
			}
			catch(Exception e) {p=prime[prime.length-1];}											//if tmp+p-1 is bigger than the array prime,it chooses the last index
			a= (long) (Math.random()*p)+1;															//generates a number between 1 and the prime P
			b= (long) (Math.random()*p)+1;															//generates a number between 1 and the prime P
//			tmp = (int) (Math.random()*11)+1;														//generates a number between 1 11
		}
		if(a!=0 & b!= 0 & p!=0 & M!=0) {
			 int tmp = (int) Math.abs(((a*hashuser-hashprod*b) % M )% p);
			 System.out.print(tmp+" ");
			 return tmp;
		}
		return -1;	
	}
}
