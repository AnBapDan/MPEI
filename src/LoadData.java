import java.io.*;
import java.util.Scanner;

public class LoadData {

	@SuppressWarnings("unlikely-arg-type")
	private static void loadInfo(File f) throws IOException {
		Scanner lFile = new Scanner(f);
		while(lFile.hasNextLine()) {
			String[] tmp = lFile.nextLine().split("/");				//Divide o nome da lista de compras
			Produto[] prodTmp = new Produto[10];
			int j = 0;
			String[] prod = tmp[1].split(",");						//Divide os produtos entre si
			for(int i =0; i < prod.length; i++ ) {
				String[] d= prod[i].split("#");						//Divide os produtos por Nome, tipo e preco 
				Tipo type = Tipo.Outros;
				for(Tipo t : Tipo.values()) {
					if(d[1].equals(Tipo.valueOf(t.toString()))) {
						type = t;
					}
				}
				
				Produto p = new Produto(d[0],type,Integer.parseInt(d[2]));
				if(!(p.exists())) {
					prodTmp[j] = p;
				} else {
					
				}

				Utilizador a = new Utilizador(tmp[0]);
				if(!(a.exists())) {
					BloomFilter.userVerify();
				} else {
					
				}
			}
		}
	}
}
