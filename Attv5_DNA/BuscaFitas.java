package Attv5_DNA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class BuscaFitas extends Thread{
	private Semaphore sem = new Semaphore(2);
	private Explorador exp = new Explorador();
	
	public void buscar() throws IOException{
		try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
		ArrayList<File> caminhos = exp.listar("C:\\Users\\mateu\\OneDrive\\Documentos\\BCC\\BCC-AltoDesempenho\\arquivosDNA\\arquivosDNA", ".txt");
		int linha=0;
		int p=0;
		int TotalFitas=0;
		int TotalValidas=0;
		int TotalInvalidas=0;
		int PosicaoInvalidas[] = {};
		for (int i=0;i<caminhos.size();i++) {
			FileWriter fw = new FileWriter("C:\\Users\\mateu\\OneDrive\\Documentos\\BCC\\BCC-AltoDesempenho\\arquivosDNA\\arquivosDNA\\File"+linha+".txt");
			try {
				Scanner in = new Scanner(new FileReader(caminhos.get(i)));
				
				while (in.hasNextLine()) {
					TotalFitas++;
	                String line = in.nextLine();
	                //System.out.println(line);
	                String letrasDNA[] = line.split("");
	                for(int j=0;j<letrasDNA.length;j++) {
	                	
	                	String test = letrasDNA[j];
	                	switch (test) {
						case "A": {
							fw.append("T");
							break;
						}
						case "G": {
							fw.append("C");
							break;
						}
						case "C": {
							fw.append("G");
							break;
						}
						case "T": {
							fw.append("A");
							break;
						}
						default:
							TotalInvalidas++;
							//PosicaoInvalidas[p] = j;
							p++;
							System.out.println(("”****FITA INVALIDA - " +line));
						}
	                }
	                linha++;
				}
				System.out.println("Total de fitas "+TotalFitas+"\nTotal validas "+TotalValidas+"\nTotal inválidas"+TotalInvalidas);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}	
}
