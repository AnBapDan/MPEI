import java.util.HashSet;
import java.util.Set;

public class testSimilarity {

	public static void main(String[] args) {
		
		Set<Long> a = new HashSet<Long>();
		Set<Long> b = new HashSet<Long>();
		
		long [] tmp = {9112,10654,192,6218,
			       19321,
			        1868,
			        7643,
			        5724, 1497,
			       10999,
			       22588,
			       11938,
			        3785,
			         272,
			        8675,
			        1824};
	
		for(int i = 0; i < tmp.length; i++) {
			a.add(tmp[i]);
		}
		
		long[] tmp2 = {129,
		        9360,
		        16265,
		         9112,
		         5831,
		          192,
		         5014,
		         2142,
		         1868,
		         7643,
		         1292,
		         1497,
		        10999,
		        16747,
		         7251,
		         3785,
		          272,
		         6712,
		         1824};
		
		for(int i = 0; i < tmp2.length; i++) {
			b.add(tmp2[i]);
		}
		
		Set<Long> intersection = new HashSet<Long>(a);
		intersection.retainAll(b);
		
		double intersect = intersection.size();
		double simil = intersect / (a.size()+b.size()-intersect);
		
		System.out.println(a.size());
		System.out.println(b.size());
		System.out.println(intersect);
		System.out.println(simil);

	}

}
