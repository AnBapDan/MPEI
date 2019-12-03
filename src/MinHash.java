import java.util.*;

public class MinHash {		
	private long[][] mins; 																			//matrix
	private int k=50;																				//k= number of hashfunctions
	private int nl=500;																				//nl = number of lists(default 1000)
	private long hashprod;																			//hashprod id 
	private static long a,b=0;																		//longer variables
	private static final int M= 38327;																//prime number
	private static int x,p=0;																		//intern variables
	private int[] prime= {233,1019,24889,38327,51949,60617,80363,87277,100019,102013,104729};		//11 "random" prime numbers
	private List<String> lines;
	private List<Set<Long>> listofsets;
	private double[][]matrix=new double[nl][nl];
	
	public MinHash(List<String> lines) {
		mins = new long[k][nl];																		//initialize a zero matrix with k rows and nl columns
		listofsets= new ArrayList<Set<Long>>();														//creates an empty List to receive each user Set 
		this.lines = lines;
		hashes();
		createSets();
		getMinHashMatrix();
	}
	
	private void hashes(){																			//fill the matrix with the hashes of the products		
		for(int i =0; i<k; i++) {
			for(int j=0; j<lines.size();j++) {
				String arg= lines.get(j);						
				String[]b = arg.split("\t");														//split the string in (user, hashprod)
				int user =Integer.parseInt(b[0])-1;
				this.hashprod=Integer.parseInt(b[1]);
				long code = minhashf(hashprod);														//creates the hash function used for the current row
				if(mins[i][user]==0) {																//if the matrix position wasn't used, just fill with the given hash
					mins[i][user]=code;																
				}else {																				//if the matrix position already have a hash
					if(mins[i][user]>code) {														//checks if the generated hash is lower than the saved one
						mins[i][user]=code;															//replaces it
					}
				}
			}
			x=0;a=0;																				//Reset after row ended
		}
	}

	private void createSets() {																		//Creates Sets for each user with the minHashes previously "filtered" 
		for(int i=0; i<nl; i++) {																	//for each position in the row
			Set<Long> tmp = new HashSet<>();														//creates a Set of Longs (minhashes)
			for(int j=0; j<k; j++) {																//searches each column
				long tmp1= mins[j][i];														
				tmp.add(tmp1);																		//adds that hash to the Set without repetitions
			}
			listofsets.add(tmp);																	//finally adds the Filled Set to the List previously created
		}	
	}
	
	private void getMinHashMatrix(){																//creates a final matrix
		for(int i=0; i<nl;i++) {																	
			for(int j=i+1; j<nl;j++) {																//fill the up right triangle of the matrix
				matrix[i][j]=Math.round(similarity(i,j)*100.0)/100.0;								//with the similarity of the sets ( similarity() method) with 2 decimal cases
			}
		}
	}
		
	private double similarity(int i, int j) {														//finds the similarity between to Sets
		double similarity=0;
		Set<Long>a=listofsets.get(i);																//Loads the Set of the i index 
		Set<Long>b=listofsets.get(j);																//Loads the Set of the j index
		Set<Long>c = new HashSet<Long>(a);															//Copies the a Set 
		c.retainAll(b);																				//Keeps the common hashes of a and b Sets in the copied Set
		double intersection = c.size();
		similarity = intersection/k;                     											 //(a.size()+b.size()-intersection);  (number of min / number of hash functions used)
		return similarity;
	}
	

	private long minhashf(long hashprod) {															//switches between to types of hash functions
		if(x==0) {																					//Needs to be reseted after each row
			x = (int) ((Math.random()*3)+1);														//TODO
		//	x=4;
		}
		switch(x) {
		
		case 1: 
			return hash1(hashprod);																	//Carter Wegman Hash Function
		
		case 2:
			return hash2(hashprod);		
		
		case 3:
			return hash3(hashprod);
		
		case 4:
			return hash4(hashprod);
		}
		return 0;
	}


	private long hash1(long hashprod) {																// Carter Wegman
		int tmp=0;	
		if(a==0) {																					//Needs to be reseted after each row
			try {
				p= (int)(Math.random()*3)+1;	
				p= prime[3+p-1];																	//tries to copy the prime number of the array
			}
			catch(Exception e) {p=prime[prime.length-1];}											//if tmp+p-1 is bigger than the array prime,it chooses the last index
			a= (long) (Math.random()*p)+1;															//generates a number between 1 and the prime P
			b= (long) (Math.random()*p)+1;															//generates a number between 1 and the prime P
			tmp = (int) (Math.random()*11)+1;														//generates a number between 1 11
			//M = prime[tmp-1];
		}
		if(a!=0 & b!= 0 & p!=0 & M!=0) {
			return(((a*hashprod+b) % p )% M);														//ℎ𝑎,𝑏(𝑥)=((𝑎𝑥+𝑏)𝑚𝑜𝑑𝑝)𝑚𝑜𝑑𝑀;
		}
		return -1;																					//error code										
	}

	private long hash2(long hashprod) {
		int tmp=0;	
		if(a==0) {																					//Needs to be reseted after each row
			try {
				p= (int)(Math.random()*3)+1;	
				p= prime[3+p-1];																	//tries to copy the prime number of the array
			}
			catch(Exception e) {p=prime[prime.length-1];}											//if tmp+p-1 is bigger than the array prime,it chooses the last index
			a= (long) (Math.random()*p)+1;															//generates a number between 1 and the prime P
			b= (long) (Math.random()*p)+1;															//generates a number between 1 and the prime P
			tmp = (int) (Math.random()*11)+1;														//generates a number between 1 11
			//M = prime[tmp-1];
		}
		if(a!=0 & b!= 0 & p!=0 & M!=0) {
			return Math.abs(((a-hashprod*b) % M )% p);														//ℎ𝑎,𝑏(𝑥)=|((𝑎-𝑥*𝑏)𝑚𝑜𝑑M)𝑚𝑜𝑑p|;
		}
		return -1;	
	}
	
	private long hash3(long hashprod) {
		if(a==0) {																					//Needs to be reseted after each row
			try {
				p= (int)(Math.random()*11)+1;	
				p= prime[p-1];																	
			}
			catch(Exception e) {p=prime[prime.length-1];}											
			a= (long) (Math.random()*p)+1;															
			b= (long) (Math.random()*p)+1;																								
		}
		if(a!=0 & b!= 0 & p!=0) {
			return (long) Math.abs(((hashprod*p*a)/Math.log(2)*b)%p);														
		}
		return -1;		
	}
	
	private long hash4(long hashprod) {
		if(a==0) {																					//Needs to be reseted after each row
			try {
				p= (int)(Math.random()*11)+1;	
				p= prime[p-1];																	
			}
			catch(Exception e) {p=prime[prime.length-1];}											
			a= (long) (Math.random()*p)+1;															
			b= (long) (Math.random()*p)+1;																								
		}
		if(a!=0 & b!= 0 & p!=0) {
			return (long) Math.abs((Math.pow(a*hashprod,2)/Math.log(b))%p);														
		}	
		return -1;
	}
	
	
	/*Getters and Setters*/
	public long[][] getMins() {
		return mins;
	}

	public double[][] getMatrix() {
		return matrix;
	}

	
	public int getNl() {
		return nl;
	}

	public void setNl(int nl) {
		this.nl = nl;
	}
	
}
