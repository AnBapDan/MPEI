public class MinHash {		
	//TODO run all the products of each list through the same hash with the same parameters, retrieving the min of them all (minhash)
	private int[][] mins; 		//matrix
	private int k;				//k= number of hashfunctions
	private int nl;				//np = number of lists
	private int hashprod;		//hashprod id 
	private int a,b,p,M,x = 0;	//intern variables
	private int[] prime= {233,1019,24889,38327,51949,60617,80363,87277,100019,102013,104729};		//11 "random" prime numbers
	public MinHash(int nh, int nl) {
		
		this.nl = nl;
		this.k = nh;
		this.hashprod=hashprod;

		mins = new int[k][nl];
		for(int i =0; i<k; i++) {
			for(int j=0; j<nl; j++) {
				mins[i][j]=minhashf(hashprod);				//fill the matrix with the minhash of each product
			}
			x=0;a=0;										//Reset after row ended
		}
	}

	private int minhashf(int hashprod) {					//switches between to types of hash functions
		if(x==0) {											//Need to reset after row
			x = (int) ((Math.random()*2)+1);	
		}

		switch(x) {
			case 1: 
				return hash1(hashprod);						//Carter Wegman Hash Function
				
			case 2:
				return hash2(hashprod);						//
		}
		return 0;
	}


	private int hash1(int hashprod) {						// Carter Wegman
		int tmp=0;	
		if(a==0) {											//Need to reset after row
			a= (int) (Math.random()*20)+1;
			b= (int) (Math.random()*20)+1;
			tmp = (int) (Math.random()*11)+1;
			M = prime[tmp-1];
			try {
				p= (int)(Math.random()*3)+1;	
				p= prime[tmp+p-1];
			}
			catch(Exception e) {p=prime[prime.length-1];}	//if tmp+p-1 is bigger than the array prime,it chooses the last index

			if(a!=0 & b!= 0 & p!=0 & M!=0) {
				return(((a*hashprod+b) % p )% M);			//ℎ𝑎,𝑏(𝑥)=((𝑎𝑥+𝑏)𝑚𝑜𝑑𝑝)𝑚𝑜𝑑𝑀;
			}
		}
		return -1;											
	}

	private int hash2(int hashprod2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
