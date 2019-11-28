import java.util.*;

public class MinHash {		
	//TODO run all the products of each list through the same hash with the same parameters, retrieving the min of them all (minhash)
	private long[][] mins; 		//matrix
	private int k=10;				//k= number of hashfunctions
	private int nl=1000;		//nl = number of lists(default 1000)
	private long hashprod;		//hashprod id 
	private static long a,b=0;	//longer variables
	private static final int M= 38327;
	private static int x,p=0;
	private int[] prime= {233,1019,24889,38327,51949,60617,80363,87277,100019,102013,104729};		//11 "random" prime numbers
	private List<String> lines;
	private List<Set<Long>> sets;
	
	public MinHash(List<String> lines) {
		mins = new long[k][nl];							//TODO receber a string, dar split no \t, usar o primeiro parametro-1 para substituir em user e o segundo é o hashprod
		this.lines = lines;
		hashes();
		longersection();
	}



	private void longersection() {
		for(int i=0; i<nl; i++) {		//search row
			Set<Long> tmp = new HashSet<>();
			for(int j=0; j<k; j++) {	//search column
				tmp.add(mins[i][j]);
			}
			sets.add(tmp);
		}
		
		
		
	}



	private void hashes(){
		for(int i =0; i<k; i++) {
			for(int j=0; j<lines.size();j++) {
				String arg= lines.get(j);
				String[]b = arg.split("\t");
				int user =Integer.parseInt(b[0])-1;
				this.hashprod=Integer.parseInt(b[1]);
				long code = minhashf(hashprod);
				if(mins[i][user]==0) {
					mins[i][user]=code;			//fill the matrix with the minhash of each product
				}else {
					if(mins[i][user]>code) {
						mins[i][user]=code;
					}
				}
			}
			x=0;a=0;										//Reset after row ended
		}
	}

	private long minhashf(long hashprod) {					//switches between to types of hash functions
		if(x==0) {											//Need to reset after row
			//x = (long) ((Math.random()*2)+1);	
			x=1;
		}

		switch(x) {
		case 1: 
			//	System.out.prlongln("case1");
			return hash1(hashprod);						//Carter Wegman Hash Function

		case 2:
			return hash2(hashprod);						//
		}
		return 0;
	}


	private long hash1(long hashprod) {						// Carter Wegman
		int tmp=0;	
		if(a==0) {											//Need to reset after row
			a= (long) (Math.random()*2567257)+1;
			b= (long) (Math.random()*20382548)+1;
			tmp = (int) (Math.random()*11)+1;
			//M = prime[tmp-1];
			try {
				p= (int)(Math.random()*3)+1;	
				p= prime[3+p-1];
			}
			catch(Exception e) {p=prime[prime.length-1];}	//if tmp+p-1 is bigger than the array prime,it chooses the last index
		}
		if(a!=0 & b!= 0 & p!=0 & M!=0) {
			return(((a*hashprod+b) % p )% M);				//ℎ𝑎,𝑏(𝑥)=((𝑎𝑥+𝑏)𝑚𝑜𝑑𝑝)𝑚𝑜𝑑𝑀;
		}
		return -1;											//error code										
	}

	private long hash2(long hashprod2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long[][] getMins() {
		return mins;
	}



}
