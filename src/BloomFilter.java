 
public class BloomFilter {

	private int[] bloom;
	private static int[] prime = {233,1019,24889,38327,51949,60617,80363,87277,100019,102013,104729};
	private static long a, b;
	private static int p;
	private static final int M = 38327;
	private int cont;
	
	private static double pe = 0.01;
	private int n;
	
	public BloomFilter(int n) {
		bloom = new int[n*8];
		a = 0;
		b = 0;
		p = 0;
		cont = 0;
		this.n = n;
	}
	
	public boolean add(int u, int p) {
		int[] hashes = {hash1(u,p),hash2(u,p),hash3(u,p),hash4(u,p),hash5(u,p)};
		
		boolean state;
		if(bloom[hashes[0]] == 1 && bloom[hashes[1]] == 1 && bloom[hashes[2]] == 1 && bloom[hashes[3]] == 1 && bloom[hashes[4]] == 1) {
			String s1 = "A Processar -> Utilizador: "+u+" | Produto: "+p+".";
			String s2 = "Estado -> Ignorado.";
			String s3 = "-------------------------------------------------";
			System.out.println(s1);
			System.out.println(s2);
			System.out.println(s3);
			state = false;
			cont++;	
		} else {
			String s1 = "A Processar -> Utilizador: "+u+" | Produto: "+p+".";
			String s2 = "Estado -> Adicionado";
			String s3 = "-------------------------------------------------";
			System.out.println(s1);
			System.out.println(s2);
			System.out.println(s3);
			bloom[hashes[0]] = 1;
			bloom[hashes[1]] = 1;
			bloom[hashes[2]] = 1;
			bloom[hashes[3]] = 1;
			bloom[hashes[4]] = 1;
			state = true;
		}
		return state;
	}
	
	public boolean exists(int u, int p) {
		int[] hashes = {hash1(u,p),hash2(u,p),hash3(u,p),hash4(u,p),hash5(u,p)};
		String s1 = "Estado -> A Verificar existencia...";
		System.out.println(s1);
		for(int i : hashes) {
			if(bloom[i] == 0) {
				String s2 = "Estado -> Nao encontrado";
				System.out.println(s2);
				return false;
			}
		}
		String s3 = "Estado -> Encontrado.";
		System.out.println(s3);
		return true;
	}
	
	public int getCont() {
		return cont;
	}

	private int hash1(long hashuser, long hashprod) {												// Carter Wegman	
		if(a==0) {																					//Needs to be reseted after each row
			try {
				p = (int)(Math.random()*3)+1;	
				p = prime[3+p-1];																	//tries to copy the prime number of the array
			}
			catch(Exception e) {p=prime[prime.length-1];}											//if tmp+p-1 is bigger than the array prime,it chooses the last index
			a = (long) (Math.random()*p)+1;															//generates a number between 1 and the prime P
			b = (long) (Math.random()*p)+1;															//generates a number between 1 and the prime P
		}
		if(a!=0 & b!= 0 & p!=0 & M!=0) {
			return (int) (((a*hashprod+b*hashuser) % p )% bloom.length);
		}
		return -1;																					//error code										
	}
	
	private int hash2(long hashuser,long hashprod) {
		
		if(a!=0 & b!= 0 & p!=0 & M!=0) {
			return (int) Math.abs((((a*hashuser-hashprod*b) % M )% p)% bloom.length);
		}
		return -1;	
	}
	
	private int hash3(long hashuser,long hashprod) {

		if(a != 0 & b != 0 & p != 0) {
			return (int) Math.abs((((hashprod*p*a*hashuser)/Math.log(2)*b)%p)% bloom.length);
		}
		return -1;		
	}

	private int hash4(long hashuser,long hashprod) {

		if(a != 0 & b != 0 & p !=0) {
			return (int) Math.abs(((Math.pow(a*hashprod*hashuser,2)/Math.log(b))%p)% bloom.length);
		}	
		return -1;
	}

	private int hash5(long hashuser,long hashprod) {

		if(a != 0 & b != 0 & p !=0) {
			return (int) Math.abs(((Math.pow(a*hashprod*hashuser,2)/Math.log(p))%b)%bloom.length);
		}	
		return -1;	
	}
}
