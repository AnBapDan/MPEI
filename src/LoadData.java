import java.io.*;
import java.util.Scanner;

public class LoadData {

	private static void loadInfo(File f) throws IOException {
		Scanner lFile = new Scanner(f);
		while(lFile.hasNextLine()) {
			String[] tmp = lFile.nextLine().split("/");				//Divide o nome da lista de compras

			String[] prod = tmp[1].split(",");						//Divide os produtos entre si
			for(int i =0; i<prod.length; i++ ) {
				String[] d= prod[i].split("#");						//Divide os produtos por Nome, tipo e preço 
				
				Produto f = new Produto(d[0],d[1],Integer.parseInt(d[2]));
				if()

			}
		}
		Utilizador a = new Utilizador();

	}
}
